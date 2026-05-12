package edu.unc.comp301;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class CustomDecorator extends RobotDecorator {
  public CustomDecorator(Robot decoratedRobot) {
    super(decoratedRobot);
  }

  @Override
  public int getHealth() {
    return decoratedRobot.getHealth() + 50;
  }

  @Override
  public Parent getVisual() {
    StackPane stackPane = (StackPane) decoratedRobot.getVisual();

    ImageView visual = new ImageView(new Image("red_power.png"));

    visual.setFitWidth(200);
    visual.setFitHeight(200);
    stackPane.getChildren().add(visual);

    return stackPane;
  }
}
