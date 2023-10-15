package model.structures;
import exceptions.QueueIsEmptyException;
import model.templates.IList;
import model.nodes.NodePriorityQueue;


/**
 * The `PriorityQueue` class is an implementation of a priority queue that stores elements in ascending
 * order based on their natural ordering.
 */
public class PriorityQueue<T extends Comparable<T>> implements IList<T> {
    private NodePriorityQueue<T> head;

/**
 * The add method adds a new element to a priority queue in ascending order based on the element's
 * natural ordering.
 * 
 * @param t The parameter `t` is of type `T`, which is a generic type. It represents the element that
 * needs to be added to the priority queue.
 * @return The method is returning a boolean value, which is always true.
 */
    @Override
    public boolean add(T t) {
        NodePriorityQueue<T> temp = new NodePriorityQueue<>(t);
        if(head==null || t.compareTo(head.getT()) < 0){
            temp.setNext(head);
            head = temp;
        }else{
            NodePriorityQueue<T> current = head;
            NodePriorityQueue<T> previousCurrent = null;
            while(current!= null && temp.getT().compareTo(current.getT())>=0) {
                previousCurrent = current;
                current = current.getNext();
            }
            previousCurrent.setNext(temp);
            temp.setNext(current);
        }
        return true;
    }

/**
 * The remove() function removes and returns the data from the head of a linked list.
 * 
 * @return The method is returning the removed data of type T.
 */
    @Override
    public T remove() {
        if (isEmpty()) {
            return null;
        }
        T removedData = head.getT();
        head = head.getNext();
        return removedData;
    }

/**
 * The function checks if the head of a linked list is null, indicating that the list is empty.
 * 
 * @return The method is returning a boolean value.
 */
    @Override
    public boolean isEmpty() {
        return head == null;
    }
/**
 * The function returns the value at the head of the queue, or throws an exception if the queue is
 * empty.
 * 
 * @return The method is returning the value of the head element in the queue.
 */
    @Override
    public T get() throws QueueIsEmptyException {
        if (isEmpty()) {
            throw new QueueIsEmptyException("The queue has no values");
        }
        return head.getT();
    }

/**
 * The function returns the size of a priority queue by iterating through its nodes and counting them.
 * 
 * @return The size of the NodePriorityQueue.
 */
    public int size() {
        int size = 0;
        NodePriorityQueue<T> current = head;
        while (current != null) {
            size++;
            current = current.getNext();
        }
        return size;
    }

/**
 * The remove function removes the first occurrence of a given element from a priority queue.
 * 
 * @param t The parameter `t` is of type `T`, which is a generic type. It represents the element that
 * needs to be removed from the priority queue.
 */
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
        current.setNext(current.getNext().getNext());
    }

}

