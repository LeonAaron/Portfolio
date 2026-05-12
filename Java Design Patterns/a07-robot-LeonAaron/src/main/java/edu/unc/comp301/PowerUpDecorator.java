package edu.unc.comp301;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class PowerUpDecorator extends RobotDecorator {
  private PowerType type;

  public PowerUpDecorator(Robot decoratedRobot, PowerType type) {
    super(decoratedRobot);
    this.type = type;
  }

  @Override
  public String getDescription() {
    // Possibly separate
    return decoratedRobot.getDescription() + ", " + type + " Power Up";
  }

  @Override
  public int getHealth() {
    return switch (type) {
      case NOVICE -> decoratedRobot.getHealth();
      case ADEPT -> decoratedRobot.getHealth() * 2;
      case JEDI -> decoratedRobot.getHealth() * 3;
      default -> throw new IllegalArgumentException();
    };
  }

  @Override
  public int getPower() {
    return switch (type) {
      case NOVICE -> decoratedRobot.getPower() * 10;
      case ADEPT -> decoratedRobot.getPower() * 20;
      case JEDI -> decoratedRobot.getPower() * 30;
      default -> throw new IllegalArgumentException();
    };
  }

  @Override
  public Parent getVisual() {
    StackPane stackPane = (StackPane) decoratedRobot.getVisual();

    ImageView visual =
        switch (type) {
          case NOVICE -> new ImageView(new Image("yellow_power.png"));
          case ADEPT -> new ImageView(new Image("red_power.png"));
          case JEDI -> new ImageView(new Image("blue_power.png"));
          default -> throw new IllegalArgumentException();
        };

    visual.setFitWidth(600);
    visual.setFitHeight(700);

    stackPane.getChildren().addFirst(visual);

    return stackPane;
  }
}
