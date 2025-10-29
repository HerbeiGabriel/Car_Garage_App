package Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Repository<ID, T> implements IRepository<ID, T> {

    Map<ID, T> map=new HashMap<>();

    @Override
    public void add(ID id, T t) {
        if (map.containsKey(id)) {
            throw new IllegalArgumentException("The ID already exists");
        }
        map.put(id, t);
    }

    @Override
    public void delete(ID id) {
        if (!map.containsKey(id)) {
            throw new IllegalArgumentException("The ID doesn't exists");
        }
        map.remove(id);
    }

    @Override
    public T get(ID id) {
        return map.get(id);
    }

    @Override
    public void update(ID id, T t) {
        if (!map.containsKey(id)) {
            throw new IllegalArgumentException("The ID doesn't exists");
        }
        map.put(id, t);
    }

    @Override
    public Iterable<T> getAll() {
        return map.values();
    }

    @Override
    public ArrayList<T> getlist(){
        return new ArrayList<>(map.values());
    }
}
