package aoc2022.day5;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Stacks {
    private final List<Stack<Character>> stacks;

    Stacks (int number) {
        stacks = new LinkedList<>();
        createStacks(number);
    }

    private void createStacks(int number) {
        for (int i = 0; i < number; i++) {
            stacks.add(new Stack<>());
        }
    }

    public void push(int i, char ch) {
        stacks.get(i).push(ch);
    }

    public char pop(int i) {
        return stacks.get(i).pop();
    }

    public int size() {
        return stacks.size();
    }

    public char peek(int i) {
        return stacks.get(i).peek();
    }
}
