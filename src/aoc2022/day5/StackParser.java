package aoc2022.day5;

import java.util.List;

public class StackParser {
    private final List<String> input;
    private final Stacks stacks;

    StackParser(List<String> input) {
        this.input = input;
        int numberOfStacks = (input.get(0).length() + 1) / 4 + 1;
        stacks = new Stacks(numberOfStacks);
    }

    public void parse() {
        for (int i = input.size() - 1; i >= 0; i--) {
            parseLine(input.get(i));
        }
    }

    private void parseLine(String line) {
        String workingLine = convertLine(line);

        for (int i = 0; i < workingLine.length(); i++) {
            char ch = workingLine.charAt(i);

            if (ch != ' ') stacks.push(i, ch);
        }
    }

    private String convertLine(String line) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < ((line.length() + 1) / 4); i++) {
            int charIndex = 4 * i + 1;
            builder.append(line.charAt(charIndex));
        }
        return builder.toString();
    }

    public Stacks getStacks() {
        return stacks;
    }
}
