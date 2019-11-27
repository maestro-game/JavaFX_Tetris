package controls;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import models.Types;
import gameutils.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ControlModule {

    private static class ControlUnit {
        private Game game;
        private Types type;

        private ControlUnit(Game game, Types type) {
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
            controlBind.put(keyCode, new ArrayList<>(Collections.singletonList(controlUnit)));
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
                }
            }
        }
    };

    public static void addControlScheme(Game game, ControlScheme controlScheme) {
        bindControlUnit(controlScheme.down, new ControlUnit(game, Types.DOWN));
        bindControlUnit(controlScheme.left, new ControlUnit(game, Types.LEFT));
        bindControlUnit(controlScheme.right, new ControlUnit(game, Types.RIGHT));
        bindControlUnit(controlScheme.rotate, new ControlUnit(game, Types.ROTATE));
    }
}
