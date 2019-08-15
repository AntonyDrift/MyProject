package services;

import entities.Car;
import services.impl.CarServiceImpl;

class TestCarService {

    CarServiceImpl carSI = new CarServiceImpl();
    private static Car car = new Car();

    public static void main(String[] args) {

        TestCarService run = new TestCarService();
        run.methodGetAllCars();
        run.methodGetCarsByClass();
        run.methodAddCar();
        run.methodUpdateCar();
        run.methodDeleteCar();

    }
    private void methodGetAllCars() {

        System.out.println("All cars list");
        for (Car cars : carSI.getAll()) {
            System.out.println(cars.getCar_id() + " " + cars.getModel() + " " +
                    cars.getWheel_drive() + " " + cars.getPower() + " " +
                    cars.getAvailable() + " " + cars.getClass_car());
        }
    }
    private void methodGetCarsByClass() {

        System.out.println("Get cars by class");
        for (Car cars : carSI.getCarsByClass("DRIFT")) {
            System.out.println(cars.getCar_id() + " " + cars.getModel() + " " +
                    cars.getWheel_drive() + " " + cars.getPower() + " " +
                    cars.getAvailable() + " " + cars.getClass_car());

        }
    }
    private void  methodAddCar() {

        System.out.println("Add car method: model, wheel_drive, power, available, class_car");
        car.setModel("TEST");
        car.setWheel_drive("FWD");
        car.setPower(300);
        car.setAvailable(1);
        car.setClass_car("TEST");
        carSI.add(car);
    }

    private void methodUpdateCar() {

        System.out.println("Update car method: available WHERE car_id=?");
        car.setAvailable(0);
        car.setCar_id(13);
        carSI.update(car);
    }

    private void methodDeleteCar() {

        System.out.println("Delete car method WHERE car_id=?");
        carSI.delete(12);
    }
}
