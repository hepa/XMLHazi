package servlets.teacher;

import business.ConsultingHour;
import business.Teacher;
import java.io.*;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/teacher/deleteConsultingHour")
public class DeleteConsultingHourServlet extends HttpServlet {

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
        
        Teacher t = new Teacher();
        t.setIdCardNumber(req.getParameter("teacherId"));
        
        int from = Integer.parseInt(req.getParameter("from"));
        String sFrom = String.valueOf(from);
        
        if (from < 10) {
            sFrom = "0" + from;
        }
        
        ConsultingHour ch = new ConsultingHour();
        ch.setDay(req.getParameter("day"));
        ch.setFrom(sFrom);    
        ch.setTeacher(t);                
        
        try {
            ch.delete();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(403);
    }
}
