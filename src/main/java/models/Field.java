package models;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import gameutils.GameConstants;

public class Field {

    private Figure
            O = new Figure(Figures.O),
            I = new Figure(Figures.I),
            S = new Figure(Figures.S),
            Z = new Figure(Figures.Z),
            L = new Figure(Figures.L),
            J = new Figure(Figures.J),
            T = new Figure(Figures.T);

    private Figure[] figure = {O, I, S, Z, L, T, J};
    private Rectangle[][] pol;
    private Group group;
    private Figure currentFigure;
    private Cell curFigPos;
    private GameConstants consts;
    private Figure nextFigure;

    public Group getGroup() {
        return group;
    }

    private boolean rotatable() {
        int newX, newY;
        if (3 - currentFigure.getDownBorder() + curFigPos.x < 0 || currentFigure.getRightBorder() + curFigPos.y >= consts.FIELD_HEIGHT ||
                curFigPos.x + 3 - currentFigure.getUpBorder() >= consts.FIELD_WIDTH || curFigPos.y + currentFigure.getLeftBorder() < 0) {
            return false;
        }
        for (Cell cell : currentFigure.getCells()) {
            newX = 3 - cell.y + curFigPos.x;
            newY = cell.x + curFigPos.y;
            if (pol[newX][newY].getFill() != consts.FIELD_COLOR) {
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
            pol[curFigPos.x + cell.x][curFigPos.y + cell.y].setFill(consts.FIELD_COLOR);
        }
    }

    private void drawCurFig() {
        for (Cell cell : currentFigure.getCells()) {
            pol[curFigPos.x + cell.x][curFigPos.y + cell.y].setFill(currentFigure.getColor());
        }
    }

    public void moveDown() {
        if (currentFigure.getDownBorder() + curFigPos.y < consts.FIELD_HEIGHT - 1) {
            clearCurFig();
            for (Cell cell : currentFigure.getCells()) {
                if (pol[cell.x + curFigPos.x][cell.y + curFigPos.y + 1].getFill() != consts.FIELD_COLOR) {
                    drawCurFig();
                    return;
                }
            }
            //moving
            curFigPos.y++;
            drawCurFig();
        } else {
            //stack figure
            lockFigure();
            newFigure();
            drawCurFig();
        }
    }

    public void moveRight() {
        if (currentFigure.getRightBorder() + curFigPos.x < consts.FIELD_WIDTH - 1) {
            clearCurFig();
            for (Cell cell : currentFigure.getCells()) {
                if (pol[cell.x + curFigPos.x + 1][cell.y + curFigPos.y].getFill() != consts.FIELD_COLOR) {
                    drawCurFig();
                    return;
                }
            }
            //moving
            curFigPos.x++;
            drawCurFig();
        }
    }

    public void moveLeft() {
        if (currentFigure.getLeftBorder() + curFigPos.x > 0) {
            clearCurFig();
            for (Cell cell : currentFigure.getCells()) {
                if (pol[cell.x + curFigPos.x - 1][cell.y + curFigPos.y].getFill() != consts.FIELD_COLOR) {
                    drawCurFig();
                    return;
                }
            }
            //moving
            curFigPos.x--;
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

    public Field(GameConstants consts) {
        this.consts = consts;
        pol = new Rectangle[consts.FIELD_WIDTH][consts.FIELD_HEIGHT];
        group = new Group();
        for (int i = 0; i < consts.FIELD_WIDTH; i++) {
            for (int j = 0; j < consts.FIELD_HEIGHT; j++) {
                pol[i][j] = new Rectangle(consts.X + consts.CELL_SIZE * i + consts.CELL_DISTANCE * (i + 2), consts.Y + consts.CELL_SIZE * j + consts.CELL_DISTANCE * (j + 2), consts.CELL_SIZE, consts.CELL_SIZE);
                pol[i][j].setFill(consts.FIELD_COLOR);
                group = new Group(group, pol[i][j]);
            }
        }
    }
}