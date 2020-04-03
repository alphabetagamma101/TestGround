import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class BinaryTreeBehaviors {

    public static void main(String[] args) {
        BinaryTreeBehaviors testClass = new BinaryTreeBehaviors();

        testClass.findHeight();
        testClass.findDiameter();

        testClass.printBoundaryTraversal();
    }

    public void findHeight() {
        Node root = populateDataForBinaryTree();
        System.out.println("Height: " + height(root));
    }

    public void findDiameter() {
        Node root = populateDataForBinaryTree();
        System.out.println("Diameter: " + diameter(root));
    }

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

    // 1. left-boundary
    // 2. right-boundary
    // 3. leaf-nodes
    // see: https://www.youtube.com/watch?v=uemjIijtu2I
    private void printBoundaryTraversal() {
        // TODO: prepare better binary tree as in video
        Node node = populateDataForBinaryTree2();

        // logic: add to set (no duplicates)
        Set<String> collector = new LinkedHashSet();

        collectLeft(node, collector);
        collectRight(node, collector);
        collectLeaf(node, collector);

        collector.stream().forEach(System.out::println);
    }

    private void addToCollector(Set<String> collector, String data) {
        System.out.println("   adding to collector: " + data);
        collector.add(data);
    }

    private void collectLeft(Node node, Set<String> collector) {
        if (null == node) {
            return;
        }

        System.out.println(" current: " + node.data);

        if (null != node.left) {
            addToCollector(collector, node.data);
            collectLeft(node.left, collector);
        } else {
            // left is null
            if (null != node.right) {
                addToCollector(collector, node.data);
                collectLeft(node.right, collector);
            }
        }
    }

    private void collectRight(Node node, Set<String> collector) {
        if (null == node) {
            return;
        }

        System.out.println(" current2: " + node.data);

        if (null != node.right) {
            addToCollector(collector, node.data);
            collectRight(node.right, collector);
        } else {
            // right is null
            if (null != node.left) {
                addToCollector(collector, node.data);
                collectRight(node.left, collector);
            }
        }
    }

    private void collectLeaf(Node node, Set<String> collector) {
        if (null == node) {
            return;
        }

        System.out.println(" current3: " + node.data);

        if (null != node.left) {
            collectLeaf(node.left, collector);
        }
        if (isLeaf(node)) {
            addToCollector(collector, node.data);
        }
        if (null != node.right) {
            collectLeaf(node.right, collector);
        }
    }

    private boolean isLeaf(Node node) {
        return (null == node.left) && (null == node.right);
    }

    private void printInorderTraversal() {

    }

    // ???
    private void printTraversal() {

    }

    private Node populateDataForBinaryTree() {

        Node a = populateDataForBinaryTree2();

        Node x = prepareNode("x");
        Node y = prepareNode("y", null, x);

        Node z = prepareNode("Z", a, y);

        return z;
    }

    private Node populateDataForBinaryTree2() {

        Node h = prepareNode("h");
        Node i = prepareNode("i");

        Node f = prepareNode("f");
        Node g = prepareNode("g", h, i);
        Node l = prepareNode("l");
        Node m = prepareNode("m");

        Node d = prepareNode("d");
        Node e = prepareNode("e", f, g);
        Node j = prepareNode("j");
        Node k = prepareNode("k", l , m);

        Node b = prepareNode("b", d, e);
        Node c = prepareNode("c", j, k);

        Node a = prepareNode("a", b, c);

        return a;
    }

    private Node prepareNode(String data) {
        return new Node(data, null, null);
    }

    private Node prepareNode(String data, Node left, Node right) {
        return new Node(data, left, right);
    }

    class Node {
        String data;
        Node left;
        Node right;
        Node(String data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
        Node(String data) {
            this(data, null, null);
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
