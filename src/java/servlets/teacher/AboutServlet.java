package servlets.teacher;

import business.Teacher;
import java.io.*; 
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.log4j.Logger;
import util.Link;

public class AboutServlet extends HttpServlet{
    
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
            Teacher t = (Teacher)session.getAttribute("teacher");
            
            req.setAttribute("my", Teacher.getInstance(t.getIdCardNumber()));        
            Link.forward(this, "/teacher/about.jsp", req, resp);            
        } catch (SQLException ex) { 
            logger.error(ex.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    
    
}
