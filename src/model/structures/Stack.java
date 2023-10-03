package model.structures;

import exceptions.StackIsEmptyException;
import model.nodes.NodeStack;
import model.templates.List;

public class Stack<T extends Comparable<T>> implements List<T> {

    private NodeStack<T> top;

    public Stack(){

    }

    public T peek(){
        T toReturn;
        try{
            toReturn = get();
        }catch(StackIsEmptyException e){
            toReturn = null;
        }
        return toReturn;
    }
    @Override
    public T get() throws StackIsEmptyException{
        if(isEmpty()){
            throw new StackIsEmptyException("The stack has no value");
        }else{
            return top.getT();
        }
    }

    @Override
    public boolean add(T t){
        NodeStack<T> temp = new NodeStack<>(t);
        temp.setNext(top);
        top = temp;
        return true;
    }

    public T pop(){
        T toReturn;
        try{
            toReturn = remove();
        }catch(StackIsEmptyException e){
            toReturn = null;
        }
        return toReturn;
    }

    @Override
    public T remove() throws StackIsEmptyException{
        if(isEmpty()){
            throw new StackIsEmptyException("The stack has no values.");
        }else{
            T temp = top.getT();
            top = (NodeStack<T>) top.getNext();
            return temp;
        }
    }

    @Override
    public boolean isEmpty(){
        return top==null;
    }
}
