package edu.unc.comp301;

public class TunaPortion extends IngredientPortionImpl {
  public TunaPortion(double amount) {
    super(new Tuna(), amount);
  }

  @Override
  public IngredientPortion combine(IngredientPortion other) {
    if (other == null) {
      return this;
    }

    if (other instanceof TunaPortion) {
      if (this.getIngredient().equals(other.getIngredient())) {
        double newAmount = this.getAmount() + other.getAmount();
        return new TunaPortion(newAmount);
      }
    }
    throw new IllegalArgumentException("Invalid other for combination");
  }
}
