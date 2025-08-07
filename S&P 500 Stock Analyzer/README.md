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
