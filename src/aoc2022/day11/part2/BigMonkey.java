package aoc2022.day11.part2;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class BigMonkey {
    List<Item> items;
    Consumer<Item> operation;
    int test;
    BigMonkey trueMonkey;
    BigMonkey falseMonkey;
    long examinedItems;

    BigMonkey(Consumer<Item> operation, int test) {
        this.items = new LinkedList<>();
        this.operation = operation;
        this.test = test;
        trueMonkey = null;
        falseMonkey = null;
        examinedItems = 0;
    }

    public void link(BigMonkey trueMonkey, BigMonkey falseMonkey) {
        this.trueMonkey = trueMonkey;
        this.falseMonkey = falseMonkey;
    }

    public long getExaminedItems() {
        return examinedItems;
    }

    public void playRound() {
        while (items.size() > 0) {
            Item item = items.remove(0);
            operation.accept(item);

            if (item.test(test)) trueMonkey.add(item);
            else falseMonkey.add(item);

            examinedItems++;
        }
    }

    public void add(Item item) {
        items.add(item);
    }
}
