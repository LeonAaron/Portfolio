#External Modules
import pandas as pd
import tkinter as tk
from tkinter import messagebox

#Hashmap of company competitors
from Competitors_Map import competitor_map

#Map of final score for text coloring
financial_values = {
}


#Analyze value invesments
class investmentAnalysis:
    def __init__(self, filepath):
        #Excel file of S&P 500 stocks and their financial standing
        self.data = pd.read_excel(filepath)

    def gather_data(self, ticker):
        """Primary logic for gathering data and determining score."""

        competitor_count = len(competitor_map[ticker])
        competitor2_exists = False

        #Compile financial data of company and competitors
        ticker_data = self.data[self.data['Ticker'] == ticker]
        competitor1_data = self.data[self.data['Ticker'] == competitor_map[ticker][0]]
        competitor2_data = None

        #Only load data if second competitor listed
        if competitor_count > 1:
            competitor2_data = self.data[self.data['Ticker'] == competitor_map[ticker][1]]
            competitor2_exists = True
        
        #Get Industry, Sector, Company, Enterprise Value (EV), Return on EV, Net Profit Margin, 2 Year Revenue Growth, Net Debt / EBITDA
        industry = ticker_data['Industry'].values[0]
        sector = ticker_data['Sector'].values[0]
        company_name = ticker_data['Company Name'].values[0]
        ev = ticker_data['Enterprise Value Millions'].values[0]  #Enterprise Value
        return_on_ev = ticker_data['Return on Enterprise Value %'].values[0]
        profit_margin = ticker_data['Net Income Margin %'].values[0]
        revenue_growth = ticker_data['Revenue CAGR % 2 Yr'].values[0]
        debt = ticker_data['Net Debt/ EBITDA'].values[0]

        #Get same information from competitors for comparison
        c1_ev = competitor1_data['Enterprise Value Millions'].values[0]  #Enterprise Value
        c1_return_on_ev = competitor1_data['Return on Enterprise Value %'].values[0]
        c1_profit_margin = competitor1_data['Net Income Margin %'].values[0]
        c1_revenue_growth = competitor1_data['Revenue CAGR % 2 Yr'].values[0]
        c1_debt = competitor1_data['Net Debt/ EBITDA'].values[0]


        #Get competitor 2 informaion if present
        if competitor2_exists:
            c2_ev = competitor2_data['Enterprise Value Millions'].values[0]  #Enterprise Value
            c2_return_on_ev = competitor2_data['Return on Enterprise Value %'].values[0]
            c2_profit_margin = competitor2_data['Net Income Margin %'].values[0]
            c2_revenue_growth = competitor2_data['Revenue CAGR % 2 Yr'].values[0]
            c2_debt = competitor2_data['Net Debt/ EBITDA'].values[0]

        
        labels = [
            "Enterprise Value (Billions)",
            "Return on EV (%)",
            "Net Profit Margin (%)",
            "2-Yr Revenue Growth (%)",
            "Net Debt / EBITDA"
            ]

        col1 = [self.safe_ev_format(ev), f"{return_on_ev} %", f"{profit_margin} %", f"{revenue_growth} %", debt]
        col2 = [self.safe_ev_format(c1_ev), f"{c1_return_on_ev} %", f"{c1_profit_margin} %", f"{c1_revenue_growth} %", c1_debt]
        if competitor2_exists:
            col3 = [self.safe_ev_format(c2_ev), f"{c2_return_on_ev} %", f"{c2_profit_margin} %", f"{c2_revenue_growth} %", c2_debt]
        else:
            col3 = [""] * len(labels)

        #Financial data for terminal
        print(company_name)
        print("\nIndustry: " + industry + "\tSector: " + sector + "\n")
        
        print(f"{'Metric':<30} {ticker:<10} {competitor_map[ticker][0]:<10} {competitor_map[ticker][1] if competitor2_exists else '':<10}")
        print("-" * 70)
        for i in range(len(labels)):
            print(f"{labels[i]:<30} {str(col1[i]):<10} {str(col2[i]):<10} {str(col3[i]):<10}")
        
        #Financial data for UI
        output_str = f"{company_name}\n"
        output_str += "\n\tSector: " + sector + "\n\tIndustry: " + industry + "\n\n"
        output_str += f"\t{'Metric':<30} {ticker:<10} {competitor_map[ticker][0]:<10} "
        output_str += f"\t{competitor_map[ticker][1] if competitor2_exists else '':<10}\n"
        output_str += "\t"+ "-" * 63 + "\n"

        for i in range(len(labels)):
            output_str += f"\t{labels[i]:<30} {str(col1[i]):<10} {str(col2[i]):<10} {str(col3[i]):<10}\n"            

        #Scoring system for stocks out of 100%
        score = 0

        WEIGHTS = {
            "return_on_ev": 0.25,
            "revenue_growth": .05,
            "debt_ratio": 0.15,
            "return_on_ev_comparison": 0.20,
            "profit_margin_comparison": 0.20,
            "revenue_growth_comparison": 0.15
        }

        #Preferably 8% or higher return on enterprise value
        score += WEIGHTS["return_on_ev"] * min(return_on_ev / 8, 1)  # cap at 100%
        score += WEIGHTS["revenue_growth"] * min(revenue_growth / 15, 1)
        score += WEIGHTS["debt_ratio"] * self.debt_score(debt)
        
        #Determine strongest financials of competitors for comparison
        if competitor2_exists:
            competitor_max_roev = max(c1_return_on_ev, c2_return_on_ev)          #Get max return on enterprise value from competitors
            competitor_max_profit_margin = max(c1_profit_margin, c2_profit_margin)
            competitor_max_revenue_growth = max(c1_revenue_growth, c2_revenue_growth) 
        else:
            competitor_max_roev = c1_ev
            competitor_max_profit_margin = c1_profit_margin
            competitor_max_revenue_growth = c1_revenue_growth
            

        score += WEIGHTS['return_on_ev_comparison'] * self.roev_score(return_on_ev, competitor_max_roev)
        score += WEIGHTS['profit_margin_comparison'] * self.margin_score(profit_margin, competitor_max_profit_margin)
        score += WEIGHTS['revenue_growth_comparison'] * self.revenue_score(revenue_growth, competitor_max_revenue_growth)

        #Display final score
        return self.display_score(score, output_str, ticker)

    
    def safe_ev_format(self, value):
        """Convert enterprise value to contain commas."""
        try:
            return f"${int(float(value) / 1000):,}"
        except:
            return value
    
    
    def debt_score(self, debt):
        """Calculate debt score."""
        #Net debt / EBTDIA < 2 is ideal
        if debt <= 2:                         
            return 1.0
        elif 2 < debt <= 5:
            return (5 - debt) / 3  # linear decrease between 2 and 5
        else:
            return 0.0

    
    def roev_score(self, roev, competitor_roev):
        """Scores stock return on enterprise value compared to strongest competitor."""
        diff = roev - competitor_roev

        #ROEV 2% better then strongest competitor is ideal
        if diff >= 2.0:
            return 1.0
        elif diff >= -0.5:
            return 0.7 + 0.3 * ((diff + 1.0) / 2.5)
        elif diff >= -2.5:
            return (diff + 2.5) / 1.5 * 0.7
        else:
            return 0.0
        
        
    def margin_score(self, margin, competitor_margin):
        """Scores stock profit margin % compared to strongest competitor."""
        diff = margin - competitor_margin

        #Profit margin 2% stronger then main competitor is ideal
        if diff >= 2.0:
            return 1.0
        elif diff >= -1.0:
            return 0.7 + 0.3 * ((diff + 1.0) / 3.0)
        elif diff >= -2.5:
            return (diff + 2.5) / 1.5 * 0.7          
        else:
            return 0.0
        
        
    def revenue_score(self, revenue, competitor_revenue):
        """Scores stock revenue growth % compared to strongest competitor."""
        diff = revenue - competitor_revenue

        #2 year revenue growth 3% better then strongest competitor is ideal
        if diff >= 3.0:
            return 1.0
        elif diff >= 0.0:
            return 0.7 + 0.3 * (diff / 3.0)
        elif diff >= -3.0:
            return 0.3 + 0.4 * ((diff + 3.0) / 3.0)
        else:
            return 0.0
        
    
    def display_score(self, score, output_str, ticker):
        """Evaluates final score out of 10."""

        #Round final score to one decimil
        final_score = round(score * 10, 1)

        #Prevent negative scoring
        if final_score < 0:
            final_score = 0

        #Ranking quality of value investment
        if final_score >= 9:
            recommendation = "Excellent Value Investment✅"
        elif final_score >= 7.5:
            recommendation = "Good Value Investment✔️"
        elif final_score >= 6:
            recommendation = "Fair Value, Requires Caution⚠️"
        elif final_score >= 4.5:
            recommendation = "Weak Investment Candidate❌"
        else:
            recommendation = "Avoid, Not a Value Investment❌"

        print(f"\n{ticker} Final Score:  {final_score} / 10")
        print(recommendation)

        
        final = f"\n\t{ticker} Score: {final_score} / 10"
        final_recc = f"{recommendation}\n"

        #Add to hashmap for coloring
        financial_values["Score"] = final_score
        financial_values["Score Text"] = final
        financial_values["Recommendation"] = recommendation

        #For spacing consistency
        spaces = 60 - len(final) - len(final_recc)

        #Modify output string for UI
        output_str += final
        output_str += " " * spaces + final_recc

        #Final output
        return output_str
    

#GUI
class StockAppGUI:
    def __init__(self, root, analyzer):
        self.analyzer = analyzer
        self.instructons = self.get_instructions()

        #Initialize UI
        self.root = root

        #Screen Dimensions
        width = root.winfo_screenwidth()
        height = root.winfo_screenheight()
        root.geometry(f"{width}x{height}")

        #Automatic Full-Screen
        root.state('zoomed')
        self.root.title("Stock Evaluator for Value Investing")
        root.configure(bg="#c1d6e6")

        FONT1 = ("Helvetica", 38, "bold")
        self.title_label = tk.Label(root, text="Stock Evaluator for Value Investing", font=FONT1, bg="#c1d6e6")
        self.title_label.pack(pady=20)

        #Financial data output
        self.output = tk.Text(root, height=15, width=80, font=("Consolas", 18), fg="#000000")
        self.output.pack()
        self.output.insert(tk.END, self.instructons)

        #Prevent user input into output box
        self.output.config(state="disabled")

        self.label = tk.Label(root, text="Enter Ticker Symbol", font=("Helvetica", 20, "bold"), bg="#c1d6e6")
        self.label.place(relx=0.5, rely=0.77, anchor='center') 

        #Text entry for ticker symbols
        self.entry = tk.Entry(root, font=("Arial", 26), width=20)
        self.entry.place(relx=0.5, rely=0.83, anchor='center')

        #Button to evaluate stocks
        self.evaluate_button = tk.Button(root, text="Evaluate", font=("Arial", 30), command=self.evaluate)
        self.evaluate_button.place(relx=0.5, rely=0.93, anchor='center')
        self.evaluate_button.configure(bg="#ff6347", fg="white")

        #Button to quit program
        quit_button = tk.Button(root, text="Quit", command=root.destroy, width=10, height=2)
        quit_button.place(relx=1.0, rely=0.0, anchor='ne', x=-10, y=10)
        quit_button.configure(bg="#c62828", fg="white")

    
    def evaluate(self):
        """Functionality to evaluate stock after Evaluate button pressed"""
        ticker = self.entry.get().upper().strip()

        #Remove input text
        self.entry.delete(0, tk.END)

        #Invalid input
        if ticker not in self.analyzer.data['Ticker'].values:
            self.show_error("Invalid ticker symbol. Please try again.")
            return

        #Run analysis
        output_str = self.analyzer.gather_data(ticker)
        
        #Clear and update output
        self.output.config(state="normal")  # Allow editing temporarily
        self.output.delete("1.0", tk.END)   
        self.output.insert(tk.END, output_str)
        self.output.config(state="disabled")

        #Center company name
        self.output.tag_configure("center_bold", justify="center", font=("Courier New", 22, "bold"))
        self.output.tag_add("center_bold", "1.0", "1.end")
        self.output.tag_add("center_bold", "end-2l linestart", "end-2l lineend")

        self.color_text(financial_values)
        return
    
    
    def show_error(self, message):
        """Error Display."""
        messagebox.showerror("Error", message)

    #Loads value investing criteria when corresponding button is clicked
    def get_instructions(self):
        
        criteria = """
                            Value Investment Criteria:

    Value investing is a long-term strategy that focuses on finding quality, 
    industry-leading companies that are undervalued by the market. 
    
    This app evaluates stocks based on the following financial metrics:

        - Return on Enterprise Value (25%)
        - Revenue Growth (5%)
        - Debt Ratio (15%)
        - Profit Margin Competitor Comparison(20%)
        - Revenue Growth Competitor Comparison(15%)
        - Return on Enterprise Value Competitor Comparison(20%)
        """

        return criteria
    

    def color_text(self, financial_values):
        """Colors final score based on rating."""
        self.output.config(state="normal")  # Allow editing temporarily

        # Define color tags
        self.output.tag_configure("green", foreground="#228B22")
        self.output.tag_configure("orange", foreground="#FF8C00")
        self.output.tag_configure("red", foreground="#B22222")

        # Get score and determine color
        score_value = financial_values.get("Score")
        score_text = financial_values.get("Score Text")
        recommendation_text = financial_values.get("Recommendation")

        if isinstance(score_value, (int, float)):
            if score_value >= 7.5:
                color = "green"
            elif score_value >= 6.0:
                color = "orange"
            else:
                color = "red"

            # Color Score
            if score_text:
                idx = self.output.search(score_text, "1.0", tk.END)
                if idx:
                    end_idx = f"{idx}+{len(score_text)}c"
                    self.output.tag_add(color, idx, end_idx)

            # Color Recommendation using same color
            if recommendation_text:
                idx = self.output.search(recommendation_text, "1.0", tk.END)
                if idx:
                    end_idx = f"{idx}+{len(recommendation_text)}c"
                    self.output.tag_add(color, idx, end_idx)

            self.output.config(state="disabled")  # Re-lock the text box


#Main program logic
def main():
    #Path to S&P 500 Excel file in script directory
    filepath = 'S&P500_Data.xlsx'
    
    analyzer = investmentAnalysis(filepath)

    #UI
    root = tk.Tk()
    app = StockAppGUI(root, analyzer)
    root.mainloop()


if __name__ == "__main__":
    main()