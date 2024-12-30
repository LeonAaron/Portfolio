import java.util.Random;
import java.util.Scanner;

public class MinesweeperBoard {

    protected int boardLength;
    protected int bombCount;

    //Minesweeper boards
    protected String[][] originalBoard;
    protected String[][] hiddenBoard;
    protected boolean[][] revealedBoard;

    //Functionality
    Scanner sc = new Scanner(System.in);
    Random random = new Random();


    public MinesweeperBoard(int boardLength, int bombCount) {
        this.boardLength = boardLength;
        this.bombCount = bombCount;

        //Initializes minesweeper boards with correct dimensions and "-" in blank spaces
        this.originalBoard = new String[boardLength][boardLength];
        this.hiddenBoard = new String[boardLength][boardLength];
        this.revealedBoard = new boolean[boardLength][boardLength];

        //Fills array with respective elements
        this.originalBoard = fillWithDashes(this.originalBoard);
        this.hiddenBoard = fillWithDashes(this.hiddenBoard);
        this.revealedBoard = fillWithBoolean(this.revealedBoard);
    }

    public void constructBoard(int boardLength, int bombCount) {
        //update result after setup steps
        this.originalBoard = placeBombs(this.originalBoard, boardLength, bombCount);
        this.originalBoard = initializeValues(this.originalBoard);

    }

    public void displayBoard(String[][] board) {
        //Prints out column labels
        System.out.print("\n   ");
        for (int i = 0; i < this.boardLength; i++) {
            System.out.print(i + " ");
        }

        for (int i = 0; i < this.boardLength; i++) {
            //Print out row labels and skip line
            System.out.print("\n" + i + "  ");
            for (int j = 0; j < this.boardLength; j++) {
                //Print out dividers between values
                if (j != this.boardLength - 1) {
                    System.out.print(board[i][j] + "|");
                }
                else {
                    System.out.print(board[i][j]);
                }
            }
        }
    }

    //For revealed board testing
    public void displayBoard(boolean[][] board) {
        //Prints out column labels
        System.out.print("\n   ");
        for (int i = 0; i < this.boardLength; i++) {
            System.out.print(i + " ");
        }

        for (int i = 0; i < this.boardLength; i++) {
            //Print out row labels and skip line
            System.out.print("\n" + i + "  ");
            for (int j = 0; j < this.boardLength; j++) {
                //Print out dividers between values
                if (j != this.boardLength - 1) {
                    System.out.print(board[i][j] + "|");
                }
                else {
                    System.out.print(board[i][j]);
                }
            }
        }
    }


    public String[][] placeBombs(String[][] board, int boardLength, int bombCount) {
        //Final matrix with randomly placed bombs
        int randomRow = 0;
        int randomColumn = 0;
        int currentBombCount = 0;

        while (currentBombCount < bombCount) {
            randomRow = random.nextInt(boardLength);
            randomColumn = random.nextInt(boardLength);

            //If bomb not in random postition, place bomb and incrememt currentBombCount
            if (board[randomRow][randomColumn] != "B") {
                board[randomRow][randomColumn] = "B";
                currentBombCount++;
            }
        }

        return board;
    }


    public String[][] initializeValues(String[][] board) {
        int neighborBombCount = 0;

        for (int i = 0; i < this.boardLength; i++) {
            for (int j = 0; j < this.boardLength; j++) {
                if (board[i][j] != "B") {
                    neighborBombCount = neighboringBombCount(board, i, j);
                    board[i][j] = Integer.toString(neighborBombCount);
                }
            }
        }
        
        return board;
    }


    public int neighboringBombCount(String[][] board, int row, int column) {
        int neighborBombCount = 0;

        //If current value not on right side, check if value to the right is a bomb
        if (column != this.boardLength - 1) {
            if (checkNeighborCell(board, row, column, "R").equals("B")) {
                neighborBombCount++;
            }
        } 
        //Same process for all directions, checks if postition is a boundry
        if (column != this.boardLength - 1 && row != this.boardLength - 1) {
            if (checkNeighborCell(board, row, column, "BR").equals("B")) {
                neighborBombCount++;
            }
        } 
        if (row != this.boardLength - 1) {
            if (checkNeighborCell(board, row, column, "B").equals("B")) {
                neighborBombCount++;
            }
        }
        if (row != this.boardLength - 1 && column != 0) {
            if (checkNeighborCell(board, row, column, "BL").equals("B")) {
                neighborBombCount++;
            }
        }
        if (column != 0) {
            if (checkNeighborCell(board, row, column, "L").equals("B")) {
                neighborBombCount++;
            }
        }
        if (row != 0 && column != 0) {
            if (checkNeighborCell(board, row, column, "TL").equals("B")) {
                neighborBombCount++;
            }
        } 
        if (row != 0) {
            if (checkNeighborCell(board, row, column, "T").equals("B")) {
                neighborBombCount++;
            }
        }
        if (row != 0 && column != this.boardLength - 1) {
            if (checkNeighborCell(board, row, column, "TR").equals("B")) {
                neighborBombCount++;
            }
        }

        return neighborBombCount;
    }
    


    public String checkNeighborCell(String board[][], int row, int column, String direction) {
        String result = " ";

        if (direction.equals("R")) {
            result = board[row][column + 1];
            
        } else if (direction.equals("BR")) {
            result = board[row + 1][column + 1];
        } else if (direction.equals("B")) {
            result = board[row + 1][column];
        } else if (direction.equals("BL")) {
            result = board[row + 1][column - 1];
        } else if (direction.equals("L")) {
            result = board[row][column - 1];
        } else if (direction.equals("TL")) {
            result = board[row - 1][column - 1];
        } else if (direction.equals("T")) {
            result = board[row - 1][column];
        } else if (direction.equals("TR")) {
            result = board[row - 1][column + 1];
        }

        return result;
    }


    public boolean checkNeighborCell(boolean board[][], int row, int column, String direction) {
        boolean result = false;

        if (direction.equals("R")) {
            result = board[row][column + 1];
        } else if (direction.equals("BR")) {
            result = board[row + 1][column + 1];
        } else if (direction.equals("B")) {
            result = board[row + 1][column];
        } else if (direction.equals("BL")) {
            result = board[row + 1][column - 1];
        } else if (direction.equals("L")) {
            result = board[row][column - 1];
        } else if (direction.equals("TL")) {
            result = board[row - 1][column - 1];
        } else if (direction.equals("T")) {
            result = board[row - 1][column];
        } else if (direction.equals("TR")) {
            result = board[row - 1][column + 1];
        }

        return result;
    }


    public String[][] fillWithDashes(String[][] board) {
        //Fills entire game board with dashes
        for (int i = 0; i < this.boardLength; i++) {
            for (int j = 0; j < this.boardLength; j++) {
                board[i][j] = "-";
            }
        } 
        return board;
    }

    
    public boolean[][] fillWithBoolean(boolean[][] revealedBoard) {
        //Fills entire game board with false
        for (int i = 0; i < this.boardLength; i++) {
            for (int j = 0; j < this.boardLength; j++) {
                revealedBoard[i][j] = false;
            }
        } 
        return revealedBoard;
    }
}
