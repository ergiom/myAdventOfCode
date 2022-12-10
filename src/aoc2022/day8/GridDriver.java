package aoc2022.day8;

import java.util.stream.Stream;

public class GridDriver {
    private final Grid grid;

    GridDriver() {
        grid = new Grid();
    }

    public void addRow(String row) {
        String[] splitRow = row.split("");
        int[] intRow = Stream.of(splitRow).mapToInt(Integer::parseInt).toArray();
        grid.addRow(intRow);
    }

    public int countVisible() {
        int sum = 0;
        for (int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                if (isVisible(i, j)) sum++;
            }
        }
        return sum;
    }

    private long calculateScenicScore(int row, int column) {
        if (row == 1 && column == 9) {
            System.out.println("Quack");
        }
        return visibleUp(row, column)
                * visibleDown(row, column)
                * visibleRight(row, column)
                * visibleLeft(row, column);
    }

    public long getBiggestScenicScore() {
        long max = 0;

        for (int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                long score = calculateScenicScore(i, j);
                max = Long.max(max, score);
            }
        }

        return max;
    }

    private long visibleUp(int row, int column) {
        int value = grid.getValue(row, column);
        int visible = 0;
        for (int i = row - 1; i >= 0; i--) {
            visible++;
            if (grid.getValue(i, column) >= value) break;
        }
        return visible;
    }

    private long visibleDown(int row, int column) {
        int value = grid.getValue(row, column);
        int visible = 0;
        for (int i = row + 1; i < grid.getHeight(); i++) {
            visible++;
            if (grid.getValue(i, column) >= value) break;
        }
        return visible;
    }

    private long visibleLeft(int row, int column) {
        int value = grid.getValue(row, column);
        int visible = 0;
        for (int i = column - 1; i >= 0; i--) {
            visible++;
            if (grid.getValue(row, i) >= value) break;
        }
        return visible;
    }

    private long visibleRight(int row, int column) {
        int value = grid.getValue(row, column);
        int visible = 0;
        for (int i = column + 1; i < grid.getWidth(); i++) {
            visible++;
            if (grid.getValue(row, i) >= value) break;
        }
        return visible;
    }

    private boolean isVisible(int i, int j) {
        return checkUp(i, j) || checkDown(i, j) || checkLeft(i, j) || checkRight(i, j);
    }

    private boolean checkUp(int row, int column) {
        int value = grid.getValue(row, column);
        for (int i = 0; i < row; i++) {
            if (grid.getValue(i, column) >= value) return false;
        }
        return true;
    }

    private boolean checkDown(int row, int column) {
        int value = grid.getValue(row, column);
        for (int i = row + 1; i < grid.getHeight(); i++) {
            if (grid.getValue(i, column) >= value) return false;
        }
        return true;
    }

    private boolean checkLeft(int row, int column) {
        int value = grid.getValue(row, column);
        for (int i = 0; i < column; i++) {
            if (grid.getValue(row, i) >= value) return false;
        }
        return true;
    }

    private boolean checkRight(int row, int column) {
        int value = grid.getValue(row, column);
        for (int i = column + 1; i < grid.getWidth(); i++) {
            if (grid.getValue(row, i) >= value) return false;
        }
        return true;
    }
}
