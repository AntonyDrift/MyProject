package dao.impl;

import dao.CarDao;
import entities.Car;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl extends AbstractDao implements CarDao {

    private static final String GET_ALL_CARS_QUERY = "SELECT * FROM cars";
    private static final String GET_CARS_BY_CLASS_QUERY = "SELECT * FROM cars WHERE class_car=?";
    private static final String DELETE_CAR_QUERY = "DELETE FROM cars WHERE car_id=?";
    private static final String UPDATE_CAR_QUERY = "UPDATE cars SET model=?, wheel_drive=?," +
            "power=?, available=?, class_car=? WHERE car_id=?";
    private static final String ADD_CAR_QUERY = "INSERT INTO cars" +
            "(model, wheel_drive, power, available, class_car)" +
            "VALUES (?, ?, ?, ?, ?)";

    private PreparedStatement getAllCars;
    private PreparedStatement getCarsByClass;
    private PreparedStatement updateCar;
    private PreparedStatement deleteCar;
    private PreparedStatement addCar;

    private static volatile CarDao INSTANCE = null;

    public static CarDao getInstance() {

        CarDao carDao = INSTANCE;
        if (carDao == null) {
            synchronized (CarDaoImpl.class) {
                carDao = INSTANCE;
                if (carDao == null) {
                    INSTANCE = carDao = new CarDaoImpl();
                }
            }
        }
        return carDao;
    }

    private Car populateEntity(ResultSet resultSet) throws SQLException {

        Car entity = new Car();

        entity.setCar_id(resultSet.getLong(1));
        entity.setModel(resultSet.getString(2));
        entity.setWheel_drive(resultSet.getString(3));
        entity.setPower(resultSet.getInt(4));
        entity.setAvailable(resultSet.getInt(5));
        entity.setClass_car(resultSet.getString(6));

        return entity;
    }

    @Override
    public List<Car> getCarsByClass(String class_car) throws SQLException {

        getCarsByClass = preparedStatement(GET_CARS_BY_CLASS_QUERY);
        getCarsByClass.setString(1, class_car);

        List<Car> carList = new ArrayList<>();
        ResultSet resultSet = getCarsByClass.executeQuery();

        while (resultSet.next()) {
            carList.add(populateEntity(resultSet));
        }
        return carList;
    }

    @Override
    public Car add(Car car) throws SQLException {

        addCar = preparedStatement(ADD_CAR_QUERY);
        addCar.setString(1, car.getModel());
        addCar.setString(2, car.getWheel_drive());
        addCar.setInt(3, car.getPower());
        addCar.setInt(4, car.getAvailable());
        addCar.setString(5, car.getClass_car());
        addCar.executeUpdate();

        return car;
    }

    @Override
    public List<Car> getAll() throws SQLException {

        getAllCars = preparedStatement(GET_ALL_CARS_QUERY);

        List<Car> carList = new ArrayList<>();
        ResultSet resultSet = getAllCars.executeQuery();

        while (resultSet.next()) {
            carList.add(populateEntity(resultSet));
        }
        close(resultSet);
        return carList;
    }

    @Override
    public Car update(Car car) throws SQLException {

        updateCar = preparedStatement(UPDATE_CAR_QUERY);
        updateCar.setString(1, car.getModel());
        updateCar.setString(2, car.getWheel_drive());
        updateCar.setInt(3, car.getPower());
        updateCar.setInt(4, car.getAvailable());
        updateCar.setString(5, car.getClass_car());
        updateCar.setLong(6, car.getCar_id());
        updateCar.executeUpdate();

        return car;
    }

    @Override
    public void delete(long car_id) throws SQLException {

        deleteCar = preparedStatement(DELETE_CAR_QUERY);
        deleteCar.setLong(1, car_id);
        deleteCar.executeUpdate();
    }
}
