package Repository;

import Domain.Car;
import Domain.Reservation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;

public class FileRepoReservations extends FileRepo<Integer, Reservation> {

    private String fileName;

    public FileRepoReservations(String fileName) {
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

                if (words.length != 5) {
                    throw new IllegalArgumentException("Each line must have exactly 5 fields for a reservation. Error reading line: " + line);
                }

                int id = Integer.parseInt(words[0]);
                int carId = Integer.parseInt(words[1]);
                LocalDate date = LocalDate.parse(words[2]);
                String customerName = words[3];
                int price = Integer.parseInt(words[4]);
                Reservation reservation = new Reservation(id, carId, date, customerName, price);
                super.add(id, reservation);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void writeToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Reservation reservation : super.getAll()) {
                bw.write(reservation.forText());
                bw.newLine();
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
