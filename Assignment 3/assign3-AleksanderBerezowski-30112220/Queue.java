/**
 * Queue.java
 * Implementation of a Queue
 *
 * @author Aleksander Berezowski
 * @version 1.8
 * @since 1.0
 */

class Queue {
    //https://www.geeksforgeeks.org/queue-poll-method-in-java/
    //https://www.geeksforgeeks.org/queue-set-1introduction-and-array-implementation/
    //https://www.w3schools.blog/java-dynamic-queue-implementation

    private int front, rear, size;
    private final int capacity;
    private Node[] array;

    /**
     * Constructor
     */
    public Queue()
    {
        this.capacity = 1000;
        front = this.size = 0;
        rear = capacity - 1;
        array = new Node[this.capacity];
    }

    /**
     * Method to see if queue is full; Queue is full
     *      when size becomes equal to the capacity
     */
    public boolean isFull()
    {
        return (this.size == this.capacity);
    }

    /**
     * Method to see if queue is full; Queue is empty
     *      when size equals 0
     */
    public boolean isEmpty()
    {
        return (this.size == 0);
    }

    /**
     * Method to add to the queue
     */
    public void enqueue(Node item)
    {
        if (isFull())
            return;
        this.rear = (this.rear + 1)
                % this.capacity;
        this.array[this.rear] = item;
        this.size = this.size + 1;
    }

    /**
     * Method to remove and return item from the queue
     */
    public Node dequeue()
    {
        if (isEmpty())
            return null;

        Node item = this.array[this.front];
        this.front = (this.front + 1)
                % this.capacity;
        this.size = this.size - 1;
        return item;
    }
}