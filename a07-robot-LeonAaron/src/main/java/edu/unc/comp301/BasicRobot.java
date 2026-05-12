package edu.unc.comp301;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class BasicRobot implements Robot {
  private String description = "Basic Robot";
  private int health = 50;
  private int shield = 0;
  private int power = 10;

  @Override
  public String getDescription() {
    return this.description;
  }

  @Override
  public int getHealth() {
    return health;
  }

  @Override
  public int getShield() {
    return shield;
  }

  @Override
  public int getPower() {
    return power;
  }

  @Override
  public Parent getHealthBar() {
    HBox hbox = new HBox();

    int total = 0;
    for (int i = 10; i <= getHealth(); i += 10) {
      ImageView heart = new ImageView(new Image("health.png"));
      heart.setFitWidth(30);
      heart.setFitHeight(30);
      hbox.getChildren().add(heart);
      total = i;
    }
    if (getHealth() - total > 0) {
      ImageView halfHeart = new ImageView(new Image("health_half.png"));
      halfHeart.setFitWidth(30);
      halfHeart.setFitHeight(30);
      hbox.getChildren().add(halfHeart);
    }

    return hbox;
  }

  @Override
  public Parent getShieldBar() {
    HBox hbox = new HBox();
    int total = 0;

    for (int i = 10; i <= getShield(); i += 10) {
      ImageView shield = new ImageView(new Image("bar.png"));
      shield.setFitHeight(30);
      shield.setFitWidth(30);
      hbox.getChildren().add(shield);
      total = i;
    }

    if (getShield() - total > 0) {
      ImageView halfShield = new ImageView(new Image("bar_half.png"));
      halfShield.setFitHeight(30);
      halfShield.setFitWidth(30);
      hbox.getChildren().add(halfShield);
    }
    return hbox;
  }

  @Override
  public Parent getVisual() {
    ImageView iv = new ImageView(new Image("base.png"));
    StackPane pane = new StackPane(iv);
    return pane;
  }
}
