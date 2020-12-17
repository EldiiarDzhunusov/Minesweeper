package minesweeper;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Minesweeper {

    public static final int verticalTiles = Main.verticalTiles;
    public static final int horizontalTiles = Main.horizontalTiles;
    public static final int minesNeeded = Main.minesNeeded;
    public static final int width = 800;
    public static final int height = 600;
    public static int tilesOpened = 0;
    public static Tile[][] grid = new Tile[horizontalTiles][verticalTiles];

    public static Parent createContent() {

        Pane root = new Pane();
        root.setPrefSize(width,height);
        int minesGenerated = 0;

        for (int y = 0; y < verticalTiles; y++) {
            for (int x = 0; x < horizontalTiles; x++) {
                Tile tile = new Tile (x, y, false);
                grid[x][y] = tile;
                root.getChildren().add(tile);
            }
        }

        while (minesGenerated < minesNeeded) {
            Random random = new Random();
            int mine_x = random.nextInt(horizontalTiles);
            int mine_y = random.nextInt(verticalTiles);
            Tile tile = grid[mine_x][mine_y];
            if (!tile.hasMine){
                tile.hasMine = true;
                minesGenerated++;
            }
        }

        for (int y = 0; y < verticalTiles; y++) {
            for (int x = 0; x < horizontalTiles; x++) {
                Tile tile = grid[x][y];
                if (tile.hasMine) {
                    tile.text.setText("\uD83D\uDCA3");
                    continue;
                }
                long mines = getNeighbours(tile).stream().filter(t -> t.hasMine).count();
                if (mines > 0) tile.text.setText(String.valueOf(mines));
                if (mines == 1) {
                    tile.text.setFill(Color.BLUE);
                }
                else if (mines == 2) {
                    tile.text.setFill(Color.GREEN);
                }
                else if (mines == 3) {
                    tile.text.setFill(Color.RED);
                }
                else if (mines == 4) {
                    tile.text.setFill(Color.PURPLE);
                }
                else if (mines == 5) {
                    tile.text.setFill(Color.MAROON);
                }
                else if (mines == 6) {
                    tile.text.setFill(Color.TURQUOISE);
                }
                else if (mines == 7) {
                    tile.text.setFill(Color.BLACK);
                }
                else if (mines == 8) {
                    tile.text.setFill(Color.GRAY);
                }
            }
        }

        return root;
    }

    public static List<Tile> getNeighbours(Tile tile) {

        List<Tile> neighbours = new ArrayList<>();

        int[] points = new int[] {
                -1,-1,
                -1,0,
                -1,1,
                0,-1,
                0,1,
                1,-1,
                1,0,
                1,1
        };

        for (int i = 0; i < points.length; i++) {
            int dx = points [i];
            int dy = points [++i];

            int newX = tile.x + dx;
            int newY = tile.y + dy;

            if (isValid (newX,newY)) {
                neighbours.add(grid[newX][newY]);
            }
        }

        return neighbours;
    }

    public static boolean isValid(int x, int y){
        return (x >= 0 && x < horizontalTiles && y >= 0 && y < verticalTiles);
    }
}
