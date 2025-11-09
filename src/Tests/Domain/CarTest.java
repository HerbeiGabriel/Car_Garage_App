package Tests.Domain;

import Domain.Car;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {

    @Test
    public void testGetId() {
        Car car = new Car(1, "Tesla", 500, 80);
        assertEquals(1, car.getId());
    }

    @Test
    public void testSetId() {
        Car car = new Car(1, "Tesla", 500, 80);
        car.setId(5);
        assertEquals(5, car.getId());
    }

    @Test
    public void testGetModel() {
        Car car = new Car(1, "Tesla", 500, 80);
        assertEquals("Tesla", car.getModel());
    }

    @Test
    public void testSetModel() {
        Car car = new Car(1, "BMW", 300, 60);
        car.setModel("Audi");
        assertEquals("Audi", car.getModel());
    }

    @Test
    public void testGetHorsepower() {
        Car car = new Car(1, "Tesla", 500, 80);
        assertEquals(500, car.getHorsepower());
    }

    @Test
    public void testSetHorsepower() {
        Car car = new Car(1, "BMW", 300, 60);
        car.setHorsepower(400);
        assertEquals(400, car.getHorsepower());
    }

    @Test
    public void testGetFueltank() {
        Car car = new Car(1, "Tesla", 500, 80);
        assertEquals(80, car.getFueltank());
    }

    @Test
    public void testSetFueltank() {
        Car car = new Car(1, "BMW", 300, 60);
        car.setFueltank(70);
        assertEquals(70, car.getFueltank());
    }

    @Test
    public void testInvalidConstructorArguments() {
        assertThrows(IllegalArgumentException.class, () -> new Car(0, "Tesla", 500, 80));
        assertThrows(IllegalArgumentException.class, () -> new Car(1, "Tesla", -10, 80));
        assertThrows(IllegalArgumentException.class, () -> new Car(1, "Tesla", 500, 0));
    }

    @Test
    public void testInvalidSetters() {
        Car car = new Car(1, "Dacia", 100, 50);
        assertThrows(IllegalArgumentException.class, () -> car.setId(0));
        assertThrows(IllegalArgumentException.class, () -> car.setHorsepower(-5));
        assertThrows(IllegalArgumentException.class, () -> car.setFueltank(0));
    }

    @Test
    public void testToString() {
        Car car = new Car(1, "Toyota", 120, 50);
        String expected = "Car{id=1, model='Toyota', horsepower=120, fueltank=50}";
        assertEquals(expected, car.toString());
    }

    @Test
    public void testForText() {
        Car car = new Car(1, "Mazda", 150, 45);
        String expected = "1,Mazda,150,45";
        assertEquals(expected, car.forText());
    }
}
