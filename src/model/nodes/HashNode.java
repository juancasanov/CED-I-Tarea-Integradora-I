package model.nodes;

import model.structures.HashTable;
import model.templates.Node;

public class HashNode<K, V extends Comparable<V>> extends Node<V> {

    private K key;
    private V value;
    private int size;

    public HashNode(K key, V value) {
        super(value);
        this.key = key;
        this.size = 1;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public int getSize() {
        return size;
    }

    public void add(Node<V> newNode) {
        if (next == null) {
            next = newNode;
        } else {
            ((HashNode<K,V>)next).add(newNode);
        }
    }

    public void removeLast() {}

    public V getNode(V value) {
        return null;
    }

    @Override
    public HashNode<K,V> getNext() {
        return (HashNode<K, V>) super.getNext();
    }
}
