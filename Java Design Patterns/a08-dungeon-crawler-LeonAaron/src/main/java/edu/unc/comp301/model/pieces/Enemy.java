package edu.unc.comp301.model.pieces;

public class Enemy extends APiece implements MovablePiece {

  public Enemy() {
    super("Enemy", "src/main/java/edu/unc/comp301/images/Enemy.png");
  }

  public CollisionResult collide(Piece other) {
    CollisionResult result;

    if (other instanceof Wall || other instanceof Exit) {
      throw new IllegalArgumentException();
    }

    if (other instanceof Treasure) {
      result = new CollisionResult(0, CollisionResult.Result.CONTINUE);
      // TODO TREASURE REMOVAL
    } else if (other instanceof Trap) {
      result = new CollisionResult(0, CollisionResult.Result.CONTINUE);
    } else if (other instanceof Hero) {
      result = new CollisionResult(0, CollisionResult.Result.GAME_OVER);
    } else if (other == null) {
      result = new CollisionResult(0, CollisionResult.Result.CONTINUE);
    } else {
      throw new IllegalArgumentException();
    }

    return result;
  }
}

// TODO MAKE SURE ENEMY/hero CANT COLLIDE WITH WALLS
// TODO make sure enemy cant collide with exit
