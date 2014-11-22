package servlets.admin;

import business.Student;
import dao.StudentDAO;
import errors.ClassNotFound;
import errors.UserNotFound;
import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.log4j.Logger;

public class StudentProfileServlet extends HttpServlet {

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
            Student s = Student.getInstance(req.getParameter("id"));            
            req.setAttribute("my", s);            
            
            logger.trace(s.getForm());
            
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/student/about.jsp");
            rd.forward(req, resp);        
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
