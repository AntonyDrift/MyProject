package services;

import entities.Car;

import java.util.List;

public interface CarService extends GenericService<Car> {

    List<Car> getCarsByClass(String class_car);
}
