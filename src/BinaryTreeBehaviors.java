import java.util.*;

public class BinaryTreeBehaviors {

    private TreePrinter treePrinter = new TreePrinter();

    public static void main(String[] args) {
        BinaryTreeBehaviors testClass = new BinaryTreeBehaviors();

        // testClass.sampleTestTable();
        // testClass.sampleTestTableWithSomeNullColumnData();

        testClass.findHeight();
        testClass.findDiameter();

        testClass.printBoundaryTraversal();
    }

    public void findHeight() {
        Node root = populateDataForBinaryTree();
        treePrinter.printTree(root);

        System.out.println("Height: " + height(root));
    }

    public void findDiameter() {
        Node root = populateDataForBinaryTree();
        treePrinter.printTree(root);

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
        Node node = prepareBinaryTree3();
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

    private void addToCollector(Set<String> collector, String data) {
        // System.out.println("   adding to collector: " + data);
        collector.add(data);
    }

    private void collectLeft(Node node, Set<String> collector) {
        if (null == node) {
            return;
        }

        // System.out.println(" current: " + node.data);

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

        // System.out.println(" current2: " + node.data);

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

        // System.out.println(" current3: " + node.data);

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

    private Node prepareBinaryTree3() {
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

    private Node prepareSmallestTree() {
        Node node = Node.builder()
                .data("a")
                .left(Node.builder()
                        .data("b")
                        .left()
                        .right()
                        .build())
                .right(Node.builder()
                        .data("c")
                        .left()
                        .right()
                        .build())
                .build();

        return node;
    }

    private Node populateDataForBinaryTree() {

        Node a = populateDataForBinaryTree2();

        Node x = prepareNode("x");
        Node y = prepareNode("y", null, x);

        Node z = prepareNode("z", a, y);

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

    static class Node {
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

        public static NodeBuilder builder() {
            return new NodeBuilder();
        }

        static class NodeBuilder {
            private String data;
            private Node left;
            private Node right;

            public Node build() {
                return new Node(this.data, this.left, this.right);
            }

            public NodeBuilder data(String data) {
                this.data = data;
                return this;
            }

            public NodeBuilder left(Node left) {
                this.left = left;
                return this;
            }

            public NodeBuilder right(Node right) {
                this.right = right;
                return this;
            }

            public NodeBuilder left() {
                this.left = null;
                return this;
            }

            public NodeBuilder right() {
                this.right = null;
                return this;
            }
        }
    }

    class TreePrinter {

        private final String LEFT_SLASH = "/";
        private final String RIGHT_SLASH = "\\";

        public void printTree(Node node) {
            printTree(node, 3);
        }

        public void printTree(Node node, int printColumnLength) {

            TheTable theTable = new TheTable();
            theTable.setPrintColumnLength(printColumnLength);

            // using 15 column table
            int middleColumn = 16;

            addToTable(theTable, node, 0, middleColumn);

            //System.out.println(String.format("rows=%d, columns=%d", theTable.rowSize(), theTable.rows().get(0).columnSize()));

            theTable.print();
        }

        private void addToTable(TheTable theTable, Node node, int startRow, int middleColumn) {
            if (null == node) {
                return;
            }
            theTable.addData(String.format("(%s)", node.data), startRow, middleColumn);
            if (null != node.left) {
                // System.out.println("adding left: " + node.left.data);
                theTable.addData(LEFT_SLASH, startRow+1, middleColumn-1);
            }
            if (null != node.right) {
                // System.out.println("adding right: " + node.right.data);
                theTable.addData(RIGHT_SLASH, startRow+1, middleColumn+1);
            }

            addToTable(theTable, node.left, startRow+2, middleColumn-2);
            addToTable(theTable, node.right, startRow+2, middleColumn+2);
        }

    }

    private void sampleTestTable() {
        TheTable table = new TheTable();
        int rows = 5;
        int columns = 5;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                String value = String.format("[%d,%d]", i, j);
                table.addData(value, i, j);
            }
        }

        table.print();
    }

    private void sampleTestTableWithSomeNullColumnData() {
        TheTable table = new TheTable();
        int rows = 5;
        int columns = 5;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (0 == (i+j) % 2) {
                    String value = String.format("[%d,%d]", i, j);
                    table.addData(value, i, j);
                }
            }
        }

        table.print();
    }


    // for pretty print above binary tree
    class TheTable {

        private Map<Integer, Row> container = new TreeMap();

        private int printColumnLength = 10;

        private Utils utils = new Utils();

        public Map<Integer, Row> rows() {
            return container;
        }

        public int rowSize() {
            return utils.getSize(container);
        }

        public void addData(String data, int rowNum, int columnNum) {
            Row row = container.get(rowNum);
            if (null == row) {
                container.put(rowNum, new Row());
                row = container.get(rowNum);
            }

            Row.Column column = row.getColumn(columnNum);

            // to avoid overlaps for tree nodes
            if (null == column.data) {
                column.setData(data);
            } else {
                column.setData(column.data + "" + data);
            }
        }

        public void setPrintColumnLength(int printColumnLength) {
            this.printColumnLength = printColumnLength;
        }

        public int getPrintColumnLength() {
            return printColumnLength;
        }

        public void print() {
            System.out.println();
            System.out.println();
            System.out.println(getPrintData());
            System.out.println();
            System.out.println();
        }

        public String getPrintData() {
            final StringBuilder stringBuilder = new StringBuilder();
            final int rowSize = rowSize();
            for (int i = 0; i < rowSize; i++) {
                final Row row = container.get(i);
                if (null == row) {
                    // System.out.println("print: null row:i="+i);
                    stringBuilder.append(Utils.NEWLINE);
                    continue;
                }

                final int columnSize = row.columnSize();
                for (int j = 0; j < columnSize; j++) {
                    final Row.Column column = row.getColumn(j);
                    if (null == column || null == column.data) {
                        // System.out.println("print: null column/its data: j="+j);
                        stringBuilder.append(utils.preparePrintData(""));
                    } else {
                        stringBuilder.append(utils.preparePrintData(column.getData()));
                    }
                }

                stringBuilder.append(Utils.NEWLINE);
            }

            return stringBuilder.toString();
        }

        class Utils {

            private static final String NEWLINE = "\n";

            public <T> int getSize(Map<Integer, T> container) {
                final Set<Integer> keys = container.keySet();

                // get last element for number of rows
                final Iterator<Integer> iterator = keys.iterator();
                Integer next = new Integer(-1);
                while(iterator.hasNext()) {
                    next = iterator.next();
                }
                return 1 + next;
            }

            public String preparePrintData(String data) {
                // TODO: better?
                int printColumnLenBy2 = getPrintColumnLength()/2;
                int halfLen = data.length()/2;
                final String gapEachSide = getGap(printColumnLenBy2 - halfLen);
                return gapEachSide + data + gapEachSide;
            }

            private String getGap(int n) {
                String gap = " ";
                StringBuilder stringBuilder = new StringBuilder();
                for (int i=0; i<n; i++) {
                    stringBuilder.append(gap);
                }
                return stringBuilder.toString();
            }
        }

        class Row {

            private Map<Integer, Column> container = new TreeMap();

            public Map<Integer, Column> columns() {
                return container;
            }

            public int columnSize() {
                return utils.getSize(container);
            }

            public Column getColumn(int columnNum) {
                Column column = container.get(columnNum);
                if (null == column) {
                    container.put(columnNum, new Column());
                    column = container.get(columnNum);
                }
                return column;
            }

            class Column {

                private String data;

                public Column() {
                }

                public Column(String data) {
                    this.data = data;
                }

                public void setData(String data) {
                    this.data = data;
                }

                public String getData() {
                    return data;
                }
            }
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

