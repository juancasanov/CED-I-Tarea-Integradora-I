package model.nodes;

import model.templates.Node;

public class NodeQueue<T extends Comparable<T>> extends Node<T> {
    public NodeQueue(T t){
        super(t);
    }
}