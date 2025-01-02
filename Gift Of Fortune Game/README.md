Here’s the complete README.md file with all the requested sections, including Example Gameplay, Game Settings, File Structure, and more:

# Gift of Fortune Game

## Overview
The "Gift of Fortune" is a Java-based word guessing game. It presents the player with a series of questions in various categories such as phrases, movies, animals, countries, and more. The player needs to guess the correct answer based on the hint provided. The game is built using Object-Oriented Programming principles, and it features randomization of categories and questions for an engaging experience.

## Features
- Multiple categories for questions (e.g., "Phrase", "Movie Title", "Animal", "Country", etc.)
- Random category selection for each round
- User-friendly interface for interacting with the game
- A variety of question types to keep the gameplay interesting
- Scoring based on correct answers

## Requirements
To run this game, you'll need:
- **Java 8 or higher** installed on your machine.
- A suitable IDE like **Eclipse**, **IntelliJ IDEA**, or **NetBeans**, or you can use the **command line**.

## Installation Instructions

### 1. Clone the repository:

To get started, clone the repository using the following command:

git clone https://github.com/LeonAaron/Java.git
This will clone the entire Java repository to your local machine.


**2. Navigate to the project folder:**

After cloning the repository, navigate to the Gift Of Fortune Game folder:

cd Java/Gift\ Of\ Fortune\ Game/


**3. Open in your IDE:**

You can now open this project in any Java-compatible IDE (such as IntelliJ IDEA or Eclipse) to start working with or running the game.

**How to Run the Program**  
Open the project in your IDE.
Locate the MainFortune.java file in the src directory.
Run the MainFortune class. This will start the game.
The game will automatically present a hint and ask the player to guess the answer. Based on the answer, you will receive feedback on whether the guess was correct or not.

**Game Settings**  
Question categories: The game can randomly select questions from different categories (e.g., "Phrase", "Movie Title", "Animal", etc.). <br>
Rounds: Each round presents a question with a hint. You need to guess the correct answer. <br>
Scoring: Players receive points for every correct answer. <br>

**Example Gameplay**

Example 1 <br>
Hint: "A common greeting." <br>
Answer: "HAVE A GOOD DAY" <br>
Player's Guess: "HAVE A GOOD DAY" <br>
Result: Correct! The player gets a point. <br>

Example 2
Hint: "A blockbuster movie featuring dinosaurs." <br>
Answer: "JURASSIC PARK" <br>
Player's Guess: "JURASSIC PARK" <br>
Result: Correct! The player gets a point. <br>

Example 3 <br>
Hint: "A domesticated animal often kept as a pet, known for its loyalty."   
Answer: "DOG" <br>
Player's Guess: "DOG" <br>
Result: Correct! The player gets a point.


**The main files in the repository are:**

MainFortune.java: The entry point of the game where the game is initialized and played.  
QuestionFortune.java: Defines the structure for each question, including the hint, answer, and category.  
FortuneGame.java: Handles the game logic, including randomly selecting questions and managing the player's score.  


**How the Game Works**

Game Setup: The game initializes a list of QuestionFortune objects, each containing a hint, answer, and category.  
Randomization: At the start of each round, the game randomly selects a category and presents a question from that category.  
Player Interaction: The player is prompted to guess the answer to the question.  
Feedback: The game provides feedback, such as whether the guess was correct or not, and continues until all rounds are completed.  

**If you'd like to customize the game, you can:**

Add more categories: You can add new question categories to the game by adding new QuestionFortune objects with different hints and answers.  
Adjust game settings: Modify the game logic in the FortuneGame class to change the number of rounds, question types, or difficulty.  
Adding New Categories:  
To add a new category, simply create a new QuestionFortune object with the following format:  

new QuestionFortune("Your hint", "Your answer", "Your category");  

For example:  
new QuestionFortune("A country known for its pyramids and ancient civilization.", "EGYPT", "Country");  

**To change the number of rounds or difficulty, modify the FortuneGame class:**  

FortuneGame game = new FortuneGame(questions, 3, 10, 0, "Phrase");  
The first number 3 represents the number of rounds.  
The second number 10 represents the time limit (you can modify this as needed).  
"Phrase" is the default category, which can be adjusted.  
