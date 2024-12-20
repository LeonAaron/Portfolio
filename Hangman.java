package Hangman;
import java.util.ArrayList;
import java.util.Scanner;

public class Hangman {
    
    //Input in main as argument
    protected String phrase;
   

    // Hangman Display
    protected String row0 = "     _________   ";
    protected String row1 = "     |       |   ";
    protected String row2 = "     O       |   ";
    protected String row3 = "             |   ";
    protected String row4 = "             |   ";
    protected String row5 = "             |   ";
    protected String row6 = "             |   ";
    protected String row7 = "         ********";
        
    protected String[] hangmanVisual = {this.row0, this.row1, this.row2, this.row3, this.row4, this.row5, this.row6, this.row7};;

    // Variables that will be altered throughout program
    protected String guess;

    protected int phraseLength;
    protected int lives;
    protected ArrayList<String> alreadyGuessed;
    protected boolean continueGame;
    protected boolean victory;

    protected ArrayList<String> hiddenPhrase;

    // Set up scanner
    protected Scanner sc;

    


    public Hangman(String phrase) {
        this.phrase = phrase.toUpperCase();

        this.phraseLength = phrase.length();
        this.lives = 6;
        this.alreadyGuessed = new ArrayList<String>();
        this.continueGame = true;
        this.hiddenPhrase = new ArrayList<String>();
        this.victory = false;
        this.sc = new Scanner(System.in);
    }


    public void game() {
        // Called once so outside of game loop
        System.out.println("Welcome to Hangman!");
        createHiddenPhrase();

        // Inside while game loop
        while (continueGame) {
            displayHangman();
            displayHiddenPhrase();
            userGuess();

            boolean correct = checkGuess();

            if (correct) {
                updateHiddenPhrase();
                checkWin();

                if (victory) {
                    displayWin();
                }
            }

            else {
                checkLoss();
            }
        }
        }


    public void createHiddenPhrase() {
        // Loop through all characters in phrase and check if letter or other input
        for (int i = 0; i < this.phraseLength; i++) {
            char letter = this.phrase.charAt(i);
            if (Character.isLetter(letter)) {
                this.hiddenPhrase.add("_");
            }
            else {
                this.hiddenPhrase.add(String.valueOf(letter));
            }  
        }
    }


    public void displayHangman() {
        // Changes hangmanVisual based on number of lives
        if (this.lives == 5){ 
            this.hangmanVisual[2] = "    (_)      |   ";
        }
        else if (this.lives == 4){ 
            this.hangmanVisual[3] = "   [< >]     |   ";
            this.hangmanVisual[4] = "   [_._]     |   ";
        }
        else if (this.lives == 3){ 
            this.hangmanVisual[3] = "[]=[< >]     |   ";
            this.hangmanVisual[4] = "[] [_._]     |   ";
            this.hangmanVisual[5] = "\"\"           |   ";
        }
        else if (this.lives == 2){ 
            this.hangmanVisual[3] = "[]=[< >]=[]  |   ";
            this.hangmanVisual[4] = "[] [_._] []  |   ";
            this.hangmanVisual[5] = "\"\"       \"\"  |   ";
        }
        else if (this.lives == 1){ 
            this.hangmanVisual[5] = "\"\" []    \"\"  |   ";
            this.hangmanVisual[6] = "   {}        |   ";
            this.hangmanVisual[7] = "   ^^    ********";
        }
        else if (this.lives == 0){ 
            this.hangmanVisual[5] = "\"\" [] [] \"\"  |   ";
            this.hangmanVisual[6] = "   {} {}     |   ";
            this.hangmanVisual[7] = "   ^^ ^^ ********";
        }

        // Prints hangman on screen
        for (String item:this.hangmanVisual) {
            System.out.println(item);
        }
        // For spacing
        System.out.println("");
    }

    
    public void displayHiddenPhrase() {
        // Prints out all values in the hiddenPhrase array
        for (String letter : hiddenPhrase) {
            System.out.print(letter);
        }
        // For spacing purposes
        System.out.println("");
        System.out.println("");
    }


    public void userGuess() {
        String input = "";

        do {
        System.out.print("Guess a letter: ");
        input = sc.nextLine();

        if (input.length() != 1) {
            System.out.println("Please enter a single letter.");
        }
        // Checks if the input is not a letter
        else if (!(input.matches("^[a-zA-Z]$"))) {
            System.out.println("Invalid input.");
        }
        else if (this.alreadyGuessed.contains(input.toUpperCase())) {
            System.out.println(input + " was already guessed");
        }
        // For spacing
        System.out.println("");
        // Loops until input is single or unique character, make sure to convert to upper case
        } while (!(input.matches("^[a-zA-Z]$")) || (this.alreadyGuessed.contains(input.toUpperCase())));

        // All words and letters will be in upper case
        this.guess = input.toUpperCase();
        // Tracks letters that were already used
        this.alreadyGuessed.add(this.guess);
    }


    public boolean checkGuess() {
        if (phrase.contains(this.guess)) {
            return true;
        }
        else {
            return false;
        }
    }


    public void updateHiddenPhrase() {
        for (int i = 0; i < this.phraseLength; i++) {
            char letter = this.phrase.charAt(i);
            // Convert letter into string so no type error
            if (this.guess.equals(Character.toString(letter))) {
                this.hiddenPhrase.set(i, Character.toString(letter));
            }
        }
    }


    public void checkLoss() {
        // Lose a life
        this.lives -= 1;

        if (this.lives == 0) {
            this.continueGame = false;
            lossDisplay();
        }
    }

    
    public void lossDisplay() {
        displayHangman();
        System.out.println("");
        System.out.println("Game Over!");
        System.out.println("The phrase was: ");
        System.out.println("");
        System.out.println(phrase);
        System.out.println("");
    }


    public void checkWin() {
        if (!(hiddenPhrase.contains("_"))) {
            this.continueGame = false;
            this.victory = true;
        }
    }


    public void displayWin() {
        System.out.println("Congratulations, You Won!");
        System.out.println("");

        System.out.println("..  _^_      ");
        System.out.println("[]  (_)        ");
        System.out.println("[]=[< >]=[]     ");
        System.out.println("   [_._] []      ");
        System.out.println("   [] [] \"\"  ");
        System.out.println("   {} {}  ");
        System.out.println("   ^^ ^^ ");
        System.out.println("");

        System.out.println(phrase);
        System.out.println("");
    }
}
