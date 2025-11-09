package Tests.Filters;

import Domain.Reservation;
import Filters.Filter_Reservation_By_Date;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class Filter_Reservation_By_DateTest {

    @Test
    void testAcceptAfterDate() {
        LocalDate filterDate = LocalDate.of(2025, 1, 1);
        Filter_Reservation_By_Date filter = new Filter_Reservation_By_Date(filterDate);

        Reservation res = new Reservation(1, 101, LocalDate.of(2025, 1, 2), "Alice", 100);

        assertTrue(filter.accept(res), "Reservation after the filter date should be accepted");
    }

    @Test
    void testRejectBeforeOrEqualDate() {
        LocalDate filterDate = LocalDate.of(2025, 1, 1);
        Filter_Reservation_By_Date filter = new Filter_Reservation_By_Date(filterDate);

        Reservation resBefore = new Reservation(4, 104, LocalDate.of(2024, 12, 31), "Diana", 250);
        Reservation resEqual = new Reservation(5, 105, LocalDate.of(2025, 1, 1), "Eve", 300);

        assertFalse(filter.accept(resBefore), "Reservation before filter date should be rejected");
        assertFalse(filter.accept(resEqual), "Reservation on filter date should be rejected");
    }
}
