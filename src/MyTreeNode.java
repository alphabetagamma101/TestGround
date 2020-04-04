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
        GMainUtility.printTree(root);
    }

    private void populateData(final Node root) {

        root.left = new Node(8);
        root.right = new Node(2);

        root.left.left = new Node(3);
        root.left.right = new Node(5);

        root.right.left = new Node(4);
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