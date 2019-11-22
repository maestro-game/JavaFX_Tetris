import javafx.scene.paint.Color;

public class GameConstants {
    final int FIELD_HEIGHT, FIELD_WIDTH, CELL_SIZE, CELL_DISTANCE, X, Y;
    final Color FIELD_COLOR;

    public GameConstants(int X, int Y, int FIELD_HEIGHT, int FIELD_WIDTH, int CELL_SIZE, int CELL_DISTANCE, Color FIELD_COLOR) {
        this.X = X;
        this.Y = Y;
        this.CELL_DISTANCE = CELL_DISTANCE;
        this.CELL_SIZE = CELL_SIZE;
        this.FIELD_HEIGHT = FIELD_HEIGHT;
        this.FIELD_WIDTH = FIELD_WIDTH;
        this.FIELD_COLOR = FIELD_COLOR;
    }
}