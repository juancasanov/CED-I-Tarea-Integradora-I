package model.structures;

import model.nodes.HashNode;
import model.templates.Hash;

public class HashTable<K, V extends Comparable<V>> implements Hash<K, V> {

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
    public void remove(K key, V value) {}

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(K key) {
       int index = hashFunction(key);
       HashNode<K,V> indexList = table[index];

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
    public V getValue(K key, V value) {
        int index = hashFunction(key);
        V found = null;

        HashNode<K, V> current = table[index];
        while (current != null) {
            if (current.getKey() != null && current.getKey().equals(key) && current.getValue() != null && current.getValue().equals(value)) {
                found = current.getValue();
            }
            current = current.getNext();
        }

        return found;
    }
}