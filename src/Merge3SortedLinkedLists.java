import java.util.*;

public class Merge3SortedLinkedLists {

    private Node nodeA;
    private Node nodeB;
    private Node nodeC;

    private final Map<Integer, Node> ALL_LINKED_LISTS = new HashMap<>();

     public static void main(String[] args) {
        Merge3SortedLinkedLists testClass = new Merge3SortedLinkedLists();
        final Node result = testClass.execute();

        System.out.println();
        System.out.println("RESULT below: ");
        testClass.printNode(result);
        System.out.println();
    }

    public Node execute() {
        populateData();

        ALL_LINKED_LISTS.put(0, nodeA);
        ALL_LINKED_LISTS.put(1, nodeB);
        ALL_LINKED_LISTS.put(2, nodeC);

        Node result = getMinimum();

        Node head = result;

        Node nextNode;
        while (null != (nextNode = getMinimum())) {
            result.next = nextNode;
            result = result.next;
        }

        return head;
    }

    private Node getMinimum() {
         return getMinimumNew();
    }

    private Node getMinimumNew() {
        final Node tmp = getNodeWithSmallestValue(0);
        if (null == tmp ) {
            // terminating condition
            return null;
        }
        moveMatchingNodeToNextElement(tmp);
        return tmp;
    }

    private Node getNodeWithSmallestValue(final int startIndex) {
        Node tmp;

        if (null != ALL_LINKED_LISTS.get(startIndex)) {
            tmp = ALL_LINKED_LISTS.get(startIndex);
            // System.out.println(tmp);
            // iterate on remaining to 'keep' the smallest
            for (int i = startIndex + 1; i < ALL_LINKED_LISTS.size(); i++) {
                Node next = ALL_LINKED_LISTS.get(i);
                if (null != next && tmp.data > next.data) {
                    tmp = next;
                }
            }
        } else {
            // node at startIndex is null
            if (0 == startIndex) {
                return null;
            }
            return getNodeWithSmallestValue(startIndex - 1);
        }

        return tmp;
    }

    // note: needs replacement in list, else would use earlier ones
    private void moveMatchingNodeToNextElement(Node tmp) {
        for (int i = 0; i < ALL_LINKED_LISTS.size();i++) {
            Node next = ALL_LINKED_LISTS.get(i);
            if (tmp.equals(next)) {
                next = next.next;
                ALL_LINKED_LISTS.put(i, next);
                break;
            }
        }
    }

    private void populateData() {
        final int[] arrayA = new int[] {2, 4, 5, 8 , 12};
        final int[] arrayB = new int[] {1, 5, 9};
        final int[] arrayC = new int[] {3, 6, 7, 9, 10, 11, 12};

        nodeA = new Node(arrayA[0]);
        nodeB = new Node(arrayB[0]);
        nodeC = new Node(arrayC[0]);

        nodeA = populateData(arrayA, nodeA);
        nodeB = populateData(arrayB, nodeB);
        nodeC = populateData(arrayC, nodeC);

        printNode(nodeA);
        System.out.println();

        printNode(nodeB);
        System.out.println();

        printNode(nodeC);
        System.out.println();
    }

    private void printNode(final Node node) {
        if (null == node) {
            return;
        }

         System.out.print(node.data + " -> ");
         if (null != node.next) {
             printNode(node.next);
         } else {
             System.out.print("[null]");
         }
    }

    private Node populateData(final int[] arr,
                              Node node) {
         final Node head = node;

         for (int index = 1; index < arr.length; index++) {
            Node nextNode = new Node(arr[index]);
            node.next = nextNode;
            node = node.next;
         }

        return head;
    }

    class Node {
        int data;
        Node next;
        Node(int data) {
            this.data=data;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return data == node.data &&
                    Objects.equals(next, node.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(data, next);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }

    // ===== older non-optimized solutions =====


    private Node getMinimumOld() {
        Node tmp = null;

        // TODO: optimize by putting in 1 list and
        //  provide 'starting offset' for remaining nodes to find mimimum
        // note: see new getMinimum method above

        if (null != nodeA) {
            tmp = nodeA;
            if (null != nodeB && tmp.data > nodeB.data) {
                tmp = nodeB;
            }
            if (null != nodeC && tmp.data > nodeC.data) {
                tmp = nodeC;
            }
        } else if (null != nodeB) {
            tmp = nodeB;
            if (null != nodeC && tmp.data > nodeC.data) {
                tmp = nodeC;
            }
        } else if (null != nodeC) {
            tmp = nodeC;
        }

        // System.out.println(tmp);

        if (null == tmp ) {
            // terminating condition
            return null;
        }

        if (tmp.equals(nodeA)) {
            nodeA = nodeA.next;
        }
        if (tmp.equals(nodeB)) {
            nodeB = nodeB.next;
        }
        if (tmp.equals(nodeC)) {
            nodeC = nodeC.next;
        }

        System.out.println(tmp.data);

        return tmp;
    }
}
