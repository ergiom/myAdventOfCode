package aoc2022.day3;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PartOneLineReducer implements LineReducer {
    private int sum;

    PartOneLineReducer() {
        sum = 0;
    }

    @Override
    public void add(String line) {
        String[] halves = splitInHalf(line);
        Set<Character> firstSet = stringToSet(halves[0]);
        Set<Character> secondSet = stringToSet(halves[1]);
        Set<Character> intersectedSet = intersection(firstSet, secondSet);
        int result = scoreSet(intersectedSet);
        sum += result;
    }

    private int scoreSet(Set<Character> set) {
        int setSum = 0;
        for (char ch: set) {
            setSum += scoreChar(ch);
        }

        return setSum;
    }

    private int scoreChar(char ch) {
        if (Character.isUpperCase(ch)) return ch - 'A' + 27;
        return ch - 'a' + 1;
    }

    private Set<Character> intersection(Set<Character> s1, Set<Character> s2) {
        Set<Character> resultSet = new HashSet<>(s1);
        resultSet.retainAll(s2);
        return resultSet;
    }

    private Set<Character> stringToSet(String str) {
        return str.chars().mapToObj(a -> (char) a).collect(Collectors.toSet());
    }

    private String[] splitInHalf(String line) {
        int halfIndex = line.length() / 2;
        String[] halves = new String[2];

        halves[0] = line.substring(0, halfIndex);
        halves[1] = line.substring(halfIndex);

        return halves;
    }

    @Override
    public String getResult() {
        return String.valueOf(sum);
    }
}
