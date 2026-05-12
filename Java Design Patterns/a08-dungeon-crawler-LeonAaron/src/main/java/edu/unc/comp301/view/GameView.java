package edu.unc.comp301.view;

import edu.unc.comp301.controller.Controller;
import edu.unc.comp301.model.Model;
import edu.unc.comp301.model.board.Posn;
import edu.unc.comp301.model.pieces.*;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class GameView implements FXComponent {
  private static final int CELL_SIZE = 50;

  private final Model model;
  private final Controller controller;
  private final Runnable themeUpdater;

  public GameView(Model model, Controller controller, Runnable themeUpdater) {
    this.model = model;
    this.controller = controller;
    this.themeUpdater = themeUpdater;
  }

  @Override
  public Parent render() {
    VBox root = new VBox();
    root.getStyleClass().add("game-root");

    // Top bar of game
    HBox topBar = new HBox();
    topBar.getStyleClass().add("top-bar");

    // Current score
    Label currScoreLabel = new Label("SCORE: ");
    currScoreLabel.getStyleClass().add("game-label");
    Label currScore = new Label("" + model.getCurScore());
    currScore.getStyleClass().add("game-score");

    // High Score
    Label highScoreLabel = new Label("  |  HIGH SCORE: ");
    highScoreLabel.getStyleClass().add("game-label");
    Label highScore = new Label("" + model.getHighScore());
    highScore.getStyleClass().add("game-score");

    // Curr level
    Label levelLabel = new Label("  |  LEVEL: ");
    levelLabel.getStyleClass().add("game-label");
    Label level = new Label("" + model.getLevel());
    level.getStyleClass().add("game-score");

    // Light / dark mode button
    ToggleButton themeToggle = new ToggleButton(ThemeState.darkMode ? "Dark Mode" : "Light Mode");
    themeToggle.setSelected(!ThemeState.darkMode);
    themeToggle.getStyleClass().add("toggle-btn");

    // Button functionality
    themeToggle.setOnAction(
        e -> {
          ThemeState.darkMode = !themeToggle.isSelected();
          themeToggle.setText(ThemeState.darkMode ? "Dark Mode" : "Light Mode");
          // Dynamically update theme
          themeUpdater.run();
        });

    // Spacer pushes the end-game button to the far right
    Region spacer = new Region();
    HBox.setHgrow(spacer, Priority.ALWAYS);

    // Button to end game
    Button endGameBtn = new Button("END GAME");
    endGameBtn.getStyleClass().add("end-btn");
    endGameBtn.setOnAction(e -> controller.endGame());

    // Add top bar elements
    topBar
        .getChildren()
        .addAll(
            currScoreLabel,
            currScore,
            highScoreLabel,
            highScore,
            levelLabel,
            level,
            themeToggle,
            spacer,
            endGameBtn);

    // Board Grid Pane
    // GridPane.add(node, colIndex, rowIndex)- col first, then row
    GridPane boardGrid = new GridPane();
    boardGrid.getStyleClass().add("board-grid");

    int rows = model.getHeight();
    int cols = model.getWidth();

    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        Piece piece = model.get(new Posn(r, c));

        StackPane cell = new StackPane();
        // Set dimensions of stack pane
        cell.setPrefSize(CELL_SIZE, CELL_SIZE);
        cell.getStyleClass().add("cell");

        // Use symbols instead of images, depends on instance of piece
        String symbol = "";
        if (piece instanceof Hero) {
          cell.getStyleClass().add("hero-cell");
          symbol = "⚔";
        } else if (piece instanceof Enemy) {
          cell.getStyleClass().add("enemy-cell");
          symbol = "☠";
        } else if (piece instanceof Treasure) {
          cell.getStyleClass().add("treasure-cell");
          symbol = "★";
        } else if (piece instanceof Wall) {
          cell.getStyleClass().add("wall-cell");
          symbol = "█";
        } else if (piece instanceof Exit) {
          cell.getStyleClass().add("exit-cell");
          symbol = "⬆";
        } else if (piece instanceof Trap) {
          cell.getStyleClass().add("trap-cell");
          symbol = "⚠";
        } else {
          cell.getStyleClass().add("floor-cell");
        }

        // Use text label to display icons
        if (!symbol.isEmpty()) {
          Label pieceLabel = new Label(symbol);
          pieceLabel.getStyleClass().add("piece-label");
          cell.getChildren().add(pieceLabel);
        }
        // col, row
        boardGrid.add(cell, c, r);
      }
    }

    // Movement control, connect to controller methods
    Button up = new Button("▲");
    up.getStyleClass().add("control-btn");
    // Link button press to model
    up.setOnAction(e -> controller.moveUp());

    Button left = new Button("◄");
    left.getStyleClass().add("control-btn");
    left.setOnAction(e -> controller.moveLeft());

    Button down = new Button("▼");
    down.getStyleClass().add("control-btn");
    down.setOnAction(e -> controller.moveDown());

    Button right = new Button("►");
    right.getStyleClass().add("control-btn");
    right.setOnAction(e -> controller.moveRight());

    // Display controls like video game
    HBox topBtnRow = new HBox(up);
    topBtnRow.getStyleClass().add("dpad-row");

    HBox midBtnRow = new HBox(left, down, right);
    midBtnRow.getStyleClass().add("dpad-row");

    // Put one button on top row and three on bottom
    VBox dpad = new VBox(topBtnRow, midBtnRow);
    dpad.getStyleClass().add("dpad");

    // Explain trap
    Text trapTitle = new Text("⚠  TRAP");
    trapTitle.getStyleClass().add("trap-info-title");

    Text trapDesc =
        new Text(
            "A trap is hidden somewhere in every room.\n"
                + "Stepping on it costs you 200 points.\n\n"
                + "WATCH YOUR STEP!");
    trapDesc.getStyleClass().add("trap-info-text");

    VBox trapInfo = new VBox(trapTitle, trapDesc);
    trapInfo.getStyleClass().add("trap-info");

    // Trap info on the left, spacer fills the middle, dpad centered on the right
    Region bottomSpacer = new Region();
    HBox.setHgrow(bottomSpacer, Priority.ALWAYS);

    HBox bottomArea = new HBox(trapInfo, bottomSpacer, dpad);
    bottomArea.getStyleClass().add("bottom-area");
    HBox.setHgrow(dpad, Priority.NEVER);

    // Add all components of game to root
    root.getChildren().addAll(topBar, boardGrid, bottomArea);
    return root;
  }
}
