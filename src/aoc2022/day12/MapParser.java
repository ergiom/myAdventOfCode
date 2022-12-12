package aoc2022.day12;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MapParser {
    private Position start;
    private Position stop;
    private final List<List<Integer>> map;
    private String[] lines;

    MapParser() {
        map = new LinkedList<>();
        start = Position.INVALID;
        stop = Position.INVALID;

    }

    public void parse(List<String> lines) {
        this.lines = lines.toArray(new String[0]);
        findAndReplaceStart();
        findAndReplaceEnd();

        for (String line : this.lines) {
            parseLine(line);
        }

        if (start == Position.INVALID) throw new RuntimeException("Starting point not found!");
        if (stop == Position.INVALID) throw new RuntimeException("End point not found!");
    }

    private void findAndReplaceEnd() {
        for (int row = 0; row < lines.length; row++) {
            String line = lines[row];
            if (! line.contains("E")) continue;

            int column = line.indexOf('E');
            stop = new Position(row, column);
            lines[row] = line.replaceAll("E", "z");
        }

    }

    private void findAndReplaceStart() {
        for (int row = 0; row < lines.length; row++) {
            String line = lines[row];
            if (! line.contains("S")) continue;

            int column = line.indexOf('S');
            start = new Position(row, column);
            lines[row] = line.replaceAll("S", "a");
        }
    }

    private void parseLine(String line) {
        List<Integer> mapLine = line.chars()
                .map(a -> a - 'a')
                .boxed()
                .collect(Collectors.toList());

        map.add(mapLine);
    }

    public Position getStart() {
        return start;
    }

    public Position getStop() {
        return stop;
    }

    public int[][] getMap() {
        int[][] result = new int[map.size()][map.get(0).size()];

        for (int row = 0; row < map.size(); row++) {
            for (int col = 0; col < map.get(0).size(); col++) {
                result[row][col] = map.get(row).get(col);
            }
        }

        return result;
    }

    public HeightMap toHeightMap() {
        return new HeightMap(getMap(), start, stop);
    }
}
