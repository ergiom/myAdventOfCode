package aoc2022.day10;

import java.util.LinkedList;
import java.util.Queue;

public class Interpreter {
    private final String[] program;
    private int programCounter;
    private long register;
    private final Queue<Instruction> queue;
    private Instruction scheduledInstruction;

    Interpreter(String ... instructions) {
        programCounter = 0;
        program = instructions;
        register = 1;
        queue = new LinkedList<>();
    }

    public void proceed(int cycles) {
        for (; cycles > 0; cycles--) {
            if (queue.isEmpty()) readInstruction();

            step();
        }
    }

    public long getRegister() {
        return register;
    }


    private void step() {
        calculateScheduledInstruction();
        scheduleInstruction();
    }

    private void scheduleInstruction() {
        scheduledInstruction = queue.remove();
    }

    private void readInstruction() {
        if (programCounter >= program.length) {
            queue.add(new Instruction(Operation.NOOP, 0));
            return;
        }
        String instruction = program[programCounter];
        programCounter++;

        if (instruction.matches("noop")) queue.add(new Instruction(Operation.NOOP, 0));
        else {
            int argument = Integer.parseInt(instruction.split(" ")[1]);
            queue.add(new Instruction(Operation.NOOP, 0));
            queue.add(new Instruction(Operation.ADDX, argument));
        }
    }

    private void calculateScheduledInstruction() {
        if (scheduledInstruction == null) return;
        if (scheduledInstruction.getOperation() == Operation.NOOP) return;

        register += scheduledInstruction.getArgument();
    }
}
