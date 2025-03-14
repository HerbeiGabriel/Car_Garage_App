package Filters;

import Domain.Reservation;

public class Filter_Reservation_By_Price implements Filter<Reservation> {

    private int price;

    public Filter_Reservation_By_Price(int price) {
        this.price = price;
    }

    @Override
    public boolean accept(Reservation t) {
        return t.getPrice() >= price;
    }
}
