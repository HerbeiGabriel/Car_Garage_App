package UI;

import Domain.Car;
import Domain.Reservation;
import Filters.Filter_Car_By_Model;
import Filters.Filter_Car_By_fuel;
import Filters.Filter_Reservation_By_Date;
import Filters.Filter_Reservation_By_Price;
import Service.Service;

import java.io.FileReader;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class UI {
    private Service service;
    public UI(Service service) {
        this.service = service;
    }

    public void addcars(){
        Car car1=new Car(1, "Mercedes", 345, 60);
        Car car2=new Car(2, "BMW", 300, 50);
        Car car3=new Car(3, "Ferrari", 500, 45);
        Car car4=new Car(4, "Citroen", 145, 40);
        Car car5=new Car(5, "Fiat", 150, 50);
        service.addcar(1, car1);
        service.addcar(2, car2);
        service.addcar(3, car3);
        service.addcar(4, car4);
        service.addcar(5, car5);
    }

    public void addreservations(){
        Reservation reservation1=new Reservation(1, 1, LocalDate.of(2025, 11, 4), "Herbei George", 400);
        Reservation reservation2=new Reservation(2, 2, LocalDate.of(2025, 3, 4), "Herbei Emil", 200);
        Reservation reservation3=new Reservation(3, 3, LocalDate.of(2025, 2, 1), "Herbei Gabriel", 250);
        Reservation reservation4=new Reservation(4, 4, LocalDate.of(2025, 6, 23), "Herbei Meda", 100);
        Reservation reservation5=new Reservation(5, 5, LocalDate.of(2025, 8, 16), "Alina Buliga", 550);
        service.addresevation(1, reservation1);
        service.addresevation(2, reservation2);
        service.addresevation(3, reservation3);
        service.addresevation(4, reservation4);
        service.addresevation(5, reservation5);
    }

    public void Menu(){
        System.out.println("Welcome to UI");
        System.out.println("Please choose one of the following options for the car menu:");
        System.out.println("1. Add car");
        System.out.println("2. Delete car");
        System.out.println("3. Show car");
        System.out.println("4. Update car");
        System.out.println("5. Show all cars");
        System.out.println("Please choose one of the following options for the reservation menu:");
        System.out.println("6. Add reservation");
        System.out.println("7. Delete reservation");
        System.out.println("8. Show reservation");
        System.out.println("9. Update reservation");
        System.out.println("10. Show all reservations");
        System.out.println("Please choose one of the following filters for the car menu:");
        System.out.println("11. Filter cars after fuel capacity");
        System.out.println("12. Filter cars after model");
        System.out.println("Please choose one of the following filters for the reservation menu:");
        System.out.println("13. Filter reservations after date");
        System.out.println("14. Filter reservations after price");
        System.out.println("Please choose one of the following stream  for the entire menu:");
        System.out.println("15. All the reservations for a car");
        System.out.println("16. The car details from a reservation");
        System.out.println("17. Show all cars reserved by a person");
        System.out.println("18. Show reservations that has a specific car or a gas tank bigger than a value ordered by date");
        System.out.println("0.Exit");
    }

    public void run(){
        Scanner scanner = new Scanner(System.in);
        Menu();
        int option=scanner.nextInt();
        while(option!=0){
            if(option==1){
                try {
                    System.out.println("Enter the car ID:");
                    int carID = scanner.nextInt();
                    System.out.println("Enter the car model:");
                    String carModel = scanner.next();
                    System.out.println("Enter the car horsepower:");
                    int carHorsepower = scanner.nextInt();
                    System.out.println("Enter the car fueltank:");
                    int carFuelTank = scanner.nextInt();
                    Car car = new Car(carID, carModel, carHorsepower, carFuelTank);
                    service.addcar(carID, car);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            else if(option==2){
                try {
                    System.out.println("Enter the car ID:");
                    int carID = scanner.nextInt();
                    service.deletecar(carID);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            else if(option==3){
                System.out.println("Enter the car ID:");
                int carID = scanner.nextInt();
                System.out.println(service.getcar(carID));
            }
            else if(option==4){
                System.out.println("Enter the car ID:");
                int carID = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter the car model:");
                String carModel = scanner.nextLine();
//                if(carModel.equals(null))
//                    carModel=service.getcar(carID).getModel();
                System.out.println("Enter the car horsepower:");
                int carHorsepower = scanner.nextInt();
//                if(carHorsepower==0)
//                    carHorsepower=service.getcar(carID).getHorsepower();
                System.out.println("Enter the car fueltank:");
                int carFuelTank = scanner.nextInt();
//                if(carFuelTank==0)
//                    carFuelTank=service.getcar(carID).getFueltank();
                Car car=new Car(carID,carModel,carHorsepower,carFuelTank);
                service.updatecar(carID,car);
            }
            else if(option==5){
                for(Car car: service.getcars()){
                    System.out.println(car);
                }
            }
            else if(option==6){
                System.out.println("Enter the reservation ID:");
                int reservationID = scanner.nextInt();
                System.out.println("Enter the car ID:");
                service.getcars();
                int carID = scanner.nextInt();
                if(service.checkIDcar(carID)==true){
                    System.out.println("Enter the date:");
                    LocalDate reservationDate = LocalDate.parse(scanner.next());
                    System.out.println("Enter the name:");
                    String reservationName = scanner.next();
                    System.out.println("Enter the price:");
                    int reservationPrice = scanner.nextInt();
                    Reservation res=new Reservation(reservationID, carID, reservationDate, reservationName, reservationPrice);
                    service.addresevation(reservationID, res);
                }
            }
            else if(option==7){
                System.out.println("Enter the reservation ID:");
                int reservationID = scanner.nextInt();
                service.deletereservation(reservationID);
            }
            else if(option==8){
                System.out.println("Enter the reservation ID:");
                int reservationID = scanner.nextInt();
                System.out.println(service.getreservation(reservationID));
            }
            else if(option==9){
                System.out.println("Enter the reservation ID:");
                int reservationID = scanner.nextInt();
                System.out.println(service.getreservation(reservationID));
                System.out.println("Enter the car ID:");
                int carID = scanner.nextInt();
                if(service.checkIDcar(carID)!=true)
                    throw new RuntimeException();
                System.out.println("Enter the date:");
                String date=scanner.next();
                LocalDate reservationDate = LocalDate.parse(date);
                System.out.println("Enter the name:");
                String reservationName = scanner.next();
                System.out.println("Enter the price:");
                int reservationPrice = scanner.nextInt();
                Reservation res=new Reservation(reservationID, carID, reservationDate, reservationName, reservationPrice);
                service.updatereservation(reservationID, res);
            }
            else if(option==10){
                for(Reservation res: service.getreservations()){
                    System.out.println(res);
                }
            }
            else if(option==11){
                System.out.println("Enter the fuel value to filter cars by:");
                int fuelValue = scanner.nextInt();
                Filter_Car_By_fuel filter = new Filter_Car_By_fuel(fuelValue);
                for(Car car: service.filterCar(filter)){
                    System.out.println(car);
                }
            }
            else if(option==12){
                System.out.println("Enter the model to filter cars by:");
                String model = scanner.next();
                Filter_Car_By_Model filter = new Filter_Car_By_Model(model);
                for(Car car: service.filterCar(filter)){
                    System.out.println(car);
                }
            }
            else if(option==13){
                System.out.println("Enter the date to filter reservations by:");
                String date = scanner.next();
                LocalDate reservationDate = LocalDate.parse(date);
                Filter_Reservation_By_Date filter = new Filter_Reservation_By_Date(reservationDate);
                for(Reservation reservation: service.filterReservation(filter)){
                    System.out.println(reservation);
                }
            }
            else if(option==14){
                System.out.println("Enter the price to filter reservations by:");
                int value = scanner.nextInt();
                Filter_Reservation_By_Price filter = new Filter_Reservation_By_Price(value);
                for(Reservation reservation: service.filterReservation(filter)){
                    System.out.println(reservation);
                }
            }
            else if(option==15){
                System.out.println("Enter the car ID:");
                int carid = scanner.nextInt();
                ArrayList<Reservation> list=service.getreservationlist();
                list.stream().filter(s->s.getCarid()==carid).forEach(System.out::println);

            }
            else if(option==16){
                System.out.println("Enter the reservation ID:");
                int reservationID = scanner.nextInt();
                ArrayList<Reservation> list=service.getreservationlist();
                list.stream().filter(s->s.getCarid()==reservationID).forEach(s->System.out.println(service.getcar(s.getCarid())));
            }
            else if(option==17){
                System.out.println("Enter the customer name:");
                String customerName = scanner.nextLine().toLowerCase();
                scanner.nextLine();
                ArrayList<Reservation> list=service.getreservationlist();
                list.stream().filter(s->s.getCustomername().toLowerCase().equals(customerName)).forEach(s->System.out.println(service.getcar(s.getCarid())));
            }
            else if(option==18){
                System.out.println("Enter the car ID:");
                int carID = scanner.nextInt();
                System.out.println("Enter a fueltank max capacity");
                int fuelTankMaxCapacity = scanner.nextInt();
                ArrayList<Reservation> list=service.getreservationlist();
                list.stream().filter(s->s.getCarid()==carID || service.getcar(s.getCarid()).getFueltank()>fuelTankMaxCapacity).sorted(Comparator.comparing(Reservation::getDate)).forEach(System.out::println);
            }
            Menu();
            option=scanner.nextInt();

        }
    }
}
