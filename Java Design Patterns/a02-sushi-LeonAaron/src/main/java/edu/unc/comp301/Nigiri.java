package edu.unc.comp301;

public class Nigiri implements Sushi {
  private IngredientPortionImpl nigiri;
  private String name;
  private RicePortion rice;

  public enum NigiriType {
    TUNA,
    YELLOWTAIL,
    EEL,
    CRAB,
    SHRIMP
  };

  public Nigiri(NigiriType type) {
    if (type == Nigiri.NigiriType.TUNA) {
      this.nigiri = new TunaPortion(.75);
    } else if (type == Nigiri.NigiriType.YELLOWTAIL) {
      this.nigiri = new YellowtailPortion(.75);
    } else if (type == Nigiri.NigiriType.EEL) {
      this.nigiri = new EelPortion(.75);
    } else if (type == Nigiri.NigiriType.CRAB) {
      this.nigiri = new CrabPortion(.75);
    } else {
      this.nigiri = new ShrimpPortion(.75);
    }
    this.name = this.nigiri.getName() + " nigiri";
    this.rice = new RicePortion(.5);
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public IngredientPortion[] getIngredients() {
    return new IngredientPortion[] {this.nigiri, this.rice};
  }

  @Override
  public int getCalories() {
    return (int) Math.round((this.nigiri.getCalories() + this.rice.getCalories()));
  }

  @Override
  public double getCost() {
    return this.nigiri.getCost() + this.rice.getCost();
  }

  @Override
  public boolean getHasRice() {
    return this.nigiri.getIsRice() || this.rice.getIsRice();
  }

  @Override
  public boolean getHasShellfish() {
    return this.nigiri.getIsShellfish() || this.rice.getIsShellfish();
  }

  @Override
  public boolean getIsVegetarian() {
    return this.nigiri.getIsVegetarian() || this.rice.getIsVegetarian();
  }
}
