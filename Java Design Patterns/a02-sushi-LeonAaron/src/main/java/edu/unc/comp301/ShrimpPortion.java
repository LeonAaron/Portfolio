package edu.unc.comp301;

public class ShrimpPortion extends IngredientPortionImpl {
  public ShrimpPortion(double amount) {
    super(new Shrimp(), amount);
  }

  @Override
  public IngredientPortion combine(IngredientPortion other) {
    if (other == null) {
      return this;
    }

    if (other instanceof ShrimpPortion) {
      if (this.getIngredient().equals(other.getIngredient())) {
        double newAmount = this.getAmount() + other.getAmount();
        return new ShrimpPortion(newAmount);
      }
    }
    throw new IllegalArgumentException("Invalid other for combination");
  }
}
