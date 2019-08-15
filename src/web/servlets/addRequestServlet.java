package web.servlets;

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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.sql.Timestamp;;
import java.util.List;

@WebServlet(urlPatterns = {"/addrequest"}, name = "addRequest")
public class addRequestServlet extends HttpServlet {

    private CarService carService = CarServiceImpl.getInstance();
    private RequestService requestService = RequestServiceImpl.getInstance();
    private TrackService trackService = TrackServiceImpl.getInstance();
    private ClientService clientService = ClientServiceImpl.getInstance();



    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        String classCar = req.getParameter("classCar");
        List<Car> carList = carService.getCarsByClass(classCar);
        req.setAttribute("cars", carList);

        String typeTrack = req.getParameter("typeTrack");
        List<Track> trackList = trackService.getByType(typeTrack);
        req.setAttribute("tracks", trackList);

        req.getRequestDispatcher("/WEB-INF/addRequest.jsp").forward(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        Client client = new Client();
        Request request = new Request();


        String message = "x";

        String surname = req.getParameter("surname");
        String client_email = req.getParameter("client_email");
        int phone_number = Integer.parseInt(req.getParameter("phone_number"));
//        long car_id = Long.parseLong(req.getParameter("car_id"));
//        long track_id = Long.parseLong(req.getParameter("track_id"));
        String car_id = req.getParameter("car_id");
        String track_id = req.getParameter("track_id");
        Timestamp request_date = new Timestamp(System.currentTimeMillis());

        if (car_id==null|track_id==null) {

            message = "Select car or track!";
            req.setAttribute("message", message);

            req.getRequestDispatcher("/WEB-INF/addRequest.jsp").forward(req, resp);

        } else {


            client.setSurname(surname);
            client.setEmail(client_email);
            client.setPhone_number(phone_number);


            if (!clientService.getClientsEmail().contains(client_email)) {
                System.out.println("1:" + client.getEmail() + " 2:" + client_email);
                clientService.add(client);
            }
            for (Client clients : clientService.getAll()) {
                if (clients.getEmail().equals(client_email)) {
                    request.setClient_id(clients.getClient_id());
                    System.out.println(request.getClient_id());
                }
            }

            request.setRequest_date(request_date.toString());
            request.setCar_id(Long.parseLong(car_id));
            request.setTrack_id(Long.parseLong(track_id));
            request.setRequest_status(0);
            request.setCost(100);
            requestService.add(request);

            message = "Thanks for request!";
            req.setAttribute("message", message);

            req.getRequestDispatcher("/index.jsp").forward(req, resp);

        }
        }
    }





