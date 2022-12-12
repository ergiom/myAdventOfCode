package aoc2022.day12;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MapTraverser {
    private final HeightMap map;

    MapTraverser(HeightMap heightMap) {
        map = heightMap;
    }

    public List<Position> shortestPath() {
        return shortestPath(map.getStart());
    }

    public List<Position> shortestPath(Position start) {
        if (start.equals(map.getEnd())) return new ArrayList<>();

        List<Position> moves = getPossibleMoves(start);
        List<Position> shortestPath = null;

        for (Position move: moves) {
            List<Position> path = shortestPath(move);

            if (shortestPath == null) shortestPath = path;
            else if (shortestPath.size() > path.size()) shortestPath = path;
        }

        return shortestPath;
    }

    private List<Position> getPossibleMoves(Position currentPosition) {
        int currentHeight = map.getHeight(currentPosition);
        List<Position> moves = new LinkedList<>();
        int[] delta = {-1, 0, 1}; //todo 9 possible, should be 4
        //todo shouldnt go backwards, should know current shortest path

        for (int i: delta) {
            for (int j: delta) {
                if (i == 0 && j == 0) continue;

                Position newPosition = new Position(
                        currentPosition.getRow() + i,
                        currentPosition.getColumn() + j);

                if (! map.onMap(newPosition)) continue;
                if (map.getHeight(newPosition) - 1 > currentHeight) continue;

                moves.add(newPosition);
            }
        }

        return moves;
    }
}
