package edu.unc.comp301.model.board;

import edu.unc.comp301.model.pieces.*;
import java.util.ArrayList;
import java.util.Random;

public class BoardImpl implements Board {
  private Piece[][] board;
  private int width;
  private int height;

  private Hero hero = new Hero();
  private Exit exit;
  private Random random = new Random();

  private ArrayList<Enemy> enemiesList = new ArrayList<>();
  private boolean hardMode = false;

  // TODO MAYBE ADD ENEMY TREASURE AND WALL COUNTERS

  public BoardImpl(int width, int height) {
    this.width = width;
    this.height = height;
    // TODO CHECK IF OTHER WAY AROUND
    this.board = new Piece[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        this.board[i][j] = null;
      }
    }

    this.exit = new Exit();
  }

  public BoardImpl(Piece[][] board) {
    this.board = board;
    this.width = board[0].length;
    this.height = board.length;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (board[i][j] instanceof Hero) {
          this.hero = (Hero) board[i][j];
        } else if (board[i][j] instanceof Enemy) {
          this.enemiesList.add((Enemy) board[i][j]);
        }
      }
    }

    this.exit = new Exit();
  }

  @Override
  public void init(int enemies, int treasures, int walls) {
    int totalSquares = width * height;

    if (enemies + treasures + walls > totalSquares - 2) {
      throw new IllegalArgumentException();
    }
    // Clear Board
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        this.board[i][j] = null;
      }
    }

    this.enemiesList.clear();

    int randRow = random.nextInt(height);
    int randCol = random.nextInt(width);
    Posn randPosn = new Posn(randRow, randCol);

    this.set(hero, randPosn);
    hero.setPosn(randPosn);
    if (hero.getPosn() == null) {
      throw new RuntimeException("Hero position is null");
    }

    while (true) {
      randRow = random.nextInt(height);
      randCol = random.nextInt(width);
      randPosn = new Posn(randRow, randCol);
      if (this.get(randPosn) != null) {
        continue;
      }
      this.set(exit, randPosn);
      exit.setPosn(randPosn);
      break;
    }

    while (enemies > 0) {
      randRow = random.nextInt(height);
      randCol = random.nextInt(width);

      randPosn = new Posn(randRow, randCol);

      if (this.get(randPosn) != null) {
        continue;
      }

      Enemy enemy = new Enemy();
      this.set(enemy, randPosn);
      enemy.setPosn(randPosn);
      this.enemiesList.add(enemy);

      enemies--;
    }

    while (treasures > 0) {
      randRow = random.nextInt(height);
      randCol = random.nextInt(width);

      randPosn = new Posn(randRow, randCol);

      if (this.get(randPosn) != null) {
        continue;
      }
      // TODO POSSIBLY STORE IN ARRAY LIST
      Treasure treasure = new Treasure();
      this.set(treasure, randPosn);
      treasure.setPosn(randPosn);

      treasures--;
    }

    while (walls > 0) {
      randRow = random.nextInt(height);
      randCol = random.nextInt(width);

      randPosn = new Posn(randRow, randCol);

      if (this.get(randPosn) != null) {
        continue;
      }
      Wall wall = new Wall();
      this.set(wall, randPosn);
      wall.setPosn(randPosn);

      walls--;
    }

    // Place exactly one Trap per game
    while (true) {
      randRow = random.nextInt(height);
      randCol = random.nextInt(width);
      randPosn = new Posn(randRow, randCol);
      if (this.get(randPosn) != null) {
        continue;
      }
      Trap trap = new Trap();
      this.set(trap, randPosn);
      trap.setPosn(randPosn);
      break;
    }
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public Piece get(Posn posn) {
    return board[posn.getRow()][posn.getCol()];
  }

  @Override
  public void set(Piece p, Posn newPos) {
    board[newPos.getRow()][newPos.getCol()] = p;
    if (p != null) {
      p.setPosn(newPos);
    }
  }

  @Override
  public CollisionResult moveHero(int drow, int dcol) {
    // UP- (-1,0) DOWN-(1,0) RIGHT-(0,1) LEFT- (0,-1)
    CollisionResult result;
    Posn currHeroPosn = hero.getPosn();

    boolean cond3 = !(drow == 0 && dcol == -1);
    if (!(drow == 1 && dcol == 0)
        && !(drow == -1 && dcol == 0)
        && !(drow == 0 && dcol == 1)
        && cond3) {
      throw new IllegalArgumentException("Invalid hero move combo");
    }

    // UP
    if (drow == -1) {
      if (currHeroPosn.getRow() == 0) {
        return new CollisionResult(0, CollisionResult.Result.CONTINUE);
      }
      // DOWN
    } else if (drow == 1) {
      if (currHeroPosn.getRow() == height - 1) {
        return new CollisionResult(0, CollisionResult.Result.CONTINUE);
      }
      // LEFT
    } else if (dcol == -1) {
      if (currHeroPosn.getCol() == 0) {
        return new CollisionResult(0, CollisionResult.Result.CONTINUE);
      }
      // RIGHT
    } else if (dcol == 1) {
      if (currHeroPosn.getCol() == width - 1) {
        return new CollisionResult(0, CollisionResult.Result.CONTINUE);
      }
    }
    Posn newHeroPosn = new Posn(currHeroPosn.getRow() + drow, currHeroPosn.getCol() + dcol);
    Piece oldPiece = this.get(newHeroPosn);
    try {
      result = hero.collide(oldPiece);
    } catch (IllegalArgumentException e) {
      return new CollisionResult(0, CollisionResult.Result.CONTINUE);
    }

    set(hero, newHeroPosn);
    set(null, currHeroPosn);

    if (result.getResults() == CollisionResult.Result.NEXT_LEVEL) {
      return result;
    }

    // Enemy Movement
    ArrayList<String> options = new ArrayList<>();
    options.add("up");
    options.add("down");
    options.add("right");
    options.add("left");
    for (Enemy enemy : enemiesList) {
      Posn currPosn = enemy.getPosn();
      ArrayList<String> currOptions = new ArrayList<>(options);

      if (currPosn.getRow() == 0) {
        currOptions.remove("up");
      }
      if (currPosn.getRow() == height - 1) {
        currOptions.remove("down");
      }
      if (currPosn.getCol() == 0) {
        currOptions.remove("left");
      }
      if (currPosn.getCol() == width - 1) {
        currOptions.remove("right");
      }

      Posn newPosn = currPosn;
      while (!currOptions.isEmpty()) {
        String randDir =
            hardMode
                ? getBestDirection(currOptions, currPosn, hero.getPosn())
                : currOptions.get(random.nextInt(currOptions.size()));
        switch (randDir) {
          case ("up"):
            currOptions.remove("up");
            newPosn = new Posn(currPosn.getRow() - 1, currPosn.getCol());
            break;
          case ("down"):
            currOptions.remove("down");
            newPosn = new Posn(currPosn.getRow() + 1, currPosn.getCol());
            break;
          case ("right"):
            currOptions.remove("right");
            newPosn = new Posn(currPosn.getRow(), currPosn.getCol() + 1);
            break;
          case ("left"):
            currOptions.remove("left");
            newPosn = new Posn(currPosn.getRow(), currPosn.getCol() - 1);
            break;
          default:
            throw new IllegalArgumentException("Switch failed");
        }

        Piece other = this.get(newPosn);
        CollisionResult enemyCollisionResult = null;
        try {
          enemyCollisionResult = enemy.collide(other);
        } catch (IllegalArgumentException e) {
          continue;
        }

        if (enemyCollisionResult.getResults() == CollisionResult.Result.GAME_OVER) {
          this.set(null, currPosn);
          this.set(enemy, newPosn);

          if (result.getPoints() != 0) {
            return new CollisionResult(result.getPoints(), CollisionResult.Result.GAME_OVER);
          }
          return enemyCollisionResult;
        }
        break;
      }

      this.set(null, currPosn);
      this.set(enemy, newPosn);
    }

    return result;
  }

  @Override
  public void setHardMode(boolean hard) {
    this.hardMode = hard;
  }

  // Manhattan distance
  private String getBestDirection(ArrayList<String> options, Posn from, Posn target) {
    String best = options.get(0);
    int bestDist = Integer.MAX_VALUE;
    for (String dir : options) {
      int newRow = from.getRow(), newCol = from.getCol();
      switch (dir) {
        case "up":
          newRow--;
          break;
        case "down":
          newRow++;
          break;
        case "left":
          newCol--;
          break;
        case "right":
          newCol++;
          break;
      }
      int dist = Math.abs(newRow - target.getRow()) + Math.abs(newCol - target.getCol());
      if (dist < bestDist) {
        bestDist = dist;
        best = dir;
      }
    }
    return best;
  }

  // TODO POSSIBLY OPTIMIZE O(N)
  @Override
  public String toString() {
    String res = "";
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        res = res + board[i][j] + " ";
      }
      res += "\n";
    }
    return res;
  }
}
