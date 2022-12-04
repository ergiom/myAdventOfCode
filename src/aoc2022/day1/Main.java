package aoc2022.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String fileName = "./src/aoc2022/day1/calories.txt";
        List<Long> list = readCalories(fileName);
        list.sort(Comparator.comparingLong(a -> -a));
        System.out.println(list.get(0));
        System.out.println((Long) list.stream().limit(3).mapToLong(a -> a).sum());
    }

    private static List<Long> readCalories(String file) {
        LinkedList<Long> list  = new LinkedList<>();
        list.add(0L);

        readFromFile(file, list);

        return list;
    }

    private static void readFromFile(String fileName, Deque<Long> deque) {
        try (
            BufferedReader reader = new BufferedReader(new FileReader(fileName))
        ) {
            String line;
            while (true) {
                line = reader.readLine();
                if (line == null) break;
                collect(line, deque);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void collect(String input, Deque<Long> deque) {
        if (input.equals("")) {
            deque.add(0L);
            return;
        }

        long last = deque.getLast();
        last += Long.parseLong(input);
        deque.add(last);
    }
}
