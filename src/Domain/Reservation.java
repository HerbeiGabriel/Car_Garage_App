package Domain;

import java.time.LocalDate;
import java.util.Date;

public class Reservation implements Identifiable<Integer>{
    private int id;
    private int carid;
    private LocalDate date;
    private String customername;
    private int price;

    public Reservation(int id1, int carid, LocalDate date, String customername, int price) {
        this.id = id1;
        this.carid = carid;
        this.date=date;
        this.customername=customername;
        this.price=price;
    }


    @Override
    public Integer getId() {
        return id;
    }

    public int getCar() {
        return carid;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCustomername() {
        return customername;
    }

    public int getPrice() {
        return price;
    }

    public int getCarid() {return carid;}

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public void setCarid(int carid) {
        this.carid = carid;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", car=" + carid +
                ", date=" + date +
                ", customername='" + customername + '\'' +
                ", price=" + price +
                '}';
    }

    public String forText(){
        return id+","+carid+","+date+","+customername+","+price;
    }
}
