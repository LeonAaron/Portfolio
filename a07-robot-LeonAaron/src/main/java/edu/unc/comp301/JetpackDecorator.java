package edu.unc.comp301;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class JetpackDecorator extends RobotDecorator {
  public JetpackDecorator(Robot decoratedRobot) {
    super(decoratedRobot);
  }

  @Override
  public String getDescription() {
    return decoratedRobot.getDescription() + ", Jetpack";
  }

  @Override
  public int getShield() {
    return decoratedRobot.getShield() + 50;
  }

  @Override
  public Parent getVisual() {
    StackPane stackPane = (StackPane) decoratedRobot.getVisual();

    ImageView visual = new ImageView(new Image("jetpack.png"));
    visual.setFitWidth(600);
    visual.setFitHeight(700);
    stackPane.getChildren().add(visual);

    return stackPane;
  }
}
