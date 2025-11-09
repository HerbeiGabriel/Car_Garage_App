package Main;

import Service.Service;
import UI.UI;

import java.io.FileReader;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        try{
            Properties prop = new Properties();
            prop.load(new FileReader("settings.properties"));
            String type=prop.getProperty("Repository");
            String location=prop.getProperty("Location");
            String car_filename = prop.getProperty("Car");
            String reservation_filename = prop.getProperty("Reservation");
            Service service=new Service(type, location, car_filename, reservation_filename);
            UI UI=new UI(service);
            UI.run();
        }catch (Exception e){
            e.printStackTrace();
        }    }
}