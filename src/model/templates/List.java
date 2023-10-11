package model.templates;

public interface List<T extends Comparable<T>>{
    public T get();
    public boolean add(T t);
    public T remove();
    public boolean isEmpty();
}