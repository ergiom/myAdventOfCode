package aoc2022.day12.HeightMap;

import java.util.HashSet;
import java.util.Set;

public class Node {
    private final Position position;
    private final int value;
    private final Set<Node> path;

    public Node(Position position, int value) {
        path = new HashSet<>();
        this.position = position;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Position getPosition() {
        return position;
    }

    public void addPath(Node node) {
        path.add(node);
    }

    public Set<Node> getPaths() {
        return path;
    }
}
