/**

Constructed tree is

              10
             /  \
           8     2
          / \   /
         3   5 4
*/

/**

 Given a binary tree and a number, return true if the tree has a
 root-to-leaf path such that adding up all the values
 along the path equals the given number.
 Return false if no such path can be found.

 21 –> 10 – 8 – 3
 23 –> 10 – 8 – 5
 16 –> 10 – 2 – 4

 So the returned value should be true only for numbers 21, 23 and 14.
 For any other number, returned value should be false.
 */

/**
Logic:
 Recursively check if left or right child has path sum equal to ( number – value at current node)
 */

public class RootToLeafPathSumMatch {

    public static void main(String[] args) {
        RootToLeafPathSumMatch mainClass = new RootToLeafPathSumMatch();
        mainClass.execute();
    }

    private void execute() {
        Node root = new Node(10);
        populateData(root);

        // printNode(root);

        final int sums[] = new int[] {21, 23, 16, 22, 18, 5};

        for (int matchSum : sums) {
            System.out.println("FOR: matchSum=" + matchSum);
            final boolean doesSumExist = doesSumExist(root, matchSum);
            System.out.println("doesSumExist: " + doesSumExist);
            System.out.println();
        }
    }

    private boolean doesSumExist(final Node node, final int matchSum) {

        System.out.println(String.format(" STEP data=%d, matchSum=%d", node.data, matchSum));

        // path did not terminate and has high values
        if (matchSum < node.data) {
            return false;
        }

        boolean hasChildren =  hasChildren(node);

        if (!hasChildren) {
            return matchSum == node.data;
        }

        // if comes here, there are children present to execute on

        int remainingSum = matchSum - node.data;

        if (null != node.left) {
            boolean leftResult = doesSumExist(node.left, remainingSum);
            if (leftResult) {
                return true;
            }
        }

        if (null != node.right) {
            boolean rightResult = doesSumExist(node.right, remainingSum);
            if (rightResult) {
                return true;
            }
        }

        return false;
    }

    private boolean hasChildren(final Node node) {
        return  (null != node.left) || (null != node.right);
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

        root.left = new Node(8);
        root.right = new Node(2);

        root.left.left = new Node(3);
        root.left.right = new Node(5);

        root.right.left = new Node(4);
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
