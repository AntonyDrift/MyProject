package web.servlets.filtres;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/accounts/admin")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        boolean loggedIn = session != null && session.getAttribute("username") != null && session.getAttribute("role") != null;
        if (loggedIn) {
            String userRole = session.getAttribute("role").toString();
            if (userRole.equals("user")) {
                response.sendRedirect(request.getContextPath() + "/accounts/user");
            } else if (userRole.equals("admin")) {
                request.getRequestDispatcher("/accounts/admin").forward(request, response);
            }

        } else response.sendRedirect("/index.jsp");
    }

    @Override
    public void destroy() {

    }
}
