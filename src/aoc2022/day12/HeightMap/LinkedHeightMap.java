package aoc2022.day12.HeightMap;


import java.util.*;

public class LinkedHeightMap {
    HeightMap heightMap;
    Node start;
    Node[][] nodes;

    public LinkedHeightMap(HeightMap heightMap) {
        this.heightMap = heightMap;
        createNodes();
        linkNodes();
        assignStart();
    }

    private void createNodes() {
        int heightMapHeight = heightMap.getMapHeight();
        int heightMapWidth = heightMap.getMapWidth();
        nodes = new Node[heightMapHeight][heightMapWidth];

        for (int row = 0; row < heightMapHeight; row++) {
            for (int column = 0; column < heightMapWidth; column++) {
                Position position = new Position(row, column);
                int value = heightMap.getHeight(position);

                nodes[row][column] = new Node(position, value);
            }
        }
    }

    private void linkNodes() {
        int heightMapHeight = heightMap.getMapHeight();
        int heightMapWidth = heightMap.getMapWidth();
        for (int row = 0; row < heightMapHeight; row++) {
            for (int column = 0; column < heightMapWidth; column++) {
                Node node = nodes[row][column];
                List<Node> neighbours = getNeighbours(row, column);

                for (Node neighbour: neighbours) {
                    if (node.getValue() >= neighbour.getValue() - 1) node.addPath(neighbour);
                }
            }
        }
    }

    private List<Node> getNeighbours(int row, int column) {
        List<Node> list = new LinkedList<>();
        Position[] positions = {
                new Position(row - 1, column),
                new Position(row, column - 1),
                new Position(row, column + 1),
                new Position(row + 1, column),
        };

        for (Position position: positions) {
            if (! heightMap.onMap(position)) continue;

            list.add(nodes[position.getRow()][position.getColumn()]);
        }

        return list;
    }

    private void assignStart() {
        Position start = heightMap.getStart();
        this.start = nodes[start.getRow()][start.getColumn()];
    }

    public int findShortestPathToEnd() {
        return findShortestPath(heightMap.getEnd());
    }

    private int findShortestPath(Position to) {
        Set<Node> visited = new HashSet<>();
        Set<Node> currentLevel = new HashSet<>();
        currentLevel.add(start);
        Set<Node> nextLevel = new HashSet<>();

        boolean finished = false;
        int steps = 0;
        while(true) {
            for (Node node: currentLevel) {
                if (node.getPosition().equals(to)) {
                    finished = true;
                    break;
                }

                HashSet<Node> difference = new HashSet<>(node.getPaths());
                difference.removeAll(visited);
                nextLevel.addAll(difference);
                visited.add(node);
            }
            if (finished) break;

            steps++;
            if (nextLevel.size() == 0) return Integer.MAX_VALUE;
            currentLevel = nextLevel;
            nextLevel = new HashSet<>();
        }

        return steps;
    }

    public void changeStart(Position position) {
        start = nodes[position.getRow()][position.getColumn()];
    }

    public Position[] findAll(int value) {
        List<Position> positions = new ArrayList<>();
        for (int row = 0; row < heightMap.getMapHeight(); row++) {
            for (int column = 0; column < heightMap.getMapWidth(); column++) {
                Node node = nodes[row][column];
                if (node.getValue() == value) positions.add(node.getPosition());
            }
        }

        return positions.toArray(new Position[0]);
    }
}
