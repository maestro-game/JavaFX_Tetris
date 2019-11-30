package controls;

import gameutils.Game;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import models.MovementTypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ControlModule {

    private static class ControlUnit {
        private Game game;
        private MovementTypes type;

        private ControlUnit(Game game, MovementTypes type) {
            this.game = game;
            this.type = type;
        }
    }

    private static HashMap<KeyCode, ArrayList<ControlUnit>> controlBind = new HashMap<>();

    public static void bindControlUnit(KeyCode keyCode, ControlUnit controlUnit) {
        ArrayList<ControlUnit> node;
        node = controlBind.get(keyCode);
        if (node != null) {
            node.add(controlUnit);
        } else {
            controlBind.put(keyCode, new ArrayList<>(Arrays.asList(controlUnit)));
        }
    }

    public static final EventHandler<KeyEvent> keyEventHandler = keyEvent -> {
        ArrayList<ControlUnit> damir = controlBind.get(keyEvent.getCode());
        if (damir != null) {
            for (ControlUnit controlUnit : damir) {
                switch (controlUnit.type) {
                    case DOWN -> controlUnit.game.moveDown();
                    case LEFT -> controlUnit.game.moveLeft();
                    case RIGHT -> controlUnit.game.moveRight();
                    case ROTATE -> controlUnit.game.rotate();
                    case DROP -> controlUnit.game.drop();
                }
            }
        }
    };

    public static void addControlScheme(Game game, ControlScheme controlScheme) {
        bindControlUnit(controlScheme.down, new ControlUnit(game, MovementTypes.DOWN));
        bindControlUnit(controlScheme.left, new ControlUnit(game, MovementTypes.LEFT));
        bindControlUnit(controlScheme.right, new ControlUnit(game, MovementTypes.RIGHT));
        bindControlUnit(controlScheme.rotate, new ControlUnit(game, MovementTypes.ROTATE));
        bindControlUnit(controlScheme.drop, new ControlUnit(game, MovementTypes.DROP));
    }
}
