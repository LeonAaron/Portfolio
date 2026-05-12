package edu.unc.comp301;

public class SeaweedPortion extends IngredientPortionImpl {
  public SeaweedPortion(double amount) {
    super(new Seaweed(), amount);
  }

  @Override
  public IngredientPortion combine(IngredientPortion other) {
    if (other == null) {
      return this;
    }

    if (other instanceof SeaweedPortion) {
      if (this.getIngredient().equals(other.getIngredient())) {
        double newAmount = this.getAmount() + other.getAmount();
        return new SeaweedPortion(newAmount);
      }
    }
    throw new IllegalArgumentException("Invalid other for combination");
  }
}
