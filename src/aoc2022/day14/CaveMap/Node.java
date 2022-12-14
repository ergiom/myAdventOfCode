package aoc2022.day14.CaveMap;

public enum Node {
    AIR(true, '.'), WALL(false, '#'), SAND(false, 'o');

    private final boolean empty;
    private final char symbol;

    Node(boolean empty, char symbol) {
        this.empty = empty;
        this.symbol = symbol;
    }

    public boolean isEmpty() {
        return empty;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
