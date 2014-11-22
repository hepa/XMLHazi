package servlets.student;

import business.Homework;
import business.Settings;
import business.Student;
import java.io.*;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.apache.log4j.Logger;

@WebServlet("/student/updateSettings")
public class UpdateSettings extends HttpServlet {

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
            HttpSession session = req.getSession();
            Student s = (Student)session.getAttribute("student");            
         
            Settings.update(s, req.getParameter("attribute"), Boolean.valueOf(req.getParameter("value")));            
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }                        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(403);
    }
}
