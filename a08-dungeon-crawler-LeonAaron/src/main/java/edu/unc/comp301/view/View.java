package edu.unc.comp301.view;

import static edu.unc.comp301.model.Model.STATUS.END_GAME;

import edu.unc.comp301.controller.Controller;
import edu.unc.comp301.model.Model;
import edu.unc.comp301.model.Observer;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class View implements FXComponent, Observer {
  private final Model model;
  private final Controller controller;
  private final int width;
  private final int height;
  private final Stage stage;
  private final Runnable themeUpdater;

  public View(Controller controller, Model model, int width, int height, Stage stage) {
    this.model = model;
    this.controller = controller;
    this.width = width;
    this.height = height;
    this.stage = stage;

    // Swaps the stylesheet and re-renders in place without a full model update.
    this.themeUpdater =
        () -> {
          Scene scene = stage.getScene();
          if (scene != null) {
            scene.getStylesheets().clear();
            scene.getStylesheets().add(cssPath());
            scene.setRoot(this.render());
          }
        };
  }

  private String cssPath() {
    String file = ThemeState.darkMode ? "/dungeon.css" : "/dungeon-light.css";
    return getClass().getResource(file).toExternalForm();
  }

  @Override
  public Parent render() {
    // Decides what screen to load
    if (model.getStatus() == END_GAME) {
      return new TitleScreenView(controller, model, themeUpdater).render();
    } else {
      return new GameView(model, controller, themeUpdater).render();
    }
  }

  @Override
  public void update() {
    Scene scene = stage.getScene();
    if (scene == null) {
      scene = new Scene(this.render(), width, height);
      scene.getStylesheets().add(cssPath());

      // Arrow key bindings — connect events to key clicks, found online
      final Scene s = scene;
      s.addEventFilter(
          KeyEvent.KEY_PRESSED,
          e -> {
            if (model.getStatus() != Model.STATUS.IN_PROGRESS) return;
            if (e.getCode() == KeyCode.UP) {
              controller.moveUp();
              e.consume();
            } else if (e.getCode() == KeyCode.DOWN) {
              controller.moveDown();
              e.consume();
            } else if (e.getCode() == KeyCode.LEFT) {
              controller.moveLeft();
              e.consume();
            } else if (e.getCode() == KeyCode.RIGHT) {
              controller.moveRight();
              e.consume();
            }
          });

      stage.setScene(scene);
    } else {
      scene.setRoot(this.render());
    }
  }
}
