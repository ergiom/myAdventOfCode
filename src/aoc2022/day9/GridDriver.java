package aoc2022.day9;

public class GridDriver {
    private final Grid grid;

    GridDriver() {
        grid = new Grid();
    }

    public void parseMovement(String line) {
        String[] command = line.split(" ");
        char direction = command[0].charAt(0);
        int length = Integer.parseInt(command[1]);

        for (int i = 0; i < length; i++) {
            grid.parseMove(direction);
        }
    }

    public int getTailCount() {
        return grid.getTailPositions().size();
    }
}
