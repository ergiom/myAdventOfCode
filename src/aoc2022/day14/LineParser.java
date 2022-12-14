package aoc2022.day14;

import java.util.List;

public class LineParser {
    public CaveMap parse(List<String> lines) {
        CaveMap map = new CaveMap();
        for (String line: lines) {
            String[] strPoints = line.split(" -> ");
            Position[] positions = new Position[strPoints.length];

            for (int i = 0; i < strPoints.length; i++) {
                String strPoint = strPoints[i];
                String[] coordinates = strPoint.split(",");
                int row = Integer.parseInt(coordinates[1]);
                int column = Integer.parseInt(coordinates[0]);
                positions[i] = new Position(row, column);
            }

            for (int i = 0; i < positions.length - 1; i++) {
                Position start = positions[i];
                Position stop = positions[i + 1];
                map.drawLine(start, stop, Node.WALL);
            }
        }

        return map;
    }
}
