package model.nodes;
import model.templates.Node;

public class NodePriorityQueue<T extends Comparable<T>> extends Node<T> {

// The code `public NodePriorityQueue(T t) { super(t); }` is a constructor for the `NodePriorityQueue`
// class. It takes a parameter `t` of type `T` and calls the constructor of the superclass `Node` with
// the same parameter `t`. This allows the `NodePriorityQueue` object to be initialized with a value
// `t`.
    public NodePriorityQueue(T t) {
        super(t);
    }

/**
 * The function overrides the getNext() method from the superclass and returns a NodePriorityQueue
 * object.
 * 
 * @return The method is returning a NodePriorityQueue object.
 */
    @Override
    public NodePriorityQueue<T> getNext() {
        return (NodePriorityQueue<T>) super.getNext();
    }
}
