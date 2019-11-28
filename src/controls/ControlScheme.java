package controls;

import javafx.scene.input.KeyCode;

public class ControlScheme {
    KeyCode down, left, right, rotate;

    public ControlScheme(KeyCode down, KeyCode left, KeyCode right, KeyCode rotate) {
        this.down = down;
        this.left = left;
        this.right = right;
        this.rotate = rotate;
    }
}
