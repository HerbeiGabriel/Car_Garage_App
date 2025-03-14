package Filters;

import Domain.Car;

public class Filter_Car_By_Model implements Filter<Car> {

    private String model;

    public Filter_Car_By_Model(String model) {
        this.model = model;
    }

    @Override
    public boolean accept(Car car) {
        if(car.getModel().equals(this.model))
            return true;
        return false;
    }
}
