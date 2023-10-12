package model.nodes;

import model.templates.Node;

public class HashNode<K, V extends Comparable<V>> extends Node<V> {

    private K key;
    private int size;

    public HashNode(K key, V value) {
        super(value);
        this.key = key;
        this.size = 1;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return getT();
    }

    public void add(Node<V> newNode) {
        if (getNext() == null) {
            setNext(newNode);
            size++;
        } else {
            ((HashNode<K,V>)getNext()).add(newNode);
        }
    }

    public void removeNode() {
        if (((HashNode<K,V>)getNext()).getNext() == null) {
            setNext(null);
            size--;
        } else {
            ((HashNode<?, ?>) getNext()).removeNode();
        }
    }

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
