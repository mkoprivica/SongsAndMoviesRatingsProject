package ratings.datastructures;

public class BST<A> {

    private BinaryTreeNode<A> root;
    private Comparator<A> comparator;

    public BST(Comparator<A> comparator) {
        this.comparator = comparator;
        this.root = null;
    }

    public BinaryTreeNode<A> getRoot() {
        return this.root;
    }

    public void insert(A value) {
        if (this.root == null) {
            this.root = new BinaryTreeNode<>(value, null, null);
        } else {
            this.insertHelper(this.root, value);
        }
    }

    private void insertHelper(BinaryTreeNode<A> node, A toInsert) {
        if (this.comparator.compare(toInsert, node.getValue())) {
            if (node.getLeft() == null) {
                node.setLeft(new BinaryTreeNode<>(toInsert, null, null));
            } else {
                insertHelper(node.getLeft(), toInsert);
            }
        } else {
            // ties go right
            if (node.getRight() == null) {
                node.setRight(new BinaryTreeNode<>(toInsert, null, null));
            } else {
                insertHelper(node.getRight(), toInsert);
            }
        }
    }

    public boolean find(A value) {
        if (this.root == null) {
            return false;
        } else {
            return findHelper(this.root, value);
        }
    }

    private boolean findHelper(BinaryTreeNode<A> node, A toFind) {
        if (this.comparator.compare(toFind, node.getValue())) {
            if (node.getLeft() == null) {
                return false;
            } else {
                return findHelper(node.getLeft(), toFind);
            }
        } else if (this.comparator.compare(node.getValue(), toFind)) {
            if (node.getRight() == null) {
                return false;
            } else {
                return findHelper(node.getRight(), toFind);
            }
        } else {
            return true;
        }
    }
}
