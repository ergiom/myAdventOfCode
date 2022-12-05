package aoc2022.day5;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class InputSplitter {
    List<String> lines;

    InputSplitter() {
        lines = new LinkedList<>();
    }

    public void split(BufferedReader input) throws IOException {
        if (lines.size() != 0) lines.clear();

        while (true) {
            String line = input.readLine();

            if (line.equals("")) break;

            lines.add(line);
        }
        if (lines.size() > 0) lines.remove(lines.size() - 1);
    }

    public List<String> getLines() {
        return lines;
    }
}
