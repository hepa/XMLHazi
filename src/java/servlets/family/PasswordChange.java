package servlets.family;

import business.Family;
import errors.PasswordsDontMatch;
import java.io.*;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import login.Login;
import org.apache.log4j.Logger;

@WebServlet("/family/password")
public class PasswordChange extends HttpServlet {

    Logger logger;
    private final String className = getClass().getName();

    @Override
    public void init() {
        logger = Logger.getRootLogger();
        logger.trace(className + " is initializing.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {                        
        try {
            Family f = (Family) req.getSession().getAttribute("family");
            Login l = new Login(f.getUsername(), req.getParameter("old_password"));
            try {
                l.passwordChange(req.getParameter("new_password"));
            } catch (PasswordsDontMatch ex) {
                resp.sendRedirect("/Neptun/family/password.jsp?errorMessage=Helytelen a jelenlegi jelszavad!");
                return;
            }
            resp.sendRedirect("/Neptun/family/about.jsp");
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(403);
    }
}
