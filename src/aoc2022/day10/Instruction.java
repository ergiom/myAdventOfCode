package aoc2022.day10;

public class Instruction {
    private final Operation operation;
    private final int argument;

    Instruction(Operation opearation, int argument) {
        this.operation = opearation;
        this.argument = argument;
    }

    public Operation getOperation() {
        return operation;
    }

    public int getArgument() {
        return argument;
    }
}
