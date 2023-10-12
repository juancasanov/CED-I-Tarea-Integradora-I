package model.structures;
import exceptions.QueueIsEmptyException;
import model.templates.List;
import model.nodes.NodePriorityQueue;


public class PriorityQueue<T extends Comparable<T>> implements List<T> {
    private NodePriorityQueue<T> head;

    @Override
    public boolean add(T t) {
        boolean added = true;
        NodePriorityQueue<T> newNode = new NodePriorityQueue<>(t);
        
        NodePriorityQueue<T> current = head;
        while (current != null) {
            if (current.getT().equals(t)) {
                added = false; // El elemento ya existe en la lista
                break;
            }
            current = current.getNext();
        }
    
        if (added) { // Añadir el elemento solo si added es true
            if (head == null || t.compareTo(head.getT()) < 0) {
                newNode.setT(head.getT());
                head = newNode;
            } else {    
                current = head;
                while (current.getNext() != null && t.compareTo(current.getNext().getT()) >= 0) {
                    current = current.getNext();
                }
                newNode.setT(current.getT());
                current.setNext(newNode);
            }
        }
    
        return added;
    }
    

    @Override
    public T remove() {
        if (isEmpty()) {
            return null;
        }
        T removedData = head.getT();
        head = head.getNext();
        return removedData;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

        @Override
    public T get() throws QueueIsEmptyException {
        if (isEmpty()) {
            throw new QueueIsEmptyException("The queue has no values");
        }
        return head.getT();
    }
}


