/**
 * LinkedList.java
 * Class for LinkedList data structure, including methods needed to
 *     modify the data structure in most ways needed for assignment
 *     2. Does not include all methods of a classical LinkedList
 *     class.
 *
 * @author Aleksander Berezowski
 * @version 1.9
 * @since 1.0
 */

import java.util.Objects;

public class LinkedList {
    //https://www.geeksforgeeks.org/implementing-a-linked-list-in-java-using-class/
    private Node head;

    public void setHead(Node newHead){
        this.head = newHead;
    }

    public Node getHead(){
        return this.head;
    }

    // Constructor to create list with a data point
    public LinkedList(String data){
        insert(data);
    }

    // Method to insert a new node at end
    public void insert(String data) {
        // Create a new node with given data
        Node new_node = new Node(data);
        new_node.setNext(null);

        // If the Linked List is empty,
        // then make the new node as head
        if (this.head == null) {
            this.head = new_node;
        }
        else {
            // Else traverse till the last node
            // and insert the new_node there
            Node last = this.head;
            while (last.getNext() != null) {
                last = last.getNext();
            }

            // Insert the new_node at last node
            last.setNext(new_node);
        }
    }

    // Method to insert a new node at start
    public void push(String new_data) {
        Node new_Node = new Node(new_data);

        new_Node.setNext(head);

        head = new_Node;
    }

    // Method to print the LinkedList.
    public void printList() {
        Node currNode = this.head;

        System.out.print("\nLinkedList: ");

        // Traverse through the LinkedList
        while (currNode != null) {
            // Print the data at current node
            System.out.print(currNode.getData() + " ");

            // Go to next node
            currNode = currNode.getNext();
        }
    }

    // Method to print the LinkedList.
    public String toString() {
        Node currNode = this.head;
        StringBuilder returnString = new StringBuilder(3000);
        String appendString = "";

        while (currNode != null) {
            appendString = currNode.getData() + " ";
            returnString.append(appendString);

            // Go to next node
            currNode = currNode.getNext();
        }
        returnString.setLength(returnString.length() - 1);
        return returnString.toString();
    }

    // Method to delete a node in the LinkedList by KEY
    public static LinkedList deleteByKey(LinkedList list, String key) {
        // Store head node
        Node currNode = list.head, prev = null;

        //
        // CASE 1:
        // If head node itself holds the key to be deleted

        if (currNode != null && currNode.getData() == key) {
            list.head = currNode.getNext(); // Changed head

            // Display the message
            System.out.println(key + " found and deleted");

            // Return the updated List
            return list;
        }

        //
        // CASE 2:
        // If the key is somewhere other than at head
        //

        // Search for the key to be deleted,
        // keep track of the previous node
        // as it is needed to change currNode.next
        while (currNode != null && currNode.getData() != key) {
            // If currNode does not hold key
            // continue to next node
            prev = currNode;
            currNode = currNode.getNext();
        }

        // If the key was present, it should be at currNode
        // Therefore the currNode shall not be null
        if (currNode != null) {
            // Since the key is at currNode
            // Unlink currNode from linked list
            prev.setNext(currNode.getNext());

            // Display the message
            System.out.println(key + " found and deleted");
        }

        //
        // CASE 3: The key is not present
        //

        // If key was not present in linked list
        // currNode should be null
        if (currNode == null) {
            // Display the message
            System.out.println(key + " not found");
        }

        // return the List
        return list;
    }

    // Method to delete a node in the LinkedList by POSITION
    public static LinkedList deleteAtPosition(LinkedList list, int index) {
        // Store head node
        Node currNode = list.head, prev = null;

        //
        // CASE 1:
        // If index is 0, then head node itself is to be
        // deleted

        if (index == 0 && currNode != null) {
            list.head = currNode.getNext(); // Changed head

            // Display the message
            System.out.println(
                    index + " position element deleted");

            // Return the updated List
            return list;
        }

        //
        // CASE 2:
        // If the index is greater than 0 but less than the
        // size of LinkedList
        //
        // The counter
        int counter = 0;

        // Count for the index to be deleted,
        // keep track of the previous node
        // as it is needed to change currNode.next
        while (currNode != null) {

            if (counter == index) {
                // Since the currNode is the required
                // position Unlink currNode from linked list
                prev.setNext(currNode.getNext());

                // Display the message
                System.out.println(
                        index + " position element deleted");
                break;
            }
            else {
                // If current position is not the index
                // continue to next node
                prev = currNode;
                currNode = currNode.getNext();
                counter++;
            }
        }

        // If the position element was found, it should be
        // at currNode Therefore the currNode shall not be
        // null
        //
        // CASE 3: The index is greater than the size of the
        // LinkedList
        //
        // In this case, the currNode should be null
        if (currNode == null) {
            // Display the message
            System.out.println(
                    index + " position element not found");
        }

        // return the List
        return list;
    }

    // Method to swap nodes by value
    public void swapNodes(String x, String y) {
        // Nothing to do if x and y are same
        if (x == y)
            return;

        // Search for x (keep track of prevX and CurrX)
        Node prevX = null, currX = head;
        while (currX != null && !Objects.equals(currX.getData(), x)) {
            prevX = currX;
            currX = currX.getNext();
        }

        // Search for y (keep track of prevY and currY)
        Node prevY = null, currY = head;
        while (currY != null && !Objects.equals(currY.getData(), y)) {
            prevY = currY;
            currY = currY.getNext();
        }

        // If either x or y is not present, nothing to do
        if (currX == null || currY == null)
            return;

        // If x is not head of linked list
        if (prevX != null)
            prevX.setNext(currY);
        else // make y the new head
            head = currY;

        // If y is not head of linked list
        if (prevY != null)
            prevY.setNext(currX);
        else // make x the new head
            head = currX;

        // Swap next pointers
        Node temp = currX.getNext();
        currX.setNext(currY.getNext());
        currY.setNext(temp);
    }

    // Method to insertion sort
    public Node insertionSort(){
        // https://leetcode.com/problems/insertion-sort-list/solution/
        Node dummy = new Node(null);
        Node curr = this.head;

        while (curr != null) {
            // At each iteration, we insert an element into the resulting list.
            Node prev = dummy;

            // find the position to insert the current node
            while (prev.getNext() != null && prev.getNext().getData().compareTo(curr.getData()) < 0) {
                prev = prev.getNext();
            }

            Node next = curr.getNext();
            // insert the current node to the new list
            curr.setNext(prev.getNext());
            prev.setNext(curr);

            // moving on to the next iteration
            curr = next;
        }

        return dummy.getNext();
    }
}