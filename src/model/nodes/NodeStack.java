package model.nodes;

import model.templates.Node;

public class NodeStack <T extends Comparable<T>> extends Node<T> {
    private String action;
    private T s;
// The code `public NodeStack(T t){ super(t); }` is a constructor for the NodeStack class. It calls the
// constructor of the superclass (Node) and passes the parameter `t` to it. This allows the NodeStack
// object to be initialized with a value for the data field in the superclass.
    public NodeStack(T t){
        super(t);
    }

/**
 * The function "getAction" returns a string value.
 * 
 * @return The method is returning a String value.
 */
    public String getAction(){
        return action;
    }

/**
 * The function sets the value of the "action" variable.
 * 
 * @param action The parameter "action" is a String that represents the action to be set.
 */
    public void setAction(String action){
        this.action = action;
    }

/**
 * The function returns the value of the variable "s".
 * 
 * @return The method is returning a value of type T.
 */
    public T getS(){
        return s;
    }

/**
 * The function sets the value of a variable "s" to the input parameter "s".
 * 
 * @param s The parameter "s" is of type T, which means it can be any type specified when the class or
 * method is used.
 */
    public void setS(T s){
        this.s = s;
    }
}
