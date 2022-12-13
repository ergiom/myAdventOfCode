package aoc2022.day13;


import aoc2022.day13.Node.ElementSplitter;
import aoc2022.day13.Node.Node;
import aoc2022.day13.Node.NodeType;

import java.util.LinkedList;
import java.util.List;

public class ListParser {
    public static List<Node> parse(String string) {
        List<Node> list = new LinkedList<>();
        if (isInteger(string)) {
            Node node = new Node(NodeType.INTEGER, Integer.valueOf(string));
            list.add(node);
        }
        else if (! isEmpty(string)) {
            ElementSplitter splitter = new ElementSplitter();
            splitter.split(string);

            for (String element: splitter) {
                list.add(new Node(NodeType.ARRAY, parse(element)));
            }
        }

        return list;
    }

    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
        }
        catch (NumberFormatException exception) {
            return false;
        }

        return true;
    }

    private static boolean isEmpty(String string) {
        return "[]".equals(string);
    }
}
