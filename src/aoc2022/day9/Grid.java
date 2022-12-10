package aoc2022.day9;

import java.util.LinkedList;
import java.util.List;

public class Grid {
    private final List<List<Piece>> list;


    Grid() {
        Piece piece = new Piece();
        piece.setHead();
        piece.setTail();

        list = new LinkedList<>();
        list.add(new LinkedList<>());
        list.get(0).add(piece);
    }


    public int getHeight() {
        return list.size();
    }

    public int getWidth() {
        return list.get(0).size();
    }

    public void expandGrid(Direction direction) {
        switch(direction) {
            case UP:
                insertRow();
                break;
            case DOWN:
                appendRow();
                break;
            case LEFT:
                insertColumn();
                break;
            case RIGHT:
                appendColumn();
                break;
        }
    }

    public Piece get(Position position) {
        return get(position.getRow(), position.getColumn());
    }

    public Piece get(int row, int column) {
        return list.get(row).get(column);
    }

    private void insertRow() {
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
        for (List<Piece> pieces : list) {
            pieces.add(0, new Piece());
        }
    }

    private void appendColumn() {
        for (List<Piece> pieces : list) {
            pieces.add(new Piece());
        }
    }
}
