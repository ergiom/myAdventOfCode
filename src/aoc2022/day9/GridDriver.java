package aoc2022.day9;


import java.util.HashSet;
import java.util.Set;

public class GridDriver {
    private final Grid grid;
    private final Position[] knots;


    GridDriver(int knots) {
        grid = new Grid();
        if (knots <= 0) throw new RuntimeException();

        this.knots = new Position[knots];
        for (int i = 0; i < knots; i++) {
            this.knots[i] = new Position(0, 0);
        }
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

    public void print() {
        for (int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                boolean found = false;
                for (int k = 0; k < knots.length; k++) {
                    if (knots[k].is(new Position(i, j))) {
                        System.out.print(k);
                        found = true;
                        break;
                    }
                }
                if (! found) System.out.print(".");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void expandGrid(Direction direction) {
        Position head = knots[0];
        switch(direction) {
            case UP:
                if (head.getRow() == 0) {
                    moveKnots(1, 0);
                    grid.expandGrid(direction);
                }
                break;
            case DOWN:
                if (head.getRow() == grid.getHeight() - 1)
                    grid.expandGrid(direction);
                break;
            case LEFT:
                if (head.getColumn() == 0) {
                    moveKnots(0, 1);
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
        knots[0] = getNewPosition(direction, knots[0]);
        Piece newPiece = grid.get(knots[0]);
        newPiece.setHead();
    }

    private void moveTail() {
        for (int i = 1; i < knots.length; i++) {
            if (isHeadClose(knots[i - 1], knots[i])) continue;

            knots[i] = findTail(knots[i-1], knots[i]);
        }
        Piece newPiece = grid.get(knots[knots.length - 1]);
        newPiece.setTail();
    }

    private Position findTail(Position head, Position tail) {
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

        if (Math.abs(delta.getColumn()) == 1) {
            int sign = delta.getRow() / Math.abs(delta.getRow());
            return new Position(tail.getRow() - sign,  tail.getColumn() - delta.getColumn());
        }

        int sign1 = delta.getRow() / Math.abs(delta.getRow());
        int sign2 = delta.getColumn() / Math.abs(delta.getColumn());
        return new Position(tail.getRow() - sign1, tail.getColumn() - sign2);
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

    private boolean isHeadClose(Position previous, Position next) {
        return previous.length(next) < 2;
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

    private void moveKnots(int rows, int columns) {
       for (int i = 0; i < knots.length; i++) {
           Position knot = knots[i];
           knots[i] = new Position(knot.getRow() + rows, knot.getColumn() + columns);
       }
    }
}
