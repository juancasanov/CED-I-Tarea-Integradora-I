package model.structures;

import model.nodes.NodeQueue;
import model.templates.IList;
import exceptions.QueueIsEmptyException;

/**
 * The Queue class is a generic implementation of a queue data structure that allows elements to be
 * added and removed in a first-in, first-out (FIFO) order.
 */
public class Queue<T extends Comparable<T>> implements IList<T> {
    private NodeQueue<T> top;

    private NodeQueue<T> bottom;

    public Queue(){

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

/**
 * The function returns the first element in the queue, or null if the queue is empty.
 * 
 * @return The method is returning the element at the front of the queue, or null if the queue is
 * empty.
 */
    public T peek(){
        T toReturn;
        try{
            toReturn = get();
        }catch (QueueIsEmptyException e){
            toReturn = null;
        }
        return toReturn;
    }
/**
 * The function returns the value at the top of the queue and throws an exception if the queue is
 * empty.
 * 
 * @return The method is returning the value of the top element in the queue.
 */
    @Override
    public T get() throws QueueIsEmptyException{
        //Manejar Excepcion
        if(isEmpty()){
            throw new QueueIsEmptyException("The queue has no values");
        }
        return top.getT();
    }

/**
 * The add() function adds a new element to the end of a queue.
 * 
 * @param t The parameter `t` is of type `T`, which is a generic type. It represents the element that
 * needs to be added to the queue.
 * @return The method is returning a boolean value indicating whether the element was successfully
 * added to the queue.
 */
    @Override
    public boolean add(T t) {
        boolean added = false;
        if(top==null){
            top = new NodeQueue<T>(t);
            bottom = top;
            added = true;
        }else{
            NodeQueue<T> node = new NodeQueue<>(t);
            bottom.setNext(node);
            bottom = node;
            added = true;
        }
        return added;
    }

/**
 * The function `poll()` removes and returns the element at the front of the queue, or returns null if
 * the queue is empty.
 * 
 * @return The method is returning an object of type T.
 */
    public T poll(){
        T toReturn;
        try{
            toReturn = remove();
        }catch (QueueIsEmptyException e){
            toReturn = null;
        }
        return toReturn;
    }
/**
 * The remove() function removes and returns the top element from the queue, throwing an exception if
 * the queue is empty.
 * 
 * @return The method is returning the value of the removed element from the queue.
 */
    @Override
    public T remove() throws QueueIsEmptyException{
        if(isEmpty()){
            throw new QueueIsEmptyException("The queue has no values");
        }
        //Manejar Excepcion
        T temp = top.getT();
        top = (NodeQueue<T>) top.getNext();
        return temp;
    }
/**
 * The remove function removes the first occurrence of a given element from a linked list.
 * 
 * @param t The parameter "t" is of type T, which is a generic type. It represents the element that
 * needs to be removed from the queue.
 */
    @Override
    public void remove (T t) {
        if (top == null) {
            return;
        }
        if (top.getT().equals(t)) {
            top = (NodeQueue<T>)top.getNext();
            return;
        }
        NodeQueue<T> current = top;
        while (current.getNext() != null && !current.getNext().getT().equals(t)) {
            current = (NodeQueue<T>)current.getNext();
        }
        if (current.getNext() != null) {
            current.setNext(current.getNext().getNext());
        }
    }
}