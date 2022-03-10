/**
 * BinaryTree.java
 * Implementation of a Binary Tree
 *
 * @author Aleksander Berezowski
 * @version 1.4
 * @since 1.0
 */

public class BinaryTree {
    // https://www.geeksforgeeks.org/binary-tree-set-1-introduction/
    // https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
    // https://www.geeksforgeeks.org/binary-search-tree-set-2-delete/

    Node root;
    StringBuilder returnString;

    /**
     * Constructor
     */
    BinaryTree(){ root = null; }

    /**
     * Method to print Binary tree by Left -> Node -> Right
     */
    private void printDepthFirstInorder(Node node) {
        // https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/

        // Print 4 2 5 1 3
        if (node == null)
            return;

        /* first recur on left child */
        printDepthFirstInorder(node.getLeft());

        /* then print the data of node */
        returnString.append(node.printOut()).append("\n");

        /* now recur on right child */
        printDepthFirstInorder(node.getRight());
    }

    /**
     * Method to insert a node
     */
    protected Node insert(Node root, Data newStudent){
        /* If the tree is empty,
           return a new node */
        if (root == null) {
            root = new Node(newStudent);
            return root;
        }

        /* Otherwise, recur down the tree */
        if (newStudent.getStudentName().compareTo(root.getStudentLastName()) < 0)
            root.setLeft(insert(root.getLeft(), newStudent));
        else
            root.setRight(insert(root.getRight(), newStudent));

        return root;
    }

    /**
     * Method to delete a node
     */
    private Node delete(Node root, Data student) {
        /* Base Case: If the tree is empty */
        if (root == null)
            return root;

        /* Otherwise, recur down the tree */
        if (student.getStudentName().compareTo(root.getStudentLastName()) < 0)
            root.setLeft(delete(root.getLeft(), student));
        else if (student.getStudentName().compareTo(root.getStudentLastName()) > 0)
            root.setRight(delete(root.getRight(), student));
            // if key is same as root's
            // key, then This is the
            // node to be deleted
        else {
            // node with only one child or no child
            if (root.getLeft() == null)
                return root.getRight();
            else if (root.getRight() == null)
                return root.getLeft();

            // node with two children: Get the inorder
            // successor (smallest in the right subtree)
            root.setStudentInfo(minValue(root.getRight()));

            // Delete the inorder successor
            root.setRight(delete(root.getRight(), root.getStudentInfo()));
        }

        return root;
    }


    /**
     * Method to find minimum node value
     */
    private Data minValue(Node root) {
        Data minv = root.getStudentInfo();
        while (root.getLeft() != null)
        {
            minv = root.getLeft().getStudentInfo();
            root = root.getLeft();
        }
        return minv;
    }

    /**
     * Method to transverse nodes in Node -> Left -> Right, used for debugging
     */
    private String traversePreOrder(Node root) {

        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(root.getStudentLastName());

        String pointerRight = "└──";
        String pointerLeft = (root.getRight() != null) ? "├──" : "└──";

        traverseNodes(sb, "", pointerLeft, root.getLeft(), root.getRight() != null);
        traverseNodes(sb, "", pointerRight, root.getRight(), false);

        return sb.toString();
    }

    /**
     * Method to transverse nodes, used for debugging
     */
    private void traverseNodes(StringBuilder sb, String padding, String pointer, Node node,
                              boolean hasRightSibling) {
        if (node != null) {
            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.getStudentLastName());

            StringBuilder paddingBuilder = new StringBuilder(padding);
            if (hasRightSibling) {
                paddingBuilder.append("│  ");
            } else {
                paddingBuilder.append("   ");
            }

            String paddingForBoth = paddingBuilder.toString();
            String pointerRight = "└──";
            String pointerLeft = (node.getRight() != null) ? "├──" : "└──";

            traverseNodes(sb, paddingForBoth, pointerLeft, node.getLeft(), node.getRight() != null);
            traverseNodes(sb, paddingForBoth, pointerRight, node.getRight(), false);
        }
    }

    /**
     * Method to do breadth First Travel
     */
    public String breadthFirstTraversal() {
        // https://www.geeksforgeeks.org/level-order-tree-traversal/
        returnString = new StringBuilder();
        Queue queue = new Queue();
        queue.enqueue(root);
        while (!queue.isEmpty()) {

            /* poll() removes the present head.
            For more information on poll() visit
            http://www.tutorialspoint.com/java/
            util/linkedlist_poll.htm */
            Node tempNode = queue.dequeue();
            returnString.append(tempNode.printOut()).append("\n");

            /*Enqueue left child */
            if (tempNode.getLeft() != null) {
                queue.enqueue(tempNode.getLeft());
            }

            /*Enqueue right child */
            if (tempNode.getRight() != null) {
                queue.enqueue(tempNode.getRight());
            }
        }

        return returnString.toString();
    }


    /**
     * Wrappers over above functions
     */
    public String printDepthFirstInorder() {
        returnString = new StringBuilder();
        printDepthFirstInorder(root);
        return returnString.toString();
    }
    public void printOutTree() { System.out.println(traversePreOrder(this.root)); }
    public void insert(Data newStudent){
        root = insert(this.root, newStudent);
    }
    public void delete(Data student) { this.root = delete(this.root, student); }
}
