package Repository;

import Domain.Car;
import java.io.*;
import java.util.HashMap;

public class BinaryFileRepoCar extends FileRepo<Integer, Car> {

    private String fileName;

    public BinaryFileRepoCar(String fileName) {
        super();
        this.fileName = fileName;
        readFromFile();
    }

    @Override
    public void readFromFile() {
        File file = new File(fileName);
        if (!file.exists()) {
            // If the file doesn't exist yet, just start with an empty map
            this.map = new HashMap<>();
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            this.map = (HashMap<Integer, Car>) ois.readObject();
        } catch (EOFException e) {
            // End of file — happens if the file is empty
            this.map = new HashMap<>();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
            this.map = new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error reading binary file: " + e.getMessage(), e);
        }
    }

    @Override
    public void writeToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(this.map);
        } catch (IOException e) {
            throw new RuntimeException("Error writing binary file: " + e.getMessage(), e);
        }
    }
}