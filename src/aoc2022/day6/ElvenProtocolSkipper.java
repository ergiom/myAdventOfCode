package aoc2022.day6;

public class ElvenProtocolSkipper {
    private final char[] buffer;
    private int writes;

    ElvenProtocolSkipper(int length) {
        buffer = new char[length];
        writes = 0;
    }

    public void write(char ch) {
        buffer[writes % buffer.length] = ch;
        writes++;
    }

    public boolean messageStart() {
        for (int i = 0; i < buffer.length; i++) {
            for (int j = 0; j < buffer.length; j++) {
                if (i == j) continue;
                if (buffer[i] == buffer[j]) return false;
            }
        }

        return true;
    }

    public boolean full() {
        return buffer.length <= writes;
    }

    public int getWrites() {
        return writes;
    }
}
