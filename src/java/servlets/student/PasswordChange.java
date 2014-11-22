package servlets.student;

import business.Homework;
import business.Student;
import dao.HomeworkDAO;
import dao.StudentDAO;
import errors.ClassNotFound;
import errors.PasswordsDontMatch;
import errors.UserNotFound;
import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import login.Login;
import org.apache.log4j.Logger;

@WebServlet("/student/password")
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
            Student s = (Student) req.getSession().getAttribute("student");
            Login l = new Login(s.getUsername(), req.getParameter("old_password"));
            try {
                l.passwordChange(req.getParameter("new_password"));
            } catch (PasswordsDontMatch ex) {
                resp.sendRedirect("/Neptun/student/password.jsp?errorMessage=Helytelen a jelenlegi jelszavad!");
                return;
            }
            resp.sendRedirect("/Neptun/student/about.jsp");
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(403);
    }
}
