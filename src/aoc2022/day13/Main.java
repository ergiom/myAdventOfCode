package aoc2022.day13;

import aoc2022.day13.Node.ListComparator;
import aoc2022.day13.Node.Node;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String fileName = "./src/aoc2022/day13/Packets.txt";
//        String fileName = "./src/aoc2022/day13/TestPackets.txt";
        System.out.println("Part 1: " + partOne(fileName));
        System.out.println("Part 2: " + partTwo(fileName));
    }

    private static int partOne(String fileName) {
        List<String> lines = fileToLines(fileName);
        List<Node>[][] pairs = new LinkedList[lines.size() / 3][2];

        for (int entry = 0; entry < lines.size() / 3; entry++) {
            List<Node> list1 = ListParser.parse(lines.get(3 * entry));
            List<Node> list2 = ListParser.parse(lines.get(3 * entry + 1));

            pairs[entry][0] = list1;
            pairs[entry][1] = list2;
        }

        int sum = 0;
        for (int i = 0; i < pairs.length; i++) {
            List<Node>[] pair = pairs[i];
            List<Node> list1 = pair[0];
            List<Node> list2 = pair[1];

            if (ListComparator.compare(list1, list2) <= 0) sum += i + 1;
        }

        return sum;
    }

    private static int partTwo(String fileName) {
        List<String> lines = fileToLines(fileName);
        lines = lines.stream().filter(a -> ! a.equals("")).collect(Collectors.toList());
        List<List<Node>> list = new LinkedList<>();

        for (String line: lines) {
            list.add(ListParser.parse(line));
        }

        list.add(ListParser.parse("[[2]]"));
        List<Node> l1 = list.get(list.size() - 1);
        list.add(ListParser.parse("[[6]]"));
        List<Node> l2 = list.get(list.size() - 1);

        list.sort(ListComparator::compare);

        return (list.indexOf(l1) + 1) * (list.indexOf(l2) + 1);
    }

    private static List<String> fileToLines(String fileName) {
        List<String> lines = new LinkedList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return lines;
    }
}
