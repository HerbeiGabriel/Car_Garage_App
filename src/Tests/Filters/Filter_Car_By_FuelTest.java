package Tests.Filters;

import Domain.Car;
import Filters.Filter_Car_By_fuel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Filter_Car_By_FuelTest {

    @Test
    public void testAcceptReturnsTrueIfCarFuelTankGreater() {
        Car car = new Car(1, "Tesla", 500, 80);
        Filter_Car_By_fuel filter = new Filter_Car_By_fuel(50);
        assertTrue(filter.accept(car), "Car fuel tank is greater than filter value, should return true");
    }

    @Test
    public void testAcceptReturnsFalseIfCarFuelTankSmallerOrEqual() {
        Car car = new Car(1, "BMW", 300, 40);
        Filter_Car_By_fuel filter = new Filter_Car_By_fuel(50);
        assertFalse(filter.accept(car), "Car fuel tank is smaller than filter value, should return false");

        Car car2 = new Car(2, "Audi", 200, 50);
        assertFalse(filter.accept(car2), "Car fuel tank equals filter value, should return false");
    }

    @Test
    public void testAcceptWithExactMatch() {
        Car car = new Car(1, "Mercedes", 400, 60);
        Filter_Car_By_fuel filter = new Filter_Car_By_fuel(60);
        assertFalse(filter.accept(car), "Exact match should return false");
    }
}
