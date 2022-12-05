package aoc2022.day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String fileName = "./src/aoc2022/day5/Stacks.txt";
        System.out.println("Part 1: " + partOne(fileName));
        System.out.println("Part 2: " + partTwo(fileName));
    }

    private static String partOne(String fileName) {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(fileName))
        ) {
            List<String> stackInput = getStackInput(reader);
            Stacks stacks = getStacks(stackInput);

            InstructionParser instructionparser = new InstructionParser(reader);

            StacksDriver stacksDriver = new StacksDriver(stacks);
            for (Integer[] instruction: instructionparser) stacksDriver.execute9000(instruction);

            return stacksDriver.peekTop();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String partTwo(String fileName) {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(fileName))
        ) {
            List<String> stackInput = getStackInput(reader);
            Stacks stacks = getStacks(stackInput);

            InstructionParser instructionparser = new InstructionParser(reader);

            StacksDriver stacksDriver = new StacksDriver(stacks);
            for (Integer[] instruction: instructionparser) stacksDriver.execute9001(instruction);

            return stacksDriver.peekTop();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Stacks getStacks(List<String> stackInput) {
        StackParser parser = new StackParser(stackInput);
        parser.parse();
        return parser.getStacks();
    }

    private static List<String> getStackInput(BufferedReader reader) throws IOException {
        InputSplitter splitter = new InputSplitter();
        splitter.split(reader);
        return splitter.getLines();
    }
}
