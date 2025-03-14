package Filters;

import Domain.Car;

public class Filter_Car_By_fuel implements Filter<Car> {

    private int fueltank;

    public Filter_Car_By_fuel(int fueltank) {
        this.fueltank = fueltank;
    }

    @Override
    public boolean accept(Car car){
        if(fueltank<car.getFueltank())
            return true;
        return false;
    }
}