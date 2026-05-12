package edu.unc.comp301;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class TorsoArmorDecorator extends RobotDecorator {
  private ArmorType armor;

  public TorsoArmorDecorator(Robot decoratedRobot, ArmorType armor) {
    super(decoratedRobot);
    this.armor = armor;
  }

  @Override
  public String getDescription() {
    return decoratedRobot.getDescription() + ", " + armor + " Torso Armor";
  }

  @Override
  public int getShield() {
    return switch (armor) {
      case BRONZE -> decoratedRobot.getShield() + 10;
      case IRON -> decoratedRobot.getShield() + 15;
      case GOLD -> decoratedRobot.getShield() + 20;
      case DIAMOND -> decoratedRobot.getShield() + 25;
      default -> throw new IllegalArgumentException();
    };
  }

  @Override
  public Parent getVisual() {
    StackPane stackPane = (StackPane) decoratedRobot.getVisual();

    ImageView visual =
        switch (armor) {
          case BRONZE -> new ImageView(new Image("torso.png"));
          case IRON -> new ImageView(new Image("torso_iron.png"));
          case GOLD -> new ImageView(new Image("torso_gold.png"));
          case DIAMOND -> new ImageView(new Image("torso_diamond.png"));
          default -> throw new IllegalArgumentException();
        };

    stackPane.getChildren().add(visual);

    return stackPane;
  }
}
