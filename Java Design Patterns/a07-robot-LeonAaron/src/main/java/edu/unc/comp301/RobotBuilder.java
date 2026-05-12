package edu.unc.comp301;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RobotBuilder extends Application {
  @Override
  public void start(Stage stage) {

    VBox root = (VBox) upgrade(Main.build());
    root.setStyle("-fx-alignment: center; -fx-padding: 20;");
    Scene scene = new Scene(root, 1920, 1080);
    stage.setTitle("Robot");
    stage.setScene(scene);
    stage.show();
  }

  public Parent upgrade(Robot robot) {
    Node robotVisual = robot.getVisual();

    if (robotVisual instanceof StackPane pane) {
      for (Node node : pane.getChildren()) {
        if (node instanceof ImageView imageView) {
          imageView.setFitWidth(600);
          imageView.setPreserveRatio(true);
        }
      }
    }

    Label descriptionLabel = new Label("Robot Description: " + robot.getDescription());
    Label powerLabel = new Label("Total Power: " + robot.getPower());
    HBox healthBar = (HBox) robot.getHealthBar();
    healthBar.setStyle("-fx-alignment: center;");
    HBox shieldBar = (HBox) robot.getShieldBar();
    shieldBar.setStyle("-fx-alignment: center;");

    return new VBox(20, robotVisual, descriptionLabel, powerLabel, shieldBar, healthBar);
  }
}
