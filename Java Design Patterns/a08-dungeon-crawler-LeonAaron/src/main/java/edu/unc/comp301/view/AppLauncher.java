package edu.unc.comp301.view;

import edu.unc.comp301.controller.Controller;
import edu.unc.comp301.controller.ControllerImpl;
import edu.unc.comp301.model.Model;
import edu.unc.comp301.model.ModelImpl;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    stage.setTitle("Leon's Dungeon Crawler");
    int width = 1000;
    int height = 700;

    // Board is 15 columns × 10 rows of tiles
    Model model = new ModelImpl(15, 10);
    Controller controller = new ControllerImpl(model);
    View view = new View(controller, model, width, height, stage);
    model.addObserver(view);

    view.update();
    stage.show();
  }
}
