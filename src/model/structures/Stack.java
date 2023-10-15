package model.structures;

import exceptions.StackIsEmptyException;
import model.nodes.NodeStack;
import model.templates.IList;

/**
 * The Stack class is a generic implementation of a stack data structure that allows for adding,
 * removing, and accessing elements.
 */
public class Stack<T extends Comparable<T>> implements IList<T> {

    private NodeStack<T> top;

    public Stack(){

    }

/**
 * The `peek` function returns the top element of a stack without removing it, or null if the stack is
 * empty.
 * 
 * @return The method is returning the element at the top of the stack, or null if the stack is empty.
 */
    public T peek(){
        T toReturn;
        try{
            toReturn = get();
        }catch(StackIsEmptyException e){
            toReturn = null;
        }
        return toReturn;
    }
/**
 * The function returns the value at the top of the stack, or throws an exception if the stack is
 * empty.
 * 
 * @return The method is returning the value of the top element in the stack.
 */
    @Override
    public T get() throws StackIsEmptyException{
        if(isEmpty()){
            throw new StackIsEmptyException("The stack has no value");
        }else{
            return top.getT();
        }
    }

/**
 * The function pushes a new element onto a stack and updates its action and value.
 * 
 * @param before The parameter "before" is of type T and represents the element that will be added to
 * the stack before performing the action.
 * @param action The "action" parameter is a string that represents the action being performed.
 * @param after The parameter "after" is of type T, which means it can be any type of object. It
 * represents the object that will be added to the stack after the "before" object.
 * @return The method is returning a boolean value of true.
 */
    public boolean push(T before,String action,T after){
        add(before);
        top.setAction(action);
        top.setS(after);
        return true;
    }
/**
 * The add function adds a new element to the top of the stack.
 * 
 * @param t The parameter `t` is of type `T`, which represents the element to be added to the stack.
 * @return The method is returning a boolean value of true.
 */
    @Override
    public boolean add(T t){
        NodeStack<T> temp = new NodeStack<>(t);
        temp.setNext(top);
        top = temp;
        return true;
    }

/**
 * The function "pop" removes and returns the top element of a stack, or returns null if the stack is
 * empty.
 * 
 * @return The method is returning an object of type T.
 */
    public T pop(){
        T toReturn;
        try{
            toReturn = remove();
        }catch(StackIsEmptyException e){
            toReturn = null;
        }
        return toReturn;
    }

/**
 * The remove() function removes and returns the top element of the stack, throwing an exception if the
 * stack is empty.
 * 
 * @return The method is returning the value of the removed element from the stack.
 */
    @Override
    public T remove() throws StackIsEmptyException{
        if(isEmpty()){
            throw new StackIsEmptyException("The stack has no values.");
        }else{
            NodeStack<T> temp = top;
            top = (NodeStack<T>) top.getNext();
            return temp.getT();
        }
    }

/**
 * The function `pop` removes and returns a `NodeStack` object based on a given flag.
 * 
 * @param flag The "flag" parameter is an integer value that is used to determine which element to
 * remove from the stack. It is used as a reference to identify the specific element to be removed.
 * @return The method is returning a NodeStack object.
 */
    public NodeStack<T> pop(int flag){
        NodeStack<T> toReturn;
        try{
            toReturn = remove(flag);
        }catch(StackIsEmptyException e){
            toReturn = null;
        }
        return toReturn;
    }

/**
 * The remove function removes the top element from the stack and returns it.
 * 
 * @param flag The flag parameter is not used in the code snippet provided. It is declared as an
 * integer but is not used anywhere within the method.
 * @return The method is returning a NodeStack object.
 */
    public NodeStack<T> remove(int flag) throws StackIsEmptyException{
        if(isEmpty()){
            throw new StackIsEmptyException("The stack has no values.");
        }else{
            NodeStack<T> temp = top;
            top = (NodeStack<T>) top.getNext();
            return temp;
        }
    }

/**
 * The remove function removes the first occurrence of a given element from a stack.
 * 
 * @param t The parameter `t` is of type `T`, which is a generic type. It represents the element that
 * needs to be removed from the stack.
 */
    @Override
    public void remove (T t) {
        if (top == null) {
            return;
        }
        if (top.getT().equals(t)) {
            top = (NodeStack<T>)top.getNext();
            return;
        }
        NodeStack<T> current = top;
        while (current.getNext() != null && !current.getNext().getT().equals(t)) {
            current = (NodeStack<T>) current.getNext();
        }
        if (current.getNext() != null) {
            current.setNext(current.getNext().getNext());
        }
    }

/**
 * The function checks if the top element of a stack is null, indicating that the stack is empty.
 * 
 * @return The method is returning a boolean value, which indicates whether the stack is empty or not.
 */
    @Override
    public boolean isEmpty(){
        return top==null;
    }
}
