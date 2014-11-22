package servlets.teacher;

import business.Class;
import business.Person;
import business.Student;
import dao.ClassDAO;
import errors.ClassNotFound;
import errors.UserNotFound;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import util.Link;

@WebServlet("/teacher/searchClass")
public class SearchClassServlet extends HttpServlet {

    private org.apache.log4j.Logger logger;
    private final String className = getClass().getName();

    @Override
    public void init() {
        logger = org.apache.log4j.Logger.getRootLogger();
        logger.trace(className + " is initializing.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");        
        resp.setCharacterEncoding("UTF-8");        
        
        HashMap<String, String> hMap = new HashMap<>();
        String className = req.getParameter("name");
        String formMaster = req.getParameter("teacher");
        
        hMap.put("name", className);        
        hMap.put("teacher_id", formMaster);
        hMap.put("student_id", req.getParameter("student"));
        
        try {
            ArrayList<Class> results = new ClassDAO().search(hMap);
            
            for (Class c : results) {
                try {
                    c.findStudents();
                } catch (UserNotFound ex) {
                    logger.error(ex.getMessage());
                }
            }
            
            req.setAttribute("results", results);
            req.setAttribute("classes", Class.getAllInstances());
            req.setAttribute("students", Student.getAllInstances());
            Link.forward(this, "/teacher/class.jsp", req, resp);
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        } catch (ClassNotFound cnf) {
            resp.sendRedirect("/Neptun/teacher/class.jsp?errorMessage=Nincs ilyen osztály.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(403);
    }
}
