package aoc2022.day14.CaveMap;

import java.util.LinkedList;
import java.util.List;

public class CaveMapBuilder {
    private final List<List<Node>> map;
    private final Node defaultNode;

    CaveMapBuilder() {
        map = new LinkedList<>();
        defaultNode = Node.AIR;
    }

    public void drawLine(Position start, Position stop, Node value) {
        if (start.getColumn() == stop.getColumn()) {
            int column = start.getColumn();
            int startRow = Math.min(start.getRow(), stop.getRow());
            int stopRow = Math.max(start.getRow(), stop.getRow());

            drawColumn(column, startRow, stopRow, value);
        }
        else if (start.getRow() == stop.getRow()) {
            int row = start.getRow();
            int startColumn = Math.min(start.getColumn(), stop.getColumn());
            int stopColumn = Math.max(start.getColumn(), stop.getColumn());

            drawRow(row, startColumn, stopColumn, value);
        }
    }

    private void drawColumn(int column, int from, int to, Node value) {
        for (int row = from; row <= to; row++) {
            setPoint(new Position(row, column), value);
        }
    }

    private void drawRow(int row, int from, int to, Node value) {
        for (int column = from; column <= to; column++) {
            setPoint(new Position(row, column), value);
        }
    }

    public void setPoint(Position point, Node value) {
        if (! onMap(point)) {
            expand(point.getRow() - getHeight() + 1, point.getColumn() - getWidth() + 1, defaultNode);
        }

        map.get(point.getRow()).set(point.getColumn(), value);
    }

    public int getHeight() {
        return map.size();
    }

    public int getWidth() {
        if (getHeight() == 0) return 0;
        return map.get(0).size();
    }

    public boolean onMap(Position point) {
        if (point.getRow() >= getHeight() || point.getRow() < 0) return false;
        if (point.getColumn() >= getWidth() || point.getColumn() < 0) return false;
        return true;
    }

    private void expand(int rows, int columns, Node value) {
        rows = Math.max(rows, 0);
        columns = Math.max(columns, 0);

        for (int i = 0; i < rows; i++) {
            map.add(new LinkedList<>());
        }

        int width = getWidth();
        for (List<Node> row: map.subList(0, map.size())) {
            while (row.size() < width + columns) {
                row.add(value);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (List<Node> row: map) {
            for (Node node: row) {
                builder.append(node);
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    public List<List<Node>> getMap() {
        return map;
    }
}
