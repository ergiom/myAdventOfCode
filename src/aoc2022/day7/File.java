package aoc2022.day7;

public class File {
    private final String name;
    private final long size;

    File (String name, long size) {
        this.name = name;
        this.size = size;
    }

    public long getSize() {
        return size;
    }
}
