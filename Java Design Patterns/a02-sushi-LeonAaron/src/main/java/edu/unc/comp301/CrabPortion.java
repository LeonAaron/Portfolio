package edu.unc.comp301;

public class CrabPortion extends IngredientPortionImpl {
  public CrabPortion(double amount) {
    super(new Crab(), amount);
  }

  @Override
  public IngredientPortion combine(IngredientPortion other) {
    if (other == null) {
      return this;
    }

    if (other instanceof CrabPortion) {
      if (this.getIngredient().equals(other.getIngredient())) {
        double newAmount = this.getAmount() + other.getAmount();
        return new CrabPortion(newAmount);
      }
    }
    throw new IllegalArgumentException("Invalid other for combination");
  }
}
