package edu.unc.comp301;

public class YellowtailPortion extends IngredientPortionImpl {
  public YellowtailPortion(double amount) {
    super(new Yellowtail(), amount);
  }

  @Override
  public IngredientPortion combine(IngredientPortion other) {
    if (other == null) {
      return this;
    }

    if (other instanceof YellowtailPortion) {
      if (this.getIngredient().equals(other.getIngredient())) {
        double newAmount = this.getAmount() + other.getAmount();
        return new YellowtailPortion(newAmount);
      }
    }
    throw new IllegalArgumentException("Invalid other for combination");
  }
}
