package aoc2022.day15.Grid;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public class ResizableGrid<T> extends Grid<T> {
    private final Supplier<T> defaultSupplier;
    private int rowIndexModifier;
    private int columnIndexModifier;

    public ResizableGrid(Supplier<T> defaultSupplier) {
        super();
        this.defaultSupplier = defaultSupplier;
        rowIndexModifier = 0;
        columnIndexModifier = 0;
    }

    @Override
    public T getValue(Position position) {
        Position realPosition = getRealPosition(position);

        if (! _onMap(realPosition)) {
            extendGrid(realPosition);
            realPosition = getRealPosition(position);
        }

        return map.get(realPosition.getRow()).get(realPosition.getColumn());
    }

    @Override
    public void setValue(Position position, T value) {
        Position realPosition = getRealPosition(position);

        if (! _onMap(realPosition)) {
            extendGrid(realPosition);
            realPosition = getRealPosition(position);
        }

        map.get(realPosition.getRow()).set(realPosition.getColumn(), value);
    }

    private void extendGrid(Position position) {
        if (position.getRow() < 0) insertRows(position.getRow(), defaultSupplier);
        else if (position.getRow() >= getHeight()) {
            insertRows(position.getRow() - getHeight() + 1, defaultSupplier);
        }

        if (position.getColumn() < 0) insertColumns(position.getColumn(), defaultSupplier);
        else if (position.getColumn() >= getWidth()) {
            insertColumns(position.getColumn() - getWidth() + 1, defaultSupplier);
        }
    }

    private void insertRows(int rows, Supplier<T> supplier) {
        int width = getWidth();

        if (rows < 0) {
            rowIndexModifier += Math.abs(rows);
            for (int i = 0; i < -rows; i++) {
                map.add(0, new LinkedList<>());
            }
        }
        else {
            for (int i = 0; i < rows; i++) {
                map.add(new LinkedList<>());
            }
        }

        for (List<T> row: map) {
            while (row.size() < width) row.add(supplier.get());
        }
    }

    private void insertColumns(int columns, Supplier<T> supplier) {
        int width = getWidth();

        if (columns < 0) {
            width += Math.abs(columns);
            columnIndexModifier += Math.abs(columns);
            for (List<T> row: map) {
                while (row.size() < width) row.add(0, supplier.get());
            }
        }
        else {
            width += columns;
            for (List<T> row: map) {
                while (row.size() < width) row.add(supplier.get());
            }
        }
    }

    private Position getRealPosition(Position position) {
        return new Position(
                position.getRow() + rowIndexModifier,
                position.getColumn()+ columnIndexModifier);
    }

    @Override
    public boolean onMap(Position position) {
        Position realPosition = getRealPosition(position);
        return _onMap(realPosition);
    }

    private boolean _onMap(Position position) {
        return super.onMap(position);
    }

    @Override
    public Position firstPosition() {
        return new Position(- rowIndexModifier, - columnIndexModifier);
    }
}
