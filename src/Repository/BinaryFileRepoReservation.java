package Repository;

import Domain.Reservation;
import java.io.*;
import java.util.HashMap;

public class BinaryFileRepoReservation extends FileRepo<Integer, Reservation> {

    private String fileName;

    public BinaryFileRepoReservation(String fileName) {
        super();
        this.fileName = fileName;
        readFromFile();
    }

    @Override
    public void readFromFile() {
        File file = new File(fileName);
        if (!file.exists()) {
            // File doesn’t exist yet — start with an empty map
            this.map = new HashMap<>();
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            this.map = (HashMap<Integer, Reservation>) ois.readObject();
        } catch (EOFException e) {
            // File is empty — no reservations yet
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
