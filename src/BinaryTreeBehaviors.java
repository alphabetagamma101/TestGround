import java.util.*;

/**
 * Binary Tree:
 *
 * 1. Find height of a binary tree
 * 2. Find diameter of a binary tree
 * 3. Print boundary traversal of a binary tree
 *
 */
public class BinaryTreeBehaviors {

    private GMainUtility.BinaryTreePrinter treePrinter = GMainUtility.createBinaryTreePrinter();

    public static void main(String[] args) {
        BinaryTreeBehaviors testClass = new BinaryTreeBehaviors();

        testClass.findHeight();
        testClass.findDiameter();

        testClass.printBoundaryTraversal();
    }

    public void findHeight() {
        Node root = SampleProvider.populateDataForBinaryTreeZ();
        treePrinter.printTree(root);

        System.out.println("Height: " + height(root));
    }

    public void findDiameter() {
        Node root = SampleProvider.populateDataForBinaryTreeZ();
        treePrinter.printTree(root);

        System.out.println("Diameter: " + diameter(root));
    }

    // Question: Find diameter of a binary tree
    //
    // d = Max ( (1 + leftHeight + rightHeight), <-- if node is part of diameter
    //            Max ( leftDiameter, rightDiameter))
    // see: https://www.youtube.com/watch?v=ey7DYc9OANo
    private int diameter(Node node) {
        if (null == node) {
            return 0;
        }
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        int leftDiameter = diameter(node.left);
        int rightDiamter = diameter(node.right);

        int nodeAsPartOfDiameter = 1 + leftHeight + rightHeight;
        int nodeNotAsPartOfDiameter = Math.max(leftDiameter, rightDiamter);

        return Math.max(nodeAsPartOfDiameter, nodeNotAsPartOfDiameter);
    }

    // Question: Find height of a binary tree
    //
    // height = 1 + number of edges
    // see: https://www.youtube.com/watch?v=_O-mK2g_jhI
    private int height(Node node) {
        if (null == node) {
            return 0;
        }
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }

    // Question: Print boundary traversal of a binary tree
    //
    // 1. left-boundary
    // 2. right-boundary
    // 3. leaf-nodes
    // see: https://www.youtube.com/watch?v=uemjIijtu2I
    private void printBoundaryTraversal() {
        Node node = SampleProvider.prepareBinaryTreeForA();
        treePrinter.printTree(node);

        // logic: add to set (no duplicates)
        Set<String> collector = new LinkedHashSet();

        collectLeft(node, collector);
        collectRight(node, collector);
        collectLeaf(node, collector);

        System.out.println("Boundary traversal: ");
        collector.stream().forEach(each -> System.out.print(each + " "));
        System.out.println();
        System.out.println(String.format("(total=%d)", collector.size()));
    }

    // part-of: boundary-traversal of binary tree
    private void collectLeft(Node node, Set<String> collector) {
        if (null == node) {
            return;
        }

        // System.out.println(" current: " + node.data);

        if (null != node.left) {
            addToCollector(collector, (String) node.data);
            collectLeft(node.left, collector);
        } else {
            // left is null
            if (null != node.right) {
                addToCollector(collector, (String) node.data);
                collectLeft(node.right, collector);
            }
        }
    }

    // part-of: boundary-traversal of binary tree
    private void collectRight(Node node, Set<String> collector) {
        if (null == node) {
            return;
        }

        // System.out.println(" current2: " + node.data);

        if (null != node.right) {
            addToCollector(collector, (String) node.data);
            collectRight(node.right, collector);
        } else {
            // right is null
            if (null != node.left) {
                addToCollector(collector, (String) node.data);
                collectRight(node.left, collector);
            }
        }
    }

    // part-of: boundary-traversal of binary tree
    private void collectLeaf(Node node, Set<String> collector) {
        if (null == node) {
            return;
        }

        // System.out.println(" current3: " + node.data);

        if (null != node.left) {
            collectLeaf(node.left, collector);
        }
        if (isLeaf(node)) {
            addToCollector(collector, (String) node.data);
        }
        if (null != node.right) {
            collectLeaf(node.right, collector);
        }
    }

    private void addToCollector(Set<String> collector, String data) {
        // System.out.println("   adding to collector: " + data);
        collector.add(data);
    }

    private boolean isLeaf(Node node) {
        return (null == node.left) && (null == node.right);
    }

    private void printInorderTraversal() {

    }

    // ???
    private void printTraversal() {

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

        protected static Node<String> populateDataForBinaryTreeZ() {
            Node a = prepareBinaryTreeHelper();

            Node x = prepareNode("x");
            Node y = prepareNode("y", null, x);

            Node z = prepareNode("z", a, y);

            return z;
        }

        private static Node<String> prepareBinaryTreeHelper() {

            Node h = prepareNode("h");
            Node i = prepareNode("i");

            Node f = prepareNode("f");
            Node g = prepareNode("g", h, i);
            Node l = prepareNode("l");
            Node m = prepareNode("m");

            Node d = prepareNode("d");
            Node e = prepareNode("e", f, g);
            Node j = prepareNode("j");
            Node k = prepareNode("k", l, m);

            Node b = prepareNode("b", d, e);
            Node c = prepareNode("c", j, k);

            Node a = prepareNode("a", b, c);

            return a;
        }

        private static Node<String> prepareNode(String data) {
            return new Node(data, null, null);
        }

        private static Node<String> prepareNode(String data, Node left, Node right) {
            return new Node(data, left, right);
        }
    }
}


/**

                z
             /     \
             a      y
          /    \     \
         b       c    x
        / \      / \
       d   e    j    k
          / \       / \
         f   g     l   m
            / \
           h   i


 */

