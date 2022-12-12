package aoc2022.day11.part2;

import java.util.HashMap;
import java.util.Map;

public class Item {
    Map<Integer, Integer> modulo;

    Item(int value, int ... divisors) {
        modulo = new HashMap<>();

        for (int divisor: divisors) {
            modulo.put(divisor, value % divisor);
        }
    }

    public void multiply(int number) {
        for (int key: modulo.keySet()) {
            int value = modulo.get(key) * number;
            modulo.put(key, value % key);
        }
    }

    public void add(int number) {
        for (int key: modulo.keySet()) {
            int value = modulo.get(key) + number;
            modulo.put(key, value % key);
        }
    }

    public void square() {
        for (int key: modulo.keySet()) {
            int value = modulo.get(key) * modulo.get(key);
            modulo.put(key, value % key);
        }
    }

    public boolean test(int number) {
        return modulo.get(number) == 0;
    }
}
