package model.templates;

/**
 * The Node class is an abstract class that represents a node in a linked list, with a generic type T
 * that must implement the Comparable interface.
 */
public abstract class Node<T extends Comparable<T>>{
    private T t;

    private Node<T> next;

    public Node(T t){
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}