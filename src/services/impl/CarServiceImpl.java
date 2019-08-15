package services.impl;

import dao.CarDao;
import dao.impl.CarDaoImpl;
import entities.Car;
import org.apache.log4j.Logger;
import services.CarService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarServiceImpl extends AbstractService implements CarService {

    private static final Logger LOGGER = Logger.getLogger(CarServiceImpl.class);
    private static volatile CarService INSTANCE = null;
    private CarDao carDao = CarDaoImpl.getInstance();

    public static boolean carErrorStatusLog;

    public static CarService getInstance() {
        CarService carService = INSTANCE;
        if (carService == null) {
            synchronized (CarServiceImpl.class) {
                carService = INSTANCE;
                if (carService == null) {
                    INSTANCE = carService = new CarServiceImpl();
                }
            }
        }
        return carService;
    }

    @Override
    public List<Car> getCarsByClass(String classCar) {

        List<Car> carsByClass = new ArrayList<>();
        try {
            carsByClass = carDao.getCarsByClass(classCar);
            carErrorStatusLog=false;
        } catch (SQLException e) {
            LOGGER.error("Error getting cars by class" + classCar);
            carErrorStatusLog=true;
        }
        return carsByClass;
    }

    @Override
    public Car add(Car car) {
        try {
            car = carDao.add(car);
            carErrorStatusLog=false;
        } catch (SQLException e) {
            LOGGER.error("Error adding car" + car);
            carErrorStatusLog=true;
        }
        return car;
    }

    @Override
    public Car update(Car car) {
        try {
            car = carDao.update(car);
            carErrorStatusLog=false;
        } catch (SQLException e) {
            LOGGER.error("Error update car" + car);
            carErrorStatusLog=true;
        }
        return car;
    }

    @Override
    public void delete (long id) {
        try {
            carDao.delete(id);
            carErrorStatusLog=false;
        } catch (SQLException e) {
            LOGGER.error("Error deleting car #" + id);
            carErrorStatusLog=true;
        }
    }

    @Override
    public List<Car> getAll() {
        List<Car> allCars = new ArrayList<>();
        carErrorStatusLog=false;
        try {
            allCars = carDao.getAll();
        } catch (SQLException e) {
            LOGGER.error("Error getting all cars");
            carErrorStatusLog=true;
        }
        return allCars;
    }
}
