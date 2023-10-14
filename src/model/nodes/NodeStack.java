package model.nodes;

import model.templates.Node;

public class NodeStack <T extends Comparable<T>> extends Node<T> {
    private String action;
    private T s;
    public NodeStack(T t){
        super(t);
    }

    public String getAction(){
        return action;
    }

    public void setAction(String action){
        this.action = action;
    }

    public T getS(){
        return s;
    }

    public void setS(T s){
        this.s = s;
    }
}
