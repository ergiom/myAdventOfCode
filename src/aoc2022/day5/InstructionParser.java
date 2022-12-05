package aoc2022.day5;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InstructionParser implements Iterator<Integer[]>, Iterable<Integer[]> {
    private static final Pattern pattern;
    private final BufferedReader reader;
    private String buffer;
    boolean hasNext;
    static {
        pattern = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");
    }
    InstructionParser(BufferedReader reader) {
        this.reader = reader;
        readLine();
    }

    private void readLine() {
        try {
            buffer = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        hasNext = buffer != null;
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public Integer[] next() {
        String line = buffer;
        readLine();
        return getNumbers(line);
    }

    private Integer[] getNumbers(String line) {
        Matcher matcher = pattern.matcher(line);
        if (! matcher.find()) throw new RuntimeException();
        int number = Integer.parseInt(matcher.group(1));
        int from = Integer.parseInt(matcher.group(2));
        int to = Integer.parseInt(matcher.group(3));

        return new Integer[] {
                number,
                from,
                to
        };
    }

    @Override
    public Iterator<Integer[]> iterator() {
        return this;
    }
}
