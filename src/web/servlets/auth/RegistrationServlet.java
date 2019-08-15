package web.servlets.auth;

import entities.User;
import services.UserService;
import services.impl.UserServiceImpl;

import static services.impl.UserServiceImpl.userErrorStatusLog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "registration", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {

    private UserService userService = UserServiceImpl.getInstance();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("WEB-INF/auth/registration.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        User user = new User();

        String username = req.getParameter("username");
        String user_email = req.getParameter("user_email");
        String user_password = req.getParameter("user_password");
        String message = null;

        user.setUsername(username);
        user.setUser_email(user_email);
        user.setUser_password(user_password);
        userService.add(user);

        if (!userErrorStatusLog) {
            message = "Succesfull reqistration";
            req.setAttribute("message", message);
            req.getRequestDispatcher("WEB-INF/auth/login.jsp").forward(req, resp);
        } else {
            if (userService.getEmails().contains(user_email) &&
                    userService.getUsernames().contains(username)) {
                    message = "This username and email are already taken";
            } else {
                if (userService.getEmails().contains(user_email)) {
                    message = "This email is already taken";
                }
                if (userService.getUsernames().contains(username)) {
                    message = "This username is already taken";
                }
            }
            req.setAttribute("message", message);
            req.getRequestDispatcher("WEB-INF/auth/registration.jsp").forward(req, resp);
        }


    }


}

