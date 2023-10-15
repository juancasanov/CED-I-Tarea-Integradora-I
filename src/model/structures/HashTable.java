package model.structures;

import exceptions.HashTableIsEmptyException;
import exceptions.NonExistentKeyException;
import model.nodes.HashNode;
import model.templates.IHash;

/**
 * The HashTable class is a generic implementation of a hash table data structure that supports
 * key-value pairs and handles collisions using chaining.
 */
public class HashTable<K, V extends Comparable<V>> implements IHash<K, V> {

    private static final int PREDEFINED_SIZE = 97;
    private HashNode<K,V>[] table;
    private int size;

// The `@SuppressWarnings("unchecked")` annotation is used to suppress compiler warnings related to
// unchecked operations. In this case, it is used to suppress the warning that occurs when creating an
// array of generic type `HashNode<K,V>[]`.
    @SuppressWarnings("unchecked")
    public HashTable() {
        table = new HashNode[PREDEFINED_SIZE];
        size = 0;
    }

/**
 * The function checks if a data structure is empty by comparing its size to zero.
 * 
 * @return The method is returning a boolean value, specifically whether the size variable is equal to
 * 0.
 */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

/**
 * The hashFunction takes a key and returns a hash value within a predefined size.
 * 
 * @param key The parameter "key" is of type K, which represents the type of the key that will be used
 * to calculate the hash value.
 * @return The method is returning the hash value of the given key.
 */
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

/**
 * The function puts a key-value pair into a hash table, using a hash function to determine the index,
 * and handles collisions by chaining.
 * 
 * @param key The key is the unique identifier for the value being stored in the hash table. It is used
 * to determine the index at which the value will be stored in the table.
 * @param value The value parameter represents the value that is being associated with the specified
 * key in the hash table.
 * @return The value that was added to the hash table.
 */
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

/**
 * The remove() function removes a key-value pair from the hash table based on the given key.
 * 
 * @param key The key is the value that is used to identify and locate the element in the hash table
 * that needs to be removed.
 */
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

/**
 * The function returns the size of a data structure.
 * 
 * @return The method is returning the value of the variable "size".
 */
    @Override
    public int size() {
        return size;
    }

/**
 * The function checks if a given key is present in the hash table.
 * 
 * @param key The key parameter is the key that we are checking for existence in the hash table.
 * @return The method is returning a boolean value.
 */
    @Override
    public boolean containsKey(K key) {
       int index = hashFunction(key);
       HashNode<K,V> indexList = table[index];
       if(indexList==null){
           return false;
       }

       return table[index].containsKey(indexList, key);
    }

/**
 * The function checks if a given value is present in the hash table.
 * 
 * @param value The parameter "value" is the value that we want to check if it exists in the hash
 * table.
 * @return The method is returning a boolean value, which indicates whether the specified value is
 * present in the hash map or not.
 */
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

/**
 * The function retrieves the value associated with a given key in a hash table.
 * 
 * @param key The key parameter is the key of the element we want to retrieve the value for.
 * @return The method is returning the value associated with the given key.
 */
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

/**
 * The function iterates through a hash table and prints the key-value pairs of each node.
 */
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