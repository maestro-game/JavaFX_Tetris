import javafx.scene.paint.Color;
import models.Figures;

class Figure {
    private Field.Cell[] cells;
    private Color color;
    private int downBorder, leftBorder, rightBorder, upBorder;

    public int getUpBorder() {
        return upBorder;
    }

    public void rotate() {
        int swp = leftBorder;
        leftBorder = 3 - downBorder;
        downBorder = rightBorder;
        rightBorder = 3 - upBorder;
        upBorder = swp;
        for (Field.Cell cell : cells) {
            int newX = 3 - cell.y;
            cell.y = cell.x;
            cell.x = newX;
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

    public Field.Cell[] getCells() {
        return cells;
    }

    public Figure(Figures figure) {
        switch (figure) {
            case O -> {
                cells = new Field.Cell[]{
                        new Field.Cell(1, 1),
                        new Field.Cell(2, 1),
                        new Field.Cell(1, 2),
                        new Field.Cell(2, 2)
                };
                this.upBorder = 1;
                this.downBorder = 2;
                this.leftBorder = 1;
                this.rightBorder = 2;
            }
            case I -> {
                cells = new Field.Cell[]{
                        new Field.Cell(1, 0),
                        new Field.Cell(1, 1),
                        new Field.Cell(1, 2),
                        new Field.Cell(1, 3)
                };
                this.upBorder = 0;
                this.downBorder = 3;
                this.leftBorder = 1;
                this.rightBorder = 1;
            }
            case S -> {
                cells = new Field.Cell[]{
                        new Field.Cell(3, 1),
                        new Field.Cell(2, 1),
                        new Field.Cell(1, 2),
                        new Field.Cell(2, 2)
                };
                this.upBorder = 1;
                this.downBorder = 2;
                this.leftBorder = 1;
                this.rightBorder = 3;
            }
            case Z -> {
                cells = new Field.Cell[]{
                        new Field.Cell(1, 1),
                        new Field.Cell(2, 1),
                        new Field.Cell(3, 2),
                        new Field.Cell(2, 2)
                };
                this.upBorder = 1;
                this.downBorder = 2;
                this.leftBorder = 1;
                this.rightBorder = 3;
            }
            case L -> {
                cells = new Field.Cell[]{
                        new Field.Cell(1, 1),
                        new Field.Cell(1, 0),
                        new Field.Cell(1, 2),
                        new Field.Cell(2, 2)
                };
                this.upBorder = 0;
                this.downBorder = 2;
                this.leftBorder = 1;
                this.rightBorder = 2;
            }
            case J -> {
                cells = new Field.Cell[]{
                        new Field.Cell(2, 0),
                        new Field.Cell(2, 1),
                        new Field.Cell(1, 2),
                        new Field.Cell(2, 2)
                };
                this.upBorder = 0;
                this.downBorder = 2;
                this.leftBorder = 1;
                this.rightBorder = 2;
            }
            case T -> {
                cells = new Field.Cell[]{
                        new Field.Cell(1, 1),
                        new Field.Cell(2, 1),
                        new Field.Cell(3, 1),
                        new Field.Cell(2, 2)
                };
                this.upBorder = 1;
                this.downBorder = 2;
                this.leftBorder = 1;
                this.rightBorder = 3;
            }
        }
        this.color = Color.rgb((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255));
    }
}
