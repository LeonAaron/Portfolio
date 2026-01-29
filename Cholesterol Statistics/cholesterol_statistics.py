"""
Total Cholesterol Distribution for US Males Aged 40-60
Using Official NHANES August 2021-August 2023 Data

Data Sources:
- NHANES August 2021-August 2023 Total Cholesterol Laboratory Data (TCHOL_L)
- NHANES August 2021-August 2023 Demographics Data (DEMO_L)

References:
- NHANES Cholesterol Data: https://wwwn.cdc.gov/Nchs/Nhanes/2021-2023/TCHOL_L.XPT
- NHANES Demographics Data: https://wwwn.cdc.gov/Nchs/Nhanes/2021-2023/DEMO_L.XPT
"""

import numpy as np
import pandas as pd
import matplotlib
matplotlib.use('Agg')  # Use non-interactive backend
import matplotlib.pyplot as plt
import urllib.request
import os

# NHANES August 2021-August 2023 Data URLs
# Note: Due to COVID-19, there is no standard "2021-2022" cycle
# This uses the August 2021-August 2023 continuous NHANES data
TCHOL_URL = "https://wwwn.cdc.gov/Nchs/Data/Nhanes/Public/2021/DataFiles/TCHOL_L.XPT"
DEMO_URL = "https://wwwn.cdc.gov/Nchs/Data/Nhanes/Public/2021/DataFiles/DEMO_L.XPT"

def download_nhanes_data(url, filename):
    """
    Download NHANES data file if not already present

    Args:
        url (str): URL to download from
        filename (str): Local filename to save to

    Returns:
        str: Path to downloaded file
    """
    if not os.path.exists(filename):
        print(f"Downloading {filename}...")
        urllib.request.urlretrieve(url, filename)
        print(f"  Downloaded: {filename}")
    else:
        print(f"  Using cached: {filename}")
    return filename

def load_nhanes_data():
    """
    Download and load NHANES August 2021-August 2023 data

    Returns:
        pd.DataFrame: Merged dataframe with cholesterol and demographics
    """
    print("Loading NHANES August 2021-August 2023 data...")

    # Download data files
    tchol_file = download_nhanes_data(TCHOL_URL, "TCHOL_L.XPT")
    demo_file = download_nhanes_data(DEMO_URL, "DEMO_L.XPT")

    # Load XPT files
    print("Reading data files...")
    tchol_df = pd.read_sas(tchol_file)
    demo_df = pd.read_sas(demo_file)

    print(f"  Total cholesterol records: {len(tchol_df)}")
    print(f"  Demographics records: {len(demo_df)}")

    # Merge datasets on SEQN (sequence number)
    merged_df = pd.merge(tchol_df, demo_df, on='SEQN', how='inner')
    print(f"  Merged records: {len(merged_df)}")

    return merged_df

def filter_males_40_60(df):
    """
    Filter dataset to males aged 40-60 (inclusive)

    Args:
        df (pd.DataFrame): NHANES merged dataframe

    Returns:
        pd.DataFrame: Filtered dataframe
    """
    print("\nFiltering to males aged 40-60...")

    # Filter conditions:
    # RIAGENDR == 1 (Male)
    # RIDAGEYR >= 40 and <= 60
    # LBXTC is not null (has cholesterol measurement)
    # WTMEC2YR > 0 (has valid sample weight)

    filtered = df[
        (df['RIAGENDR'] == 1) &
        (df['RIDAGEYR'] >= 40) &
        (df['RIDAGEYR'] <= 60) &
        (df['LBXTC'].notna()) &
        (df['WTMEC2YR'] > 0)
    ].copy()

    print(f"  Total males aged 40-60: {len(filtered)}")
    print(f"  Cholesterol range: {filtered['LBXTC'].min():.1f} - {filtered['LBXTC'].max():.1f} mg/dL")
    print(f"  Mean (unweighted): {filtered['LBXTC'].mean():.1f} mg/dL")

    return filtered

def generate_cholesterol_distribution(df):
    """
    Generate weighted cholesterol distribution for males aged 40-60

    Args:
        df (pd.DataFrame): Filtered NHANES dataframe

    Returns:
        pd.DataFrame: DataFrame with cholesterol levels and weighted percentages
    """
    print("\nGenerating weighted distribution...")

    # Create bins from 50 to 400 with step of 5
    bins = np.arange(50, 405, 5)
    bin_centers = bins[:-1] + 2.5  # Center of each bin

    # Assign each observation to a bin
    df['cholesterol_bin'] = pd.cut(df['LBXTC'], bins=bins, labels=bin_centers, include_lowest=True)

    # Calculate weighted counts for each bin
    weighted_counts = df.groupby('cholesterol_bin', observed=True)['WTMEC2YR'].sum()

    # Convert to DataFrame and fill missing bins with 0
    distribution = pd.DataFrame({
        'cholesterol_level': bin_centers,
        'weighted_count': 0.0
    })

    # Fill in the actual weighted counts
    for bin_center, count in weighted_counts.items():
        if pd.notna(bin_center):
            idx = distribution[distribution['cholesterol_level'] == float(bin_center)].index
            if len(idx) > 0:
                distribution.loc[idx[0], 'weighted_count'] = count

    # Calculate percentages
    total_weight = distribution['weighted_count'].sum()
    distribution['population_perc'] = (distribution['weighted_count'] / total_weight) * 100

    # Keep only required columns
    distribution = distribution[['cholesterol_level', 'population_perc']]

    print(f"  Total weighted population: {total_weight:,.0f}")
    print(f"  Number of bins with data: {(distribution['population_perc'] > 0).sum()}")

    return distribution

def plot_distribution(df, highlight_value=184):
    """
    Plot cholesterol distribution with arrow highlighting specific value

    Args:
        df (pd.DataFrame): DataFrame with cholesterol_level and population_perc
        highlight_value (int): Cholesterol level to highlight with arrow (default: 184)
    """
    # Create figure
    fig, ax = plt.subplots(figsize=(14, 8))

    # Plot the distribution
    ax.plot(df['cholesterol_level'], df['population_perc'],
            linewidth=2.5, color='#2E86AB', label='NHANES 2021-2022 Data')
    ax.fill_between(df['cholesterol_level'], df['population_perc'],
                     alpha=0.3, color='#2E86AB')

    # Find the percentage at the highlighted value
    highlight_idx = (df['cholesterol_level'] - highlight_value).abs().argmin()
    highlight_perc = df.loc[highlight_idx, 'population_perc']

    # Add vertical line at highlighted value
    ax.axvline(x=highlight_value, color='#E63946', linestyle='--',
               linewidth=2, alpha=0.7, label=f'Your Level: {highlight_value} mg/dL')

    # Add arrow pointing to the highlighted value
    # Arrow starts from above and points down to the curve
    arrow_start_y = max(df['population_perc']) * 0.85
    ax.annotate(f'{highlight_value} mg/dL',
                xy=(highlight_value, highlight_perc),
                xytext=(highlight_value, arrow_start_y),
                fontsize=14,
                fontweight='bold',
                color='#E63946',
                ha='center',
                arrowprops=dict(
                    arrowstyle='->',
                    color='#E63946',
                    lw=2.5,
                    shrinkA=0,
                    shrinkB=0
                ))

    # Add reference lines for cholesterol categories
    ax.axvline(x=200, color='gray', linestyle=':', alpha=0.5, linewidth=1)
    ax.axvline(x=240, color='#FF6B6B', linestyle=':', alpha=0.5, linewidth=1)

    # Add text labels for categories
    ax.text(150, max(df['population_perc']) * 0.95, 'Desirable\n(<200)',
            ha='center', fontsize=10, color='green', weight='bold', alpha=0.7)
    ax.text(220, max(df['population_perc']) * 0.95, 'Borderline High\n(200-239)',
            ha='center', fontsize=10, color='orange', weight='bold', alpha=0.7)
    ax.text(300, max(df['population_perc']) * 0.95, 'High\n(≥240)',
            ha='center', fontsize=10, color='red', weight='bold', alpha=0.7)

    # Formatting
    ax.set_xlabel('Total Cholesterol Level (mg/dL)', fontsize=13, fontweight='bold')
    ax.set_ylabel('Percentage of Population (%)', fontsize=13, fontweight='bold')
    ax.set_title('Total Cholesterol Distribution in US Males Aged 40-60\n' +
                 'NHANES August 2021-August 2023 Survey Data',
                 fontsize=16, fontweight='bold', pad=20)

    # Set x-axis limits and ticks
    ax.set_xlim(50, 400)
    ax.set_xticks(np.arange(50, 401, 25))
    ax.tick_params(axis='both', labelsize=11)

    # Add grid
    ax.grid(True, alpha=0.3, linestyle='--', linewidth=0.5)

    # Add legend
    ax.legend(loc='upper right', fontsize=11, framealpha=0.9)

    # Add data source note
    fig.text(0.5, 0.02,
             'Data Source: NHANES August 2021-August 2023 | Survey-weighted distribution\n' +
             'https://wwwn.cdc.gov/Nchs/Nhanes/2021-2023/TCHOL_L.XPT',
             ha='center', fontsize=9, style='italic', color='gray')

    plt.tight_layout()

    return fig

def main():
    """Main execution function"""
    print("=" * 70)
    print("TOTAL CHOLESTEROL DISTRIBUTION ANALYSIS")
    print("US Males Aged 40-60 (NHANES August 2021-August 2023)")
    print("=" * 70)
    print()

    # Load NHANES data
    nhanes_data = load_nhanes_data()

    # Filter to males aged 40-60
    filtered_data = filter_males_40_60(nhanes_data)

    # Generate weighted distribution
    distribution_df = generate_cholesterol_distribution(filtered_data)

    # Save to CSV
    csv_filename = 'cholesterol_distribution.csv'
    distribution_df.to_csv(csv_filename, index=False)
    print(f"\n[OK] Distribution data saved to: {csv_filename}")
    print(f"  - {len(distribution_df)} data points")
    print(f"  - Range: {distribution_df['cholesterol_level'].min()}-{distribution_df['cholesterol_level'].max()} mg/dL")
    print()

    # Calculate weighted mean and std
    weighted_mean = np.average(filtered_data['LBXTC'], weights=filtered_data['WTMEC2YR'])
    weighted_var = np.average((filtered_data['LBXTC'] - weighted_mean)**2, weights=filtered_data['WTMEC2YR'])
    weighted_std = np.sqrt(weighted_var)

    print("Weighted Distribution Statistics:")
    print(f"  Mean:   {weighted_mean:.1f} mg/dL")
    print(f"  Std Dev: {weighted_std:.1f} mg/dL")
    print()

    # Calculate percentiles from cumulative distribution
    cumulative = np.cumsum(distribution_df['population_perc'])
    p25_idx = (cumulative - 25).abs().argmin()
    p50_idx = (cumulative - 50).abs().argmin()
    p75_idx = (cumulative - 75).abs().argmin()

    print("Weighted Percentiles:")
    print(f"  25th percentile: {distribution_df.loc[p25_idx, 'cholesterol_level']:.0f} mg/dL")
    print(f"  50th percentile: {distribution_df.loc[p50_idx, 'cholesterol_level']:.0f} mg/dL")
    print(f"  75th percentile: {distribution_df.loc[p75_idx, 'cholesterol_level']:.0f} mg/dL")
    print()

    # Find percentage at 184 mg/dL
    highlight_idx = (distribution_df['cholesterol_level'] - 184).abs().argmin()
    highlight_level = distribution_df.loc[highlight_idx, 'cholesterol_level']
    cumulative_at_184 = cumulative[highlight_idx]

    print(f"At 184 mg/dL cholesterol level:")
    print(f"  {cumulative_at_184:.1f}% of the male population (aged 40-60) has lower cholesterol")
    print(f"  This is in the 'Desirable' category (<200 mg/dL)")
    print()

    # Plot distribution
    print("Creating visualization...")
    fig = plot_distribution(distribution_df, highlight_value=184)

    # Save plot
    plot_filename = 'cholesterol_distribution.png'
    fig.savefig(plot_filename, dpi=300, bbox_inches='tight')
    print(f"[OK] Plot saved to: {plot_filename}")
    print()

    print("=" * 70)
    print("Analysis complete. Using official NHANES August 2021-August 2023")
    print("data with survey-weighted population estimates for males aged 40-60.")
    print("=" * 70)

if __name__ == "__main__":
    main()
