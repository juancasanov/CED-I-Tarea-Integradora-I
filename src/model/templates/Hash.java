package model.templates;

public interface HashTable<K, V extends Comparable<V>> extends List<V> {
    public boolean containsKey(Object key);
    public boolean containsValue(Object value);
    public V get(Object key);
    public V put(K key, V value);
    public V remove(Object key);
    public int size();
}
