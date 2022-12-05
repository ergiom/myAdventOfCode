package aoc2022.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Stream;
import java.util.Map;

public class Main {
    private static final Map<String, Integer> map1 = new HashMap<>();
    private static final Map<String, Integer> map2 = new HashMap<>();

    static {
        map1.put("A X", 4);
        map1.put("A Y", 8);
        map1.put("A Z", 3);
        map1.put("B X", 1);
        map1.put("B Y", 5);
        map1.put("B Z", 9);
        map1.put("C X", 7);
        map1.put("C Y", 2);
        map1.put("C Z", 6);

        map2.put("A X", 3);
        map2.put("A Y", 4);
        map2.put("A Z", 8);
        map2.put("B X", 1);
        map2.put("B Y", 5);
        map2.put("B Z", 9);
        map2.put("C X", 2);
        map2.put("C Y", 6);
        map2.put("C Z", 7);
    }

    public static void main(String[] args) {
        String fileName = "./src/aoc2022/day2/Strategy.txt";

        try (
                BufferedReader reader1 = new BufferedReader(new FileReader(fileName));
                BufferedReader reader2 = new BufferedReader(new FileReader(fileName));
        ) {
            Stream<String> stream1 = reader1.lines();
            int result1 = stream1.map(map1::get).reduce(0, Integer::sum);
            System.out.println(result1);

            Stream<String> stream2 = reader2.lines();
            int result2 = stream2.map(map2::get).reduce(0, Integer::sum);
            System.out.println(result2);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
