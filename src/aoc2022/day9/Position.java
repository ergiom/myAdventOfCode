package aoc2022.day9;

public class Position {
    private static final Position ZERO;
    private final int row;
    private final int column;

    static {
         ZERO = new Position(0, 0);
    }
    Position(int x, int y) {
        this.row = x;
        this.column = y;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Position delta(Position reference) {
        int dx = row - reference.getRow();
        int dy = column - reference.getColumn();

        return new Position(dx, dy);
    }

    public double length() {
        return length(ZERO);
    }

    public double length(Position reference) {
        double drow = row - reference.getRow();
        double dcol = column - reference.getColumn();

        return Math.sqrt(Math.pow(drow, 2) + Math.pow(dcol, 2));
    }
}
