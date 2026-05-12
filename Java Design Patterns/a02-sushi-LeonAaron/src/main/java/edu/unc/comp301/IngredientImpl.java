package edu.unc.comp301;

public class IngredientImpl implements Ingredient {
  private String name;
  private double pricePerOunce;
  private int caloriesPerOunce;
  private boolean isVegetarian;
  private boolean hasRice;
  private boolean hasShellfish;

  public IngredientImpl(
      String name,
      double pricePerOunce,
      int caloriesPerOunce,
      boolean isVegetarian,
      boolean hasRice,
      boolean hasShellfish) {
    this.name = name;
    this.pricePerOunce = pricePerOunce;
    this.caloriesPerOunce = caloriesPerOunce;
    this.isVegetarian = isVegetarian;
    this.hasRice = hasRice;
    this.hasShellfish = hasShellfish;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public double getCaloriesPerDollar() {
    double ouncesPerDollar = 1.00 / this.getPricePerOunce();
    return this.getCaloriesPerOunce() * ouncesPerDollar;
  }

  @Override
  public int getCaloriesPerOunce() {
    return this.caloriesPerOunce;
  }

  @Override
  public double getPricePerOunce() {
    return this.pricePerOunce;
  }

  @Override
  public boolean getIsVegetarian() {
    return this.isVegetarian;
  }

  @Override
  public boolean getIsRice() {
    return this.hasRice;
  }

  @Override
  public boolean getIsShellfish() {
    return this.hasShellfish;
  }

  @Override
  public boolean equals(Ingredient other) {
    if (other == null) {
      return false;
    }

    boolean cond1 = this.getName().equals(other.getName());
    boolean cond2 = this.getCaloriesPerDollar() == other.getCaloriesPerDollar();
    boolean cond3 = this.getCaloriesPerOunce() == other.getCaloriesPerOunce();
    boolean cond4 = this.getIsVegetarian() == other.getIsVegetarian();
    boolean cond5 = this.getIsRice() == other.getIsRice();
    boolean cond6 = this.getIsShellfish() == other.getIsShellfish();

    return cond1 && cond2 && cond3 && cond4 && cond5 && cond6;
  }
}
