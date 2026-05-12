package edu.unc.comp301.model.pieces;

public class Hero extends APiece implements MovablePiece {
  public Hero() {
    super("Hero", "src/main/java/edu/unc/comp301/images/Hero.png");
  }

  public CollisionResult collide(Piece other) {
    CollisionResult result;

    if (other instanceof Wall) {
      throw new IllegalArgumentException();
    }

    if (other instanceof Treasure) {
      Treasure treasure = (Treasure) other;
      int points = treasure.getValue();
      result = new CollisionResult(points, CollisionResult.Result.CONTINUE);
    } else if (other instanceof Trap) {
      Trap trap = (Trap) other;
      result = new CollisionResult(trap.getPenalty(), CollisionResult.Result.CONTINUE);
    } else if (other instanceof Enemy) {
      result = new CollisionResult(0, CollisionResult.Result.GAME_OVER);
    } else if (other instanceof Exit) {
      result = new CollisionResult(0, CollisionResult.Result.NEXT_LEVEL);
    } else if (other == null) {
      result = new CollisionResult(0,  CollisionResult.Result.CONTINUE);
    } else {
      throw new IllegalArgumentException();
    }

    return result;
  }
}
