package aoc2022.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String fileName = "./src/aoc2022/day3/ListOfItems.txt";
        System.out.println("Part 1: " + partOne(fileName));
        System.out.println("Part 2: " + partTwo(fileName));
    }

    private static String partOne(String fileName) {
        LineReducer reducer = new PartOneLineReducer();
        try (
            BufferedReader reader = new BufferedReader(new FileReader(fileName))
        ) {
            for (String line: reader.lines().collect(Collectors.toList())) {
                reducer.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return reducer.getResult();
    }

    private static String partTwo(String fileName) {
        LineReducer reducer = new PartTwoLineReducer();
        try (
                BufferedReader reader = new BufferedReader(new FileReader(fileName))
        ) {
            for (String line: reader.lines().collect(Collectors.toList())) {
                reducer.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return reducer.getResult();
    }
}
