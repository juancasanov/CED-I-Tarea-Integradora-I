package model.structures;

import exceptions.HashTableIsEmptyException;
import exceptions.NonExistentKeyException;
import model.nodes.HashNode;
import model.templates.IHash;

public class HashTable<K, V extends Comparable<V>> implements IHash<K, V> {

    private static final int PREDEFINED_SIZE = 97;
    private HashNode<K,V>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public HashTable() {
        table = new HashNode[PREDEFINED_SIZE];
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public int hashFunction(K key) {
        int hashValue = key.hashCode();

        if (hashValue > PREDEFINED_SIZE) {
            hashValue = hashValue%PREDEFINED_SIZE;
        } else if (hashValue < -PREDEFINED_SIZE) {
            hashValue = -hashValue%PREDEFINED_SIZE;
        }else if (hashValue <= 0 && hashValue > -PREDEFINED_SIZE) {
            hashValue = -hashValue;
        }

        return hashValue;
    }

    @Override
    public V put(K key, V value) {
        int index = hashFunction(key);
        HashNode<K,V> newNode = new HashNode<>(key, value);
        if(table[index] == null) {
            table[index] = newNode;
            size++;
        } else {
            table[index].add(newNode);
        }

        return value;
    }

    @Override
    public void remove(K key) throws HashTableIsEmptyException, NonExistentKeyException {
        int index = hashFunction(key);

        if (isEmpty()) {
            throw new HashTableIsEmptyException("The hash table is Empty.");
        } else {
            if (table[index] == null) {
                throw new NonExistentKeyException("The task has not been found.");
            } else {
                if (table[index].getNext() == null) {
                    table[index] = null;
                    size--;
                } else {
                    table[index].removeNode();
                }
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(K key) {
       int index = hashFunction(key);
       HashNode<K,V> indexList = table[index];
       if(indexList==null){
           return false;
       }

       return table[index].containsKey(indexList, key);
    }

    @Override
    public boolean containsValue(V value) {
        boolean flag = false;

        for (int i = 0; i < table.length; i++) {
            HashNode<K,V> currentNode = table[i];

            while (currentNode != null) {
                if (currentNode.getValue().equals(value)) {
                    flag = true;
                }

                currentNode = currentNode.getNext();
            }
        }

        return flag;
    }

    @Override
    public V getValue(K key) {
        int index = hashFunction(key);
        V found = null;

        HashNode<K, V> current = table[index];
        while (current != null) {
            if (current.getKey() != null && current.getKey().equals(key) && current.getValue() != null) {
                found = current.getValue();
            }
            current = current.getNext();
        }

        return found;
    }

    public void printHashTable() {
        for (int i = 0; i < table.length-1; i++) {
            HashNode<K,V> currentNode = table[i];
            while (currentNode != null) {
                System.out.println("Title: " + currentNode.getKey() + currentNode.getValue()+ "\n");
                currentNode = currentNode.getNext();
            }
        }
    }

}