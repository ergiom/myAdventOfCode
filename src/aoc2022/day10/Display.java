package aoc2022.day10;

public class Display {
    private int row;
    private int column;
    private final boolean[][] currentPicture;
    private final int height;
    private final int width;
    Display(int height, int width) {
        this.height = height;
        this.width = width;
        currentPicture = new boolean[height][width];

        row = 0;
        column = 0;
    }

    public String draw(long position) {
        currentPicture[row][column] = Math.abs(position - column) <= 1;
        nextPosition();
        return pictureToString();
    }

    private String pictureToString() {
        StringBuilder builder = new StringBuilder();

        for (boolean[] row: currentPicture) {
            for (boolean pixel: row) {
                builder.append(pixel ? '#' : '.');
            }
            builder.append('\n');
        }
        builder.append('\n');
        return builder.toString();
    }

    private void nextPosition() {
        column++;
        row += column / width;

        row = row % height;
        column = column % width;
    }
}
