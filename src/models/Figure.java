package models;

import javafx.scene.paint.Color;

public class Figure {
    private Cell[] cells;
    private Color color;
    private int downBorder, leftBorder, rightBorder, upBorder;

    public Figure(Figures figure) {
        switch (figure) {
            case O -> {
                cells = new Cell[]{
                        new Cell(1, 1),
                        new Cell(2, 1),
                        new Cell(1, 2),
                        new Cell(2, 2)
                };
                this.upBorder = 1;
                this.downBorder = 2;
                this.leftBorder = 1;
                this.rightBorder = 2;
            }
            case I -> {
                cells = new Cell[]{
                        new Cell(1, 0),
                        new Cell(1, 1),
                        new Cell(1, 2),
                        new Cell(1, 3)
                };
                this.upBorder = 0;
                this.downBorder = 3;
                this.leftBorder = 1;
                this.rightBorder = 1;
            }
            case S -> {
                cells = new Cell[]{
                        new Cell(3, 1),
                        new Cell(2, 1),
                        new Cell(1, 2),
                        new Cell(2, 2)
                };
                this.upBorder = 1;
                this.downBorder = 2;
                this.leftBorder = 1;
                this.rightBorder = 3;
            }
            case Z -> {
                cells = new Cell[]{
                        new Cell(1, 1),
                        new Cell(2, 1),
                        new Cell(3, 2),
                        new Cell(2, 2)
                };
                this.upBorder = 1;
                this.downBorder = 2;
                this.leftBorder = 1;
                this.rightBorder = 3;
            }
            case L -> {
                cells = new Cell[]{
                        new Cell(1, 1),
                        new Cell(1, 0),
                        new Cell(1, 2),
                        new Cell(2, 2)
                };
                this.upBorder = 0;
                this.downBorder = 2;
                this.leftBorder = 1;
                this.rightBorder = 2;
            }
            case J -> {
                cells = new Cell[]{
                        new Cell(2, 0),
                        new Cell(2, 1),
                        new Cell(1, 2),
                        new Cell(2, 2)
                };
                this.upBorder = 0;
                this.downBorder = 2;
                this.leftBorder = 1;
                this.rightBorder = 2;
            }
            case T -> {
                cells = new Cell[]{
                        new Cell(1, 1),
                        new Cell(2, 1),
                        new Cell(3, 1),
                        new Cell(2, 2)
                };
                this.upBorder = 1;
                this.downBorder = 2;
                this.leftBorder = 1;
                this.rightBorder = 3;
            }
        }
    }

    public Figure(Cell[] cells, Color color) {
        this.upBorder = 100;
        this.downBorder = -1;
        this.leftBorder = 100;
        this.rightBorder = -1;
        for (Cell cell : cells) {
            if (cell.getX() < upBorder) upBorder = cell.getX();
            if (cell.getX() > downBorder) downBorder = cell.getX();
            if (cell.getY() < leftBorder) leftBorder = cell.getY();
            if (cell.getY() > rightBorder) rightBorder = cell.getY();
        }
    }

    public int getUpBorder() {
        return upBorder;
    }

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
}
