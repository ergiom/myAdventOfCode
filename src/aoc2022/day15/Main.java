package aoc2022.day15;

import aoc2022.day15.Grid.Grid;
import aoc2022.day15.Grid.Position;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
//        String fileName = "./src/aoc2022/day15/TestSonars.txt";
        String fileName = "./src/aoc2022/day15/Sonars.txt";
        System.out.println("Part 1: " + partOne(fileName));
//        System.out.println("Part 2: " + partTwo(fileName));
    }

    private static int partOneOldOld(String fileName) {
        List<String> lines = getLines(fileName);

        GridBuilder builder = new GridBuilder();

        Parser parser = new Parser();
        parser.parse(lines);
        Map<Position, Position> map = parser.getMap();

        for (Position sensor: map.keySet()) {
            Position beacon = map.get(sensor);
            builder.addSensor(sensor, beacon);

//            System.out.println(builder.getGrid());
//            System.out.println();
        }

        int sum = 0;
        Grid<Node> grid = builder.getGrid();
//        System.out.println(grid);
        Position firstPoint = grid.firstPosition();
        for (int row = firstPoint.getRow(); row < grid.getHeight() + firstPoint.getRow(); row++) {
            Position position = new Position(2000000, row);
//            Position position = new Position(row, 10);
            Node node = grid.getValue(position);
            if (node.isKnown() && node != Node.BEACON) sum++;
        }

        return sum;
    }

    private static long partOneOld(String fileName) {
        List<String> lines = getLines(fileName);
        Parser parser = new Parser();
        parser.parse(lines);
        Map<Position, Position> map = parser.getMap();

        Set<Position> set = new HashSet<>();
        for (Position sensor: map.keySet()) {
            Position beacon = map.get(sensor);
            set.addAll(ManhattanPoints.getPoints(sensor, beacon));
        }

        set.removeAll(map.values());

        return set.stream().filter(p -> p.getColumn() == 2000000).count();
//        return set.stream().filter(p -> p.getColumn() == 10).count();
    }

    private static int partOne(String fileName) {
        return 0;
    }

    private static List<String> getLines(String fileName) {
        List<String> lines = new LinkedList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) lines.add(line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return lines;
    }
}
