package Service;
import java.io.IOException;
import java.util.*;

import Domain.*;
import Filters.Filter;
import Repository.*;

public class Service {
    private IRepository<Integer, Car> carrepo;
    private IRepository<Integer, Reservation> reservationrepo;

    public Service(String type, String location, String car, String reservation) {
        if(type.equals("database")){
            String repopath = location + "/" + car + "s" + ".sqlite";
            this.carrepo=new DataRepoCars(repopath, "Cars");
            this.reservationrepo=new DataRepoReservations(repopath, "Reservations");
        }
        else if(type.equals("memory")){
            this.carrepo=new Repository<Integer, Car>();
            this.reservationrepo=new Repository<Integer, Reservation>();
        }
        else if(type.equals("TextFile")){
            String filenameCar= location + "/" + type +car+".txt";
            String filenameReservation=location+ "/" + type +reservation+".txt";
            this.carrepo=new FileRepoCar(filenameCar);
            this.reservationrepo=new FileRepoReservations(filenameReservation);
        }
        else if(type.equals("BinaryFile")){
            String filenameCar = location + "/" + type +car+".bin";
            String filenameReservation=location+ "/" + type +reservation+".bin";
            this.carrepo=new FileRepoCar(filenameCar);
            this.reservationrepo=new FileRepoReservations(filenameReservation);
        }
    }

    public void addcar(int ID, Car car){
        carrepo.add(ID, car);
    }

    public void deletecar(int ID){
        carrepo.delete(ID);
    }

    public Car getcar(int ID){
        return carrepo.get(ID);
    }

    public void updatecar(int ID, Car car){
        carrepo.update(ID, car);
    }

    public Iterable<Car> getcars(){
        return carrepo.getAll();
    }

    public void addresevation(int ID, Reservation reservation){
        reservationrepo.add(ID, reservation);
    }

    public void deletereservation(int ID){
        reservationrepo.delete(ID);
    }

    public Reservation getreservation(int ID){
        return reservationrepo.get(ID);
    }

    public void updatereservation(int ID, Reservation reservation){
        reservationrepo.update(ID, reservation);
    }

    public Iterable<Reservation> getreservations(){
        return reservationrepo.getAll();
    }

    public ArrayList<Car> getcarlist(){return carrepo.getlist();}

    public ArrayList<Reservation> getreservationlist(){return reservationrepo.getlist();}

    public Boolean checkIDcar(int ID){
        for(Car car : carrepo.getAll()){
            if(car.getId()==ID){
                return true;
            }
        }
        return false;
    }

    public List<Reservation> filterReservation(Filter filter){
        List<Reservation> list = new ArrayList<>();
        for(Reservation reservation : reservationrepo.getAll()){
            if(filter.accept(reservation)){
                list.add(reservation);
            }
        }
        return list;
    }

    public List<Car> filterCar(Filter filter){
        List<Car> list = new ArrayList<>();
        for(Car car : carrepo.getAll()){
            if(filter.accept(car)){
                list.add(car);
            }
        }
        return list;
    }

}
