package gameutils;

import controls.ControlModule;
import controls.ControlScheme;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.util.Duration;
import models.Field;

import java.util.ArrayList;

public class Game {
    private Field field;
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
        field.moveDown();
        timer.stop();
        timer.play();
    }

    public void moveLeft() {
        field.moveLeft();
    }

    public void moveRight() {
        field.moveRight();
    }

    public void rotate() {
        field.rotate();
    }

    public void stop() {
        timer.stop();
    }

    public Game(GameConstants consts, ControlScheme controlScheme) {
        gameArray.add(this);
        timer = new Timeline(new KeyFrame(Duration.millis(800), actionEvent -> field.moveDown()));
        timer.setCycleCount(Animation.INDEFINITE);
        field = new Field(consts);
        group = field.getGroup();
        ControlModule.addControlScheme(this, controlScheme);
        field.newFigure();
        field.newFigure();
    }
}