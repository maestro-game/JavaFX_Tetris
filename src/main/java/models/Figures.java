package models;

import javafx.scene.paint.Color;

public enum Figures {

    O(new Cell[]{
            new Cell(1, 1),
            new Cell(2, 1),
            new Cell(1, 2),
            new Cell(2, 2)},
            Color.YELLOW, new int[]{1, 2, 1, 2}
    ),
    I(new Cell[]{
            new Cell(1, 0),
            new Cell(1, 1),
            new Cell(1, 2),
            new Cell(1, 3)},
            Color.CYAN, new int[]{0, 3, 1, 1}
    ),
    S(new Cell[]{
            new Cell(3, 1),
            new Cell(2, 1),
            new Cell(1, 2),
            new Cell(2, 2)},
            Color.GREEN, new int[]{1, 2, 1, 3}
    ),
    Z(new Cell[]{
            new Cell(1, 1),
            new Cell(2, 1),
            new Cell(3, 2),
            new Cell(2, 2)},
            Color.RED, new int[]{1, 2, 1, 3}
    ),
    L(new Cell[]{
            new Cell(1, 1),
            new Cell(1, 0),
            new Cell(1, 2),
            new Cell(2, 2)},
            Color.ORANGE, new int[]{0, 2, 1, 2}
    ),
    J(new Cell[]{
            new Cell(2, 0),
            new Cell(2, 1),
            new Cell(1, 2),
            new Cell(2, 2)},
            Color.BLUE, new int[]{0, 2, 1, 2}
    ),
    T(new Cell[]{
            new Cell(1, 1),
            new Cell(2, 1),
            new Cell(3, 1),
            new Cell(2, 2)},
            Color.PURPLE, new int[]{1, 2, 1, 3}
    )
    ;
    private Cell[] cells;
    private Color color;
    private int[] borders;

    Figures(Cell[] cells, Color color, int[] borders) {
        this.cells = cells;
        this.color = color;
        this.borders = borders;
    }

    public Cell[] getCells() {
        return cells;
    }

    public Color getColor() {
        return color;
    }

    public int[] getBorders() {
        return borders;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}