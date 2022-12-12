package aoc2022.day11.part1;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class Monkey {
    List<BigInteger> items;
    UnaryOperator<BigInteger> operation;
    BigInteger test;
    Monkey trueMonkey;
    Monkey falseMonkey;
    long examinedItems;

    Monkey(long[] items, UnaryOperator<BigInteger> operation, int test) {
        this.items = Arrays.stream(items).boxed().map(BigInteger::valueOf).collect(Collectors.toList());
        this.operation = operation;
        this.test = BigInteger.valueOf(test);
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

    public void playRound(UnaryOperator<BigInteger> operator) {
        while (items.size() > 0) {
            BigInteger item = items.remove(0);
            item = operator.apply(operation.apply(item));

            if (item.mod(test).equals(BigInteger.ZERO)) trueMonkey.add(item);
            else falseMonkey.add(item);

            examinedItems++;
        }
    }

    private void add(BigInteger item) {
        items.add(item);
    }
}
