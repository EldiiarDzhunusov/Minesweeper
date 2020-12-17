package minesweeper;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    @FXML public Button buttonStart, buttonExit;
    @FXML public TextField fieldHeight, fieldWidth, fieldMines;
    @FXML public AnchorPane gameWindow;
    @FXML public GridPane gameGrid;

    public static int verticalTiles;
    public static int horizontalTiles;
    public static int minesNeeded;

    public static Scene game;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("minesweeper.fxml"));
        game = new Scene(root);
        primaryStage.setTitle("Minesweeper");
        primaryStage.setScene(game);
        primaryStage.resizableProperty().setValue(Boolean.TRUE);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void launch() {
        if (fieldHeight.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.NONE, "Enter the height.", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                return;
            }
        } else {
            verticalTiles = Integer.valueOf(fieldHeight.getText());
        }
        if (fieldWidth.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.NONE, "Enter the width.", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                return;
            }
        } else {
            horizontalTiles = Integer.valueOf(fieldWidth.getText());
        }
        if (fieldMines.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.NONE, "Enter the amount of mines.", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                return;
            }
        } else {
            minesNeeded = Integer.valueOf(fieldMines.getText());
        }
        if (verticalTiles > 1 && verticalTiles < 41 && horizontalTiles > 1 && horizontalTiles < 31 && minesNeeded > 0 && minesNeeded <= (horizontalTiles*verticalTiles-1)) {
            game.setRoot(Minesweeper.createContent());
        }else{
            Alert alert = new Alert(Alert.AlertType.NONE, "Height must be greater than 1 and less than 41; width must be greater than 1 and less than 31; there must be at least one mine.", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                return;
            }
        }
    }

    public void exit() {
        System.exit(0);
    }
}
