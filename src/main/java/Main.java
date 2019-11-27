import controls.ControlModule;
import controls.ControlScheme;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import gameutils.Game;
import gameutils.GameConstants;

public class Main extends Application {

    public interface allin{
        int drinkVodka(String kogda);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Tetris");
        //stage.setResizable(false);
        //stage.setWidth(consts.CELL_SIZE * (consts.FIELD_WIDTH + 5) + consts.CELL_DISTANCE * (consts.FIELD_WIDTH + 8) + 14);
        //stage.setHeight(consts.CELL_SIZE * consts.FIELD_HEIGHT + consts.CELL_DISTANCE * (consts.FIELD_HEIGHT + 3) + 38);

        GameConstants consts = new GameConstants(0, 0, 20, 10, 15, 3, Color.WHITE);
        GameConstants consts2 = new GameConstants(200, 0, 20, 10, 15, 3, Color.WHITE);
        GameConstants consts3 = new GameConstants(400, 0, 20, 10, 15, 3, Color.WHITE);
        GameConstants consts4 = new GameConstants(600, 0, 20, 10, 15, 3, Color.WHITE);
        GameConstants consts5 = new GameConstants(800, 0, 20, 10, 15, 3, Color.WHITE);
        GameConstants consts6 = new GameConstants(1000, 0, 20, 10, 15, 3, Color.WHITE);
        GameConstants consts7 = new GameConstants(1200, 0, 20, 10, 15, 3, Color.WHITE);
        GameConstants consts8 = new GameConstants(0, 400, 20, 10, 15, 3, Color.WHITE);
        GameConstants consts9 = new GameConstants(200, 400, 20, 10, 15, 3, Color.WHITE);
        GameConstants consts10 = new GameConstants(400, 400, 20, 10, 15, 3, Color.WHITE);
        GameConstants consts11 = new GameConstants(600, 400, 20, 10, 15, 3, Color.WHITE);
        GameConstants consts12 = new GameConstants(800, 400, 20, 10, 15, 3, Color.WHITE);
        GameConstants consts13 = new GameConstants(1000, 400, 20, 10, 15, 3, Color.WHITE);
        GameConstants consts14 = new GameConstants(1200, 400, 20, 10, 15, 3, Color.WHITE);
        new Game(consts, new ControlScheme(KeyCode.S, KeyCode.A, KeyCode.D, KeyCode.W));
        new Game(consts2, new ControlScheme(KeyCode.S, KeyCode.A, KeyCode.D, KeyCode.W));
        new Game(consts3, new ControlScheme(KeyCode.S, KeyCode.A, KeyCode.D, KeyCode.W));
        new Game(consts4, new ControlScheme(KeyCode.S, KeyCode.A, KeyCode.D, KeyCode.W));
        new Game(consts5, new ControlScheme(KeyCode.S, KeyCode.A, KeyCode.D, KeyCode.W));
        new Game(consts6, new ControlScheme(KeyCode.S, KeyCode.A, KeyCode.D, KeyCode.W));
        new Game(consts7, new ControlScheme(KeyCode.S, KeyCode.A, KeyCode.D, KeyCode.W));
        new Game(consts8, new ControlScheme(KeyCode.S, KeyCode.A, KeyCode.D, KeyCode.W));
        new Game(consts9, new ControlScheme(KeyCode.S, KeyCode.A, KeyCode.D, KeyCode.W));
        new Game(consts10, new ControlScheme(KeyCode.S, KeyCode.A, KeyCode.D, KeyCode.W));
        new Game(consts11, new ControlScheme(KeyCode.S, KeyCode.A, KeyCode.D, KeyCode.W));
        new Game(consts12, new ControlScheme(KeyCode.S, KeyCode.A, KeyCode.D, KeyCode.W));
        new Game(consts13, new ControlScheme(KeyCode.S, KeyCode.A, KeyCode.D, KeyCode.W));
        new Game(consts14, new ControlScheme(KeyCode.S, KeyCode.A, KeyCode.D, KeyCode.W));

        Group rootGroup = new Group();
        for (Game game : Game.getGameArray()) {
            rootGroup.getChildren().add(game.getGroup());
        }
        Scene tetrisScene = new Scene(rootGroup);
        tetrisScene.setFill(Color.BLACK);
        tetrisScene.setOnKeyPressed(ControlModule.keyEventHandler);

        //SHOWING TETRIS SCENE
        stage.setScene(tetrisScene);
        //stage.setFullScreen(true);
        stage.show();
        Game.startAll();
    }

    @Override
    public void stop() {
        Game.stopAll();
    }
}