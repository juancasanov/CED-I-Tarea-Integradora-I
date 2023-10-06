package model.templates;

public interface Hash<K, V extends Comparable<V>> {
    public boolean containsKey(K key);
    public boolean containsValue(V value);
    public V getValue(K key, V value);
    public V put(K key, V value);
    public void remove(K key, V value);
    public int size();
    public boolean isEmpty();
}
