package models;

import gameutils.GameConstants;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Tetris {

    private Figure
            O = new Figure(Figures.O),
            I = new Figure(Figures.I),
            S = new Figure(Figures.S),
            Z = new Figure(Figures.Z),
            L = new Figure(Figures.L),
            J = new Figure(Figures.J),
            T = new Figure(Figures.T);

    private Text scoreField;
    private Figure[] figure = {O, I, S, Z, L, T, J};
    private Rectangle[][] field, nextFigureField; //array of lines ( [y][x] )
    private Group group;
    private Figure currentFigure;
    private Cell curFigPos;
    private GameConstants consts;
    private Figure nextFigure;
    private int score;

    public int getScore() {
        return score;
    }

    public Group getGroup() {
        return group;
    }

    public int delFullLines() {
        int score = 0;
        for (int i = curFigPos.getY() + currentFigure.getDownBorder(); i >= curFigPos.getY() + currentFigure.getUpBorder(); i--) {
            int j;
            for (j = 0; j < consts.FIELD_WIDTH; j++) {
                if (field[i][j].getFill() == consts.FIELD_COLOR) {
                    break;
                }
            }
            if (j == consts.FIELD_WIDTH) {
                score++;
            } else {
                for (int k = 0; k < consts.FIELD_WIDTH; k++) {
                    field[i + score][k].setFill(field[i][k].getFill());
                }
            }
        }
        if (score == 0) {
            return score;
        }

        for (int i = curFigPos.getY() + currentFigure.getUpBorder() - 1; i >= 0; i--) {
            for (int k = 0; k < consts.FIELD_WIDTH; k++) {
                field[i + score][k].setFill(field[i][k].getFill());
            }
        }
        return score;
    }

    private boolean rotatable() {
        int newX, newY;
        if (3 - currentFigure.getDownBorder() + curFigPos.getX() < 0 ||
                currentFigure.getRightBorder() + curFigPos.getY() >= consts.FIELD_HEIGHT ||
                curFigPos.getX() + 3 - currentFigure.getUpBorder() >= consts.FIELD_WIDTH ||
                curFigPos.getY() + currentFigure.getLeftBorder() < 0) {
            return false;
        }
        for (Cell cell : currentFigure.getCells()) {
            newX = 3 - cell.getY() + curFigPos.getX();
            newY = cell.getX() + curFigPos.getY();
            if (field[newY][newX].getFill() != consts.FIELD_COLOR) {
                return false;
            }
        }
        return true;
    }

    private void nextFigure() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                nextFigureField[j][i].setFill(consts.FIELD_COLOR);
            }
        }
        currentFigure = nextFigure;
        nextFigure = figure[(int) Math.floor((Math.random() * 6))];
        for (Cell cell : nextFigure.getCells()){
            nextFigureField[cell.getY()][cell.getX()].setFill(nextFigure.getColor());
        }
        //TODO интересеный выбор цвета для фигуры
        curFigPos = new Cell(consts.FIELD_WIDTH / 2 - 2, 0);
    }

    private void clearCurFig() {
        for (Cell cell : currentFigure.getCells()) {
            field[curFigPos.getY() + cell.getY()][curFigPos.getX() + cell.getX()].setFill(consts.FIELD_COLOR);
        }
    }

    private void drawCurFig() {
        for (Cell cell : currentFigure.getCells()) {
            field[curFigPos.getY() + cell.getY()][curFigPos.getX() + cell.getX()].setFill(currentFigure.getColor());
        }
    }

    public void moveDown() {
        if (currentFigure.getDownBorder() + curFigPos.getY() < consts.FIELD_HEIGHT - 1) meWhole:{
            clearCurFig();
            for (Cell cell : currentFigure.getCells()) {
                if (field[cell.getY() + curFigPos.getY() + 1][cell.getX() + curFigPos.getX()].getFill() != consts.FIELD_COLOR) {
                    drawCurFig();
                    break meWhole;
                }
            }
            //moving
            curFigPos.setY(curFigPos.getY() + 1);
            drawCurFig();
            return;
        }
        score += delFullLines();
        scoreField.setText("" + score);
        nextFigure();
        drawCurFig();
    }

    public void drop(){
        Figure f = currentFigure;
        while (currentFigure == f){
            moveDown();
        }
    }

    public void moveRight() {
        if (currentFigure.getRightBorder() + curFigPos.getX() < consts.FIELD_WIDTH - 1) {
            clearCurFig();
            for (Cell cell : currentFigure.getCells()) {
                if (field[cell.getY() + curFigPos.getY()][cell.getX() + curFigPos.getX() + 1].getFill() != consts.FIELD_COLOR) {
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
                if (field[cell.getY() + curFigPos.getY()][cell.getX() + curFigPos.getX() - 1].getFill() != consts.FIELD_COLOR) {
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

    public void initialise(){
        nextFigure = figure[(int) Math.floor((Math.random() * 6))];
        currentFigure = O;
        scoreField.setX(consts.X + consts.CELL_SIZE * (consts.FIELD_WIDTH) +
                consts.CELL_DISTANCE * (consts.FIELD_WIDTH + 4));
        scoreField.setY(consts.Y + consts.CELL_SIZE * 7 + consts.CELL_DISTANCE * 9);
        scoreField.setFill(consts.FIELD_COLOR);
        scoreField.setFont(consts.FONT);
        scoreField.setText("0");
        nextFigure();
        drawCurFig();
    }

    public Tetris(GameConstants consts) {
        this.consts = consts;
        field = new Rectangle[consts.FIELD_HEIGHT][consts.FIELD_WIDTH];
        scoreField = new Text();
        group = new Group(scoreField);
        for (int i = 0; i < consts.FIELD_HEIGHT; i++) {
            for (int j = 0; j < consts.FIELD_WIDTH; j++) {
                field[i][j] = new Rectangle(consts.X + consts.CELL_SIZE * j + consts.CELL_DISTANCE * (j + 2),
                        consts.Y + consts.CELL_SIZE * i + consts.CELL_DISTANCE * (i + 2), consts.CELL_SIZE, consts.CELL_SIZE);
                field[i][j].setFill(consts.FIELD_COLOR);
                group = new Group(group, field[i][j]);
            }
        }
        nextFigureField = new Rectangle[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                nextFigureField[i][j] = new Rectangle(consts.X + consts.CELL_SIZE * (consts.FIELD_WIDTH + j) +
                        consts.CELL_DISTANCE * (consts.FIELD_WIDTH + j + 4),
                        consts.Y + consts.CELL_SIZE * i + consts.CELL_DISTANCE * (i + 4),
                        consts.CELL_SIZE, consts.CELL_SIZE);
                nextFigureField[i][j].setFill(consts.FIELD_COLOR);
                group = new Group(group, nextFigureField[i][j]);
            }
        }
    }
}