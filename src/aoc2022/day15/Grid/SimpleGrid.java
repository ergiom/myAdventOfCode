package aoc2022.day15.Grid;


import java.util.LinkedList;
import java.util.function.Supplier;

public class SimpleGrid<T> extends Grid<T> {
    public SimpleGrid(int height, int width, Supplier<T> supplier) {
        super();
        fillMap(height, width, supplier);
    }

    @Override
    public T getValue(Position position) {
        if (! onMap(position)) throw new RuntimeException("Position is outside the grid");

        return map.get(position.getRow()).get(position.getColumn());
    }

    @Override
    public void setValue(Position position, T value) {
        if (! onMap(position)) throw new RuntimeException("Position is outside the grid");

        map.get(position.getRow()).set(position.getColumn(), value);
    }

    @Override
    public Position firstPosition() {
        return new Position(0, 0);
    }

    private void fillMap(int height, int width, Supplier<T> supplier) {
        for (int row = 0; row < height; row++) {
            map.add(new LinkedList<>());
            for (int column = 0; column < width; column++) {
                map.get(row).add(supplier.get());
            }
        }
    }
}
