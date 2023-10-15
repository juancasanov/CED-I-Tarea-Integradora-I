package model.templates;

// The code is defining a Java interface called `IList` with a generic type parameter `T` that must
// extend the `Comparable` interface.
public interface IList<T extends Comparable<T>>{
    public T get();
    public boolean add(T t);
    public T remove();
    public boolean isEmpty();
    public void remove(T t);
}