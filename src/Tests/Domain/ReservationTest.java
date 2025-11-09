package Tests.Domain;

import Domain.Reservation;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationTest {

    @Test
    public void testGetId() {
        Reservation r = new Reservation(1, 101, LocalDate.of(2025, 11, 9), "Alice", 200);
        assertEquals(1, r.getId());
    }

    @Test
    public void testSetId() {
        Reservation r = new Reservation(1, 101, LocalDate.of(2025, 11, 9), "Alice", 200);
        r.setId(5);
        assertEquals(5, r.getId());
    }

    @Test
    public void testGetCarId() {
        Reservation r = new Reservation(1, 101, LocalDate.of(2025, 11, 9), "Alice", 200);
        assertEquals(101, r.getCarid());
    }

    @Test
    public void testSetCarId() {
        Reservation r = new Reservation(1, 101, LocalDate.of(2025, 11, 9), "Alice", 200);
        r.setCarid(202);
        assertEquals(202, r.getCarid());
    }

    @Test
    public void testGetDate() {
        LocalDate date = LocalDate.of(2025, 11, 9);
        Reservation r = new Reservation(1, 101, date, "Alice", 200);
        assertEquals(date, r.getDate());
    }

    @Test
    public void testSetDate() {
        Reservation r = new Reservation(1, 101, LocalDate.of(2025, 11, 9), "Alice", 200);
        LocalDate newDate = LocalDate.of(2025, 12, 1);
        r.setDate(newDate);
        assertEquals(newDate, r.getDate());
    }

    @Test
    public void testGetCustomerName() {
        Reservation r = new Reservation(1, 101, LocalDate.of(2025, 11, 9), "Alice", 200);
        assertEquals("Alice", r.getCustomername());
    }

    @Test
    public void testSetCustomerName() {
        Reservation r = new Reservation(1, 101, LocalDate.of(2025, 11, 9), "Alice", 200);
        r.setCustomername("Bob");
        assertEquals("Bob", r.getCustomername());
    }

    @Test
    public void testGetPrice() {
        Reservation r = new Reservation(1, 101, LocalDate.of(2025, 11, 9), "Alice", 200);
        assertEquals(200, r.getPrice());
    }

    @Test
    public void testSetPrice() {
        Reservation r = new Reservation(1, 101, LocalDate.of(2025, 11, 9), "Alice", 200);
        r.setPrice(300);
        assertEquals(300, r.getPrice());
    }

    @Test
    public void testToString() {
        Reservation r = new Reservation(1, 101, LocalDate.of(2025, 11, 9), "Alice", 200);
        String expected = "Reservation{id=1, car=101, date=2025-11-09, customername='Alice', price=200}";
        assertEquals(expected, r.toString());
    }

    @Test
    public void testForText() {
        Reservation r = new Reservation(1, 101, LocalDate.of(2025, 11, 9), "Alice", 200);
        String expected = "1,101,2025-11-09,Alice,200";
        assertEquals(expected, r.forText());
    }
}
