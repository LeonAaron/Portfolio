# Minesweeper Game

## Overview
Minesweeper is a classic puzzle game that I recreated in Java. The player interacts with a grid, trying to uncover cells that don't contain randomly placed bombs, while avoiding the ones that do. The number of a tile represents the number of bombs surrounding that tile. The game is built using Object-Oriented Programming principles and allows for different board sizes and bomb counts. Players will need to uncover all safe cells without triggering a bomb to win the game.

## Features
- Customizable board size and bomb count
- Random bomb placement to make each game unique
- A revealed grid to keep track of discovered safe spaces
- Recursive algorithm used to uncover all connected safe tiles on first move
- Option to reset the board and play multiple rounds while keeping track of total victories
  
## Requirements
To run this game, you'll need:
- **Java 8 or higher** installed on your computer.
- A suitable IDE like **Eclipse** or **IntelliJ IDEA.**
  
## Installation Instructions

### 1. Clone the repository:

To get started, clone the repository using the following command:

```bash 
    git clone https://github.com/LeonAaron/Portfolio.git
 ```

This will clone my entire portfolio repository to your computer.

### 2. Navigate to the project folder:

After cloning the repository, navigate to the Minesweeper folder.

### 3. Open in your IDE:

You can now open this project in any Java-compatible IDE (such as IntelliJ IDEA or Eclipse) to start working with or running the game.

### How to Run the Program
- Open the project in your IDE.
- Locate **Main.java** in the file directory.
- Run the Main class. This will start the game.
- The game will initialize with a predetermined grid size and bomb count, and you can start making moves.

### Game Settings
The game settings can be modified in Main.java

**Board Size:** Modify the boardLength to change the grid dimensions (e.g., 8x8, 10x10).  
**Bomb Count:** Modify the bombCount to determine how many bombs are randomly placed on the board.  

**Example Setup**
```java
int boardLength = 8;
int bombCount = 10;
MinesweeperGame game = new MinesweeperGame(boardLength, bombCount);
game.playGame();
```
- **boardLength:** Defines the size of the board (e.g., 8 for an 8x8 grid).
- **bombCount:** Sets the number of bombs to be placed on the board.
- **game.playGame();** Runs the game.
  
### Example Gameplay   

- Board size: 8x8
- Bomb count: 10
- Initial Display:  
. 0 1 2 3 4 5 6 7  
0: - - - - - - - -  
1: - - - - - - - -  
2: - - - - - - - -  
3: - - - - - - - -  
4: - - - - - - - -  
5: - - - - - - - -  
6: - - - - - - - -  
7: - - - - - - - -  
- First User Move (ROW, COLUMN): (5, 3)
- Display:  
.  0 1 2 3 4 5 6 7  
0:  - - - - - - - -  
1:  - - - - - - - -  
2:  - - 1 1 2 - - -  
3:  1 1 1 0 1 - - -  
4:  0 0 0 0 2 - - -  
5:  0 0 **0** 0 2 - - -  
6:  1 1 0 0 2 - - -  
7:  - 1 0 0 1 - - -  
- Safe User Move (ROW, COLUMN): (0, 0)
- Display:  
.  0 1 2 3 4 5 6 7  
0:  **1** - - - - - - -  
1:  - - - - - - - -  
2:  - - 1 1 2 - - -  
3:  1 1 1 0 1 - - -  
4:  0 0 0 0 2 - - -  
5:  0 0 0 0 2 - - -  
6:  1 1 0 0 2 - - -  
7:  - 1 0 0 1 - - -  
- Safe User Move (ROW, COLUMN): (3, 5)
- Display:  
.  0 1 2 3 4 5 6 7  
0:  1 - - - - - - -  
1:  - - - - - - - -  
2:  - - 1 1 2 - - -  
3:  1 1 1 0 1 **3** - -  
4:  0 0 0 0 2 - - -  
5:  0 0 0 0 2 - - -  
6:  1 1 0 0 2 - - -  
7:  - 1 0 0 1 - - -  
- Unsafe User Move (ROW, COLUMN): (2, 5)
- Display:  
.  0 1 2 3 4 5 6 7  
0:  1 - - - - - - -  
1:  - - - - - - - -  
2:  - - 1 1 2 **B** - -  
3:  1 1 1 0 1 3 - -  
4:  0 0 0 0 2 - - -  
5:  0 0 0 0 2 - - -  
6:  1 1 0 0 2 - - -  
7:  - 1 0 0 1 - - -  
- Display: Game Over!

## The files of the Minesweeper project are:

- **Main.java:** Main file where the game is initialized and played.
- **MinesweeperBoard.java:** Defines the structure for the Minesweeper board and handles bomb placement.
- **MinesweeperGame.java:** Handles the game logic, including starting the game, processing moves, and displaying the board state.
  
## How the Game Works

- **Game Setup:** The game initializes a Minesweeper board with random bomb placement. The player is prompted to select cells on the board by their row and column, trying to uncover non-bomb cells.
- **Random Bomb Placement:** Bombs are placed randomly on the board using the placeBombs() function, and the neighboring cells are updated to reflect the number of adjacent bombs.
- **Player Interaction:** The player interacts by selecting cells. The game reveals the contents of the selected cell, either showing the number of adjacent bombs or triggering a game over if a bomb is selected.
- **Feedback:** The game ends when all non-bomb cells are uncovered or if a bomb is triggered.
  
## If you would like to customize the game, you can:

**Adjust the board size and bomb count:** Modify the parameters in the Main.java class to adjust the difficulty of the game.  

For example:  
```java
public class Main {
    public static void main(String[] args) {
        int boardLength = 10;  // 10x10 grid
        int bombCount = 20;    // 20 bombs
        MinesweeperGame game = new MinesweeperGame(boardLength, bombCount);
        game.playGame();
    }
}
```


**Created By Leon Aaron**
