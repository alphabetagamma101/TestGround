/**
 * Part of GMainUtility utils
 */
class Node<T> {
    T data;
    Node<T> left;
    Node<T> right;
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
        private Node<T> left;
        private Node<T> right;

        public Node<T> build() {
            return new Node(this.data, this.left, this.right);
        }

        public NodeBuilder<T> data(T data) {
            this.data = data;
            return this;
        }

        public NodeBuilder<T> left(Node<T> left) {
            this.left = left;
            return this;
        }

        public NodeBuilder<T> right(Node<T> right) {
            this.right = right;
            return this;
        }

        public NodeBuilder<T> left() {
            this.left = null;
            return this;
        }

        public NodeBuilder<T> right() {
            this.right = null;
            return this;
        }
    }
}
