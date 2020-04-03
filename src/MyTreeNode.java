/*

Constructed tree is

              10
             /  \
           8     2
          / \   /
         3   5 4
*/

public class MyTreeNode {
    public static void main(String[] args) {
        MyTreeNode mainClass = new MyTreeNode();
        mainClass.execute();
    }

    private void execute() {
        Node root = new Node(10);
        populateData(root);

        printNode(root);
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


/*

Constructed tree is

              10
             /  \
           8     2
          / \   /
         3   5 4
*/

/**
 Output:

 10
 getting l of: 10
 8
 getting l of: 8
 3
 getting l of: 3
 getting r of: 3
 getting r of: 8
 5
 getting l of: 5
 getting r of: 5
 getting r of: 10
 2
 getting l of: 2
 4
 getting l of: 4
 getting r of: 4
 getting r of: 2

*/