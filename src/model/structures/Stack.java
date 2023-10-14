package model.structures;

import exceptions.StackIsEmptyException;
import model.nodes.NodeStack;
import model.templates.IList;

public class Stack<T extends Comparable<T>> implements IList<T> {

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

    public boolean push(T before,String action,T after){
        add(before);
        top.setAction(action);
        top.setS(after);
        return true;
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
            NodeStack<T> temp = top;
            top = (NodeStack<T>) top.getNext();
            return temp.getT();
        }
    }

    public NodeStack<T> pop(int flag){
        NodeStack<T> toReturn;
        try{
            toReturn = remove(flag);
        }catch(StackIsEmptyException e){
            toReturn = null;
        }
        return toReturn;
    }

    public NodeStack<T> remove(int flag) throws StackIsEmptyException{
        if(isEmpty()){
            throw new StackIsEmptyException("The stack has no values.");
        }else{
            NodeStack<T> temp = top;
            top = (NodeStack<T>) top.getNext();
            return temp;
        }
    }

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

    @Override
    public boolean isEmpty(){
        return top==null;
    }
}
