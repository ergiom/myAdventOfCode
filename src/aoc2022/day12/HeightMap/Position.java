package aoc2022.day12.HeightMap;

public class Position {
    public final static Position INVALID;
    private final int row;
    private final int column;

    static {
        INVALID = new Position(-1, -1);
    }

    public Position(int row, int column) {
        this.column = column;
        this.row = row;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean equals(Position position) {
        return this.row == position.row && this.column == position.column;
    }
}
