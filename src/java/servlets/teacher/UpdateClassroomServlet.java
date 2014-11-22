package servlets.teacher;

import business.ClassRoom;
import business.Teacher;
import dao.ClassRoomDAO;
import dao.TeacherDAO;
import errors.UserNotFound;
import java.io.*; 
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.log4j.Logger;
import util.Link;

public class UpdateClassroomServlet extends HttpServlet{
    
    Logger logger;
    private final String className = getClass().getName();
    
    @Override
    public void init() {
        logger = Logger.getRootLogger();  
        logger.trace(className + " is initializing.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
        String name = req.getParameter("name");        
        try {
            ClassRoom cr = new ClassRoom(name, req.getParameter("desc"));
            cr.update();
            resp.sendRedirect("/Neptun/teacher/classroom.jsp?name=" + name);
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    
    
}
