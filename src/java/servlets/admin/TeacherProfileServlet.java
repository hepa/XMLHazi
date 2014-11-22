package servlets.admin;

import business.Teacher;
import dao.StudentDAO;
import dao.TeacherDAO;
import errors.UserNotFound;
import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.log4j.Logger;

public class TeacherProfileServlet extends HttpServlet {

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
            
            req.setAttribute("my", Teacher.getInstance(req.getParameter("id")));
            
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/teacher/about.jsp");
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
