package aoc2022.day12;

import aoc2022.day12.HeightMap.LinkedHeightMap;
import aoc2022.day12.HeightMap.Position;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String fileName = "./src/aoc2022/day12/HeightMap.txt";

        System.out.println("Part 1: " + partOne(fileName));
        System.out.println("Part 2: " + partTwo(fileName));
    }

    public static int partOne(String fileName) {
        List<String> lines = new LinkedList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        MapParser parser = new MapParser();
        parser.parse(lines);
        LinkedHeightMap map = parser.toLinkedHeightMap();
        return map.findShortestPathToEnd();
    }

    public static int partTwo(String fileName) {
        List<String> lines = new LinkedList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        MapParser parser = new MapParser();
        parser.parse(lines);
        LinkedHeightMap map = parser.toLinkedHeightMap();

        int min = Integer.MAX_VALUE;
        for (Position start: map.findAll(0)) {
            map.changeStart(start);
            min = Integer.min(map.findShortestPathToEnd(), min);
        }

        return min;
    }
}
