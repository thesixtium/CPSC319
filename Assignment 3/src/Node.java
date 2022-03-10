/**
 * Node.java
 * Implementation of a Node for a binary tree
 *
 * @author Aleksander Berezowski
 * @version 1.7
 * @since 1.0
 */

public class Node {
    private Data studentInfo;
    private Node left, right;
    private int balance = 1;

    /**
     * Constructor
     */
    public Node(Data newStudent){
        this.studentInfo = new Data(newStudent);
        this.left = null;
        this.right = null;
    }

    /**
     * Method to get string of student info
     */
    public String printOut(){ return this.studentInfo.printOut(); }

    /**
     * Getter for student info object
     */
    public Data getStudentInfo() { return this.studentInfo; }

    /**
     * Getter for student's last name (used for comparisons)
     */
    public String getStudentLastName() { return this.studentInfo.getStudentName(); }

    /**
     * Getter for left node
     */
    public Node getLeft() { return this.left; }

    /**
     * Getter for right node
     */
    public Node getRight() { return this.right; }

    /**
     * Setter for left node
     */
    public void setLeft(Node left) { this.left = left; }

    /**
     * Setter for right node
     */
    public void setRight(Node right) { this.right = right; }

    /**
     * Setter for student info
     */
    public void setStudentInfo(Data studentInfo) {
        this.studentInfo = studentInfo;
    }

    /**
     * Getter for  balance
     */
    public int getBalance() {
        return this.balance;
    }

    /**
     * Setter for balance
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }
}
