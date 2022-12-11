package aoc2022.day11;

import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class Monkey {
    List<Long> items;
    UnaryOperator<Long> operation;
    int test;
    Monkey trueMonkey;
    Monkey falseMonkey;
    long examinedItems;

    Monkey(long[] items, UnaryOperator<Long> operation, int test) {
        this.items = Arrays.stream(items).boxed().collect(Collectors.toList());
        this.operation = operation;
        this.test = test;
        trueMonkey = null;
        falseMonkey = null;
        examinedItems = 0;
    }

    public void link(Monkey trueMonkey, Monkey falseMonkey) {
        this.trueMonkey = trueMonkey;
        this.falseMonkey = falseMonkey;
    }

    public long getExaminedItems() {
        return examinedItems;
    }

    public void playRound(UnaryOperator<Long> operator) {
        while (items.size() > 0) {
            long item = items.remove(0);
            item = operator.apply(operation.apply(item));

            if (item % test == 0) trueMonkey.add(item);
            else falseMonkey.add(item);

            examinedItems++;
        }
    }

    private void add(long item) {
        items.add(item);
    }
}
