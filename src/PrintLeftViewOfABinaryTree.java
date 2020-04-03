import java.util.ArrayList;
import java.util.List;

/**

 Question:
 The left view contains all nodes that are "first" nodes in their levels.

 Constructed tree is

     1
   /   \
  2     3
 / \     \
 4   5    6

 Expected Output : 1 2 4

Another:

 Input :
        1
     /    \
    2       3
     \
      4
       \
       5
        \
         6

 Output :1 2 4 5 6

 */

public class PrintLeftViewOfABinaryTree {

    private static List<Integer> printLevels = new ArrayList<>();

    public static void main(String[] args) {
        PrintLeftViewOfABinaryTree mainClass = new PrintLeftViewOfABinaryTree();
        mainClass.execute();
    }

    private void execute() {
        Node root = new Node(1);

        populateData(root);
        // populateData2(root);

        printNode(root);

        iterateNode(root, 0);
    }

    private void iterateNode(final Node node, final int level) {
        if (!printLevels.contains(level)) {
            System.out.print(node.data + " ");
            printLevels.add(level);
        }

        int nextLevel = level + 1;

        boolean hasLeft = null != node.left;

        if (hasLeft) {
            iterateNode(node.left, nextLevel);
        }

        if (null != node.right) {
            iterateNode(node.right, nextLevel);
        }
    }

    private void printNode(final Node root) {
        if (null == root) {
            return;
        }
        System.out.println(root.data);
        System.out.println("   getting l of: " + root.data);
        printNode(root.left);
        System.out.println("   getting r of: " + root.data);
        printNode(root.right);
    }

    private void populateData(final Node root) {

        root.left = new Node(2);
        root.right = new Node(3);

        root.left.right = new Node(4);

        root.left.right.right = new Node(5);

        root.left.right.right.right = new Node(6);
    }

    private void populateData2(final Node root) {

        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);

        root.right.right = new Node(6);
    }

    class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }
}
