package model.nodes;

import model.templates.Node;

public class NodeHash<T extends Comparable<T>> extends Node<T> {

    private NodeHash<T> prev;

    public NodeHash(T t) {
        super(t);
    }

    public NodeHash<T> getPrev() {
        return prev;
    }

    public void setPrev(NodeHash<T> prev) {
        this.prev = prev;
    }
}
