package edu.unc.comp301;

public enum PowerType {
  NOVICE(1),
  ADEPT(2),
  JEDI(3);
  // Possible spelling error for autograder
  private int mulitiplier;

  PowerType(int mulitiplier) {
    this.mulitiplier = mulitiplier;
  }

  public int getMulitiplier() {
    return this.mulitiplier;
  }

  @Override
  public String toString() {
    return switch (this) {
      case NOVICE -> "NOVICE";
      case ADEPT -> "ADEPT";
      case JEDI -> "JEDI";
      default -> throw new IllegalArgumentException();
    };
  }
}
