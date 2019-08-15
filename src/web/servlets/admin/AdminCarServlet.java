package web.servlets.admin;

import entities.Car;
import services.CarService;
import services.impl.CarServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static services.impl.CarServiceImpl.carErrorStatusLog;

@WebServlet(urlPatterns = {"/accounts/admin/editcars"}, name = "adminCars")
public class AdminCarServlet extends HttpServlet {

    private CarService carService = CarServiceImpl.getInstance();
    private static Car car = new Car();
    private static AdminCarServlet run = new AdminCarServlet();

    private void attributeLists(HttpServletRequest req) {

        List<Car> carList = carService.getAll();
        req.setAttribute("cars", carList);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        run.attributeLists(req);
        req.getRequestDispatcher("/WEB-INF/accounts/admin/editCars.jsp").forward(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        String message = null;

        String delete_car = req.getParameter("delete");
        String update_car = req.getParameter("update");
        String add_car = req.getParameter("add");

        String model = req.getParameter("model");
        String wheel_drive = req.getParameter("wheel_drive");
        String power = req.getParameter("power");
        String available = req.getParameter("available");
        String class_car = req.getParameter("class_car");

        if (delete_car != null) {
            if (req.getParameter("car_id") == null) {
                message = "Choose car on table";

            } else {

                carService.delete(Long.parseLong(req.getParameter("car_id")));
                if (!carErrorStatusLog) {
                    message = "Car successfully deleted";
                }
            }

        } else if (model == "" || wheel_drive == "" || power == ""
                || available == "" || class_car == "") {
            message = "Enter fields, please";

        } else {
            car.setModel(model);
            car.setWheel_drive(wheel_drive);
            car.setPower(Integer.parseInt(power));
            car.setAvailable(Integer.parseInt(available));
            car.setClass_car(class_car);

            if (update_car != null) {

                if (req.getParameter("car_id") == null) {
                    message = "Choose car on table";

                } else {
                    car.setCar_id(Long.parseLong(req.getParameter("car_id")));
                    carService.update(car);
                    if (!carErrorStatusLog) {
                        message = "Car successfully updated";
                    }
                }
            } else if (add_car != null) {
                if (!carErrorStatusLog) {
                    carService.add(car);
                    message = "Car successfully added";
                }
            }
        }
        run.attributeLists(req);
        req.setAttribute("message", message);
        req.getRequestDispatcher("/WEB-INF/accounts/admin/editCars.jsp").

                forward(req, resp);
    }
}