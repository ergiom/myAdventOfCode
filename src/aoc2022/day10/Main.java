package aoc2022.day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String inputFile = "./src/aoc2022/day10/Program.txt";
//        String inputFile = "./src/aoc2022/day10/TestProgram.txt";
        System.out.println("Part 1: " + partOne(inputFile));
        System.out.println("Part 2:\n" + partTwo(inputFile));
    }

    private static long partOne(String inputFile) {
        String[] program;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))){
            program = reader.lines().toArray(String[]::new);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Interpreter interpreter = new Interpreter(program);

        return calculateTotalSignalStrength(interpreter);
    }

    private static String partTwo(String inputFile) {
        String[] program;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))){
            program = reader.lines().toArray(String[]::new);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Communicator comm = new Communicator(program);
        return comm.execute();
    }

    private static long calculateTotalSignalStrength(Interpreter interpreter) {
        long sum = 0;
        int cycle = 0;


        interpreter.proceed(20);
        cycle += 20;
        sum += cycle * interpreter.getRegister();

        while (cycle < 220) {
            interpreter.proceed(40);
            cycle += 40;
            sum += cycle * interpreter.getRegister();
        }

        return sum;
    }
}
