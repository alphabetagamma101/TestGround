import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.execute();
    }

    private void execute() {
        TreeNode<Integer> rootNode = new TreeNodeBuilder().value(4).build();
        populateData(rootNode);
        System.out.println(rootNode.toString());
    }

    private void populateData(final TreeNode rootNode) {
        // 4[1[9,8,5], 5[8[2],8], 4]
        final TreeNode<Integer> t1 = new TreeNodeBuilder().value(1)
                .addChild(new TreeNode(9))
                .addChild(new TreeNode(8))
                .addChild(new TreeNode(5))
                .build();

        rootNode.addChild(t1);
    }

    class TreeNodeBuilder<T> {

        TreeNode<T> treeNode;

        TreeNodeBuilder<T> value(final T value) {
            this.treeNode = new TreeNode(value);
            return this;
        }

        TreeNodeBuilder<T> addChild(final TreeNode childNode) {
            this.treeNode.addChild(childNode);
            return this;
        }

        TreeNode<T> build() {
            return this.treeNode;
        }
    }

    class TreeNode<T> {
        T value;
        List<TreeNode> children = new ArrayList<>();

        TreeNode(final T value) {
            this(value, new ArrayList());
        }

        TreeNode(final T value, final List<TreeNode> children) {
            this.value = value;
            this.children = children;
        }

        void addChild(final TreeNode childNode) {
            this.children.add(childNode);
        }
    }
}