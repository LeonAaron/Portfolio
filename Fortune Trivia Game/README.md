# Fortune Trivia Game

## Overview
Fortune Trivia Game is a Java-based word guessing game. It presents the player with a series of questions in various categories such as phrases, movies, animals, countries, and more. After guessing letters for a specified number of turns, the player needs to guess the correct answer based on the hint provided. The game is built using Object-Oriented Programming principles, and it features randomization of categories and questions for an engaging experience.

## Features
- Multiple categories for questions (e.g., "Phrase", "Movie Title", "Animal", "Country", etc.)
- Option for random category selection for each round
- A variety of question types to keep the gameplay interesting
- Scoring based on correct answers

## Requirements
To run this game, you'll need:
- **Java 8 or higher** installed on your machine.
- A suitable IDE like **Eclipse** or **IntelliJ IDEA**

## Installation Instructions

### 1. Clone the repository:

To get started, clone the repository using the following command:

```bash 
    git clone https://github.com/LeonAaron/Portfolio.git
 ```

This will clone the entire Java repository to your computer.


### 2. Navigate to the project folder:

After cloning the repository, navigate to the Fortune Trivia Game folder.

### 3. Open in your IDE:

You can now open this project in any Java-compatible IDE (such as IntelliJ IDEA or Eclipse) to start working with or running the game.

### How to Run the Program 
- Open the project in your IDE.
- Locate **MainFortune.java** in the file directory.
- Run the MainFortune class. This will start the game.
- After selecting a random prize for the round, the game will automatically present a hint and ask the player to guess the answer. Based on the answer, you will receive feedback on whether the guess was correct or not.

### Game Settings  
Can be modified in MainFortune.py  

**Number of Tries:** User can customize number of letter guesses per word.  
**Number of Turns:** Customize total number of turns in a game.  
**Starting Net Worth:** Modify starting net worth. 
  
**Question Categories:**  
- The game can randomly select questions from different categories if **Question.generateRandomCategory()** is input.    
- Can select specific category by inputing category name (Ex. "Movie Title").   
- May use all categories by omitting input.  

**Example Setup**  
```java
FortuneGame game = new FortuneGame(questions, 3, 10, 0, "Phrase");  
```
- **questions:** references static attribute within QuestionFortune class that contains all question objects in array list.      
- **3:** Total letter guessing tries per turn.    
- **10:** Total turns per game.  
- **0:** Starting net worth.  
- **"Phrase":** Specific category selection.   


**Scoring:** Players receive points/prizes for every correctly guessed word.      

### Example Gameplay

**Example 1** 
- Hint: "A common greeting."
- Tries: 3    
- Answer: "HAVE A GOOD DAY"
- Display: "___ _ ____ ___"
- Player's Correct Letter Guess: "A"
- Display: "\_A__ A ____ \_A_"
- Player's Correct Letter Guess: "H"
- Display: "HA__ A ____ \_A_"
- Player's Incorrect Letter Guess: "X"
- Display: "HA__ A ____ \_A_"
- Player's Correct Word Guess: "HAVE A GOOD DAY"   
- Result: Correct! The player's score is increased.   <br>
   
**Example 2**
- Hint: "A blockbuster movie featuring dinosaurs."
- Tries: 2
- Answer: "JURASSIC PARK"
- Display: "________ ____"
- Player's Invalid Guess: "3"
- Display: "INVALID INPUT"
- Player's Correct Letter Guess: "J"
- Display: "J_______ ____"
- Player's Incorrect Duplicate Letter Guess: "J"
- Display: "J was already guessed. TRY AGAIN"
- Player's Correct Letter Guess: "K"
- Display: "J_______ ___K"
- Player's Incorrect Word Guess: "JABBERED WORK"  
- Result: Incorrect! Next round.  

### The files in the repository are:

- **MainFortune.java:** Main file where the game is initialized and played.  
- **QuestionFortune.java:** Defines the structure for each question, including the hint, answer, and category.  
- **FortuneGame.java:** Handles the game logic, including randomly selecting questions and managing the player's score.  

### How the Game Works  

- **Game Setup:** The game initializes a list of QuestionFortune objects, each containing a hint, answer, and category.   
- **Randomization:** At the start of each round, the game randomly selects a prize and question depending on category settings.   
- **Player Interaction:** The player is prompted to guess letters that they think are contained in the answer. After the designated number of tries, the user can guess the full phrase.  
- **Feedback:** The game provides feedback, such as whether the guess was correct or not, and continues until all rounds are completed.    

### If you'd like to customize the game, you can:    

- **Add more questions:** You can add a new question to the game by creating an new QuestionFortune object with personalized hint, answer, and category.    
- **Adjust game settings:** Modify the game logic in the MainFortune class to customize the number of rounds, question categories, or number of tries.   

### Adding New Questions/Categories:  

To add a new question, create a new QuestionFortune object with the following format:  
```java
new QuestionFortune("Your hint", "Your answer", "Your category");  
```
For example: 

```java
new QuestionFortune("A country known for its pyramids and ancient civilization.", "EGYPT", "Country");  
```  

  
**Created By Leon Aaron**
