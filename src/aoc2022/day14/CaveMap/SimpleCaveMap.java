package aoc2022.day14.CaveMap;

import java.util.LinkedList;
import java.util.List;

public class SimpleCaveMap {
    private final List<List<Node>> map;
    private final Position sandCreator;
    private final Node defaultNode;
    private int sandCount;

    public SimpleCaveMap(CaveMapBuilder builder, Position sandCreator, Node defaultNode) {
        map = builder.getMap();
        this.sandCreator = sandCreator;
        this.defaultNode = defaultNode;
    }

    public void setPoint(Position point, Node value) {
        if (!onMap(point)) {
            expand(point.getRow() - getHeight() + 1, point.getColumn() - getWidth() + 1, defaultNode);
        }

        map.get(point.getRow()).set(point.getColumn(), value);
    }

    public Node getPoint(Position point) {
        if (!onMap(point)) return null;

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
        else if (!down.equals(position)) {
            return down;
        }

        if (left == null) return null;
        else if (!left.equals(position)) {
            return left;
        }

        if (right == null) return null;
        else if (!right.equals(position)) {
            return right;
        }

        return position;
    }

    private Position goDown(Position position) {
        Position newPosition = new Position(position.getRow() + 1, position.getColumn());
        if (!onMap(newPosition)) return null;

        Node node = getPoint(newPosition);
        return node.isEmpty() ? newPosition : position;
    }

    private Position goLeft(Position position) {
        Position newPosition = new Position(position.getRow() + 1, position.getColumn() - 1);
        if (!onMap(newPosition)) return null;

        Node node = getPoint(newPosition);
        return node.isEmpty() ? newPosition : position;
    }

    private Position goRight(Position position) {
        Position newPosition = new Position(position.getRow() + 1, position.getColumn() + 1);
        if (!onMap(newPosition)) return null;

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
        for (List<Node> row : map) {
            while (row.size() < width + columns) {
                row.add(value);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (List<Node> row : map) {
            for (Node node : row) {
                builder.append(node);
            }
            builder.append("\n");
        }

        return builder.toString();
    }
}
