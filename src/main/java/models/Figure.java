package models;

import javafx.scene.paint.Color;

public class Figure {
    private Cell[] cells;
    private Color color;
    private int downBorder, leftBorder, rightBorder, upBorder;

    public void rotate() {
        int swp = leftBorder;
        leftBorder = 3 - downBorder;
        downBorder = rightBorder;
        rightBorder = 3 - upBorder;
        upBorder = swp;
        for (Cell cell : cells) {
            int newX = 3 - cell.getY();
            cell.setY(cell.getX());
            cell.setX(newX);
        }
    }

    public int getUpBorder() {
        return upBorder;
    }

    public int getDownBorder() {
        return downBorder;
    }

    public int getLeftBorder() {
        return leftBorder;
    }

    public int getRightBorder() {
        return rightBorder;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Cell[] getCells() {
        return cells;
    }

    public Figure(Figures figure) {
        this.cells = figure.getCells();
        this.color = figure.getColor();
        int[] borders = figure.getBorders();
        this.downBorder = borders[0];
        this.leftBorder = borders[1];
        this.rightBorder = borders[2];
        this.upBorder = borders[3];
    }

    // TODO: CUSTOM FIGURES CONSTRUCTOR
    public Figure() {
        this.cells = new Cell[]{
                new Cell(-1,-1),
                new Cell(-1,-1),
                new Cell(-1,-1),
                new Cell(-1,-1)};
        this.downBorder = -1;
        this.leftBorder = -1;
        this.rightBorder = -1;
        this.upBorder = -1;
        this.color = Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
    }
}
