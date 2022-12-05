package aoc2022.day5;

import java.util.Stack;

public class StacksDriver {
    private final Stacks stacks;
    StacksDriver(Stacks stacks) {
        this.stacks = stacks;
    }

    public void execute9000(Integer[] instruction) {
        int number = instruction[0];
        int from = instruction[1];
        int to = instruction[2];

        for (int i = 0; i < number; i++) {
            char ch = stacks.pop(from - 1);
            stacks.push(to - 1, ch);
        }
    }

    public void execute9001(Integer[] instruction) {
        int number = instruction[0];
        int from = instruction[1];
        int to = instruction[2];

        Stack<Character> buffer = new Stack<>();

        for (int i = 0; i < number; i++) {
            buffer.push(stacks.pop(from - 1));
        }

        while (buffer.size() != 0) {
            char ch = buffer.pop();
            stacks.push(to - 1, ch);
        }
    }

    public String peekTop() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < stacks.size(); i++) {
            builder.append(stacks.peek(i));
        }

        return builder.toString();
    }
}
