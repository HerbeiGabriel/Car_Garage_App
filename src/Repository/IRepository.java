package Repository;

import Domain.Reservation;

import java.util.ArrayList;
import java.util.HashMap;

public interface IRepository<ID, T> {

    public void add(ID id, T obj);

    public void delete(ID id);

    public void update(ID id, T obj);

    public T get(ID id);

    public Iterable<T> getAll();

    public ArrayList<T> getlist();
}
