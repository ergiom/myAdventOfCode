package aoc2022.day14;

import java.util.LinkedList;
import java.util.List;

public class CaveMap {
    List<List<Node>> map;
    Position sandCreator;
    Node defaultNode;
    int sandCount;

    CaveMap() {
        map = new LinkedList<>();
        sandCreator = new Position(0, 500);
        defaultNode = Node.AIR;
        sandCount = 0;
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

    public Node getPoint(Position point) {
        if (! onMap(point)) return null;

        return map.get(point.getRow()).get(point.getColumn());
    }

    public void simulate() {
        sandCount = 0;
        while (true) {
            Position destination = simulateSandBlock();
            if (destination == null) break;

            setPoint(destination, Node.SAND);
            sandCount++;
        }
    }

    public void simulateWithFloor() {
        expand(1, 0, Node.AIR);
        expand(1, 0, Node.WALL);

        sandCount = 0;
        while (true) {
            Position destination = simulateSandBlock();
            if (destination == sandCreator) break;

            System.out.println(this);
            setPoint(destination, Node.SAND);
            sandCount++;
        }
    }

    private Position simulateSandBlock() {
        Position current = sandCreator;

        while (true) {
            Position next = nextSandPosition(current);

            if (next == null) return null;
            else if (next.equals(current)) return current;

            current = next;
        }
    }

    private Position nextSandPosition(Position position) {
        Position down = goDown(position);
        Position left = goLeft(position);
        Position right = goRight(position);

        if (down == null) return null;
        else if (! down.equals(position)) {
            return down;
        }

        if (left == null) return null;
        else if (! left.equals(position)) {
            return left;
        }

        if (right == null) return null;
        else if (! right.equals(position)) {
            return right;
        }

        return position;
    }

    private Position goDown(Position position) {
        Position newPosition = new Position(position.getRow() + 1, position.getColumn());
        if (! onMap(newPosition)) return null;

        Node node = getPoint(newPosition);
        return node.isEmpty() ? newPosition : position;
    }

    private Position goLeft(Position position) {
        Position newPosition = new Position(position.getRow() + 1, position.getColumn() - 1);
        if (! onMap(newPosition)) return null;

        Node node = getPoint(newPosition);
        return node.isEmpty() ? newPosition : position;
    }

    private Position goRight(Position position) {
        Position newPosition = new Position(position.getRow() + 1, position.getColumn() + 1);
        if (! onMap(newPosition)) return null;

        Node node = getPoint(newPosition);
        return node.isEmpty() ? newPosition : position;
    }

    public int getSandNumber() {
        return sandCount;
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
}
