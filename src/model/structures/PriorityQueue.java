package model.structures;
import exceptions.QueueIsEmptyException;
import model.templates.IList;
import model.templates.Node;
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
        // Convert the priority queue to an array
        NodePriorityQueue<T>[] arr = toArray();

        // Perform Heap Sort
        if (arr != null) {
            int n = arr.length;
            for (int i = n / 2 - 1; i >= 0; i--) {
                heapify(arr, n, i);
            }

            for (int i = n - 1; i > 0; i--) {
                // Swap the root (maximum element) with the last element
                NodePriorityQueue<T> temp = arr[0];
                arr[0] = arr[i];
                arr[i] = temp;

                // Heapify the reduced heap
                heapify(arr, i, 0);
            }

            // Reconstruct the priority queue from the sorted array
            head = arr[0];
            NodePriorityQueue<T> current = head;
            for (int i = 1; i < n; i++) {
                current.setNext(arr[i]);
                current = current.getNext();
            }
            current.setNext(null);
        }
    }
    private void heapify(NodePriorityQueue<T>[] arr, int n, int i) {
        int largest = i;
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;

        if (leftChild < n && arr[leftChild].getT().compareTo(arr[largest].getT()) > 0) {
            largest = leftChild;
        }

        if (rightChild < n && arr[rightChild].getT().compareTo(arr[largest].getT()) > 0) {
            largest = rightChild;
        }

        if (largest != i) {
            NodePriorityQueue<T> temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            heapify(arr, n, largest);
        }
    }

    private NodePriorityQueue<T>[] toArray() {
        int temp = size();
        if (temp != 0) {
            NodePriorityQueue<T>[] arr = new NodePriorityQueue[temp];
            NodePriorityQueue<T> current = head;
            for (int i = 0; i < temp; i++) {
                arr[i] = current;
                current = current.getNext();
            }
            return arr;
        }
        return null;
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
        if (current.getNext() != null) {
            current.setNext(current.getNext().getNext());
        }
    }

}

