package aoc2022.day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Part 1: " + partOne());
        System.out.println("Part 2: " + partTwo());
    }

    private static int partOne() {
        ElvenProtocolDriver driver = new ElvenProtocolDriver(4);
        String fileName = "./src/aoc2022/day6/Signal.txt";
        int charsRead;

        try (
            BufferedReader reader = new BufferedReader(new FileReader(fileName))
        ) {
            charsRead = driver.skipToMessage(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return charsRead;
    }

    private static int partTwo() {
        ElvenProtocolDriver driver = new ElvenProtocolDriver(14);
        String fileName = "./src/aoc2022/day6/Signal.txt";
        int charsRead;

        try (
                BufferedReader reader = new BufferedReader(new FileReader(fileName))
        ) {
            charsRead = driver.skipToMessage(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return charsRead;
    }
}
