package edu.unc.comp301.model;

import edu.unc.comp301.model.board.Board;
import edu.unc.comp301.model.board.BoardImpl;
import edu.unc.comp301.model.board.Posn;
import edu.unc.comp301.model.pieces.CollisionResult;
import edu.unc.comp301.model.pieces.Piece;
import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {
  private Board board;
  private int curScore;
  private int highScore;
  private STATUS status;
  private int level;
  private List<Observer> observers = new ArrayList<>();

  public ModelImpl(int width, int height) {
    this.board = new BoardImpl(width, height);

    this.curScore = 0;
    this.highScore = 0;
    this.status = STATUS.END_GAME;
    this.level = 0;
    notifyObservers();
  }

  public ModelImpl(Board board) {
    this.board = board;

    this.curScore = 0;
    this.highScore = 0;
    this.status = STATUS.END_GAME;
    this.level = 0;
    notifyObservers();
  }

  @Override
  public int getWidth() {
    return board.getWidth();
  }

  @Override
  public int getHeight() {
    return board.getHeight();
  }

  @Override
  public Piece get(Posn p) {
    return board.get(p);
  }

  @Override
  public int getCurScore() {
    return curScore;
  }

  @Override
  public int getHighScore() {
    return highScore;
  }

  @Override
  public int getLevel() {
    return level;
  }

  @Override
  public STATUS getStatus() {
    return status;
  }

  @Override
  public void startGame() {
    this.status = STATUS.IN_PROGRESS;
    this.curScore = 0;
    this.level = 1;

    int enemies = level + 1;
    int treasures = 2;
    int walls = level + 1;
    this.board.init(enemies, treasures, walls);

    notifyObservers();

    // TODO reinitialize board when hero touches exit, and incriment level in while loop
  }

  @Override
  public void endGame() {
    this.status = STATUS.END_GAME;
    if (curScore > highScore) {
      highScore = curScore;
    }
    notifyObservers();
  }

  public void reinitBoard() {
    int enemies = level + 1;
    int treasures = 2;
    int walls = level + 1;
    this.board.init(enemies, treasures, walls);
    notifyObservers();
  }

  @Override
  public void moveUp() {
    CollisionResult res = board.moveHero(-1, 0);

    if (res.getPoints() != 0) {
      curScore += res.getPoints();
    }

    if (res.getResults() == CollisionResult.Result.NEXT_LEVEL) {
      this.level += 1;
      reinitBoard();
    } else if (res.getResults() == CollisionResult.Result.GAME_OVER) {
      endGame();
    }
    notifyObservers();
  }

  @Override
  public void moveDown() {
    CollisionResult res = board.moveHero(1, 0);

    if (res.getPoints() != 0) {
      curScore += res.getPoints();
    }

    if (res.getResults() == CollisionResult.Result.NEXT_LEVEL) {
      this.level += 1;
      reinitBoard();
    } else if (res.getResults() == CollisionResult.Result.GAME_OVER) {
      endGame();
    }
    notifyObservers();
  }

  @Override
  public void moveLeft() {
    CollisionResult res = board.moveHero(0, -1);

    if (res.getPoints() != 0) {
      curScore += res.getPoints();
    }

    if (res.getResults() == CollisionResult.Result.NEXT_LEVEL) {
      this.level += 1;
      reinitBoard();
    } else if (res.getResults() == CollisionResult.Result.GAME_OVER) {
      endGame();
    }
    notifyObservers();
  }

  @Override
  public void moveRight() {
    CollisionResult res = board.moveHero(0, 1);
    if (res.getPoints() != 0) {
      curScore += res.getPoints();
    }
    if (res.getResults() == CollisionResult.Result.NEXT_LEVEL) {
      this.level += 1;
      reinitBoard();
    } else if (res.getResults() == CollisionResult.Result.GAME_OVER) {
      endGame();
    }
    notifyObservers();
  }

  @Override
  public void setHardMode(boolean hard) {
    board.setHardMode(hard);
  }

  @Override
  public void addObserver(Observer o) {
    observers.add(o);
  }

  private void notifyObservers() {
    for (Observer o : observers) {
      o.update();
    }
  }
}
