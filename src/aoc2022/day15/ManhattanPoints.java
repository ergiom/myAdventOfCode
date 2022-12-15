package aoc2022.day15;


import aoc2022.day15.Grid.Position;

import java.util.LinkedList;
import java.util.List;

public class ManhattanPoints {
    public static List<Position> getPoints(Position center, Position point) {
        List<Position> list = new LinkedList<>();

        int distance = Math.abs(center.getRow() - point.getRow()) + Math.abs(center.getColumn() - point.getColumn());

        for (int row = center.getRow() - distance; row <= center.getRow() + distance; row++) {
            int distanceLeft = distance - Math.abs(row - center.getRow());
            for (int column = center.getColumn() - distanceLeft; Math.abs(center.getColumn() - column) + Math.abs(center.getRow() - row) <= distance; column++) {
                list.add(new Position(row, column));
            }
        }

        return list;
    }
}
