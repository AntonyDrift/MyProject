package web.servlets.auth;

import entities.User;
import services.UserService;
import services.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private UserService userService = UserServiceImpl.getInstance();
    private static User user = new User();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        req.getRequestDispatcher("/WEB-INF/auth/login.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {


        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String username = req.getParameter("username");
        System.out.println(username);
        String password = req.getParameter("user_password");
//
        List<User> userList = userService.getAll();
        req.setAttribute("users", userList);

        for (User users : userList) {

            if (userService.getUsernames().contains(username)) {

                if (password.equals(users.getUser_password())) {
                    HttpSession session = req.getSession();
                    session.setAttribute("username", users.getUsername());
                    session.setAttribute("password", users.getUser_password());
                    session.setAttribute("myEmail", users.getUser_email());

                    if (users.getRole().equals("admin")) {
                        session.setAttribute("role", users.getRole());

                        resp.sendRedirect("accounts/admin");
//                        req.getRequestDispatcher("accounts/admin").forward(req,resp);
                    }
                    else if (users.getRole().equals("user")) {
                        session.setAttribute("role", users.getRole());
//                    req.getRequestDispatcher("index.jsp").forward(req, resp);
                        resp.sendRedirect("accounts/user");
                    }
                }
            }
        }
//        out.print("Wrong login or password");
//        req.getRequestDispatcher("WEB-INF/auth/login.jsp").include(req, resp);

    }
}
