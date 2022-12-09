package aoc2022.day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String fileName = "./src/aoc2022/day9/Movement.txt";
//        String fileName = "./src/aoc2022/day9/MovementTest.txt";
        System.out.println("Part 1: " + partOne(fileName));
        System.out.println("Part 2: " + partTwo(fileName));
    }

    private static int partOne(String fileName) {
        GridDriver driver = new GridDriver(2);
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                driver.parseMovement(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return driver.getTailCount();
    }

    private static int partTwo(String fileName) {
        GridDriver driver = new GridDriver(10);
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                driver.parseMovement(line);
//                System.out.println(line);
//                driver.print();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return driver.getTailCount();
    }
}
