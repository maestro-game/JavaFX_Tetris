package controls;

import javafx.scene.input.KeyCode;

public class ControlScheme {
    KeyCode down, left, right, rotate, drop;

    public ControlScheme(KeyCode down, KeyCode left, KeyCode right, KeyCode rotate, KeyCode drop) {
        this.down = down;
        this.left = left;
        this.right = right;
        this.rotate = rotate;
        this.drop = drop;
    }
}
