import java.util.LinkedList;
import java.util.Queue;

/**
 * direct-code from leetcode
 *
 * prints nicely 'sideways'
 *
 */

public class BinaryTreePrettyPrinter {

    public static void main(String[] args) {
        BinaryTreePrettyPrinter.prettyPrintTree(SampleProvider.prepareBinaryTreeForA());
    }

    /**
     * Definition for a binary tree node.
     * public class Node {
     * int val;
     * Node left;
     * Node right;
     * Node(int x) { val = x; }
     * }
     */

    public static String NodeToString(Node root) {
        if (root == null) {
            return "[]";
        }

        String output = "";
        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()) {
            Node node = nodeQueue.remove();

            if (node == null) {
                output += "null, ";
                continue;
            }

            output += String.valueOf(node.data) + ", ";
            nodeQueue.add(node.left);
            nodeQueue.add(node.right);
        }
        return "[" + output.substring(0, output.length() - 2) + "]";
    }

    public static Node stringToNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        Node root = new Node(Integer.parseInt(item));
        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while (!nodeQueue.isEmpty()) {
            Node node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new Node(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new Node(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static void prettyPrintTree(Node node, String prefix, boolean isLeft) {
        if (node == null) {
            System.out.println("Empty tree");
            return;
        }

        if (node.right != null) {
            prettyPrintTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
        }

        System.out.println(prefix + (isLeft ? "└── " : "┌── ") + node.data);

        if (node.left != null) {
            prettyPrintTree(node.left, prefix + (isLeft ? "    " : "│   "), true);
        }
    }

    public static void prettyPrintTree(Node node) {
        prettyPrintTree(node, "", true);
    }

    static class SampleProvider {
        protected static Node<String> prepareBinaryTreeForA() {
            Node a = Node.builder()
                    .data("a")
                    .left(Node.builder()
                            .data("b")
                            .left(Node.builder()
                                    .data("c")
                                    .left(Node.builder()
                                            .data("d")
                                            .right(Node.builder()
                                                    .data("f")
                                                    .right(Node.builder()
                                                            .data("e")
                                                            .build())
                                                    .build())
                                            .build())
                                    .right(Node.builder()
                                            .data("g")
                                            .build())
                                    .build())
                            .right(Node.builder()
                                    .data("h")
                                    .build())
                            .build())
                    .right(Node.builder()
                            .data("k")
                            .left(Node.builder()
                                    .data("j")
                                    .build())
                            .right(Node.builder()
                                    .data("l")
                                    .left(Node.builder()
                                            .data("m")
                                            .build())
                                    .right(Node.builder()
                                            .data("n")
                                            .build())
                                    .build())
                            .build())
                    .build();

            return a;
        }
    }

}