package Filters;

public interface Filter<T> {

    public boolean accept(T object);
}
