package edu.unc.comp301;

public enum ArmorType {
  BRONZE(10),
  IRON(15),
  GOLD(20),
  DIAMOND(25);

  private int shieldUpgrade;

  ArmorType(int shieldUpgrade) {
    this.shieldUpgrade = shieldUpgrade;
  }

  @Override
  public String toString() {
    return switch (this) {
      case BRONZE -> "BRONZE";
      case IRON -> "IRON";
      case GOLD -> "GOLD";
      case DIAMOND -> "DIAMOND";
      default -> throw new IllegalArgumentException();
    };
  }
}
