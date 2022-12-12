package aoc2022.day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String fileName = "./src/aoc2022/day12/HeightMap.txt";

        System.out.println("Part 1: " + partOne(fileName));
//        System.out.println("Part 2: " + partTwo(fileName));
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
        MapTraverser traverser = new MapTraverser(parser.toHeightMap());
        return traverser.shortestPath().size();
    }
}
