package edu.unc.comp301;

public class AvocadoPortion extends IngredientPortionImpl {
  public AvocadoPortion(double amount) {
    super(new Avocado(), amount);
  }

  @Override
  public IngredientPortion combine(IngredientPortion other) {
    if (other == null) {
      return this;
    }

    if (other instanceof AvocadoPortion) {
      if (this.getIngredient().equals(other.getIngredient())) {
        double newAmount = this.getAmount() + other.getAmount();
        return new AvocadoPortion(newAmount);
      }
    }
    throw new IllegalArgumentException("Invalid other for combination");
  }
}
