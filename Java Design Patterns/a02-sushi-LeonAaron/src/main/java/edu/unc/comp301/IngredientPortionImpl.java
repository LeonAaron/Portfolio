package edu.unc.comp301;

public class IngredientPortionImpl implements IngredientPortion {
  private double amount;
  private IngredientImpl ingredient;

  public IngredientPortionImpl(IngredientImpl ingredient, double amount) {
    if (amount < 0) {
      throw new IllegalArgumentException();
    }
    this.amount = amount;
    this.ingredient = ingredient;
  }

  @Override
  public Ingredient getIngredient() {
    return this.ingredient;
  }

  @Override
  public double getAmount() {
    return this.amount;
  }

  @Override
  public String getName() {
    return this.ingredient.getName();
  }

  @Override
  public boolean getIsVegetarian() {
    return this.ingredient.getIsVegetarian();
  }

  @Override
  public boolean getIsRice() {
    return this.ingredient.getIsRice();
  }

  @Override
  public boolean getIsShellfish() {
    return this.ingredient.getIsShellfish();
  }

  @Override
  public double getCalories() {
    return this.ingredient.getCaloriesPerOunce() * this.getAmount();
  }

  @Override
  public double getCost() {
    return this.ingredient.getPricePerOunce() * this.getAmount();
  }

  @Override
  public IngredientPortion combine(IngredientPortion other) {
    // Placeholder, will update in child classes
    return null;
  }
}
