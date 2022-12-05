package aoc2022.day3;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PartTwoLineReducer implements LineReducer {
    private int lineNumber;
    private int sum;
    private final String[] buffer;

    PartTwoLineReducer() {
        lineNumber = 0;
        sum = 0;
        buffer = new String[3];
    }

    @Override
    public void add(String line) {
        lineNumber++;
        addToBuffer(line);

        if (bufferFull()) {
            char badge = getBadge();
            sum += scoreChar(badge);
        }
    }

    private void addToBuffer(String str) {
        int mod = lineNumber % 3;
        buffer[mod] = str;
    }

    private boolean bufferFull() {
        int mod = lineNumber % 3;
        return mod == 0;
    }

    private char getBadge() {
        Set<Character> line1 = stringToSet(buffer[0]);
        Set<Character> line2 = stringToSet(buffer[1]);
        Set<Character> line3 = stringToSet(buffer[2]);

        Set<Character> result = intersection(intersection(line1, line2), line3);
        return result.iterator().next();
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

    @Override
    public String getResult() {
        return String.valueOf(sum);
    }
}
