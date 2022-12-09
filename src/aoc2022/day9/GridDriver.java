package aoc2022.day9;


import java.util.HashSet;
import java.util.Set;

public class GridDriver {
    private final Grid grid;
    private Position head;
    private Position tail;


    GridDriver() {
        grid = new Grid();
        head = new Position(0, 0);
        tail = new Position(0, 0);
    }


    public void parseMovement(String line) {
        String[] command = line.split(" ");
        Direction direction = parseDirection(command[0].charAt(0));
        int length = Integer.parseInt(command[1]);

        for (int i = 0; i < length; i++) {
            move(direction);
        }
    }

    public int getTailCount() {
        return getTailPositions().size();
    }


    private void expandGrid(Direction direction) {
        switch(direction) {
            case UP:
                if (head.getRow() == 0) {
                    head = new Position(head.getRow() + 1, head.getColumn());
                    tail = new Position(tail.getRow() + 1, tail.getColumn());
                    grid.expandGrid(direction);
                }
                break;
            case DOWN:
                if (head.getRow() == grid.getHeight() - 1)
                    grid.expandGrid(direction);
                break;
            case LEFT:
                if (head.getColumn() == 0) {
                    head = new Position(head.getRow(), head.getColumn() + 1);
                    tail = new Position(tail.getRow(), tail.getColumn() + 1);
                    grid.expandGrid(direction);
                }
                break;
            case RIGHT:
                if (head.getColumn() == grid.getWidth() - 1)
                    grid.expandGrid(direction);
                break;
        }
    }

    private void move(Direction direction) {
        expandGrid(direction);
        moveHead(direction);
        moveTail();
    }

    private Direction parseDirection(char ch) {
        switch(ch) {
            case 'R':
                return Direction.RIGHT;
            case 'L':
                return Direction.LEFT;
            case 'U':
                return Direction.UP;
            case 'D':
                return Direction.DOWN;
            default:
                throw new RuntimeException();
        }
    }

    private void moveHead(Direction direction) {
        head = getNewPosition(direction, head);
        Piece newPiece = grid.get(head);
        newPiece.setHead();
    }

    private void moveTail() {
        if (isHeadClose()) return;

        tail = findTail();
        Piece newPiece = grid.get(tail);

        newPiece.setTail();
    }

    private Position findTail() {
        Position delta = tail.delta(head);

        if (delta.getRow() == 0) {
            int sign = delta.getColumn() / Math.abs(delta.getColumn());
            return new Position(tail.getRow(), tail.getColumn() - sign);
        }

        if (delta.getColumn() == 0) {
            int sign = delta.getRow() / Math.abs(delta.getRow());
            return new Position(tail.getRow() - sign, tail.getColumn());
        }

        if (Math.abs(delta.getRow()) == 1) {
            int sign = delta.getColumn() / Math.abs(delta.getColumn());
            return new Position(tail.getRow() - delta.getRow(), tail.getColumn() - sign);
        }

        int sign = delta.getRow() / Math.abs(delta.getRow());
        return new Position(tail.getRow() - sign,  tail.getColumn() - delta.getColumn());
    }

    private Position getNewPosition(Direction direction, Position position) {
        Position newPosition;
        switch (direction) {
            case RIGHT:
                newPosition = new Position(position.getRow(), position.getColumn() + 1);
                break;
            case LEFT:
                newPosition = new Position(position.getRow(), position.getColumn() - 1);
                break;
            case DOWN:
                newPosition = new Position(position.getRow() + 1, position.getColumn());
                break;
            case UP:
                newPosition = new Position(position.getRow() - 1, position.getColumn());
                break;
            default:
                throw new RuntimeException();
        }
        return newPosition;
    }

    private boolean isHeadClose() {
        return head.length(tail) < 2;
    }

    private Set<Position> getTailPositions() {
        Set<Position> set = new HashSet<>();

        for (int row = 0; row < grid.getHeight(); row++) {
            for (int column = 0; column < grid.getWidth(); column++) {
                Piece piece = grid.get(row, column);
                if (piece.hadTail()) set.add(new Position(row, column));
            }
        }

        return set;
    }
}
