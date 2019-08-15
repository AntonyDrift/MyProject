package web.servlets.admin;

import entities.Client;
import services.ClientService;
import services.impl.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;

import static services.impl.ClientServiceImpl.clientErrorStatusLog;

@WebServlet(urlPatterns = {"/accounts/admin/editclients"}, name = "adminClients")
public class AdminClientServlet extends HttpServlet {

    private ClientService clientService = ClientServiceImpl.getInstance();
    private static Client client = new Client();
    private static AdminClientServlet run = new AdminClientServlet();

    private void attributeLists(HttpServletRequest req) {

        List<Client> clientList = clientService.getAll();
        req.setAttribute("clients", clientList);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        run.attributeLists(req);
        req.getRequestDispatcher("/WEB-INF/accounts/admin/editClients.jsp").forward(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        String message = null;

        String delete_client = req.getParameter("delete");
        String update_client = req.getParameter("update");
        String add_client = req.getParameter("add");

        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String phone_number = req.getParameter("phone_number");

        if (delete_client != null) {
            if (req.getParameter("client_id") == null) {
                message = "Choose client on table";

            } else {
                clientService.delete(Long.parseLong(req.getParameter("client_id")));
                if (!clientErrorStatusLog) {
                    message = "Client successfully deleted";
                }
            }

        } else if (surname == "" | email == "" | phone_number == "") {
            message = "Enter fields, please";

        } else {
            client.setSurname(surname);
            client.setEmail(email);
            client.setPhone_number(Integer.parseInt(phone_number));

            if (update_client != null) {

                if (req.getParameter("client_id") == null) {
                    message = "Choose client on table";

                } else {
                    client.setClient_id(Long.parseLong(req.getParameter("client_id")));
                    clientService.update(client);
                    if (!clientErrorStatusLog) {
                        message = "Client successfully updated";
                    }
                }
            } else if (add_client != null) {

                clientService.add(client);
                if (!clientErrorStatusLog) {
                message = "Client successfully added";}
            }
        }
        run.attributeLists(req);
        req.setAttribute("message", message);
        req.getRequestDispatcher("/WEB-INF/accounts/admin/editClients.jsp").forward(req, resp);
    }
}