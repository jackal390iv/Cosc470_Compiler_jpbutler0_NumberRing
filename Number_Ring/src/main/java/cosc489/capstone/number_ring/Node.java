/*
 * Class: COSC 489
 * Project: Capstone
 */
package cosc489.capstone.number_ring;

/**
 * This class is a simple Node class containing data and links to previous and
 * next Nodes
 *
 * @author Jonathan Butler <https://github.com/jackal390iv>
 */
public class Node {

    private Integer data = null;
    private Node next = null;
    private Node previous = null;

    /**
     * creates an empty Node
     */
    public Node() {
    }

    /**
     * Creates a Node containing data
     *
     * @param data
     */
    public Node(int data) {
        this.data = data;
    }

    /**
     * Sets data value
     *
     * @param data
     */
    public void setData(int data) {
        this.data = data;
    }

    /**
     * Designates the next Node
     *
     * @param next
     */
    public void setNext(Node next) {
        this.next = next;
    }

    /**
     * Designates the previous Node
     *
     * @param previous
     */
    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    /**
     * returns the Node's data
     *
     * @return
     */
    public Integer getData() {
        return data;
    }

    /**
     * Returns the next Node
     *
     * @return
     */
    public Node getNext() {
        return next;
    }

    /**
     * Returns the previous Node
     *
     * @return
     */
    public Node getPrevious() {
        return previous;
    }
}
