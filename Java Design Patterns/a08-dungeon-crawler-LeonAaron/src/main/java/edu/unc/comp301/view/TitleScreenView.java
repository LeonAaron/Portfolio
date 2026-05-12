package edu.unc.comp301.view;

import edu.unc.comp301.controller.Controller;
import edu.unc.comp301.model.Model;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.*;

public class TitleScreenView implements FXComponent {
  private final Controller controller;
  private final Model model;
  private final Runnable themeUpdater;

  public TitleScreenView(Controller controller, Model model, Runnable themeUpdater) {
    this.controller = controller;
    this.model = model;
    this.themeUpdater = themeUpdater;
  }

  @Override
  public Parent render() {
    // Root: StackPane centers the VBox in the window
    StackPane allCenteredContent = new StackPane();

    VBox titlePage = new VBox();
    titlePage.getStyleClass().add("title-page");

    // Title
    Label title = new Label("BLOX CRAWLER");
    title.getStyleClass().add("title-title");

    // High score row
    HBox highScoreContainer = new HBox();
    highScoreContainer.getStyleClass().add("hbox");
    Label highScoreLabel = new Label("HIGH SCORE: ");
    highScoreLabel.getStyleClass().add("title-label");
    Label highScore = new Label("" + model.getHighScore());
    highScore.getStyleClass().add("title-score");
    highScoreContainer.getChildren().addAll(highScoreLabel, highScore);

    // Last score row
    HBox lastScoreContainer = new HBox();
    lastScoreContainer.getStyleClass().add("hbox");
    Label lastScoreLabel = new Label("LAST SCORE: ");
    lastScoreLabel.getStyleClass().add("title-label");
    Label lastScore = new Label("" + model.getCurScore());
    lastScore.getStyleClass().add("title-score");
    lastScoreContainer.getChildren().addAll(lastScoreLabel, lastScore);

    // Dark / Light mode toggle
    ToggleButton colorToggle = new ToggleButton(ThemeState.darkMode ? "Dark Mode" : "Light Mode");
    colorToggle.setSelected(!ThemeState.darkMode); // selected = light mode active
    colorToggle.getStyleClass().add("toggle-btn");
    colorToggle.setOnAction(
        e -> {
          ThemeState.darkMode = !colorToggle.isSelected();
          colorToggle.setText(ThemeState.darkMode ? "Dark Mode" : "Light Mode");
          themeUpdater.run();
        });

    // Easy / Hard mode toggle
    ToggleButton modeToggle = new ToggleButton(ThemeState.hardMode ? "Hard Mode" : "Easy Mode");
    modeToggle.setSelected(ThemeState.hardMode); // selected = hard mode active
    modeToggle.getStyleClass().add("toggle-btn");
    modeToggle.setOnAction(
        e -> {
          ThemeState.hardMode = modeToggle.isSelected();
          modeToggle.setText(ThemeState.hardMode ? "Hard Mode" : "Easy Mode");
          controller.setHardMode(ThemeState.hardMode);
        });

    // Initilaize toggle Buttons container
    HBox toggleBtns = new HBox();
    toggleBtns.getStyleClass().add("hbox");
    toggleBtns.getChildren().addAll(colorToggle, modeToggle);

    // How to play legend — GridPane with colored mini-tiles
    GridPane legendGrid = new GridPane();
    legendGrid.getStyleClass().add("legend-grid");

    Label howToPlay = new Label("HOW TO PLAY");
    howToPlay.getStyleClass().add("legend-header");
    GridPane.setColumnSpan(howToPlay, 3);
    legendGrid.add(howToPlay, 0, 0);

    // format of [cssClass, symbol, name, description]
    String[][] entries = {
      {"hero-cell", "⚔", "YOU", "Move with the arrow keys or direction buttons"},
      {"exit-cell", "⬆", "EXIT", "Reach it to advance to the next level"},
      {"treasure-cell", "★", "TREASURE", "+100 points"},
      {"enemy-cell", "☠", "ENEMY", "Avoid — touching one ends the game"},
      {"trap-cell", "⚠", "TRAP", "Stepping on it costs 200 points!"},
      {"wall-cell", "█", "WALL", "Blocks all movement"},
    };

    // Put elements into gridPand display
    for (int i = 0; i < entries.length; i++) {
      StackPane tile = new StackPane();
      tile.getStyleClass().addAll("legend-tile", entries[i][0]);
      Label sym = new Label(entries[i][1]);
      sym.getStyleClass().add("legend-tile-label");
      tile.getChildren().add(sym);

      Label nameLabel = new Label(entries[i][2]);
      nameLabel.getStyleClass().add("legend-name");

      Label descLabel = new Label(entries[i][3]);
      descLabel.getStyleClass().add("legend-desc");

      legendGrid.add(tile, 0, i + 1);
      legendGrid.add(nameLabel, 1, i + 1);
      legendGrid.add(descLabel, 2, i + 1);
    }

    // Start button
    Button startBtn = new Button("START");
    startBtn.getStyleClass().add("start-btn");
    startBtn.setOnAction(e -> controller.startGame());

    // Author label
    Label author = new Label("BY: LEON AARON");
    author.getStyleClass().add("title-label");

    titlePage
        .getChildren()
        .addAll(
            title,
            highScoreContainer,
            lastScoreContainer,
            legendGrid,
            toggleBtns,
            startBtn,
            author);

    allCenteredContent.getChildren().add(titlePage);
    // Return stack pane
    return allCenteredContent;
  }
}
