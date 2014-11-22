package servlets.teacher;

import business.Teacher;
import errors.PasswordsDontMatch;
import java.io.*;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import login.Login;
import org.apache.log4j.Logger;

@WebServlet("/teacher/password")
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
            Teacher t = (Teacher) req.getSession().getAttribute("teacher");
            Login l = new Login(t.getUsername(), req.getParameter("old_password"));
            try {
                l.passwordChange(req.getParameter("new_password"));
            } catch (PasswordsDontMatch ex) {
                resp.sendRedirect("/Neptun/teacher/password.jsp?errorMessage=Helytelen a jelenlegi jelszavad!");
                return;
            }
            resp.sendRedirect("/Neptun/teacher/about.jsp");
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(403);
    }
}
