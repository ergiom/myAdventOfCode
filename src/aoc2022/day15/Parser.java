package aoc2022.day15;

import aoc2022.day15.Grid.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    Map<Position, Position> map;

    Parser() {
        map = new HashMap<>();
    }

    public void parse(List<String> lines) {
        for (String line: lines) {
            parse(line);
        }
    }

    public void parse(String line) {
        Pattern pattern = Pattern.compile("^Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)$");
        Matcher matcher = pattern.matcher(line);

        if (! matcher.find()) throw new RuntimeException("Line doesn't match");

        Position sensor = new Position(
            Integer.parseInt(matcher.group(2)),
            Integer.parseInt(matcher.group(1))
        );

        Position beacon = new Position(
                Integer.parseInt(matcher.group(4)),
                Integer.parseInt(matcher.group(3))
        );

        map.put(sensor, beacon);
    }

    public Map<Position, Position> getMap() {
        return new HashMap<>(map);
    }
}
