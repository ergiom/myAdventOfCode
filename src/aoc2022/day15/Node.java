package aoc2022.day15;

public enum Node {
    EMPTY(true, '#'), BEACON(true, 'B'),
    SENSOR(true, 'S'), UNKNOWN(false, '.');

    private final boolean isKnown;
    private final char symbol;

    Node(boolean isKnown, char symbol) {
        this.isKnown = isKnown;
        this.symbol = symbol;
    }

    public boolean isKnown() {
        return isKnown;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
