package Tests.Filters;

import Domain.Car;
import Filters.Filter_Car_By_Model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Filter_Car_By_ModelTest {

    @Test
    public void testAcceptReturnsTrueIfModelMatches() {
        Car car = new Car(1, "Tesla", 500, 80);
        Filter_Car_By_Model filter = new Filter_Car_By_Model("Tesla");
        assertTrue(filter.accept(car), "Car model matches filter, should return true");
    }

    @Test
    public void testAcceptReturnsFalseIfModelDoesNotMatch() {
        Car car = new Car(1, "BMW", 300, 60);
        Filter_Car_By_Model filter = new Filter_Car_By_Model("Tesla");
        assertFalse(filter.accept(car), "Car model does not match filter, should return false");
    }


    @Test
    public void testAcceptWithFilterNull() {
        Car car = new Car(1, "Tesla", 500, 80);
        Filter_Car_By_Model filter = new Filter_Car_By_Model(null);
        assertFalse(filter.accept(car), "Filter model is null, should return false");
    }
}
