package aoc2022.day13.Node;

public class Node {
    private final NodeType type;
    private final Object value;

    public Node(NodeType type, Object value) {
        this.type = type;
        this.value = value;
    }

    public NodeType getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }
}
