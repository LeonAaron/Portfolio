package GiftOfFortune;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FortuneGame {

    //Constants
    protected int VOWEL_COST = 100;
    protected String[] VOWELS = {"A", "E", "I", "O", "U"};

    //Use String concatenation to create multi-lined string
    protected String PRESENT_DISPLAY = "   ___M___     ___M___     ___M___ \n" +     
                                       "  |   |   |   |   |   |   |   |   |\n" +
                                       "  |##-1-##|   |##-2-##|   |##-3-##|\n" +
                                       "  |   |   |   |   |   |   |   |   |\n" +
                                       "  '''''''''   '''''''''   ''''''''' ";

    //Turns of the game
    protected int totalTurns;
    protected int currentTurn = 1;

    //Tries to guess a word
    protected int totalTries;
    protected int currentTries;

    // Contains all possible prizes
    protected String[] prizes = {"Bankrupt", "Lose A Turn", "Vacation", "Car", "2000", "1250", "1000", "900", "750", "600", "500", "450", "250", "100", "1"};
    protected String[] vacations = {"Barcelona, Spain", "Miami, Florida", "Rome, Italy", "Paris, France", "San Diego, California"};
    protected String[] cars = {"Toyota Prius", "Ford Mustang", "Mercades Benz", "Tesla Model X", "Dodge Charger"};

    protected String currentPrize = "";

    //If user gers vacation or car, they are stored here
    protected String vacation = "";
    protected String car = "";

    protected boolean winCar = false;
    protected boolean winVacation = false;

    //All used and unused Question objects
    protected ArrayList<Question> questions;
    protected ArrayList<Question> usedQuestions = new ArrayList<>();

    //Current answer to question and the hidden form, question, and category
    protected String currentAnswer = "";
    protected String currentQuestion = "";
    protected String category;

    protected Question currentQuestionObject;

    //Hidden answer as StringBuilder to modify
    protected StringBuilder hiddenAnswer = new StringBuilder("");

    
    //External functionality
    protected Scanner sc = new Scanner(System.in);
    protected Random random = new Random();

    //Final Score
    protected int netWorth;

    //If user guesses phrase correctly or not
    protected boolean correct = false;

    //User guesses
    protected ArrayList<String> guessedLetters = new ArrayList<>();

    protected String letterGuess = "";
    protected String phraseGuess = "";


    //Two constructors
    public FortuneGame(ArrayList<Question> questions, int totalTries, int totalTurns, int startingNetWorth, String category) {
        //Initialize attributes that depend on user input
        this.questions = questions;
        this.totalTries = totalTries;
        this.currentTries = totalTries;
        this.totalTurns = totalTurns;
        this.netWorth = startingNetWorth;
        this.category = category;
    }


    public FortuneGame(ArrayList<Question> questions, int totalTries, int totalTurns, int startingNetWorth) {
        //Constructor for when category is not given, all question categories applicable
        this.questions = questions;
        this.totalTries = totalTries;
        this.currentTries = totalTries;
        this.totalTurns = totalTurns;
        this.netWorth = startingNetWorth;
        this.category = "None";
    }   


    public void play() {
        determineValidQuestions();
        displayRules();

        //Game loop
        while (currentTurn <= totalTurns) {
            displayStats();
            selectPrize();
            
            //If bankrupt or lose a turn, loop from beginning
            if (loseATurnLogic() == true || bankruptLogic() == true) {
                continue;
            }

            otherPrizeLogic();
            generateQuestion();
            createHiddenAnswer();
            
            //Logic for the word guessing
            this.currentTries = this.totalTries;
            while (currentTries > 0 && this.correct == false) {
                displayPrompt();

                //If user inputs "Guess", break out of character guessing
                if (userLetterGuess() == true) {
                    break;
                }

                //Functionality depending on whether user guesses a correct letter
                if (checkIfLetterCorrect() == true) {
                    updateHiddenAnswer();
                    checkWin();
                } else {
                    System.out.println("\nINCORRECT GUESS");
                }
            }

            //Functionality if user did not already guess phrase through letters
            if (this.correct == false) {
                userPhraseGuess();
                checkCorrect();
            }

            if (this.correct == true) {
                correctLogic();
            }
            else {
                incorrectLogic();
            }

            reset();
        }

        displayEndgame();
    }


    public void determineValidQuestions() {
        //If category is provided, gets questions from that category
        ArrayList<Question> validQuestions = new ArrayList<>();

        if (this.category.equals("None") == false) {
            for (Question question : questions) {
                //If category of specific questoin is equal to input category
                if (question.category.equalsIgnoreCase(this.category)) {
                    validQuestions.add(question);
                } 
            }
            //Update attribute
            this.questions = validQuestions;
        }
    }


    public void displayRules() {
        System.out.println("Welcome To Gift Of Fortune!\n");
        System.out.println("The goal of the game is to end with the highest net worth possible!\n");
        System.out.println("You start with a net worth of $" + this.netWorth + "!\n");
        System.out.println("Every turn, you will select a present which contains a prize.\n");
        System.out.println("To claim this prize, you must correctly guess a phrase by using a hint as help!\n");
        System.out.println("You will first guess possible letters.\n");
        System.out.println("If you choose to guess a vowel, that will cost you $" + this.VOWEL_COST + "!\n");
        System.out.println("Afterwards, you will have an attempt to guess the phrase!\n");
        System.out.println("If correct, you will recieve the prize!\n");
        System.out.println("If incorect, you will miss out on the reward.\n");
        System.out.println("Time To Begin!\n\n");

        if (this.category.equals("None") == false) {
            System.out.println("The category is " + this.category.toUpperCase() + "\n");
        }
    }


    public void displayStats() {
        System.out.println("TURN: " + this.currentTurn + "/" + this.totalTurns + "\n");
        System.out.println("NET WORTH: $" + this.netWorth + "\n");
        System.out.println(this.PRESENT_DISPLAY + "\n");
    }


    public void selectPrize() {
        //Get three random indexes to get 3 random prizes
        int randomIndex1 = random.nextInt(this.prizes.length);
        int randomIndex2 = random.nextInt(this.prizes.length);
        int randomIndex3 = random.nextInt(this.prizes.length);
        
        String[] randomPrizes = {prizes[randomIndex1], prizes[randomIndex2], prizes[randomIndex3]};

        int boxSelection = 0;

        do {
            //Obtain user input
            System.out.print("Select a present and get a prize! {1, 2, 3}: ");
            String input = sc.nextLine().trim();

            if (input.equals("1") || input.equals("2") || input.equals("3")) {
                boxSelection = Integer.parseInt(input);
            }
            else {
                System.out.println("\nINVALID INPUT");
            }
        } while (boxSelection == 0);

        //Sets currentPrize equal to corresponding box
        this.currentPrize = randomPrizes[boxSelection - 1]; 
    }


    public boolean loseATurnLogic() {
        //Returns true if the prize is "Lose A Turn"
        if (this.currentPrize.equals("Lose A Turn")) {
            this.currentTurn++;
            System.out.println("\n\nLOSE A TURN\n");
            return true;
        }
        return false;
        
    }


    public boolean bankruptLogic() {
        if (this.currentPrize.equals("Bankrupt")) {
            this.currentTurn++;
            this.netWorth = 0;

            if (this.winCar) {
                this.winCar = false;
                this.prizes[3] = "Car";

            }
            if (this.winVacation) {
                this.winVacation = false;
                this.prizes[2] = "Vacation";
            }
            System.out.println("\n\nBANKRUPT\n");
            return true;
        }
        return false;
    }

    
    public void carLogic() {
        //Select random car
        int randomIndex = random.nextInt(this.cars.length);
        this.car = this.cars[randomIndex];
        System.out.println("\n\nYou have the chance to win a brand new " + this.car + "!");
    }


    public void vacationLogic() {
        //Select random vacation
        int randomIndex = random.nextInt(this.vacations.length);
        this.vacation = this.vacations[randomIndex];
        System.out.println("\n\nA vacation to " + this.vacation + " is at stake!");
    }


    public void otherPrizeLogic() {
        if (this.currentPrize.equals("Car")) {
            carLogic();
        }
        else if (this.currentPrize.equals("Vacation")) {
            vacationLogic();
        }
        else {
            System.out.println("\n\n$" + this.currentPrize + " is on the line!");
        }
    }


    public void generateQuestion() {
        this.currentQuestion = "";

        do {
            //Generate random index for accesss random element in questions ArrayList
            int randomIndex = random.nextInt(this.questions.size());
            Question randomQuestion = this.questions.get(randomIndex);
            
            //If question is unused, alter currentAnswer, currentQuestion, and usedQuestions attributes
            if (this.usedQuestions.contains(randomQuestion) == false) {
                this.currentQuestion = randomQuestion.hint;
                this.currentAnswer = randomQuestion.answer;
                this.currentQuestionObject = randomQuestion;
                this.usedQuestions.add(randomQuestion);
            }
        } while (this.currentQuestion.equals(""));
    }


    public void createHiddenAnswer() {
        //Clears hiddenAnswer StringBuilder so additional hidden answers do not append
        this.hiddenAnswer.setLength(0);

        for (int i = 0; i < this.currentAnswer.length(); i++) {
            char letter = this.currentAnswer.charAt(i);
            //Creates dashed lines for alphabetical characters, mutuates StringBuilder
            if (Character.isLetter(letter)) {
                this.hiddenAnswer.append("_");
            }
            else {
                this.hiddenAnswer.append(String.valueOf(letter));
            }  
        }
    }


    public void displayPrompt() {
        System.out.println("\n\nTRIES LEFT: " + this.currentTries + "/" + this.totalTries + "\n");
        //If all categories allowed, display category by refrencing question object
        if (this.category.equals("None")) {
            System.out.println("CATEGORY: " + currentQuestionObject.category + "\n");
        }
        System.out.println("HINT: " + this.currentQuestion + "\n");
        System.out.println(this.hiddenAnswer);
    }


    public boolean checkIfVowel(String input) {
        for (String vowel : this.VOWELS) {
            if (vowel.equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }


    public boolean userLetterGuess() {
        String input = "";
        //For voewl guessing
        boolean insufficeintFunds;

        do {
            System.out.print("\nGuess a letter or type \"Guess\" to guess the word: ");
            insufficeintFunds = false;
            input = sc.nextLine().trim();
            input = input.toUpperCase();

            //Returns true if the user is ready to guess
            if (input.equalsIgnoreCase("Guess")) {
                return true;
            }
            //If input is not a single letter, the input is invalid
            else if (input.matches("^[a-zA-Z]$") == false) {
                System.out.println("INVALID INPUT");
            }
            else if (this.guessedLetters.contains(input)) {
                System.out.println(input + " was already guessed. TRY AGAIN");
            }
            //If input is vowel, check validity
            else if (checkIfVowel(input) == true) {
                if (this.netWorth < VOWEL_COST) {
                    System.out.println("\nINSUFFICEINT FUNDS\n\nGUESS A CONOSANT\n");
                    insufficeintFunds = true;
                }
                else {
                    this.netWorth -= this.VOWEL_COST;
                    System.out.println("\nThat vowel costed you $" + this.VOWEL_COST);
                    System.out.println("\nNET WORTH: $" + this.netWorth);
                }
            }
        // Loops until input is single or unique character, make sure to convert to upper case
        } while (input.matches("^[a-zA-Z]$") == false || (this.guessedLetters.contains(input)) || insufficeintFunds);

        this.letterGuess = input;
        this.currentTries--;

        // Tracks used letters
        this.guessedLetters.add(this.letterGuess);
        return false;
    }


    public boolean checkIfLetterCorrect() {
        if (this.currentAnswer.contains(this.letterGuess)) {
            return true;
        } 
        else {
            return false;
        }
    }


    public void updateHiddenAnswer() {
        for (int i = 0; i < this.currentAnswer.length(); i++) {
            char letter = this.currentAnswer.charAt(i);
            // Convert letter into string so no type error
            if (this.letterGuess.equals(Character.toString(letter))) {
                this.hiddenAnswer.setCharAt(i, letter);
            }
        }
    }


    public void checkWin() {
        //Convert to string to use .contains functionality
        if (hiddenAnswer.toString().contains("_") == false) {
            this.correct = true;
        }
    }


    public void userPhraseGuess() {
        System.out.println("\n\nNow it's time to guess the phrase!\n");
        System.out.println("HINT: " + this.currentQuestion + "\n");
        System.out.println(this.hiddenAnswer + "\n");

        String input = "";
        boolean loopAgain = false;

        //Array of input and answer words
        String[] answerWords = this.currentAnswer.split(" ");

        do {
            //Set to false every iteration, becomes true if individual words length doesn't match
            loopAgain = false;

            System.out.print("GUESS: ");
            input = sc.nextLine().trim();
            String[] inputWords = input.split(" ");

            //Makes sure that user input is same size as the answer
            if (input.length() != this.currentAnswer.length()) {
                System.out.println("\nPlease enter a guess of valid length. TRY AGIAN");
            }
            else {
                //Makes sure individual input word length is same as answer word length
                for (int i = 0; i < answerWords.length; i++) {
                    if (answerWords[i].length() != inputWords[i].length()) {
                        System.out.println("\nPlease enter a guess of valid length. TRY AGIAN");
                        loopAgain = true;
                        break;
                    }
                }
            }

        } while (input.length() != this.currentAnswer.length() || loopAgain);

        this.phraseGuess = input;
    }


    public void checkCorrect() {
        if (this.phraseGuess.equalsIgnoreCase(this.currentAnswer)) {
            this.correct = true;
        }
    }


    public void correctLogic() {
        if (this.currentPrize.equals("Vacation")) {
            this.winVacation = true;
            //If vacation or car is won, replace it with cash prize to make sure only one todal vacation/car can be won
            this.prizes[2] = "400";
            System.out.println("\nCORRECT! You won a vacation to " + this.vacation + "!\n\n");
        }
        else if (this.currentPrize.equals("Car")) {
            this.winCar = true;
            this.prizes[3] = "1500";
            System.out.println("\nCORRECT! You won a brand new " + this.car + "!\n\n");
        }
        else {
            this.netWorth += Integer.parseInt(this.currentPrize);
            System.out.println("\nCORRECT! You won $" + currentPrize + "!\n\n");
        }
    }


    public void incorrectLogic() {
        System.out.println("\nINCORRECT");
        System.out.println("\nThe answer was " + this.currentAnswer + "\n\n");
    }


    public void reset() {
        //Reset attributes for next loop and incriment currentTurn
        this.correct = false;
        this.guessedLetters.clear();
        this.currentTries = this.totalTries;
        this.currentTurn++;
    }


    public void displayEndgame() {
        String winPhrase = "";

        System.out.println("GAME OVER");

        if (this.netWorth >= 1000 || this.winCar || this.winVacation) {
            winPhrase = "\nGREAT JOB! You won $" + Integer.toString(this.netWorth) + "!!!";
        }
        else {
            winPhrase = "\nUNFORTUNATE. You only won $" + Integer.toString(this.netWorth) + ". Better luck next time.";
        }
        //Alters win message depending on if car and/or vacation is won
        if (this.winCar && this.winVacation && this.netWorth == 0) {
            winPhrase = "\nGREAT JOB! You won a luxary vacation to " + this.vacation + " and a brand new " + this.car + "!!!";
        }
        else if (this.winCar && this.winVacation) {
            winPhrase = winPhrase.replace("!!!", ", a luxary vacation to " + this.vacation + ", and a brand new " + this.car + "!!!");
        }
        else if (this.winCar && this.netWorth == 0) {
            winPhrase = "\nGREAT JOB! You won a brand new " + this.car + "!!!";
        }
        else if (this.winCar) {
            winPhrase = winPhrase.replace("!!!", " and a brand new " + this.car + "!!!");
        }
        else if (this.winVacation && this.netWorth == 0) {
            winPhrase = "\nGREAT JOB! You won a luxary vacation to " + this.vacation + "!!!";
        }
        else if (this.winVacation) {
            winPhrase = winPhrase.replace("!!!", " and a luxary vacation to " + this.vacation + "!!!");
        }
        else if (this.netWorth == 0) {
            winPhrase = "\nUNFORTUNATE. You won nothing. Better luck next time.";
        }
        System.out.println(winPhrase + "\n");
    }
}