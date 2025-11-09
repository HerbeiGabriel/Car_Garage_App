package Tests.Service;

import Domain.Car;
import Domain.Reservation;
import Filters.Filter;
import Service.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    private Service service;

    @BeforeEach
    void setUp() {
        // Using in-memory repositories
        service = new Service("memory", "", "", "");
    }

    @Test
    void testAddAndGetCar() {
        Car car = new Car(1, "Tesla", 500, 80);
        service.addcar(car.getId(), car);

        Car retrieved = service.getcar(1);
        assertNotNull(retrieved);
        assertEquals("Tesla", retrieved.getModel());
    }

    @Test
    void testUpdateCar() {
        Car car = new Car(1, "Tesla", 500, 80);
        service.addcar(car.getId(), car);

        Car updatedCar = new Car(1, "BMW", 300, 60);
        service.updatecar(1, updatedCar);

        Car retrieved = service.getcar(1);
        assertEquals("BMW", retrieved.getModel());
        assertEquals(300, retrieved.getHorsepower());
    }

    @Test
    void testDeleteCar() {
        Car car = new Car(1, "Tesla", 500, 80);
        service.addcar(car.getId(), car);

        service.deletecar(1);
        assertNull(service.getcar(1));
    }

    @Test
    void testAddAndGetReservation() {
        Reservation res = new Reservation(1, 1, LocalDate.now(), "Alice", 100);
        service.addresevation(res.getId(), res);

        Reservation retrieved = service.getreservation(1);
        assertNotNull(retrieved);
        assertEquals("Alice", retrieved.getCustomername());
    }

    @Test
    void testUpdateReservation() {
        Reservation res = new Reservation(1, 1, LocalDate.now(), "Alice", 100);
        service.addresevation(res.getId(), res);

        Reservation updatedRes = new Reservation(1, 1, LocalDate.now(), "Bob", 150);
        service.updatereservation(1, updatedRes);

        Reservation retrieved = service.getreservation(1);
        assertEquals("Bob", retrieved.getCustomername());
        assertEquals(150, retrieved.getPrice());
    }

    @Test
    void testDeleteReservation() {
        Reservation res = new Reservation(1, 1, LocalDate.now(), "Alice", 100);
        service.addresevation(res.getId(), res);

        service.deletereservation(1);
        assertNull(service.getreservation(1));
    }

    @Test
    void testCheckIDcar() {
        Car car = new Car(1, "Tesla", 500, 80);
        service.addcar(car.getId(), car);

        assertTrue(service.checkIDcar(1));
        assertFalse(service.checkIDcar(2));
    }

    @Test
    void testFilterCar() {
        Car car1 = new Car(1, "Tesla", 500, 80);
        Car car2 = new Car(2, "BMW", 300, 60);
        service.addcar(car1.getId(), car1);
        service.addcar(car2.getId(), car2);

        Filter<Car> filter = c -> c.getHorsepower() > 400;
        List<Car> filtered = service.filterCar(filter);

        assertEquals(1, filtered.size());
        assertEquals("Tesla", filtered.get(0).getModel());
    }

    @Test
    void testFilterReservation() {
        Reservation res1 = new Reservation(1, 1, LocalDate.of(2025, 1, 1), "Alice", 100);
        Reservation res2 = new Reservation(2, 2, LocalDate.of(2024, 1, 1), "Bob", 200);
        service.addresevation(res1.getId(), res1);
        service.addresevation(res2.getId(), res2);

        Filter<Reservation> filter = r -> r.getPrice() > 150;
        List<Reservation> filtered = service.filterReservation(filter);

        assertEquals(1, filtered.size());
        assertEquals("Bob", filtered.get(0).getCustomername());
    }
}
