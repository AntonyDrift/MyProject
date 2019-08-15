package dao;

import entities.Car;

import java.sql.SQLException;
import java.util.List;

public interface CarDao extends GenericDao<Car> {

    List<Car> getCarsByClass(String class_car) throws SQLException;
}
