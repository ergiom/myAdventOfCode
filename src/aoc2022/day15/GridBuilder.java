package aoc2022.day15;

import aoc2022.day15.Grid.Grid;
import aoc2022.day15.Grid.Position;
import aoc2022.day15.Grid.ResizableGrid;

public class GridBuilder {
    private final Grid<Node> grid;

    GridBuilder() {
        grid = new ResizableGrid<>(() -> Node.UNKNOWN);
    }

    public void addSensor(Position sensor, Position beacon) {
        grid.setValue(sensor, Node.SENSOR);
        grid.setValue(beacon, Node.BEACON);

        for (Position position: ManhattanPoints.getPoints(sensor, beacon)) {
            if (! grid.getValue(position).isKnown()) {
                grid.setValue(position, Node.EMPTY);
            }
        }
    }

    public Grid<Node> getGrid() {
        return grid;
    }
}
