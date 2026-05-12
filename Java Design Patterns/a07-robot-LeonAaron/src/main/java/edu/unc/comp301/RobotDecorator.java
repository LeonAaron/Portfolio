package edu.unc.comp301;

//
// TODO ONCE YOU LEARN DECORATOR DESIGN PATTERN
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public abstract class RobotDecorator implements Robot {
  // POSSIBLY CHANGE TO PRIVATE
  protected Robot decoratedRobot;

  public RobotDecorator(Robot decoratedRobot) {
    this.decoratedRobot = decoratedRobot;
  }

  @Override
  public String getDescription() {
    return decoratedRobot.getDescription();
  }

  @Override
  public int getHealth() {
    return decoratedRobot.getHealth();
  }

  @Override
  public int getShield() {
    return decoratedRobot.getShield();
  }

  @Override
  public int getPower() {
    return decoratedRobot.getPower();
  }

  // TODO CREATE GET HEALTH AND SHIELD AGAIN TO ACCOUNT FOR UPGRADES
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
    // IDENTICAL TO BasicRobot
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
  public abstract Parent getVisual();
}
