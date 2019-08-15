package web.servlets.admin;

import entities.Car;
import entities.Client;
import entities.Request;
import entities.Track;
import services.CarService;
import services.ClientService;
import services.RequestService;
import services.TrackService;
import services.impl.CarServiceImpl;
import services.impl.ClientServiceImpl;
import services.impl.RequestServiceImpl;
import services.impl.TrackServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.sql.Timestamp;
import java.util.List;

import static services.impl.RequestServiceImpl.requestErrorStatusLog;

@WebServlet(urlPatterns = {"/accounts/admin/editrequests"}, name = "adminRequests")
public class AdminRequestServlet extends HttpServlet {

    private CarService carService = CarServiceImpl.getInstance();
    private RequestService requestService = RequestServiceImpl.getInstance();
    private TrackService trackService = TrackServiceImpl.getInstance();
    private ClientService clientService = ClientServiceImpl.getInstance();

    private static Client client = new Client();
    private static Request request = new Request();
    private static AdminRequestServlet run = new AdminRequestServlet();

    private void attributeLists(HttpServletRequest req) {

        List<Request> requestList = requestService.getAll();
        req.setAttribute("requests", requestList);

        List<Client> clientList = clientService.getAll();
        req.setAttribute("clients", clientList);

        List<Car> carList = carService.getAll();
        req.setAttribute("cars", carList);

        List<Track> trackList = trackService.getAll();
        req.setAttribute("tracks", trackList);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        run.attributeLists(req);
        req.getRequestDispatcher("/WEB-INF/accounts/admin/editRequests.jsp").forward(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        String message = null;

        String delete_request = req.getParameter("delete");
        String update_request = req.getParameter("update");
        String add_request = req.getParameter("add");

        String request_date = null;
        String client_id = req.getParameter("client_id");
        String car_id = req.getParameter("car_id");
        String track_id = req.getParameter("track_id");
        String request_status = req.getParameter("request_status");
        String cost = req.getParameter("cost");

        if (delete_request != null) {
            if (req.getParameter("request_id") == null) {
                message = "Choose request on table";

            } else {

                requestService.delete(Long.parseLong(req.getParameter("request_id")));
                if (!requestErrorStatusLog) {
                    message = "Request successfully deleted";
                }
            }

        } else if (client_id == "" | car_id == "" | track_id == "" |
                request_status == "" | cost == "") {
            message = "Enter fields, please";

        } else {
            request_date = new Timestamp(System.currentTimeMillis()).toString();
            request.setRequest_date(request_date);
            request.setClient_id(Long.parseLong(client_id));
            request.setCar_id(Long.parseLong(car_id));
            request.setTrack_id(Long.parseLong(track_id));
            request.setRequest_status(Integer.parseInt(request_status));
            request.setCost(Integer.parseInt(cost));

            if (update_request != null) {

                if (req.getParameter("request_id") == null) {
                    message = "Choose request on table";

                } else {
                    request.setRequest_id(Long.parseLong(req.getParameter("request_id")));
                    requestService.update(request);
                    if (!requestErrorStatusLog) {
                        message = "Request successfully updated";
                    }
                }
            } else if (add_request != null) {
                if (!requestErrorStatusLog) {
                    requestService.add(request);
                    message = "Request successfully added";
                }
            }
        }
        run.attributeLists(req);
        req.setAttribute("message", message);
        req.getRequestDispatcher("/WEB-INF/accounts/admin/editRequests.jsp").forward(req, resp);
    }
}
