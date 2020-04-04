/**
 * Part of GMainUtility utils
 */
class Node<T> {
    T data;
    Node left;
    Node right;
    Node(T data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
    Node(T data) {
        this(data, null, null);
    }

    public static <U> NodeBuilder<U> builder() {
        return new NodeBuilder<U>();
    }

    /**
     * Class NodeBuilder
     */
    static class NodeBuilder<T> {
        private T data;
        private Node left;
        private Node right;

        public Node build() {
            return new Node(this.data, this.left, this.right);
        }

        public NodeBuilder data(T data) {
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
