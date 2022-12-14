package aoc2022.day14;

import aoc2022.day14.CaveMap.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String fileName = "./src/aoc2022/day14/Slice.txt";
        System.out.println("Part 1: " + partOne(fileName));
        System.out.println("Part 2: " + partTwo(fileName));
    }

    private static int partOne(String fileName) {
        List<String> lines = getLines(fileName);

        LineParser parser = new LineParser();
        SimpleCaveMap map = new SimpleCaveMap(parser.parse(lines),new Position(0, 500), Node.AIR);
        map.simulate();
//        System.out.println(map);
        return map.getSandNumber();
    }

    private static int partTwo(String fileName) {
        List<String> lines = getLines(fileName);

        LineParser parser = new LineParser();
        FlooredCaveMap map = new FlooredCaveMap(parser.parse(lines), new Position(0, 500), Node.AIR);
        map.simulate();
//        System.out.println(map);
        return map.getSandNumber();
    }

    private static List<String> getLines(String fileName) {
        List<String> lines = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while((line = reader.readLine()) != null) lines.add(line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return lines;
    }
}
