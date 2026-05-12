package edu.unc.comp301;

public class RicePortion extends IngredientPortionImpl {
  public RicePortion(double amount) {
    super(new Rice(), amount);
  }

  @Override
  public IngredientPortion combine(IngredientPortion other) {
    if (other == null) {
      return this;
    }

    if (other instanceof RicePortion) {
      if (this.getIngredient().equals(other.getIngredient())) {
        double newAmount = this.getAmount() + other.getAmount();
        return new RicePortion(newAmount);
      }
    }
    throw new IllegalArgumentException("Invalid other for combination");
  }
}
