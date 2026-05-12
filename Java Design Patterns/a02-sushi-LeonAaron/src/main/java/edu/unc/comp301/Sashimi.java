package edu.unc.comp301;

public class Sashimi implements Sushi {
  private IngredientPortionImpl sashimi;
  private String name;

  public enum SashimiType {
    TUNA,
    YELLOWTAIL,
    EEL,
    CRAB,
    SHRIMP
  };

  public Sashimi(SashimiType type) {
    if (type == SashimiType.TUNA) {
      this.sashimi = new TunaPortion(.75);
    } else if (type == SashimiType.YELLOWTAIL) {
      this.sashimi = new YellowtailPortion(.75);
    } else if (type == SashimiType.EEL) {
      this.sashimi = new EelPortion(.75);
    } else if (type == SashimiType.CRAB) {
      this.sashimi = new CrabPortion(.75);
    } else {
      this.sashimi = new ShrimpPortion(.75);
    }
    this.name = this.sashimi.getName() + " sashimi";
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public IngredientPortion[] getIngredients() {
    return new IngredientPortion[] {this.sashimi};
  }

  @Override
  public int getCalories() {
    return (int) Math.round(this.sashimi.getCalories());
  }

  @Override
  public double getCost() {
    return this.sashimi.getCost();
  }

  @Override
  public boolean getHasRice() {
    return this.sashimi.getIsRice();
  }

  @Override
  public boolean getHasShellfish() {
    return this.sashimi.getIsShellfish();
  }

  @Override
  public boolean getIsVegetarian() {
    return this.sashimi.getIsVegetarian();
  }
}
