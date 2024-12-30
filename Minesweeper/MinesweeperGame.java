import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MinesweeperGame {
    //Board object
    protected MinesweeperBoard board;

    //Is game win or loss
    protected boolean isWin = false;
    protected boolean isLoss = false;

    //Game Boards
    protected String[][] originalBoard;
    protected String[][] hiddenBoard;
    protected boolean[][] revealedBoard;

    //Functionality
    Scanner sc = new Scanner(System.in);
    Random random = new Random();

    //User input
    protected int currentRow = 0;
    protected int currentColumn = 0;

    protected int boardLength;
    protected String[] validUserInput;

    //Whether you user will flag this turn
    protected boolean flag = false;

    //Number of bombs
    protected int bombCount;
    protected int currentBombCount;

    protected String currentTile = "";

    //Whether the player wants to go for another round
    protected boolean playAgain = false;

    protected static int totalWins = 0;


    public MinesweeperGame(int boardLength, int bombCount) {
        //Construct board at object initialization
        this.board = new MinesweeperBoard(boardLength, bombCount);

        //Initialize boards
        this.board.constructBoard(boardLength, bombCount);
        this.originalBoard = this.board.originalBoard;
        this.hiddenBoard = this.board.hiddenBoard;
        this.boardLength = this.board.boardLength;
        this.revealedBoard = this.board.revealedBoard;
        this.validUserInput = validInput();
        this.bombCount = bombCount;
        this.currentBombCount = bombCount;
        this.originalBoard = this.board.fillWithDashes(this.originalBoard);
    
    }


    public void playGame() {
        displayInstructions();
        //First user guess
        firstUserInput();

        //Game loop, break out when isWin or isLoss
        while (this.isWin == false && this.isLoss == false) {
            //Reset this.flag
            this.flag = false;
            this.board.displayBoard(this.hiddenBoard);
            getUserInput();

            //Executes if user wants to flag potential bomb
            if (this.flag == true) {
                placeFlag(this.currentRow, this.currentColumn);
            }

            getCurrentTile();

            if (this.currentTile.equals("0")) {
                chainReaction(this.currentRow, this.currentColumn);
            }
            //checks if loss, dimisses flags
            else if (this.currentTile.equals("B") && this.flag == false) {
                revealTile(this.currentRow, this.currentColumn);
                this.isLoss = true;
            }
            else if (this.flag == false) {
                revealTile(this.currentRow, this.currentColumn);
            }
            
            if (this.isLoss == false) {
                checkWin();
            }
            
        }

        if (this.isWin) {
           winMessage();
        }
        else if (this.isLoss) {
            lossMessage();
        }

        //Prompt user to play again
        playAgain();

        if (this.playAgain) {
            //Create new game
            MinesweeperGame game = new MinesweeperGame(this.boardLength, this.bombCount);
            game.playGame();
        }
    }


    public void revealTile(int row, int column) {
        //Reveals tiles at specific postition
        this.hiddenBoard[row][column] = this.originalBoard[row][column];
        this.revealedBoard[row][column] = true;
    }

    //Recursive function
    public boolean chainReaction(int row, int column) {
        //Reveal current tile 
        revealTile(row, column);

        if (this.originalBoard[row][column].equals("0") == false) {
            return true;
        }

        //If current value not on right side, check if value to the right is a 0
        if (column != this.boardLength - 1) {
            if (this.board.checkNeighborCell(originalBoard, row, column, "R").equals("0")) {
                if (this.board.checkNeighborCell(revealedBoard, row, column, "R") == false) {
                    //Use recursion to uncover all adjecent 0 tiles
                    chainReaction(row, column + 1);
                }
            } else {
                revealTile(row, column + 1);
            }
        }  
        if (row != this.boardLength - 1) {
            if (this.board.checkNeighborCell(originalBoard, row, column, "B").equals("0")) {
                if (this.board.checkNeighborCell(revealedBoard, row, column, "B") == false) {
                    //Use recursion to uncover all adjecent 0 tiles
                    chainReaction(row + 1, column);
                }
            } else {
                //If not 0, reveal anyway
                revealTile(row + 1, column);
            }
        }
        if (column != 0) {
            if (this.board.checkNeighborCell(originalBoard, row, column, "L").equals("0")) {
                if (this.board.checkNeighborCell(revealedBoard, row, column, "L") == false) {
                    chainReaction(row, column - 1);
                }
             } else {
                revealTile(row, column - 1);
             }
        } 
        if (row != 0) {
            if (this.board.checkNeighborCell(originalBoard, row, column, "T").equals("0")) {
                if (this.board.checkNeighborCell(revealedBoard, row, column, "T") == false) {
                    chainReaction(row - 1, column);
                }
            } else {
                revealTile(row - 1, column);
            }
        }

        //Checks diagonals
        if (column != this.boardLength - 1 && row != this.boardLength - 1) {
            if (this.board.checkNeighborCell(originalBoard, row, column, "BR").equals("0")) {
                if (this.board.checkNeighborCell(revealedBoard, row, column, "BR") == false) {
                    //Use recursion to uncover all adjecent 0 tiles
                    chainReaction(row + 1, column + 1);
                }
            } else {
                revealTile(row + 1, column + 1);
            }
        }  
        if (row != this.boardLength - 1 && column != 0) {
            if (this.board.checkNeighborCell(originalBoard, row, column, "BL").equals("0")) {
                if (this.board.checkNeighborCell(revealedBoard, row, column, "BL") == false) {
                    //Use recursion to uncover all adjecent 0 tiles
                    chainReaction(row + 1, column - 1);
                }
            } else {
                //If not 0, reveal anyway
                revealTile(row + 1, column - 1);
            }
        }
        if (column != 0 && row != 0) {
            if (this.board.checkNeighborCell(originalBoard, row, column, "TL").equals("0")) {
                if (this.board.checkNeighborCell(revealedBoard, row, column, "TL") == false) {
                    chainReaction(row - 1, column - 1);
                }
             } else {
                revealTile(row - 1, column - 1);
             }
        } 
        if (row != 0 && column != this.boardLength - 1) {
            if (this.board.checkNeighborCell(originalBoard, row, column, "TR").equals("0")) {
                if (this.board.checkNeighborCell(revealedBoard, row, column, "TR") == false) {
                    chainReaction(row - 1, column + 1);
                }
            } else {
                revealTile(row - 1, column + 1);
            }
        }
    return true;
    }


    public String[] validInput() {
        //Determine valid user input for rows and columns
        String[] result = new String[this.boardLength];

        for (int i = 0; i < this.boardLength; i++) {
            //Convert i to string
            result[i] = Integer.toString(i);
        }

        return result;
    }

    public void getUserInput() {
        //convert user input to integer
        do {
            this.currentRow = Integer.parseInt(getUserRow());
            this.currentColumn = Integer.parseInt(getUserColumn());
            //Invalid if input not on '-', unless input is on 'F'
            if (this.hiddenBoard[this.currentRow][this.currentColumn].equals("-") == false && this.hiddenBoard[this.currentRow][this.currentColumn].equals("F") == false) {
                System.out.println("\nINVALID POSTITON. TRY AGAIN");
            }
        //Loops until postition is new or will overtake flag
        } while (this.hiddenBoard[this.currentRow][this.currentColumn].equals("-") == false && this.hiddenBoard[this.currentRow][this.currentColumn].equals("F") == false);
    }


    public String getUserRow() {
        String inputRow = "";

        do {
            System.out.print("\nEnter Row or type \"Flag\": ");
            inputRow = sc.nextLine();
            if (inputRow.equalsIgnoreCase("flag") || inputRow.equalsIgnoreCase("F")) {
                this.flag = true;
                inputRow = flagGetUserRow("");
            }
            //Checks if input within boundries of board
            if (Arrays.asList(this.validUserInput).contains(inputRow) == false) {
                System.out.println("INVALID INPUT");
            }
        //Loops until unput is in validUserInput array
        } while (Arrays.asList(this.validUserInput).contains(inputRow) == false);

        return inputRow;
    }


    public String flagGetUserRow(String inputRow) {
        do {
            System.out.print("\nEnter Row: ");
            inputRow = sc.nextLine();
            
            if (Arrays.asList(this.validUserInput).contains(inputRow) == false) {
                System.out.println("INVALID INPUT");
            }
        //Loops until unput is in validUserInput array
        } while (Arrays.asList(this.validUserInput).contains(inputRow) == false);

        return inputRow;
    }


    public String getUserColumn() {
        String inputColumn = "";

        do {
            System.out.print("Enter Column: ");
            inputColumn = sc.nextLine();

            if (Arrays.asList(this.validUserInput).contains(inputColumn) == false) {
                System.out.println("INVALID INPUT");
            }
        //Loops until unput is in validUserInput array
        } while (Arrays.asList(this.validUserInput).contains(inputColumn) == false);

        return inputColumn;
    }


    public void firstUserInput() {
        this.board.displayBoard(this.hiddenBoard);
        this.currentRow = Integer.parseInt(flagGetUserRow(""));
        this.currentColumn = Integer.parseInt(getUserColumn());

        replaceBombs(this.originalBoard, this.boardLength, this.bombCount);

        //Ensures player doesnt land on bomb on first turn
        if (this.originalBoard[this.currentRow][this.currentColumn].equals("B")) {
            //Placeholder
            this.originalBoard[this.currentRow][this.currentColumn] = "0";
            //Reevaluates board
            this.originalBoard = board.initializeValues(this.originalBoard);
        }
        else if (this.originalBoard[this.currentRow][this.currentColumn].equals("0")) {
            chainReaction(this.currentRow, this.currentColumn);
        }
        revealTile(this.currentRow, this.currentColumn);
    }


    public void placeFlag(int row, int column) {
        this.hiddenBoard[row][column] = "F";
    }


    public void getCurrentTile() {
        this.currentTile = this.originalBoard[this.currentRow][this.currentColumn];
    }

    public void checkWin() {
        boolean win = true;
        //Win if all tiles that aren't bombs are uncovered
        for (int i = 0; i < this.boardLength; i++) {
            for (int j = 0; j < this.boardLength; j++) {
                if (this.originalBoard[i][j].equals("B") == false)
                    if (this.hiddenBoard[i][j].equals("-") == true) {
                        win = false;
                    }
            }
        } 

        this.isWin = win;
    }

    
    public void winMessage() {
        //Run when won
        MinesweeperGame.totalWins += 1;
        board.displayBoard(this.hiddenBoard);
        System.out.println("\nCongtratulations! You beat minesweeper!");
        System.out.println("\nTotal Wins: " + MinesweeperGame.totalWins + "\n");
    }


    public void lossMessage() {
        //Run when lost
        board.displayBoard(this.hiddenBoard);
        System.out.println("\nGAME OVER");
        System.out.println("\nTotal Wins: " + MinesweeperGame.totalWins);
    }


    public void playAgain() {
        String input = "";

        System.out.print("Would you like to play again (Y/N)? ");
        input = sc.nextLine();

        if (input.equalsIgnoreCase("Yes") || input.equalsIgnoreCase("Y")) {
            this.playAgain = true;
        }
    }


    public void displayInstructions() {
        System.out.println("\n====WELCOME TO MINESWEEPER====");
        System.out.println("\nSelect a row and column each turn to reveal the tile");
        System.out.println("\nIf you select a bomb tile, it is game over");
        System.out.println("\nTile numbers represent the number of surrounding bombs");
        System.out.println("\nFlag spaces that you believe to be bombs");
        System.out.println("\nGAME START");
    }


    public void reconstructBoard(int boardLength, int bombCount) {
        //update result after setup steps
        this.originalBoard = replaceBombs(this.originalBoard, this.boardLength, this.bombCount);
        this.originalBoard = board.initializeValues(this.originalBoard);

    }


    public String[][] replaceBombs(String[][] board, int boardLength, int bombCount) {
        //Allows for user to always click on '0' tile
        int randomRow = 0;
        int randomColumn = 0;
        int currentBombCount = 0;

        while (currentBombCount < bombCount) {
            randomRow = random.nextInt(boardLength);
            randomColumn = random.nextInt(boardLength);

            //If bomb in valid postition, place bomb and incrememt currentBombCount
            if (board[randomRow][randomColumn] != "B" && isNextToFirst(randomRow, randomColumn) == false) {
                board[randomRow][randomColumn] = "B";
                currentBombCount++;
            }
        }
        this.board.initializeValues(board);
        return board;
    }


    public boolean isNextToFirst(int row, int column) {
        //Returns true if postitoin is next to the starting postiton
        if (this.currentRow == row - 1 && this.currentColumn == column) {
            return true;
        }
        if (this.currentRow == row + 1 && this.currentColumn == column) {
            return true;
        }
        if (this.currentRow == row && this.currentColumn == column + 1) {
            return true;
        }
        if (this.currentRow == row && this.currentColumn == column - 1) {
            return true;
        }
        if (this.currentRow == row + 1 && this.currentColumn == column - 1) {
            return true;
        }
        if (this.currentRow == row - 1 && this.currentColumn == column - 1) {
            return true;
        }
        if (this.currentRow == row + 1 && this.currentColumn == column + 1) {
            return true;
        }
        if (this.currentRow == row - 1 && this.currentColumn == column + 1) {
            return true;
        }
        return false;
    }
}
