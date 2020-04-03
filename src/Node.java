/**
 * Part of GMain utils
 */
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

    public static NodeBuilder builder() {
        return new NodeBuilder();
    }

    /**
     * Class NodeBuilder
     */
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
