package entities;

public class Car {

    private long car_id;
    private String model;
    private String wheel_drive;
    private int power;
    private int available;
    private String class_car;

    public Car() {
    }



    public long getCar_id() {
        return car_id;
    }

    public void setCar_id(long car_id) {
        this.car_id = car_id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getWheel_drive() {
        return wheel_drive;
    }

    public void setWheel_drive(String wheel_drive) {
        this.wheel_drive = wheel_drive;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getClass_car() {
        return class_car;
    }

    public void setClass_car(String class_car) {
        this.class_car = class_car;
    }
}
