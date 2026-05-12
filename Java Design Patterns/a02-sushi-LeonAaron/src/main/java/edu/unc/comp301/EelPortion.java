package edu.unc.comp301;

public class EelPortion extends IngredientPortionImpl {
  public EelPortion(double amount) {
    super(new Eel(), amount);
  }

  @Override
  public IngredientPortion combine(IngredientPortion other) {
    if (other == null) {
      return this;
    }

    if (other instanceof EelPortion) {
      if (this.getIngredient().equals(other.getIngredient())) {
        double newAmount = this.getAmount() + other.getAmount();
        return new EelPortion(newAmount);
      }
    }
    throw new IllegalArgumentException("Invalid other for combination");
  }
}
