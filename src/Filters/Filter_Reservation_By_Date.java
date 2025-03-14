package Filters;

import Domain.Reservation;

import java.time.LocalDate;

public class Filter_Reservation_By_Date implements Filter<Reservation> {

    private LocalDate date;

    public Filter_Reservation_By_Date(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean accept(Reservation t) {
        if(t.getDate().isAfter(date))
            return true;
        return false;
    }
}
