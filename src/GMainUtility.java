import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * GOAL: Single class with internal classes to support
 * utility behaviors. This class can be dumped as utility
 * directly.
 *
 * 1. Needs Node class along with it
 *
 * Note:
 * 1. Ensure this class depends on java packages only
 * (and not third-party libraries)
 *
 * Useful internal classes:
 * 1. Table
 * 2. BinaryTreePrinter (uses Node [external] class)
 *
 *
 * See Sample creator class - to get usage
 */
public final class GMainUtility {

    public static Table createTable() {
        return new GMainUtility().new Table();
    }

    public static BinaryTreePrinter createBinaryTreePrinter() {
        return new GMainUtility().new BinaryTreePrinter();
    }

    /**
     * Class Table
     */
    class Table {

        private Map<Integer, GMainUtility.Table.Row> container = new TreeMap();

        private int printColumnLength = 10;

        private GMainUtility.Table.Utils utils = new GMainUtility.Table.Utils();

        public Map<Integer, GMainUtility.Table.Row> rows() {
            return container;
        }

        public int rowSize() {
            return utils.getSize(container);
        }

        public void addData(String data, int rowNum, int columnNum) {
            GMainUtility.Table.Row row = container.get(rowNum);
            if (null == row) {
                container.put(rowNum, new GMainUtility.Table.Row());
                row = container.get(rowNum);
            }

            GMainUtility.Table.Row.Column column = row.getColumn(columnNum);

            // to avoid overlaps for tree nodes
            if (null == column.getData()) {
                column.setData(data);
            } else {
                column.setData(column.getData() + "" + data);
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
                final GMainUtility.Table.Row row = container.get(i);
                if (null == row) {
                    // System.out.println("print: null row:i="+i);
                    stringBuilder.append(GMainUtility.Table.Utils.NEWLINE);
                    continue;
                }

                final int columnSize = row.columnSize();
                for (int j = 0; j < columnSize; j++) {
                    final GMainUtility.Table.Row.Column column = row.getColumn(j);
                    if (null == column || null == column.getData()) {
                        // System.out.println("print: null column/its data: j="+j);
                        stringBuilder.append(utils.preparePrintData(""));
                    } else {
                        stringBuilder.append(utils.preparePrintData(column.getData()));
                    }
                }

                stringBuilder.append(GMainUtility.Table.Utils.NEWLINE);
            }

            return stringBuilder.toString();
        }

        /**
         * Class - quick utils for table class
         */
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

        /**
         * Class Row
         */
        class Row {

            private Map<Integer, GMainUtility.Table.Row.Column> container = new TreeMap();

            public Map<Integer, GMainUtility.Table.Row.Column> columns() {
                return container;
            }

            public int columnSize() {
                return utils.getSize(container);
            }

            public Row.Column getColumn(int columnNum) {
                GMainUtility.Table.Row.Column column = container.get(columnNum);
                if (null == column) {
                    container.put(columnNum, new Row.Column());
                    column = container.get(columnNum);
                }
                return column;
            }

            /**
             * Class Column
             */
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

    // ========= TREE ========

    /**
     * Class BinaryTreePrinter for pretty-print
     * binary tree.
     */
    class BinaryTreePrinter {

        private final String LEFT_SLASH = "/";
        private final String RIGHT_SLASH = "\\";

        public <T> void printTree(Node<T> node) {
            printTree(node, 3);
        }

        public <T> void printTree(Node<T>  node, int printColumnLength) {

            GMainUtility.Table theTable = new GMainUtility.Table();
            theTable.setPrintColumnLength(printColumnLength);

            // using 15 column table
            int middleColumn = 16;

            addToTable(theTable, node, 0, middleColumn);

            //System.out.println(String.format("rows=%d, columns=%d", theTable.rowSize(), theTable.rows().get(0).columnSize()));

            theTable.print();
        }

        private void addToTable(GMainUtility.Table theTable,
                                Node node,
                                int startRow,
                                int middleColumn) {
            
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

    private GMainUtility() {
        // do not instantiate
    }

    // ====================== TEST METHODS ================= //

    public static void main(String[] args) {
        final GMainUtility testClass = new GMainUtility();

        // testClass.sampleTestTable();
        testClass.performTests();
    }

    private void performTests() {
        // table
        Table table;

        table = TestSamples.sampleTestTable();
        table.print();

        table = TestSamples.sampleTestTableWithSomeNullColumnData();
        table.print();

        // tree
        final BinaryTreePrinter binaryTreePrinter = GMainUtility.createBinaryTreePrinter();

        Node node;

        node = TestSamples.sampleSmallestBinaryTree();
        binaryTreePrinter.printTree(node);

        node = TestSamples.sampleLargeBinaryTree();
        binaryTreePrinter.printTree(node);
    }

    /**
     * Class TestSamples
     */
    static class TestSamples {
        protected static Table sampleTestTable() {
            GMainUtility.Table table = GMainUtility.createTable();
            int rows = 5;
            int columns = 5;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    String value = String.format("[%d,%d]", i, j);
                    table.addData(value, i, j);
                }
            }
            return table;
        }

        protected static Table sampleTestTableWithSomeNullColumnData() {
            GMainUtility.Table table = GMainUtility.createTable();
            int rows = 5;
            int columns = 5;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (0 == (i + j) % 2) {
                        String value = String.format("[%d,%d]", i, j);
                        table.addData(value, i, j);
                    }
                }
            }

            return table;
        }

        protected static Node sampleSmallestBinaryTree() {
            final Node node = Node.builder()
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

        protected static Node sampleLargeBinaryTree() {
            final Node a = Node.builder()
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
