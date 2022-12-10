package aoc2022.day8;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Grid {
    private final List<List<Integer>> grid;

    Grid() {
        grid = new LinkedList<>();
    }

    public void addRow(int ... elements) {
        List<Integer> row = Arrays.stream(elements).boxed().collect(Collectors.toList());
        grid.add(row);
    }

    public void addColumn(int ... elements) {
        if (elements.length != grid.size()) throw new RuntimeException();
        for (int i = 0; i < elements.length; i++) {
            grid.get(i).add(elements[i]);
        }
    }

    public Integer getValue(int row, int column) {
        return grid.get(row).get(column);
    }

    public int getHeight() {
        return grid.size();
    }

    public int getWidth() {
        if (grid.size() == 0) return 0;

        return grid.get(0).size();
    }
}
