package model.nodes;

import model.structures.HashTable;
import model.templates.Node;

import java.security.Key;

public class HashNode<K, V extends Comparable<V>> extends Node<V> {

    private K key;
    private V value;
    private int size;

    public HashNode(K key, V value) {
        super(value);
        this.key = key;
        this.value = value;
        this.size = 1;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void add(Node<V> newNode) {
        if (next == null) {
            next = newNode;
            size++;
        } else {
            ((HashNode<K,V>)next).add(newNode);
        }
    }

    public void removeNode() {
        if (((HashNode<K,V>)next).getNext() == null) {
            next = null;
            size--;
        } else {
            ((HashNode<?, ?>) next).removeNode();
        }
    }

    public V getNode(V value) {
        if (next != null) {
            if (next.getT().equals(value)) {
                return next.getT();
            } else {
                return ((HashNode<K, V>) next).getNode(value);
            }
        } else {
            return null;
        }
    }

    @Override
    public HashNode<K,V> getNext() {
        return (HashNode<K, V>) super.getNext();
    }

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
