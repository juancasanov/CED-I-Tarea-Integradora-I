package model.templates;

import model.nodes.HashNode;

public abstract class Node<T extends Comparable<T>>{
    private T t;

    protected Node<T> next;

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