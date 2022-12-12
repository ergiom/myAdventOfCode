package aoc2022.day12;

public class HeightMap {
    private final int[][] map;
    private final Position start;
    private final Position end;

    HeightMap(int[][] map, Position start, Position end) {
        this.map = map;
        this.start = start;
        this.end = end;
    }

    public int getHeight(Position position) {
        return map[position.getRow()][position.getColumn()];
    }

    public Position getStart() {
        return start;
    }

    public Position getEnd() {
        return end;
    }

    public boolean onMap(Position position) {
        int row = position.getRow();
        int column = position.getColumn();

        if (row < 0 || row >= map.length) return false;
        if (column < 0 || column >= map[0].length) return false;

        return true;
    }
}
