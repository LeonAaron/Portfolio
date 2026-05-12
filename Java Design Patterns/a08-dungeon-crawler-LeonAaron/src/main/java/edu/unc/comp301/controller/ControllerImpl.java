package edu.unc.comp301.controller;

import edu.unc.comp301.model.Model;

public class ControllerImpl implements Controller {
  private final Model model;

  public ControllerImpl(Model model) {
    this.model = model;
  }

  public void moveUp() {
    model.moveUp();
  }

  public void moveDown() {
    model.moveDown();
  }

  public void moveLeft() {
    model.moveLeft();
  }

  public void moveRight() {
    model.moveRight();
  }

  public void startGame() {
    model.startGame();
  }

  public void setHardMode(boolean hard) {
    model.setHardMode(hard);
  }

  public void endGame() {
    model.endGame();
  }
}
