# S&P 500 Stock Analyzer

## Overview

S&P 500 Stock Analyzer is a Python-based desktop application that helps users evaluate whether a publicly traded company is a good value investment. Built with financial principles used by professional analysts, the app scores each stock based on six key metrics and compares its performance to top competitors in the same industry. The GUI provides a user-friendly interface for analyzing individual stocks and visualizing their financial strengths and weaknesses.

The project was created using **pandas** for data analysis and **Tkinter** for the graphical user interface. It was designed to support real-world investing decisions and was used to guide $10,000 in stock selections for the **Hagan Scholarship Foundation** investment account.

---

## Features

- Evaluates stocks from the **S&P 500** using:
  - Return on Enterprise Value (ROEV)
  - Net Profit Margin
  - Revenue Growth (2-Year CAGR)
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
- **Excel file** (`S&P500_Data.xlsx`) with financial data for S&P 500 companies

---

## Installation Instructions

### 1. Clone the repository

```bash
git clone https://github.com/LeonAaron/Portfolio.git
```

### 2. Navigate to the project folder

```bash
cd Portfolio/S&P_500_Stock_Analyzer
```

### 3. Install dependencies

You can install the required packages using pip:

```bash
pip install pandas
```

> `tkinter` is typically pre-installed with Python, but if not, install it based on your operating system.

---

## How to Run the Program

1. Open a terminal or IDE (like VS Code or PyCharm).
2. Ensure the `S&P500_Data.xlsx` file is in the same directory as the Python script.
3. Run the main Python file:

```bash
python stock_analyzer.py
```

4. A full-screen GUI will appear.
5. Enter a valid stock ticker symbol (e.g., `TSLA`, `WMT`, `GOOGL`) to evaluate.
6. The app will analyze the company, compare it to competitors, and display a score and recommendation.

---

## Example Analysis Output

```
Sector: Consumer Discretionary
Industry: Automobiles

Metric                          TSLA       GM         F         
-----------------------------------------------------------------
Enterprise Value (Billions)     $748       $91        $53
Return on EV (%)                9.3 %      5.2 %      6.1 %
Net Profit Margin (%)           12.1 %     6.7 %      7.4 %
2-Yr Revenue Growth (%)         20.8 %     7.2 %      8.0 %
Net Debt / EBITDA               0.5        2.3        2.1

TSLA Score: 9.1 / 10                             Excellent Value Investment ✅
```

---

## Scoring Breakdown

The stock score is computed out of 10 based on the following weights:

| Metric                                 | Weight |
|----------------------------------------|--------|
| Return on Enterprise Value             | 25%    |
| Revenue Growth                         | 5%     |
| Debt Ratio (Net Debt / EBITDA)         | 15%    |
| ROEV vs. Top Competitor                | 20%    |
| Profit Margin vs. Top Competitor       | 20%    |
| Revenue Growth vs. Top Competitor      | 15%    |

---

## Customization

You can modify the following settings directly in the code:

- **Competitor Mapping**: Edit the `competitor_map` dictionary to adjust which companies are compared.
- **Weight Configuration**: Adjust the `WEIGHTS` dictionary in the `investmentAnalysis` class to prioritize different financial metrics.
- **GUI Settings**: Change fonts, colors, or layout in the `StockAppGUI` class.

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

## Project Files

- `stock_analyzer.py` – Main program file with scoring logic and GUI
- `Competitors_Map.py` – Python file containing the dictionary of competitors for each stock ticker
- `S&P500_Data.xlsx` – Excel file with financial metrics for S&P 500 stocks

---

## Created By

**Leon Aaron**

- UNC Chapel Hill Computer Science Student
- Passionate about AI, value investing, and data analysis
- [GitHub Portfolio](https://github.com/LeonAaron/Portfolio)
