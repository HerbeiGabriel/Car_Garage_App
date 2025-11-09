package Repository;

import Domain.Car;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class FileRepoCar extends FileRepo<Integer, Car> {

    private String fileName;

    public FileRepoCar(String fileName) {
        super();
        this.fileName = fileName;
        readFromFile();
    }

    @Override
    public void readFromFile() {
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split(",");

                if (words.length != 4) {
                    throw new IllegalArgumentException("Each line must have exactly 4 fields for a car. Error reading line: " + line);
                }

                int id = Integer.parseInt(words[0]);
                String model = words[1];
                int horsePower = Integer.parseInt(words[2]);
                int fuelTank = Integer.parseInt(words[3]);
                Car car = new Car(id, model, horsePower, fuelTank);
                super.add(id, car);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void writeToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Car car : super.getAll()) {
                bw.write(car.forText());
                bw.newLine();
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
