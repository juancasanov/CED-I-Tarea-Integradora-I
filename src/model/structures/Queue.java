package model.structures;

import model.nodes.NodeQueue;
import model.templates.List;
import exceptions.QueueIsEmptyException;

public class Queue<T extends Comparable<T>> implements List<T> {
    private NodeQueue<T> top;

    private NodeQueue<T> bottom;

    public Queue(){

    }

    @Override
    public boolean isEmpty(){
        return top==null;
    }

    public T peek(){
        T toReturn;
        try{
            toReturn = get();
        }catch (QueueIsEmptyException e){
            toReturn = null;
        }
        return toReturn;
    }
    @Override
    public T get() throws QueueIsEmptyException{
        //Manejar Excepcion
        if(isEmpty()){
            throw new QueueIsEmptyException("The queue has no values");
        }
        return top.getT();
    }

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

    public T poll(){
        T toReturn;
        try{
            toReturn = remove();
        }catch (QueueIsEmptyException e){
            toReturn = null;
        }
        return toReturn;
    }
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
}