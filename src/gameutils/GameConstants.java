package gameutils;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameConstants {
    public final int FIELD_HEIGHT, FIELD_WIDTH, CELL_SIZE, CELL_DISTANCE, X, Y;
    public final Color FIELD_COLOR;
    public final Font FONT;

    public GameConstants(int X, int Y, int FIELD_HEIGHT, int FIELD_WIDTH, int CELL_SIZE, int CELL_DISTANCE, Color FIELD_COLOR, Font FONT) {
        this.X = X;
        this.Y = Y;
        this.CELL_DISTANCE = CELL_DISTANCE;
        this.CELL_SIZE = CELL_SIZE;
        this.FIELD_HEIGHT = FIELD_HEIGHT;
        this.FIELD_WIDTH = FIELD_WIDTH;
        this.FIELD_COLOR = FIELD_COLOR;
        this.FONT = FONT;
    }
}