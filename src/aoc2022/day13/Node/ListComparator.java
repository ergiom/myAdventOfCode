package aoc2022.day13.Node;

import java.util.List;

public class ListComparator {
    public static int compare(List<Node> list1, List<Node> list2) {
        int i = 0;
        while(true) {
            if (list1.size() == i && i == list2.size()) return 0;
            if (list1.size() == i) return -1;
            if (list2.size() == i) return 1;

            Node node1 = list1.get(i);
            Node node2 = list2.get(i);

            int result = compareNodes(node1, node2);
            if (result != 0) return result;

            i++;
        }
    }

    private static int compareNodes(Node node1, Node node2) {
        if (node1.getType() == NodeType.EMPTY && node2.getType() == NodeType.EMPTY) return 0;
        if (node1.getType() == NodeType.EMPTY && node2.getType() != NodeType.EMPTY) return -1;
        if (node1.getType() != NodeType.EMPTY && node2.getType() == NodeType.EMPTY) return 1;

        if (node1.getType() == NodeType.INTEGER && node2.getType() == NodeType.INTEGER) {
            return (Integer) node1.getValue() - (Integer) node2.getValue();
        }
        if (node1.getType() == NodeType.INTEGER && node2.getType() == NodeType.ARRAY) {
            return compareListAndInt(node1, node2);
        }
        if (node1.getType() == NodeType.ARRAY && node2.getType() == NodeType.INTEGER) {
            return - compareListAndInt(node2, node1);
        }
        if (node1.getType() == NodeType.ARRAY && node2.getType() == NodeType.ARRAY) {
            return compare((List<Node>) node1.getValue(), (List<Node>) node2.getValue());
        }

        throw new RuntimeException();
    }

    private static int compareListAndInt(Node intNode, Node listNode) {
        List<Node> list = (List<Node>) listNode.getValue();
        if (list.size() == 0) return 1;

        int result = compareNodes(intNode, list.get(0));
        if (result != 0) return result;
        else if (list.size() > 1) return -1;
        else return 0;
    }
}
