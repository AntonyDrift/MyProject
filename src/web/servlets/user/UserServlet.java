package web.servlets.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/accounts/user")
public class UserServlet extends HttpServlet {

        public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html");
            req.getRequestDispatcher("/WEB-INF/accounts/user/user.jsp").forward(req, resp);
        }

        public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html");
            req.getRequestDispatcher("/WEB-INF/accounts/user/user.jsp").forward(req, resp);
        }
    }

