package model.nodes;
import model.templates.Node;

public class NodePriorityQueue<T extends Comparable<T>> extends Node<T> {

    public NodePriorityQueue(T t) {
        super(t);
    }

    @Override
    public NodePriorityQueue<T> getNext() {
        return (NodePriorityQueue<T>) super.getNext();
    }
}
