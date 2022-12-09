package aoc2022.day9;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Grid {
    private final List<List<Piece>> list;
    private Position head;
    private Position tail;

    Grid() {
        Piece piece = new Piece();
        piece.setHead();
        piece.setTail();

        list = new LinkedList<>();
        list.add(new LinkedList<>());
        list.get(0).add(piece);

        head = new Position(0, 0);
        tail = new Position(0, 0);
    }

    public void parseMove(char direction) {
        switch(direction) {
            case 'R':
                move(Direction.RIGHT);
                break;
            case 'L':
                move(Direction.LEFT);
                break;
            case 'U':
                move(Direction.UP);
                break;
            case 'D':
                move(Direction.DOWN);
                break;
        }
    }

    private void move(Direction direction) {
        expandGrid(direction);
        moveHead(direction);
        moveTail();
    }

    private void expandGrid(Direction direction) {
        switch(direction) {
            case UP:
                if (head.getRow() == 0) insertRow();
                break;
            case DOWN:
                if (head.getRow() == list.size() - 1) appendRow();
                break;
            case LEFT:
                if (head.getColumn() == 0) insertColumn();
                break;
            case RIGHT:
                if (head.getColumn() == list.get(0).size() - 1) appendColumn();
                break;
        }
    }

    private void insertRow() {
        head = new Position(head.getRow() + 1, head.getColumn());
        tail = new Position(tail.getRow() + 1, tail.getColumn());

        list.add(0, new LinkedList<>());
        for (int i = 0; i < list.get(1).size(); i++) {
            list.get(0).add(new Piece());
        }
    }

    private void appendRow() {
        List<Piece> list = new LinkedList<>();
        this.list.add(list);
        for (int i = 0; i < this.list.get(0).size(); i++) {
            list.add(new Piece());
        }

    }

    private void insertColumn() {
        head = new Position(head.getRow(), head.getColumn() + 1);
        tail = new Position(tail.getRow(), tail.getColumn() + 1);

        for (List<Piece> pieces : list) {
            pieces.add(0, new Piece());
        }
    }

    private void appendColumn() {
        for (List<Piece> pieces : list) {
            pieces.add(new Piece());
        }
    }

    private void moveHead(Direction direction) {
        Piece oldPiece = list.get(head.getRow()).get(head.getColumn());
        setHead(getNewPosition(direction, head));
        Piece newPiece = list.get(head.getRow()).get(head.getColumn());
        oldPiece.removeHead();
        newPiece.setHead();
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

    private void setHead(Position position) {
        head = new Position(position.getRow(), position.getColumn());
    }

    private void moveTail() {
        if (isHeadClose()) return;

        Piece oldPiece = get(tail);
        setNewTail();
        Piece newPiece = get(tail);

        oldPiece.removeTail();
        newPiece.setTail();
    }

    private boolean isHeadClose() {
        return head.length(tail) < 2;
    }

    private void setNewTail() {
        Position delta = tail.delta(head);

        if (delta.getRow() == 0) {
            int sign = delta.getColumn() / Math.abs(delta.getColumn());
            tail = new Position(tail.getRow(), tail.getColumn() - sign);
            return;
        }

        if (delta.getColumn() == 0) {
            int sign = delta.getRow() / Math.abs(delta.getRow());
            tail = new Position(tail.getRow() - sign, tail.getColumn());
            return;
        }

        if (Math.abs(delta.getRow()) == 1) {
            int sign = delta.getColumn() / Math.abs(delta.getColumn());
            tail = new Position(tail.getRow() - delta.getRow(), tail.getColumn() - sign);
            return;
        }

        int sign = delta.getRow() / Math.abs(delta.getRow());
        tail = new Position(tail.getRow() - sign,  tail.getColumn() - delta.getColumn());
    }

    private Piece get(Position position) {
        return list.get(position.getRow()).get(position.getColumn());
    }

    public Set<Position> getTailPositions() {
        Set<Position> set = new HashSet<>();

        for (int row = 0; row < list.size(); row++) {
            for (int column = 0; column < list.size(); column++) {
                Piece piece = list.get(row).get(column);
                if (piece.hadTail()) set.add(new Position(row, column));
            }
        }

        return set;
    }
}
