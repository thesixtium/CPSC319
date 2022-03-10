/**
 * AVLTree.java
 * Pseudo implementation of an AVL Tree. For most functions
 *          extends BinaryTree class.
 *
 * @author Aleksander Berezowski
 * @version 1.2
 * @since 1.0
 */


public class AVLTree extends BinaryTree{
    //https://www.geeksforgeeks.org/avl-tree-set-1-insertion/
    //https://www.happycoders.eu/algorithms/avl-tree-java/

    /**
     * Helper method to get the height of the tree
     */
    int height(Node N) {
        if (N == null)
            return 0;
        return N.getBalance();
    }

    /**
     * Helper method to get the maximum of two integers
     */
    int max(int a, int b) {
        return Math.max(a, b);
    }

    /**
     * Helper method to update tree height
     */
    private void updateHeight(Node node) {
        int leftChildHeight = height(node.getLeft());
        int rightChildHeight = height(node.getRight());
        node.setBalance(max(leftChildHeight, rightChildHeight) + 1);
    }

    /**
     * Helper method get balance
     */
    private int balanceFactor(Node node) {
        return height(node.getRight()) - height(node.getLeft());
    }

    /**
     * Helper method to right rotate a subtree
     */
    private Node rotateRight(Node node) {
        Node leftChild = node.getLeft();

        node.setLeft(leftChild.getRight());
        leftChild.setRight(node);

        updateHeight(node);
        updateHeight(leftChild);

        return leftChild;
    }

    /**
     * Helper method to left rotate a subtree
     */
    private Node rotateLeft(Node node) {
        Node rightChild = node.getRight();

        node.setRight(rightChild.getLeft());
        rightChild.setLeft(node);

        updateHeight(node);
        updateHeight(rightChild);

        return rightChild;
    }

    /**
     * Method to insert a node
     */
    protected Node insert(Node node, Data newStudent) {
        node = super.insert(node, newStudent);

        updateHeight(node);

        return rebalance(node);
    }

    /**
     * Method to re-balance the AVL tree
     */
    private Node rebalance(Node node) {
        int balanceFactor = balanceFactor(node);

        // Left-heavy
        if (balanceFactor < -1) {
            if (balanceFactor(node.getLeft()) <= 0) {    // Case 1
                // Rotate right
                node = rotateRight(node);
            } else {                                // Case 2
                // Rotate left-right
                node.setLeft(rotateLeft(node.getLeft()));
                node = rotateRight(node);
            }
        }

        // Right-heavy
        if (balanceFactor > 1) {
            if (balanceFactor(node.getRight()) >= 0) {    // Case 3
                // Rotate left
                node = rotateLeft(node);
            } else {                                 // Case 4
                // Rotate right-left
                node.setRight(rotateRight(node.getRight()));
                node = rotateLeft(node);
            }
        }

        return node;
    }

    /**
     * Wrappers over above functions
     */
    public void insert(Data newStudent){
        root = insert(this.root, newStudent);
    }
}
