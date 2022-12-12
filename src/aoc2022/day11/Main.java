package aoc2022.day11;

import aoc2022.day11.part1.Monkey;
import aoc2022.day11.part1.Parser;
import aoc2022.day11.part2.BigMonkey;
import aoc2022.day11.part2.BigParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String fileName = "./src/aoc2022/day11/Notes.txt";
//        String fileName = "./src/aoc2022/day11/TestNotes.txt";
        System.out.println("Part 1: " + partOne(fileName));
        System.out.println("Part 2: " + partTwo(fileName));
    }

    private static long partOne(String fileName) {
        List<String> lines = new LinkedList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while((line = reader.readLine()) != null) lines.add(line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parser parser = new Parser(lines);
        parser.parse();
        Monkey[] monkeys = parser.createMonkeys();

        for (int i = 0; i < 20; i++) {
            for (Monkey monkey: monkeys) {
                monkey.playRound(a -> a.divide(BigInteger.valueOf(3)));
            }
        }

        List<Long> twos = Arrays.stream(monkeys)
                                .map(Monkey::getExaminedItems)
                                .sorted((a, b) -> (int) (b - a))
                                .limit(2)
                                .collect(Collectors.toList());
        return twos.get(0) * twos.get(1);
    }

    private static String partTwo(String fileName) {
        List<String> lines = new LinkedList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while((line = reader.readLine()) != null) lines.add(line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BigParser parser = new BigParser(lines);
        parser.parse();
        BigMonkey[] monkeys = parser.createMonkeys();

        for (int i = 0; i < 10000; i++) {
            for (BigMonkey monkey: monkeys) {
                monkey.playRound();
            }
        }

        List<Long> twos = Arrays.stream(monkeys)
                .map(BigMonkey::getExaminedItems)
                .sorted((a, b) -> (int) (b - a))
                .limit(2)
                .collect(Collectors.toList());
        System.out.println(twos);

        BigInteger one = new BigInteger(String.valueOf(twos.get(0)));
        BigInteger two = new BigInteger(String.valueOf(twos.get(1)));
        return one.multiply(two).toString();
    }
}
