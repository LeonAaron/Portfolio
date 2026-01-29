import requests
import time
import re
import random
import pandas as pd
from bs4 import BeautifulSoup
from urllib.parse import urljoin, urlparse
from concurrent.futures import ThreadPoolExecutor, as_completed

# -----------------------------
# CONFIG
# -----------------------------
ARCHIVE_ROOT = "https://web.archive.org"
TIMESTAMP = "20250708180027"
START_URL = f"{ARCHIVE_ROOT}/web/{TIMESTAMP}/https://www.myfootdr.com.au/our-clinics/"
CSV_FILE = "myfootdr_clinics_FINAL.csv"

HEADERS = {
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120 Safari/537.36"
}

REQUEST_DELAY = 0.8  # Balanced for speed and reliability
DEBUG = False  # Disabled for faster execution
MAX_WORKERS = 3  # Parallel workers (too many causes archive.org to reject)

# -----------------------------
# HELPERS
# -----------------------------
def clean_text(text):
    return re.sub(r"\s+", " ", text).strip()

def fetch_soup(url, retries=3):
    """Fetch URL and return BeautifulSoup object"""
    for attempt in range(retries):
        try:
            if DEBUG and attempt == 0:
                print(f"[DEBUG] Fetching: {url[:80]}...")
            # Add random variation to avoid looking like a bot
            delay = REQUEST_DELAY + random.uniform(0, 0.3)
            time.sleep(delay)
            r = requests.get(url, headers=HEADERS, timeout=20)
            r.raise_for_status()
            if DEBUG:
                print(f"[DEBUG] Successfully fetched ({len(r.text)} bytes)")
            return BeautifulSoup(r.text, "html.parser")
        except requests.exceptions.RequestException as e:
            if DEBUG:
                print(f"[WARN] Fetch failed ({attempt+1}/{retries}): {str(e)[:100]}")
            if attempt < retries - 1:
                time.sleep(2)
        except Exception as e:
            if DEBUG:
                print(f"[ERROR] Unexpected error: {str(e)[:100]}")
            if attempt < retries - 1:
                time.sleep(2)
    print(f"[ERROR] All {retries} attempts failed for: {url[:80]}")
    return None

def test_connection():
    """Test if we can connect to the archive"""
    print("[TEST] Testing connection to web.archive.org...")
    try:
        r = requests.get(ARCHIVE_ROOT, headers=HEADERS, timeout=10)
        print(f"[TEST] Connection OK (status: {r.status_code})")
        return True
    except Exception as e:
        print(f"[TEST] Connection failed: {e}")
        return False

# -----------------------------
# REGION & CLINIC URL DISCOVERY
# -----------------------------
def get_region_urls():
    """Find all region pages from the main clinics page"""
    print(f"[INFO] Fetching main page: {START_URL}")
    soup = fetch_soup(START_URL)
    if not soup:
        print("[ERROR] Could not fetch main page")
        return []

    urls = set()

    # Try multiple patterns for region links
    patterns = [
        'a[href*="/our-clinics/regions/"]',
        'a[href*="/regions/"]',
        'a[href*="our-clinics"]'
    ]

    for pattern in patterns:
        links = soup.select(pattern)
        if DEBUG and links:
            print(f"[DEBUG] Pattern '{pattern}' found {len(links)} links")

        for a in links:
            href = a.get("href")
            if href and "/regions/" in href:
                # Construct full archive URL
                if href.startswith("/web/"):
                    full = urljoin(ARCHIVE_ROOT, href)
                elif href.startswith("http"):
                    # Already has archive root
                    full = href
                else:
                    # Relative URL - need to add archive timestamp
                    full = f"{ARCHIVE_ROOT}/web/{TIMESTAMP}/{href.lstrip('/')}"

                urls.add(full.split("#")[0])  # Remove anchors
                if DEBUG:
                    print(f"[DEBUG] Region found: {a.get_text(strip=True)[:50]}")

    print(f"[INFO] Found {len(urls)} unique region URLs")
    if DEBUG and urls:
        for u in sorted(urls):
            print(f"  - {u}")

    return sorted(urls)

def get_clinic_urls(region_url):
    """Extract all clinic links from a region page"""
    if DEBUG:
        print(f"[DEBUG] Processing region: {region_url}")

    soup = fetch_soup(region_url)
    if not soup:
        print(f"[WARN] Could not fetch region page")
        return []

    clinic_urls = set()
    base_domain = "myfootdr.com.au/our-clinics/"

    for a in soup.find_all("a", href=True):
        href = a["href"]

        # Skip region links, external links, and navigation
        if "/regions/" in href or "javascript:" in href or href.startswith("#"):
            continue

        # Accept clinic pages from our-clinics that aren't the main page
        if base_domain in href:
            # Construct proper archive URL
            if href.startswith("/web/"):
                full = urljoin(ARCHIVE_ROOT, href)
            elif href.startswith("http"):
                full = href
            else:
                full = f"{ARCHIVE_ROOT}/web/{TIMESTAMP}/{href.lstrip('/')}"

            # Clean URL
            full = full.split("#")[0].rstrip("/")

            # Skip if it's the main clinics page or regions page
            if full.endswith("/our-clinics") or "/regions/" in full:
                continue

            # Check that it's a specific clinic (has content after /our-clinics/)
            if "/our-clinics/" in full:
                path_after = full.split("/our-clinics/")[1]
                if path_after and path_after.strip("/"):
                    clinic_urls.add(full)
                    if DEBUG:
                        clinic_name = a.get_text(strip=True)[:40]
                        print(f"[DEBUG]   Clinic: {clinic_name}")

    print(f"[INFO]   Found {len(clinic_urls)} clinics in this region")
    return sorted(clinic_urls)

# -----------------------------
# SCRAPING
# -----------------------------
def scrape_clinic(url):
    """Scrape all details from a clinic page"""
    if DEBUG:
        print(f"[DEBUG] Scraping: {url}")

    soup = fetch_soup(url)
    if not soup:
        print(f"[ERROR] Failed to fetch {url}")
        return None

    # Name - try h1, then title
    name = None
    name_tag = soup.find("h1")
    if name_tag:
        name = clean_text(name_tag.text)
        # Remove common suffixes
        name = re.sub(r'\s*[-|]\s*MyFootDr.*$', '', name, flags=re.IGNORECASE)
    if not name:
        title_tag = soup.find("title")
        if title_tag:
            name = clean_text(title_tag.text).split("|")[0].split("-")[0].strip()

    # Address
    addr = None
    address_tag = soup.find("address")
    if address_tag:
        addr = clean_text(address_tag.text)
    else:
        # Try alternate selectors
        for selector in ['.address', '[itemprop="address"]', '.location']:
            alt_addr = soup.select_one(selector)
            if alt_addr:
                addr = clean_text(alt_addr.text)
                break

    # Email - try multiple methods
    email = None

    # Method 1: mailto links
    email_tag = soup.select_one('a[href^="mailto:"]')
    if email_tag:
        email = email_tag.get("href").replace("mailto:", "").strip()

    # Method 2: Search for email pattern in text
    if not email:
        email_pattern = r'\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Z|a-z]{2,}\b'
        page_text = soup.get_text()
        email_matches = re.findall(email_pattern, page_text)
        if email_matches:
            # Filter out common false positives
            for em in email_matches:
                if not any(skip in em.lower() for skip in ['example.com', 'test.com', 'domain.com']):
                    email = em
                    break

    # Method 3: Look in specific sections (footer, contact)
    if not email:
        for section in soup.find_all(['footer', 'div', 'section'], class_=re.compile(r'contact|footer|email', re.I)):
            email_tag = section.find('a', href=re.compile(r'^mailto:', re.I))
            if email_tag:
                email = email_tag.get("href").replace("mailto:", "").strip()
                break

    # Phone - prioritize clinic-specific numbers over generic 1800 numbers
    phone = None
    generic_number = "1800366837"  # MyFootDr's general customer service number

    # Method 1: Find all tel: links and prioritize non-1800 numbers
    all_phone_tags = soup.find_all('a', href=re.compile(r'^tel:', re.I))
    local_phones = []
    generic_phones = []

    for tag in all_phone_tags:
        phone_num = tag.get("href").replace("tel:", "").strip()
        # Clean the phone number (remove spaces, dashes, parentheses)
        phone_clean = re.sub(r'[\s\-\(\)]', '', phone_num)

        if generic_number in phone_clean:
            generic_phones.append(phone_num)
        else:
            local_phones.append(phone_num)

    # Prefer local/specific numbers over generic 1800
    if local_phones:
        phone = local_phones[0]
    elif generic_phones:
        phone = generic_phones[0]

    # Method 2: Look for phone numbers in the main content using regex
    if not phone or generic_number in phone:
        # Australian phone number patterns: (02) 1234 5678, 07 1234 5678, etc.
        phone_pattern = r'\(?0[2-9]\)?\s?\d{4}\s?\d{4}'

        # Search in address section first
        address_section = soup.find("address")
        if address_section:
            phone_matches = re.findall(phone_pattern, address_section.get_text())
            if phone_matches:
                phone = phone_matches[0].strip()

        # Search in contact sections
        if not phone or generic_number in phone:
            contact_sections = soup.find_all(['div', 'section'], class_=re.compile(r'contact|phone|call', re.I))
            for section in contact_sections:
                phone_matches = re.findall(phone_pattern, section.get_text())
                for match in phone_matches:
                    clean_match = re.sub(r'[\s\-\(\)]', '', match)
                    if generic_number not in clean_match:
                        phone = match.strip()
                        break
                if phone and generic_number not in phone:
                    break

    # Clean up phone format
    if phone:
        phone = re.sub(r'\s+', ' ', phone).strip()

    # Services - comprehensive extraction
    services = []

    # Method 1: Look for service headings and nearby content
    service_headings = soup.find_all(['h2', 'h3', 'h4'], string=re.compile(r'service|treatment|offer|speciali[sz]|what we (do|offer|provide)', re.I))
    for heading in service_headings:
        # Get the next sibling elements (lists, divs, paragraphs)
        next_element = heading.find_next_sibling()
        if next_element:
            # Extract from lists
            if next_element.name in ['ul', 'ol']:
                for li in next_element.find_all('li'):
                    txt = clean_text(li.get_text())
                    if 5 < len(txt) < 300:
                        services.append(txt)
            # Extract from divs
            elif next_element.name == 'div':
                for li in next_element.find_all('li'):
                    txt = clean_text(li.get_text())
                    if 5 < len(txt) < 300:
                        services.append(txt)

    # Method 2: Look for service-specific sections by class/id
    service_containers = soup.find_all(["section", "div", "article"], class_=re.compile(r'service|treatment|offer|what-we', re.I))
    service_containers += soup.find_all(["section", "div", "article"], id=re.compile(r'service|treatment|offer', re.I))

    for container in service_containers:
        # Skip navigation and headers
        if container.find_parent(['nav', 'header']):
            continue
        for li in container.find_all("li"):
            txt = clean_text(li.get_text())
            if (5 < len(txt) < 300 and
                not any(skip in txt.lower() for skip in ['home', 'about us', 'contact', 'login', 'menu', 'search', 'cart', 'facebook', 'twitter', 'instagram'])):
                services.append(txt)

    # Method 3: Look for common podiatry service keywords in page content
    if not services:
        main_content = soup.find("main") or soup.find("article") or soup.find("div", class_=re.compile(r'content|main', re.I))
        if main_content:
            # Find lists in main content
            for ul in main_content.find_all(['ul', 'ol']):
                # Skip if in navigation, header, or footer
                if ul.find_parent(["nav", "header", "footer"]):
                    continue
                # Check if this list seems to contain services
                list_text = ul.get_text().lower()
                if any(keyword in list_text for keyword in ['podiatry', 'foot', 'nail', 'orthotic', 'assessment', 'treatment', 'care', 'therapy', 'heel', 'ankle']):
                    for li in ul.find_all("li", recursive=False):
                        txt = clean_text(li.get_text())
                        if (5 < len(txt) < 300 and
                            not any(skip in txt.lower() for skip in ['home', 'about us', 'contact', 'login', 'menu', 'search'])):
                            services.append(txt)

    # Method 4: Look for service keywords in paragraph text
    if not services:
        keywords = ['custom orthotics', 'ingrown toenail', 'diabetic foot', 'heel pain', 'sports injury',
                   'biomechanical', 'gait analysis', 'children', 'nail surgery', 'plantar fasciitis',
                   'bunion', 'footwear', 'corn', 'callus', 'wart', 'fungal', 'athlete']
        main_content = soup.find("main") or soup.find("article") or soup.find("div", class_=re.compile(r'content|main', re.I))
        if main_content:
            paragraphs = main_content.find_all('p')
            found_services = set()
            for p in paragraphs:
                txt = p.get_text().lower()
                for keyword in keywords:
                    if keyword in txt:
                        # Extract a short phrase around the keyword
                        sentences = re.split(r'[.!?]', p.get_text())
                        for sentence in sentences:
                            if keyword in sentence.lower():
                                service_text = clean_text(sentence)
                                if 10 < len(service_text) < 300:
                                    found_services.add(service_text)
                                    break
            services.extend(list(found_services)[:10])  # Limit to 10 to avoid too much text

    # Remove duplicates, clean up, and join
    if services:
        # Clean and deduplicate
        services = list(set([s for s in services if s and len(s) > 3]))
        # Sort and limit to reasonable number
        services = sorted(services)[:20]
        services = "; ".join(services)
    else:
        services = None

    if DEBUG:
        print(f"[DEBUG] Scraped - Name: {name}, Addr: {addr[:30] if addr else None}, Email: {email}, Phone: {phone}")

    return {
        "Name of Clinic": name,
        "Address": addr,
        "Email": email,
        "Phone": phone,
        "Services": services
    }

# -----------------------------
# MAIN
# -----------------------------
if __name__ == "__main__":
    print("=" * 60)
    print("MyFootDr Clinic Scraper")
    print("=" * 60)

    # Test connection first
    if not test_connection():
        print("[ERROR] Cannot connect to archive. Check your internet connection.")
        exit(1)

    all_clinic_urls = set()

    # Step 1: Get all regions
    print("\n[STEP 1] Discovering regions...")
    regions = get_region_urls()

    if not regions:
        print("[WARN] No regions found! Trying to find clinics directly from main page...")
        # Fallback: try to find clinic links directly from main page
        clinic_urls = get_clinic_urls(START_URL)
        all_clinic_urls.update(clinic_urls)
    else:
        # Step 2: For each region page, collect clinic URLs
        print(f"\n[STEP 2] Discovering clinics in {len(regions)} regions...")
        for i, region in enumerate(regions, 1):
            print(f"\n[{i}/{len(regions)}] Processing region...")
            urls = get_clinic_urls(region)
            all_clinic_urls.update(urls)
            print(f"  Total unique clinics so far: {len(all_clinic_urls)}")

    print(f"\n{'=' * 60}")
    print(f"[INFO] Total unique clinic URLs found: {len(all_clinic_urls)}")
    print(f"{'=' * 60}\n")

    if not all_clinic_urls:
        print("[ERROR] No clinic URLs found! Check the URL patterns and website structure.")
        exit(1)

    # Step 3: Scrape all clinics (using parallel processing)
    print(f"\n[STEP 3] Scraping {len(all_clinic_urls)} clinics using {MAX_WORKERS} parallel workers...\n")
    data = []
    failed = []
    completed = 0

    with ThreadPoolExecutor(max_workers=MAX_WORKERS) as executor:
        # Submit all scraping tasks
        future_to_url = {executor.submit(scrape_clinic, url): url for url in sorted(all_clinic_urls)}

        # Process results as they complete
        for future in as_completed(future_to_url):
            completed += 1
            url = future_to_url[future]
            try:
                result = future.result()
                if result:
                    data.append(result)
                    print(f"[{completed}/{len(all_clinic_urls)}] OK - {result['Name of Clinic']}")
                else:
                    failed.append(url)
                    print(f"[{completed}/{len(all_clinic_urls)}] FAIL")
            except Exception as e:
                failed.append(url)
                print(f"[{completed}/{len(all_clinic_urls)}] ERROR: {str(e)[:50]}")

    # Step 4: Output CSV
    print(f"\n{'=' * 60}")
    if data:
        df = pd.DataFrame(data)
        df.to_csv(CSV_FILE, index=False, encoding="utf-8-sig")
        print(f"[SUCCESS] Saved {len(df)} clinics to {CSV_FILE}")
    else:
        print("[ERROR] No clinic data scraped!")

    if failed:
        print(f"[WARN] Failed to scrape {len(failed)} clinics:")
        for url in failed[:5]:  # Show first 5
            print(f"  - {url}")
        if len(failed) > 5:
            print(f"  ... and {len(failed) - 5} more")

    print(f"{'=' * 60}")
