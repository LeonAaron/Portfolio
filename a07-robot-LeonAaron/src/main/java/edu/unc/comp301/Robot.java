package edu.unc.comp301;

import javafx.scene.Parent;

public interface Robot {
  String getDescription();

  int getHealth();

  int getShield();

  int getPower();

  Parent getHealthBar();

  Parent getShieldBar();

  Parent getVisual();
}
