package gameutils;

import controls.ControlModule;
import controls.ControlScheme;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.util.Duration;
import models.Tetris;

import java.util.ArrayList;

public class Game {
    private Tetris tetris;
    private Group group;
    Timeline timer;

    private static ArrayList<Game> gameArray = new ArrayList<>();

    public Group getGroup() {
        return group;
    }

    public static void stop(int number) {
        if (number < gameArray.size())
            gameArray.get(number).stop();
    }

    public static ArrayList<Game> getGameArray() {
        return gameArray;
    }

    public static void start(int number) {
        if (number < gameArray.size())
            gameArray.get(number).start();
    }

    public static void stopAll() {
        for (Game game : gameArray) {
            game.stop();
        }
    }

    public static void startAll() {
        for (Game game : gameArray) {
            game.start();
        }
    }

    public void start() {
        timer.play();
    }

    public void moveDown() {
        timer.stop();
        tetris.moveDown();
        timer.play();
    }

    public void moveLeft() {
        tetris.moveLeft();
    }

    public void moveRight() {
        tetris.moveRight();
    }

    public void drop(){
        timer.stop();
        tetris.drop();
        timer.play();
    }

    public void rotate() {
        tetris.rotate();
    }

    public void stop() {
        timer.stop();
    }

    public Game(GameConstants consts, ControlScheme controlScheme) {
        gameArray.add(this);
        timer = new Timeline(new KeyFrame(Duration.millis(800), actionEvent -> {
            tetris.moveDown();
        }));
        timer.setCycleCount(Animation.INDEFINITE);
        tetris = new Tetris(consts);
        group = tetris.getGroup();
        ControlModule.addControlScheme(this, controlScheme);
        tetris.initialise();
    }
}