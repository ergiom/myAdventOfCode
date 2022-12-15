package aoc2022.day15.Grid;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public abstract class Grid<T> {
    final List<List<T>> map;

    public Grid() {
        map = new LinkedList<>();
    }

    public int getWidth() {
        if (getHeight() == 0) return 0;
        return map.get(0).size();
    }

    public int getHeight() {
        return map.size();
    }

    public abstract T getValue(Position position);

    public abstract void setValue(Position position, T value);

    public boolean onMap(Position position) {
        if (position.getColumn() < 0 || position.getColumn() >= getWidth()) return false;
        if (position.getRow() < 0 || position.getRow() >= getHeight()) return false;

        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (List<T> row: map) {
            for (T value: row) {
                builder.append(value);
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    public abstract Position firstPosition();
}
