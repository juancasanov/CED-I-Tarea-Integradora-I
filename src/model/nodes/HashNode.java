package model.nodes;

import model.templates.Node;

public class HashNode<K, V extends Comparable<V>> extends Node<V> {

    private K key;
    private int size;

// The `public HashNode(K key, V value)` constructor is creating a new instance of the `HashNode` class
// with the specified key and value. It calls the constructor of the superclass `Node<V>` with the
// value parameter, and then sets the key and size variables of the `HashNode` instance. The size is
// initialized to 1 because this constructor is used when creating a new node, so it represents the
// initial size of the node.
    public HashNode(K key, V value) {
        super(value);
        this.key = key;
        this.size = 1;
    }

/**
 * The function returns the key of a generic type.
 * 
 * @return The method is returning the value of the variable "key".
 */
    public K getKey() {
        return key;
    }

/**
 * The function returns the value of type V.
 * 
 * @return The method is returning the value of type V.
 */
    public V getValue() {
        return getT();
    }

/**
 * The add function adds a new node to the linked list if the next node is null, otherwise it
 * recursively calls the add function on the next node.
 * 
 * @param newNode The parameter `newNode` is of type `Node<V>`, which means it is a node containing a
 * value of type `V`.
 */
    public void add(Node<V> newNode) {
        if (getNext() == null) {
            setNext(newNode);
            size++;
        } else {
            ((HashNode<K,V>)getNext()).add(newNode);
        }
    }

/**
 * The removeNode() function removes a node from a linked list if it is the last node, otherwise it
 * recursively calls itself on the next node.
 */
    public void removeNode() {
        if (((HashNode<K,V>)getNext()).getNext() == null) {
            setNext(null);
            size--;
        } else {
            ((HashNode<?, ?>) getNext()).removeNode();
        }
    }

/**
 * The function `getNode` searches for a node with a specific value in a linked list of nodes.
 * 
 * @param value The parameter "value" is of type V, which represents the value of the node that we are
 * searching for in the hash table.
 * @return The method is returning a value of type V, which is the value associated with the node that
 * matches the given value. If no matching node is found, it returns null.
 */
    public V getNode(V value) {
        if (getNext() != null) {
            if (getNext().getT().equals(value)) {
                return getNext().getT();
            } else {
                return ((HashNode<K, V>)getNext()).getNode(value);
            }
        } else {
            return null;
        }
    }

/**
 * The function overrides the getNext() method and returns the next HashNode in the linked list.
 * 
 * @return The method is returning a HashNode object.
 */
    @Override
    public HashNode<K,V> getNext() {
        return (HashNode<K, V>) super.getNext();
    }

/**
 * The function checks if a given key is present in a hash node or its linked list.
 * 
 * @param node The current node being checked for the key.
 * @param key The key parameter is the key that we are checking for in the HashNode.
 * @return The method is returning a boolean value, which indicates whether the given key is present in
 * the HashNode or not.
 */
    public boolean containsKey(HashNode<K,V> node, K key) {
        boolean flag = false;

        if (node.getKey().equals(key)) {
            flag = true;
        } else {
            containsKey(node.getNext(), key);
        }

        return flag;
    }

}
