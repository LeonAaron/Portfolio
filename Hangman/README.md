# Hangman Game

## Overview
The Hangman Game is a classic word-guessing game written in Java. The game presents a hidden word or phrase, and the player is prompted to guess the letters of the word. With each incorrect guess, the player loses one life, and a visual representation of the hangman is displayed. The game continues until the player guesses the word correctly or runs out of lives.

## Features
- **Classic Hangman Gameplay:** Players guess letters that they believe are in word.
- **Visual Hangman Display:** As incorrect guesses accumulate, a visual representation of the hangman is shown.
- **Customizable Word or Phrase:** User can modify the word or phrase in MainHangman.java.
- **Lives:** Players have 6 lives to guess the correct word or phrase until they are eliminated.

## Requirements
To run this game, you'll need:
- **Java 8 or higher** installed on your computer.
- A suitable IDE like **Eclipse** or **IntelliJ IDEA**.
  
## Installation Instructions

### 1. Clone the repository:

To get started, clone the repository using the following command:

```bash 
    git clone https://github.com/LeonAaron/Portfolio.git
 ```

This will clone my entire portfolio repository to your computer.

### 2. Navigate to the project folder:

After cloning the repository, navigate to the Hangman folder.

### 3. Open in your IDE:

You can now open this project in any Java-compatible IDE (such as IntelliJ IDEA or Eclipse) to start working with or running the game.  

## How to Run the Program
- Open the project in your IDE.
- Locate **MainHangman.java** in the file directory.
- Run the MainHangman class. This will start the game.
- The game will present a hidden word or phrase, and the player will guess letters until they either win or lose.
  
### Game Settings
The game settings can be modified in MainHangman.java:  

**Phrase:** Modify the phrase that the player must guess. 

**Example Setup**
```java
String phrase = "HAPPY BIRTHDAY!";
Hangman game = new Hangman(phrase);
game.game();
```

- **phrase:** A string representing the word or phrase the player will guess. You can change this to any word or phrase you like.
- **game.game();** Runs the game.

### Example Gameplay  

**Example 1**  
- Phrase: "HEY"
- Tries: 6
- Player's Correct Letter Guess: "E"
- Display: "\_E_"
- Player's Correct Letter Guess: "H"
- Display: "HE_"
- Player's Correct Letter Guess: "Y"
- Display: "HEY"
- Result: Correct! You win!  

**Example 2**  
- Phrase: "BANANA"
- Tries: 6
- Player's Invalid Guess: "NANA"
- Display: "INVALID INPUT"
- Player's Correct Letter Guess: "A"
- Display: "\_A_A_A"
- Player's Incorrect Letter Guess: "K"
- Display: Incorrect! You lose a life.
- Display: "\_A_A_A"
- Player's Correct Letter Guess: "B"
- Display: "BA_A_A"
- Player's Correct Letter Guess: "N"
- Display: "BANANA"
- Result: Correct! You win

### Files in the Repository:
- **MainHangman.java:** The main class where the game is initialized and played.
- **Hangman.java:** Contains the core logic of the Hangman game, including the methods for displaying the game state and checking guesses.

### How the Game Works  

- **Game Setup:** The game initializes with a phrase that the player must guess.
- **Player Interaction:** The player is prompted to guess letters. For each incorrect guess, a part of the hangman is drawn. The game ends when the player either guesses the word correctly or loses all their lives.
- **Feedback:** The game provides feedback after each guess, including whether the guess was correct, and updates the visual representation of the hangman.
  

#### To customize the game, you can:

- **Change the phrase:** Modify the phrase in MainHangman.java.

```java
String phrase = "NEW PHRASE HERE";
Hangman game = new Hangman(phrase);
```
For example:

```java
String phrase = "HELLO WORLD";
Hangman game = new Hangman(phrase);
```

**Created By Leon Aaron**
