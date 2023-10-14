package model.structures;
import exceptions.QueueIsEmptyException;
import model.templates.IList;
import model.classes.Task;
import model.nodes.NodePriorityQueue;


public class PriorityQueue<T extends Comparable<T>> implements IList<T> {
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
            if (head == null){
                head = newNode;
            }else if(t.compareTo(head.getT()) < 0) {
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

    public void sort(PriorityQueue<T> tasks) {
        NodePriorityQueue<T> sorted = null;
        NodePriorityQueue<T> current = head;
        
        while (current != null) {
            NodePriorityQueue<T> next = current.getNext();
            sorted = insertByPriority(sorted, current);
            current = next;
        }
        
        // Update head to point to the sorted list
        head = sorted;
    }
    
    private NodePriorityQueue<T> insertByPriority(NodePriorityQueue<T> sorted, NodePriorityQueue<T> newNode) {
        if (sorted == null || newNode.getT().compareTo(sorted.getT()) < 0) {
            newNode.setNext(sorted);
            return newNode;
        } else {
            NodePriorityQueue<T> current = sorted;
            while (current.getNext() != null && newNode.getT().compareTo(current.getNext().getT()) >= 0) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
            return sorted;
        }
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

    @Override
    public void remove (T t) {
        if (head == null) {
            return;
        }
        if (head.getT().equals(t)) {
            head = head.getNext();
            return;
        }
        NodePriorityQueue<T> current = head;
        while (current.getNext() != null && !current.getNext().getT().equals(t)) {
            current = current.getNext();
        }
        if (current.getNext() != null) {
            current.setNext(current.getNext().getNext());
        }
    }

}

