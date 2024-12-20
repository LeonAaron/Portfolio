package Hangman;

public class Main {
    
    public static void main(String[] args) {
        String phrase = "HAPPY BIRTHDAY PAPA!";
        Hangman game = new Hangman(phrase);
        game.game();
    }
}
