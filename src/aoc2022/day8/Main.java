package aoc2022.day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String fileName = "./src/aoc2022/day8/Forest.txt";
        System.out.println("Part 1: " + partOne(fileName));
        System.out.println("Part 2: " + partTwo(fileName));
    }

    private static int partOne(String fileName) {
        GridDriver gridDriver = new GridDriver();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                gridDriver.addRow(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return gridDriver.countVisible();
    }

    private static long partTwo(String fileName) {
        GridDriver gridDriver = new GridDriver();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                gridDriver.addRow(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return gridDriver.getBiggestScenicScore();
    }
}
