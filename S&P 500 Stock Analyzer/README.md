# S&P 500 Stock Analyzer

## Overview

S&P 500 Stock Analyzer is a **Python**-based desktop application that helps users evaluate whether a publicly traded company is a good value investment. Built with financial principles used by professional analysts, the app scores each stock based on six key metrics and compares its performance to top competitors in the same industry. The GUI provides a user-friendly interface for analyzing individual stocks and visualizing their financial strengths and weaknesses.

The project was created using **pandas** for data analysis and **Tkinter** for the graphical user interface. It was designed to support real-world investing decisions and was used to guide $10,000 in stock selections for a **Hagan Scholarship Foundation** investment account.

---

## Features

- Evaluates stocks from the **S&P 500** using:
  - Return on Enterprise Value (ROEV)
  - Net Profit Margin
  - Revenue Growth
  - Net Debt / EBITDA Ratio
- **Compares stocks to top industry competitors** using a built-in competitor mapping system
- **Scoring algorithm** outputs a value investment rating (0–10 scale)
- **Color-coded GUI** displays stock performance with investment suggestions
- Recommendation categories include:
  - ✅ Excellent Value Investment
  - ✔️ Good Value Investment
  - ⚠️ Fair Value, Requires Caution
  - ❌ Weak or Avoid

---

## Requirements

To run this project, you’ll need:

- **Python 3.8 or higher**
- Required Python packages:
  - `pandas`
  - `tkinter` (usually pre-installed with Python)

---

## Installation Instructions

### 1. Clone the repository

```bash
git clone https://github.com/LeonAaron/Portfolio.git
```

### 2. Navigate to the project folder

```bash
"cd Portfolio/S&‌P_500_Stock_Analyzer"
```

### 3. Install libraries

You can install the required packages using pip:

```bash
pip install pandas
```

> `tkinter` is typically pre-installed with Python, but if not, install it based on your operating system.

---

## How to Run the Program

1. Open a terminal or IDE (like VS Code or PyCharm).
2. Ensure the `S&P500_Data.xlsx`, `Competitors_Map.py`, and `S&P_500_Stock_Analyzer.py` files are in the same folder.
3. Run the main Python file:

```bash
"S&‌P_500_Stock_Analyzer.py"
```

4. A full-screen GUI will appear.
5. Enter a valid S&P 500 stock ticker symbol (e.g., `NVDA`, `WMT`, `JPM`) and click **Evaluate**.
6. The app will analyze the company, compare it to competitors, and display a score and recommendation.

---

## Project Files

- `S&P_500_Stock_Analyzer.py` – Main program file with scoring logic and GUI
- `Competitors_Map.py` – Python file containing the dictionary of competitors for each stock ticker
- `S&P500_Data.xlsx` – Excel file with financial metrics for S&P 500 stocks

---

## Scoring Breakdown

The stock score is computed out of 10 based on the following weights:

| Metric                                 | Weight |
|----------------------------------------|--------|
| Return on Enterprise Value (ROEV)      | 25%    |
| Revenue Growth                         | 5%     |
| Debt Ratio (Net Debt / EBITDA)         | 15%    |
| ROEV vs. Top Competitor                | 20%    |
| Profit Margin vs. Top Competitor       | 20%    |
| Revenue Growth vs. Top Competitor      | 15%    |

---
## Example Analysis Output

```
                        JPMorgan Chase & Co.

Sector: Financials
Industry: Diversified Banks

Metric                         JPM        BAC         WFC       
---------------------------------------------------------------
Enterprise Value (Billions)    $733       $332        $243      
Return on EV (%)               8.14 %     8.38 %      8.22 %    
Net Profit Margin (%)          35.4 %     28.6 %      25.9 %    
2-Yr Revenue Growth (%)        14.3 %     1.5 %       2.3 %     
Net Debt / EBITDA              0.0        0.0         0.0       

JPM Score: 9.6 / 10           Excellent Value Investment✅
```

---

## Real-World Application

This project was used to guide real investments, including:

- **$10,000 Hagan Scholarship Foundation Investment Account**
- Recommended companies outperformed S&P 500 averages with:
  - **+9 pts** in revenue growth
  - **+8 pts** in profit margin
  - **+3.7 pts** in return on enterprise value
  - **57% less debt**

---

## Results

The program identified several strong value investment candidates among the S&P 500 stocks analyzed. The top 10 rated stocks are included below.

**ROEV** -> Return on Enterprise Value

### Excellent Value Investments (3 Stocks)

| Rank | Stock | Score | ROEV (%) | 2-Yr Revenue Growth (%) | Net Profit Margin (%) | Net Debt / EBITDA |
|------|-------|-------|----------|-------------------------|-----------------------|--------------------|
| 1    | ACGL  | 10.0  |   9.95%  |         29.5%                |       20.7%                |           0.3         |
| 2    | JPM   | 9.6   |    8.14%      |        14.3%                 |    35.4%                   |      0.0              |
| 3    | UAL   | 9.2   |   8.41%       |      8.8%                   |       6.34%                |    1.4                |

### Good Value Investments (7 of 31 Stocks Shown)

| Rank | Stock | Score | ROEV (%) | 2-Yr Revenue Growth (%) | Net Profit Margin (%) | Net Debt / EBITDA |
|------|-------|-------|----------|--------------------------|----------------------|-------------------|
| 4    | PHM   | 8.8   |  14.29%        |      4.4%                    |    16.5%                  |    0.2               |
| 5    | LULU  | 8.7   |    4.82%      |      14.3%                    |      17.1%                |   0.0                |
| 6    | UNH   | 8.6   |   6.61%       |    10.5%                      |    5.39%                  |   1.3                |
| 7    | TRMB  | 8.5   |   8.36%       |   -0.4%                       |    42.4%                  |  1.6                 |
| 8    | MRK   | 8.2   |  7.97%        |    5.1%                      |    27.3%                  |    0.9               |
| 9    | REGN  | 8.2   |  12.20%        |     6.7%                     |   31.9%                   |   0.0                |
| 10   | SYF   | 8.2   |  13.50%        |     4.7%                     |     34.0%                 |   0.0                |

Metrics shown are based on financial data from **June 1, 2025**.

---

## Definitions

**Value Investing:**  A long-term strategy focused on buying high-quality, industry-leading companies trading below their intrinsic value.

**Enterprise Value:**  *Market Cap + Total Debt – Cash & Equivalents*. A comprehensive measure of a company’s total value, calculated as:  
                     

**Return on Enterprise Value:**  *Net Income ÷ Enterprise Value*. Measures how efficiently a company generates profit relative to its total value (including debt and cash).

**2-Year Revenue Growth:**  The percentage increase in a company’s revenue over the past two years, excluding effects from mergers or acquisitions.

**Net Profit Margin:**  *Net Income ÷ Revenue*. Shows what percentage of revenue becomes profit after all expenses, taxes, and interest are paid.

**Net Debt / EBITDA:**  *Net Debt ÷ EBITDA*. Indicates how many years a company would need to pay off its debt using its core operating earnings.

---

## Customization

You can modify the following settings directly in the code:

- **Competitor Mapping**: Edit the `competitor_map` dictionary to adjust which companies are compared.
- **Weight Configuration**: Adjust the `WEIGHTS` dictionary in the `investmentAnalysis` class to prioritize different financial metrics.
- **GUI Settings**: Change fonts, colors, or layout in the `StockAppGUI` class.

---

**Created By Leon Aaron**
