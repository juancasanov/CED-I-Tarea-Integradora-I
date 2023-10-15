package model.templates;

// The code is defining an interface called `IHash` with generic types `K` and `V`.
public interface IHash<K, V extends Comparable<V>> {
    public boolean containsKey(K key);
    public boolean containsValue(V value);
    public V getValue(K key);
    public V put(K key, V value);
    public void remove(K key);
    public int size();
    public boolean isEmpty();
}
