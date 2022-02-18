/**
 * Node.java
 * Node class to be used in LinkedList class. Single linked node
 *      with a String data type and a next Node.
 *
 * @author Aleksander Berezowski
 * @version 1.2
 * @since 1.0
 */

public class Node {
    private String data;
    private Node next;

    /**
     * Only constructor for Node, accepts one argument of a
     *      string that is automatically put into Node's
     *      data variable.
     * @param data String of data to use for this node
     */
    public Node(String data){
        this.setData(data);
        this.setNext(null);
    }

    /**
     * Set value of private String class variable data
     * @param data String of what to set data to
     */
    public void setData(String data){
        this.data = data;
    }

    /**
     * Set value of private Node class variable next
     * @param next Node of what to set next Node to
     */
    public void setNext(Node next){
        this.next = next;
    }

    /**
     * Get value of private String class variable data
     * @return String data
     */
    public String getData(){
        return this.data;
    }

    /**
     * Get value of private Node class variable next
     * @return Node next
     */
    public Node getNext(){
        return this.next;
    }
}
