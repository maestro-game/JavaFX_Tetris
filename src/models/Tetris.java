package models;

import gameutils.GameConstants;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

public class Tetris {

    private Figure
            O = new Figure(Figures.O),
            I = new Figure(Figures.I),
            S = new Figure(Figures.S),
            Z = new Figure(Figures.Z),
            L = new Figure(Figures.L),
            J = new Figure(Figures.J),
            T = new Figure(Figures.T);

    private Figure[] figure = {O, I, S, Z, L, T, J};
    private Rectangle[][] field;
    private Group group;
    private Figure currentFigure;
    private Cell curFigPos;
    private GameConstants consts;
    private Figure nextFigure;

    public Group getGroup() {
        return group;
    }

    public void delFullLines(){
    }

    private boolean rotatable() {
        int newX, newY;
        if (3 - currentFigure.getDownBorder() + curFigPos.getX() < 0 || currentFigure.getRightBorder() + curFigPos.getY() >= consts.FIELD_HEIGHT ||
                curFigPos.getX() + 3 - currentFigure.getUpBorder() >= consts.FIELD_WIDTH || curFigPos.getY() + currentFigure.getLeftBorder() < 0) {
            return false;
        }
        for (Cell cell : currentFigure.getCells()) {
            newX = 3 - cell.getY() + curFigPos.getX();
            newY = cell.getX() + curFigPos.getY();
            if (field[newX][newY].getFill() != consts.FIELD_COLOR) {
                return false;
            }
        }
        return true;
    }

    private void lockFigure() {
        currentFigure = nextFigure;
    }

    public void newFigure() {
        currentFigure = nextFigure;
        nextFigure = figure[(int) Math.floor((Math.random() * 6))];
        //TODO интересеный выбор цвета для фигуры
        curFigPos = new Cell(consts.FIELD_WIDTH / 2 - 2, 0);
    }

    private void clearCurFig() {
        for (Cell cell : currentFigure.getCells()) {
            field[curFigPos.getX() + cell.getX()][curFigPos.getY() + cell.getY()].setFill(consts.FIELD_COLOR);
        }
    }

    private void drawCurFig() {
        for (Cell cell : currentFigure.getCells()) {
            field[curFigPos.getX() + cell.getX()][curFigPos.getY() + cell.getY()].setFill(currentFigure.getColor());
        }
    }

    public void moveDown() {
        if (currentFigure.getDownBorder() + curFigPos.getY() < consts.FIELD_HEIGHT - 1) {
            clearCurFig();
            for (Cell cell : currentFigure.getCells()) {
                if (field[cell.getX() + curFigPos.getX()][cell.getY() + curFigPos.getY() + 1].getFill() != consts.FIELD_COLOR) {
                    drawCurFig();
                    return;
                }
            }
            //moving
            curFigPos.setY(curFigPos.getY() + 1);
            drawCurFig();
        } else {
            lockFigure();
            delFullLines();
            newFigure();
            drawCurFig();
        }
    }

    public void moveRight() {
        if (currentFigure.getRightBorder() + curFigPos.getX() < consts.FIELD_WIDTH - 1) {
            clearCurFig();
            for (Cell cell : currentFigure.getCells()) {
                if (field[cell.getX() + curFigPos.getX() + 1][cell.getY() + curFigPos.getY()].getFill() != consts.FIELD_COLOR) {
                    drawCurFig();
                    return;
                }
            }
            //moving
            curFigPos.setX(curFigPos.getX() + 1);
            drawCurFig();
        }
    }

    public void moveLeft() {
        if (currentFigure.getLeftBorder() + curFigPos.getX() > 0) {
            clearCurFig();
            for (Cell cell : currentFigure.getCells()) {
                if (field[cell.getX() + curFigPos.getX() - 1][cell.getY() + curFigPos.getY()].getFill() != consts.FIELD_COLOR) {
                    drawCurFig();
                    return;
                }
            }
            //moving
            curFigPos.setX(curFigPos.getX() - 1);
            drawCurFig();
        }
    }

    public void rotate() {
        clearCurFig();
        if (rotatable()) {
            currentFigure.rotate();
        }
        drawCurFig();
    }

    public Tetris(GameConstants consts) {
        this.consts = consts;
        field = new Rectangle[consts.FIELD_WIDTH][consts.FIELD_HEIGHT];
        group = new Group();
        for (int i = 0; i < consts.FIELD_WIDTH; i++) {
            for (int j = 0; j < consts.FIELD_HEIGHT; j++) {
                field[i][j] = new Rectangle(consts.X + consts.CELL_SIZE * i + consts.CELL_DISTANCE * (i + 2), consts.Y +
                        consts.CELL_SIZE * j + consts.CELL_DISTANCE * (j + 2), consts.CELL_SIZE, consts.CELL_SIZE);
                field[i][j].setFill(consts.FIELD_COLOR);
                group = new Group(group, field[i][j]);
            }
        }
    }
}