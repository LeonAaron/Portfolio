package edu.unc.comp301.model.pieces;

public class Trap extends APiece {

  public Trap() {
    super("Trap", "src/main/java/edu/unc/comp301/images/Trap.png");
  }

  public int getPenalty() {
    return -200;
  }
}
