package web.servlets.user;

import entities.Car;
import entities.Client;
import entities.Request;
import entities.Track;
import services.CarService;
import services.ClientService;
import services.RequestService;
import services.TrackService;
import services.impl.*;
import web.servlets.admin.AdminRequestServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static services.impl.RequestServiceImpl.requestErrorStatusLog;

@WebServlet(urlPatterns = {"/accounts/user/editrequests"}, name = "userRequests")
public class UserRequestServlet extends HttpServlet {

    private CarService carService = CarServiceImpl.getInstance();
    private RequestService requestService = RequestServiceImpl.getInstance();
    private TrackService trackService = TrackServiceImpl.getInstance();
    private ClientService clientService = ClientServiceImpl.getInstance();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        HttpSession session = req.getSession();
        String email = session.getAttribute("myEmail").toString();

        long client_id = 0;
        List<Request> requestList = new ArrayList<>();

       for (Client clients : clientService.getAll()) {
           if (clients.getEmail().equals(email)) {
                client_id=clients.getClient_id();
           }
       }

        for (Request requests : requestService.getAll()) {
            if (requests.getClient_id()==client_id) {
                requestList.add(requests);
            }
        }
        req.setAttribute("requests", requestList);

        List<Car> carList = carService.getAll();
        req.setAttribute("cars", carList);

        List<Track> trackList = trackService.getAll();
        req.setAttribute("tracks", trackList);

        req.getRequestDispatcher("/WEB-INF/accounts/user/editRequests.jsp").forward(req, resp);
    }
}