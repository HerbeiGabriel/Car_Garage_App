package Repository;

import Domain.Car;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public abstract class DataRepository<ID, T> extends Repository<ID, T> {

    public abstract void writetoDB(T elem);
    public abstract void updateDB(ID id, T elem);
    public abstract void readFromDB();
    public abstract void deleteDB(ID id);

    @Override
    public void add(ID id, T t){
        super.add(id, t);
        writetoDB(t);
    }

    @Override
    public void update(ID id, T t){
        super.update(id, t);
        updateDB(id, t);
    }

    @Override
    public void delete(ID id){
        super.delete(id);
        deleteDB(id);
    }

}
