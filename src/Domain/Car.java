package Domain;

public class Car implements Identifiable<Integer> {
    private Integer id;
    private String model;
    private int horsepower;
    private int fueltank;

    public Car(Integer id, String model, int horsepower, int fueltank) {
        if(id<=0)
            throw new IllegalArgumentException();
        if(horsepower<=0)
            throw new IllegalArgumentException();
        if(fueltank<=0)
            throw new IllegalArgumentException();
        this.id = id;
        this.model = model;
        this.horsepower = horsepower;
        this.fueltank = fueltank;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public int getFueltank() {
        return fueltank;
    }

    @Override
    public void setId(Integer id) {
        if(id<=0)
            throw new IllegalArgumentException();
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setHorsepower(int horsepower) {
        if(horsepower<=0)
            throw new IllegalArgumentException();
        this.horsepower = horsepower;
    }

    public void setFueltank(int fueltank) {
        if(fueltank<=0)
            throw new IllegalArgumentException();
        this.fueltank = fueltank;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", horsepower=" + horsepower +
                ", fueltank=" + fueltank +
                '}';
    }
}
