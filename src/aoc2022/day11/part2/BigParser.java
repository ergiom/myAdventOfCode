package aoc2022.day11.part2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class BigParser {
    List<String> lines;
    List<Integer> noteStarts;

    public BigParser(List<String> list) {
        lines = list;
        noteStarts = new LinkedList<>();
    }

    public void parse() {
        noteStarts.add(0);

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.matches("")) {
                if (i + 1 < lines.size() && ! lines.get(i + 1).matches("")) noteStarts.add(i + 1);
            }
        }
    }

    public BigMonkey[] createMonkeys() {
        BigMonkey[] monkeys = new BigMonkey[noteStarts.size()];
        int[][] directions = new int[noteStarts.size()][2];
        int[] divisors = new int[noteStarts.size()];

        for (int i = 0; i < noteStarts.size(); i++) {
            int noteStart = noteStarts.get(i);
            monkeys[i] = createMonkey(noteStart);
            directions[i] = getDirections(noteStart + 4);
            divisors[i] = getTest(noteStart + 3);
        }

        for (int i = 0; i < noteStarts.size(); i++) {
            int noteStart = noteStarts.get(i);
            List<Item> items = Arrays.stream(getItems(noteStart + 1))
                    .boxed()
                    .map(a -> new Item(a, divisors))
                    .collect(Collectors.toList());
            for (Item item: items) {
                monkeys[i].add(item);
            }
        }

        for (int i = 0; i < monkeys.length; i++) {
            BigMonkey trueMonkey = monkeys[directions[i][0]];
            BigMonkey falseMonkey = monkeys[directions[i][1]];

            monkeys[i].link(trueMonkey, falseMonkey);
        }

        return monkeys;
    }

    private BigMonkey createMonkey(int startIndex) {
        if (startIndex + 5 >= lines.size()) throw new RuntimeException();

        Consumer<Item> operation = getOperation(startIndex + 2);
        int test = getTest(startIndex + 3);

        return new BigMonkey(operation, test);
    }

    private int[] getDirections(int index) {
        int[] directions = new int[2];

        String trueLine = lines.get(index);
        String falseLine = lines.get(index + 1);

        directions[0] = Integer.parseInt(trueLine.replaceAll("\\s*If true: throw to monkey ", ""));
        directions[1] = Integer.parseInt(falseLine.replaceAll("\\s*If false: throw to monkey ", ""));

        return directions;
    }

    private int[] getItems(int index) {
        String specification = lines.get(index);
        String[] strItems = specification.replaceAll("\\s*Starting items:", "")
                                         .split(",");

        int[] items = new int[strItems.length];
        for (int i = 0; i < strItems.length; i++) {
            String strItem = strItems[i];

            items[i] = Integer.parseInt(strItem.trim());
        }

        return items;
    }

    private Consumer<Item> getOperation(int index) {
        String line = lines.get(index);

        String cleanEquation = line.replaceAll("\\s*Operation: new = old ", "");
        if (cleanEquation.matches("\\* old")) {
            return Item::square;
        }
        else if (cleanEquation.matches("\\* \\d+")) {
            int value = Integer.parseInt(cleanEquation.replaceAll("\\* ", ""));
            return a -> a.multiply(value);
        } else if (cleanEquation.matches("\\+ \\d+")) {
            int value = Integer.parseInt(cleanEquation.replaceAll("\\+ ", ""));
            return a -> a.add(value);
        }

        throw new RuntimeException();

    }

    private int getTest(int index) {
        String line = lines.get(index);
        String strTest = line.replaceAll("\\s*Test: divisible by", "").trim();

        return Integer.parseInt(strTest);
    }
}
