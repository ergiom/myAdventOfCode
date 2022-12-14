package aoc2022.day14.CaveMap;

public class Position {
    private final int row;
    private final int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean equals(Position position) {
        return (row == position.getRow()) && (column == position.getColumn());
    }
}
