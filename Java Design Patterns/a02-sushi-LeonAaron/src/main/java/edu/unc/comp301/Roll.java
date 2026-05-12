package edu.unc.comp301;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Roll implements Sushi {
  private String name;
  private IngredientPortion[] roll_ingredients;
  // Map string name to integer index
  private Map<String, Integer> ingredientsMap;
  // To prevent null values at end of array
  private List<IngredientPortion> ingredientsList;

  public Roll(String name, IngredientPortion[] roll_ingredients) {
    this.name = name;

    this.ingredientsMap = new HashMap<>();
    this.ingredientsList = new ArrayList<>();

    if (roll_ingredients == null) {
      throw new IllegalArgumentException("Null array");
    }

    double seaweedAmount = 0.0;

    for (int i = 0; i < roll_ingredients.length; i++) {
      IngredientPortion portion = roll_ingredients[i];

      if (portion == null) {
        throw new IllegalArgumentException("Null element in array");
      }

      if (portion instanceof SeaweedPortion) {
        seaweedAmount += portion.getAmount();
      }

      if (this.ingredientsMap.containsKey(portion.getName())) {
        int index = this.ingredientsMap.get(portion.getName());
        this.ingredientsList.set(index, this.ingredientsList.get(index).combine(portion));
      } else {
        // Index must correspond to ArrayList
        this.ingredientsMap.put(portion.getName(), ingredientsList.size());
        this.ingredientsList.add(portion);
      }
    }

    double remainder = .1 - seaweedAmount;

    if (remainder > 0) {
      SeaweedPortion seaweed = new SeaweedPortion(remainder);

      if (this.ingredientsMap.containsKey(seaweed.getName())) {
        int index = this.ingredientsMap.get(seaweed.getName());
        this.ingredientsList.set(index, this.ingredientsList.get(index).combine(seaweed));
      } else {

        this.ingredientsList.add(seaweed);
      }
    }
    // Convert array list into regular list
    this.roll_ingredients = this.ingredientsList.toArray(new IngredientPortion[0]);
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public IngredientPortion[] getIngredients() {
    return this.roll_ingredients.clone();
  }

  @Override
  public int getCalories() {
    double total = 0;

    for (IngredientPortion portion : this.roll_ingredients) {
      total += portion.getCalories();
    }

    return (int) Math.round(total);
  }

  @Override
  public double getCost() {
    double total = 0;

    for (IngredientPortion portion : this.roll_ingredients) {
      total += portion.getCost();
    }

    // To return result with 2 decimil places
    return Math.round(total * 100.0) / 100.0;
  }

  @Override
  public boolean getHasRice() {
    for (IngredientPortion portion : this.roll_ingredients) {
      if (portion.getIsRice()) {
        return true;
      }
    }

    return false;
  }

  @Override
  public boolean getHasShellfish() {
    for (IngredientPortion portion : this.roll_ingredients) {
      if (portion.getIsShellfish()) {
        return true;
      }
    }

    return false;
  }

  @Override
  public boolean getIsVegetarian() {
    for (IngredientPortion portion : this.roll_ingredients) {
      if (!portion.getIsVegetarian()) {
        return false;
      }
    }

    return true;
  }

  public static void main(String[] args) {
    TunaPortion tunaPortion = new TunaPortion(3.0);
    SeaweedPortion seaweedPortion = new SeaweedPortion(.05);
    IngredientPortion[] ingredients = {seaweedPortion, tunaPortion};
    Roll roll = new Roll("sushi", ingredients);

    System.out.println(roll.getName());
    for (IngredientPortion portion : roll.getIngredients()) {
      System.out.println(portion.getAmount());
      System.out.println(portion.getName());
    }
    System.out.println(Arrays.toString(roll.getIngredients()));
    System.out.println(roll.getCalories());
    System.out.println(roll.getCost());
    System.out.println(roll.getHasRice());
    System.out.println(roll.getHasShellfish());
    System.out.println(roll.getIsVegetarian());
  }
}