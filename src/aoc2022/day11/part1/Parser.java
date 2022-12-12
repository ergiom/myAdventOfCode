package aoc2022.day11.part1;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.function.UnaryOperator;

public class Parser {
    List<String> lines;
    List<Integer> noteStarts;

    public Parser(List<String> list) {
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

    public Monkey[] createMonkeys() {
        Monkey[] monkeys = new Monkey[noteStarts.size()];
        int[][] directions = new int[noteStarts.size()][2];

        for (int i = 0; i < noteStarts.size(); i++) {
            int noteStart = noteStarts.get(i);
            monkeys[i] = createMonkey(noteStart);
            directions[i] = getDirections(noteStart + 4);
        }

        for (int i = 0; i < monkeys.length; i++) {
            Monkey trueMonkey = monkeys[directions[i][0]];
            Monkey falseMonkey = monkeys[directions[i][1]];

            monkeys[i].link(trueMonkey, falseMonkey);
        }

        return monkeys;
    }

    private Monkey createMonkey(int startIndex) {
        if (startIndex + 5 >= lines.size()) throw new RuntimeException();

        long[] items = getItems(startIndex + 1);
        UnaryOperator<BigInteger> operation = getOperation(startIndex + 2);
        int test = getTest(startIndex + 3);

        return new Monkey(items, operation, test);
    }

    private int[] getDirections(int index) {
        int[] directions = new int[2];

        String trueLine = lines.get(index);
        String falseLine = lines.get(index + 1);

        directions[0] = Integer.parseInt(trueLine.replaceAll("\\s*If true: throw to monkey ", ""));
        directions[1] = Integer.parseInt(falseLine.replaceAll("\\s*If false: throw to monkey ", ""));

        return directions;
    }

    private long[] getItems(int index) {
        String specification = lines.get(index);
        String[] strItems = specification.replaceAll("\\s*Starting items:", "")
                                         .split(",");

        long[] items = new long[strItems.length];
        for (int i = 0; i < strItems.length; i++) {
            String strItem = strItems[i];

            items[i] = Integer.parseInt(strItem.trim());
        }

        return items;
    }

    private UnaryOperator<BigInteger> getOperation(int index) {
        String line = lines.get(index);

        String cleanEquation = line.replaceAll("\\s*Operation: new = old ", "");
        if (cleanEquation.matches("\\* old")) {
            return a -> a.multiply(a);
        }
        else if (cleanEquation.matches("\\+ old")) {
            return a -> a.add(a);
        }
        else if (cleanEquation.matches("\\* \\d+")) {
            int value = Integer.parseInt(cleanEquation.replaceAll("\\* ", ""));
            return a -> a.multiply(BigInteger.valueOf(value));
        } else if (cleanEquation.matches("\\+ \\d+")) {
            int value = Integer.parseInt(cleanEquation.replaceAll("\\+ ", ""));
            return a -> a.add(BigInteger.valueOf(value));
        }

        throw new RuntimeException();

    }

    private int getTest(int index) {
        String line = lines.get(index);
        String strTest = line.replaceAll("\\s*Test: divisible by", "").trim();

        return Integer.parseInt(strTest);
    }
}
