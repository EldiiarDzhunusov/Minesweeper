package minesweeper;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

public class Tile extends StackPane {

    public int x, y;
    public boolean hasMine;
    public boolean isOpen = false;
    public boolean flagged = false;
    public static final int tileSize = 20;

    public Rectangle border = new Rectangle (tileSize - 2, tileSize - 2);
    public Text text = new Text();
    public Text flag = new Text();



    public Tile (int x, int y, boolean hasMine) {

        this.x = x;
        this.y = y;
        this.hasMine = hasMine;

        border.setStroke(Color.LIGHTGRAY);
        border.setFill(Color.WHITE);
        flag.setText ("");
        flag.setFill(Color.RED);
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        flag.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        text.setVisible(false);

        getChildren().addAll(border,text,flag);

        setTranslateX(x * tileSize);
        setTranslateY(y * tileSize);

        setOnMouseClicked(e ->
        {
            if (e.getButton() == MouseButton.PRIMARY)
            {
                open();
            } else if (e.getButton() == MouseButton.SECONDARY)
            {
                setFlag();
            }
        });
    }

    public void open(){
        if (isOpen) return;
        isOpen = true;
        text.setVisible(true);
        flag.setText ("");
        border.setFill(Color.LIGHTGRAY);

        if (hasMine) {
            Alert alert = new Alert(Alert.AlertType.NONE, "You lost. Good game!", ButtonType.YES);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                System.exit(0);
                return;
            }
            System.exit(0);
        }

        if (Minesweeper.tilesOpened == Minesweeper.verticalTiles * Minesweeper.horizontalTiles - Minesweeper.minesNeeded - 1) {
            Alert alert = new Alert(Alert.AlertType.NONE, "You won. Well played!", ButtonType.YES);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                System.exit(0);
                return;
            }
            System.exit(0);
        } else{
            Minesweeper.tilesOpened++;
        }

        if (text.getText().isEmpty()){
            Minesweeper.getNeighbours(this).forEach(Tile::open);
        }
    }

    public void setFlag(){
        if (isOpen) return;
        if (flagged) {
            flagged = false;
            flag.setText ("");
        } else {
            flagged = true;
            flag.setText ("⚐");
        }
    }
}
