package Repository;

import java.io.File;

public abstract class FileRepo<ID, T> extends Repository<ID, T>{

    public abstract void readFromFile();

    public abstract void writeToFile();

    @Override
    public void add (ID id, T elem){
        super.add(id, elem);
        writeToFile();
    }

    @Override
    public void delete (ID id) {
        super.delete(id);
        writeToFile();
    }

}
