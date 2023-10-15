package model.structures;
import exceptions.QueueIsEmptyException;
import model.templates.IList;
import model.nodes.NodePriorityQueue;


public class PriorityQueue<T extends Comparable<T>> implements IList<T> {
    private NodePriorityQueue<T> head;

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

    /*
    public void sort() {
        NodePriorityQueue<T>[] arr = toArray();
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            NodePriorityQueue<T> key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].getT().compareTo(key.getT()) > 0) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
        if (n > 0) {
            head = arr[0];
            for (int i = 0; i < n - 1; i++) {
                arr[i].setNext(arr[i + 1]);
            }
            arr[n - 1].setNext(null);
        }
    }
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

    public int size() {
        int size = 0;
        NodePriorityQueue<T> current = head;
        while (current != null) {
            size++;
            current = current.getNext();
        }
        return size;
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
        current.setNext(current.getNext().getNext());
    }

}

