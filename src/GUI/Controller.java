package GUI;
import Filters.Filter_Car_By_Model;
import Filters.Filter_Car_By_fuel;
import Filters.Filter_Reservation_By_Date;
import Filters.Filter_Reservation_By_Price;
import Service.*;
import Domain.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

import GUI.GUI;


public class Controller<T> {
    private Service service;
    ObservableList<Car> list1;
    ObservableList<Reservation> list2;

    public Controller(Service service) {
        this.service = service;
    }

    @FXML
    private Button button, buttonfilter;

    @FXML
    private TextField Text1, Text2, Text3, Text4;

    @FXML
    private ChoiceBox ChoiceBox1, ChoiceBox2, ChoiceBox3;

    @FXML
    private ListView<T> List;

    @FXML
    private DatePicker date;

    public void initialize() {
        ArrayList<Car> cars = service.getcarlist();
        ArrayList<Reservation> reservations = service.getreservationlist();
        ArrayList<String> choices = new ArrayList<>();
        choices.add("Cars");
        choices.add("Reservations");
        ChoiceBox1.getItems().setAll(choices);
        ChoiceBox1.setOnAction(this::mainlist);
        ArrayList<String> choices1 = new ArrayList<>();
        choices1.add("Add");
        choices1.add("Delete");
        choices1.add("Update");
        ChoiceBox2.getItems().setAll(choices1);
        ChoiceBox2.setOnAction(this::setboxes);
        ChoiceBox3.setOnAction(this::setboxes1);
        button.setOnAction(this::dobedobado);
        buttonfilter.setOnAction(this::filter);

    }

    public void mainlist(Event event) {
        String choice = ChoiceBox1.getValue().toString();
        if(choice.equals("Cars")) {
            ArrayList<Car> cars = service.getcarlist();
            list1= FXCollections.observableArrayList(cars);
            List.setItems((ObservableList<T>) list1);
        }
        else if(choice.equals("Reservations")) {
            ArrayList<Reservation> reservations = service.getreservationlist();
            list2= FXCollections.observableArrayList(reservations);
            List.setItems((ObservableList<T>) list2);
        }
        setchoicebox();

    }

    public void dobedobado(Event event) {
        String choice1= ChoiceBox1.getValue().toString();
        String choice = ChoiceBox2.getValue().toString();
        if(choice.equals("Add")) {
            if(choice1.equals("Cars")){
                int id=Integer.parseInt(Text1.getText());
                String model= Text2.getText();
                int horse= Integer.parseInt(Text3.getText());
                int price= Integer.parseInt(Text4.getText());
                Car car = new Car(id,model,horse,price);
                try {
                    service.addcar(id, car);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                list1.add(car);
                List.setItems((ObservableList<T>) list1);
                List.refresh();
            }
            if(choice1.equals("Reservations")){
                int id=Integer.parseInt(Text1.getText());
                int carid= Integer.parseInt(Text2.getText());
                LocalDate ldate= date.getValue();
                String customer= Text3.getText();
                int price= Integer.parseInt(Text4.getText());
                Reservation reservation = new Reservation(id,carid,ldate,customer,price);
                try {
                    service.addresevation(id, reservation);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                list2.add(reservation);
                List.setItems((ObservableList<T>) list2);
                List.refresh();
            }
        }
        else if(choice.equals("Delete")) {
            int id=Integer.parseInt(Text1.getText());
            if(choice1.equals("Cars")){
                list1.remove(list1.indexOf(service.getcar(id)));
                service.deletecar(id);
                List.setItems((ObservableList<T>) list1);
                List.refresh();
            }
            else if(choice1.equals("Reservations")){
                list2.remove(list2.indexOf(service.getreservation(id)));
                service.deletereservation(id);
                List.setItems((ObservableList<T>) list2);
                List.refresh();
            }
        }
        else if(choice.equals("Update")) {
            if(choice1.equals("Cars")){
                int id=Integer.parseInt(Text1.getText());
                String model= Text2.getText();
                int horse= Integer.parseInt(Text3.getText());
                int price= Integer.parseInt(Text4.getText());
                Car car = new Car(id,model,horse,price);
                list1.set(list1.indexOf(service.getcar(id)), car);
                service.updatecar(id, car);
                List.setItems((ObservableList<T>) list1);
                List.refresh();
                try {
                    service.addcar(id, car);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(choice1.equals("Reservations")){
                int id=Integer.parseInt(Text1.getText());
                int carid= Integer.parseInt(Text2.getText());
                LocalDate ldate= date.getValue();
                String customer= Text3.getText();
                int price= Integer.parseInt(Text4.getText());
                Reservation reservation = new Reservation(id,carid,ldate,customer,price);
                list2.set(list2.indexOf(service.getreservation(id)), reservation);
                service.updatereservation(id, reservation);
                List.setItems((ObservableList<T>) list2);
                List.refresh();
                try {
                    service.addresevation(id, reservation);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setboxes(Event event) {
        String choice1= ChoiceBox1.getValue().toString();
        String choice2 = ChoiceBox2.getValue().toString();
        if(choice2.equals("Add")) {
            repeat1(choice1);
        }
        else if(choice2.equals("Delete")) {
            Text1.setVisible(true);
            Text1.setPromptText("Id");
        }
        else if(choice2.equals("Update")) {
            repeat1(choice1);
        }
    }

    public void repeat1(String choice1) {
        if(choice1.equals("Cars")){
            Text1.setVisible(true);
            Text1.setPromptText("Id");
            Text2.setVisible(true);
            Text2.setPromptText("Model");
            Text3.setVisible(true);
            Text3.setPromptText("Fuel tank");
            Text4.setVisible(true);
            Text4.setPromptText("Horsepower");
        }
        else if(choice1.equals("Reservations")) {
            Text1.setVisible(true);
            Text1.setPromptText("Id");
            Text2.setVisible(true);
            Text2.setPromptText("Car id");
            date.setVisible(true);
            date.setPromptText("Date");
            Text3.setVisible(true);
            Text3.setPromptText("Customer name");
            Text4.setVisible(true);
            Text4.setPromptText("Price");
        }
    }

    public void setchoicebox() {
        String choice1= ChoiceBox1.getValue().toString();
        if(choice1.equals("Cars")){
            ArrayList<String> choice=new ArrayList<>();
            choice.add("Filter cars after fuel capacity");
            choice.add("Filter cars after model");
            choice.add("The car details from a reservation");
            choice.add("Show all cars reserved by a person");
            ChoiceBox3.getItems().setAll(choice);
        }
        else if(choice1.equals("Reservations")){
            ArrayList<String> choice=new ArrayList<>();
            choice.add("Filter reservations after date");
            choice.add("Filter reservations after price");
            choice.add("All the reservations for a car");
            choice.add("Show reservations after car id and tank");
            ChoiceBox3.getItems().setAll(choice);
        }
    }

    public void setboxes1(Event event) {
        String choice3= ChoiceBox3.getValue().toString();
        if(choice3.equals("Filter cars after fuel capacity")){
            Text3.setVisible(true);
            Text3.setPromptText("Fuel tank");
        }
        else if(choice3.equals("Filter cars after model")){
            Text2.setVisible(true);
            Text2.setPromptText("Model");
        }
        else if(choice3.equals("Filter reservations after date")){
            date.setVisible(true);
            date.setPromptText("Date");
        }
        else if(choice3.equals("Filter reservations after price")){
            Text4.setVisible(true);
            Text4.setPromptText("Price");
        }
        else if(choice3.equals("The car details from a reservation")){
            Text1.setVisible(true);
            Text1.setPromptText("Id");
        }
        else if(choice3.equals("Show all cars reserved by a person")){
            Text3.setVisible(true);
            Text3.setPromptText("Customer name");
        }
        else if(choice3.equals("All the reservations for a car")){
            Text1.setVisible(true);
            Text1.setPromptText("Id");
        }
        else if(choice3.equals("Show reservations after car id and tank")){
            Text2.setVisible(true);
            Text2.setPromptText("Car id");
            Text3.setVisible(true);
            Text3.setPromptText("Fuel tank");
        }
    }

    public void filter(Event event) {
        String choice3= ChoiceBox3.getValue().toString();
        if(choice3.equals("Filter cars after fuel capacity")){
            Filter_Car_By_fuel filter=new Filter_Car_By_fuel(Integer.parseInt(Text3.getText()));
            ObservableList<Car> list= FXCollections.observableArrayList(service.filterCar(filter));
            List.setItems((ObservableList<T>) list);
        }
        else if(choice3.equals("Filter cars after model")){
            Filter_Car_By_Model filter= new Filter_Car_By_Model(Text2.getText());
            ObservableList<Car> list= FXCollections.observableArrayList(service.filterCar(filter));
            List.setItems((ObservableList<T>) list);
        }
        else if(choice3.equals("Filter reservations after date")){
            Filter_Reservation_By_Date filter=new Filter_Reservation_By_Date(date.getValue());
            ObservableList<Reservation> list= FXCollections.observableArrayList(service.filterReservation(filter));
            List.setItems((ObservableList<T>) list);
        }
        else if(choice3.equals("Filter reservations after price")){
            Filter_Reservation_By_Price filter=new Filter_Reservation_By_Price(Integer.parseInt(Text4.getText()));
            ObservableList<Reservation> list= FXCollections.observableArrayList(service.filterReservation(filter));
            List.setItems((ObservableList<T>) list);
        }
        else if(choice3.equals("The car details from a reservation")){
            int reservationID = Integer.parseInt(Text1.getText());
            ArrayList<Reservation> list=service.getreservationlist();
            list.stream().filter(s->s.getCarid()==reservationID).forEach(s->System.out.println(service.getcar(s.getCarid())));
        }
        else if(choice3.equals("Show all cars reserved by a person")){
            String customerName = Text3.getText().toLowerCase();
            ArrayList<Reservation> list=service.getreservationlist();
            list.stream().filter(s->s.getCustomername().toLowerCase().equals(customerName)).forEach(s->System.out.println(service.getcar(s.getCarid())));
        }
        else if(choice3.equals("All the reservations for a car")){
            int carid = Integer.parseInt(Text1.getText());
            ArrayList<Reservation> list=service.getreservationlist();
            list.stream().filter(s->s.getCarid()==carid).forEach(System.out::println);
        }
        else if(choice3.equals("Show reservations after car id and tank")){
            System.out.println("Enter the car ID:");
            int carID = Integer.parseInt(Text1.getText());
            System.out.println("Enter a fueltank max capacity");
            int fuelTankMaxCapacity = Integer.parseInt(Text3.getText());
            ArrayList<Reservation> list=service.getreservationlist();
            list.stream().filter(s->s.getCarid()==carID || service.getcar(s.getCarid()).getFueltank()>fuelTankMaxCapacity).sorted(Comparator.comparing(Reservation::getDate)).forEach(System.out::println);
        }
    }

}
