package servlets.teacher;

import business.Class;
import business.Meeting;
import business.MeetingType;
import dao.ClassDAO;
import dao.MeetingDAO;
import dao.MeetingTypeDAO;
import java.io.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;
import util.Link;

public class DeleteMeetingServlet extends HttpServlet {

    private org.apache.log4j.Logger logger;
    private final String className = getClass().getName();

    @Override
    public void init() {
        logger = org.apache.log4j.Logger.getRootLogger();
        logger.trace(className + " is initializing.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        try {                        
            Date from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(req.getParameter("dateFrom"));
            HttpSession session = req.getSession();
            Class c = (Class)session.getAttribute("my_class");
            
            try {
                Meeting m = Meeting.getInstance(c.getId(), new Timestamp(from.getTime()));
                m.remove(c);                
                
                resp.sendRedirect("/Neptun/teacher/addMeeting.jsp?successMessage=Sikeresen törölve.");
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }
        } catch (ParseException ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(403);
    }
}
