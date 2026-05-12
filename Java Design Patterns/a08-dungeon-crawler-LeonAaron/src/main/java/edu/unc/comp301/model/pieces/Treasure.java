package edu.unc.comp301.model.pieces;

public class Treasure extends APiece {

  public Treasure() {
    super("Treasure", "src/main/java/edu/unc/comp301/images/Treasure.png");
  }

  public int getValue() {
    return 100;
  }
}
