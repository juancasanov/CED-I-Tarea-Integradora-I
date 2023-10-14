package model.templates;

public interface IList<T extends Comparable<T>>{
    public T get();
    public boolean add(T t);
    public T remove();
    public boolean isEmpty();
    public void remove(T t);
}