package aoc2022.day13.Node;

import java.util.Iterator;

public class ElementSplitter implements Iterator<String>, Iterable<String> {
    int index;
    String string;

    public ElementSplitter() {
        index = 0;
    }

    public void split(String string) {
        this.string = string.substring(1, string.length() - 1);
    }

    @Override
    public boolean hasNext() {
        return index != string.length();
    }

    @Override
    public String next() {
        int i = index;
        int bracketCount = 0;

        while(true) {
            char ch = string.charAt(i);
            if (ch == ',' && bracketCount == 0) {
                String subString = string.substring(index, i);
                index = i + 1;
                return subString;
            }
            else if (ch == '[') {
                bracketCount++;
                i++;
            }
            else if (ch == ']') {
                bracketCount--;
                i++;
            }
            else i++;

            if (i == string.length()) {
                String subString = string.substring(index, i);
                index = i;
                return subString;
            }
            if (bracketCount < 0) throw new RuntimeException();
        }
    }

    @Override
    public Iterator<String> iterator() {
        return this;
    }
}
