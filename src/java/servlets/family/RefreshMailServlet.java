package servlets.family;

import business.Family;
import servlets.student.*;
import business.Message;
import business.Student;
import dao.MessageDAO;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/family/refreshMail")
public class RefreshMailServlet extends HttpServlet {

    private org.apache.log4j.Logger logger;
    private final String className = getClass().getName();

    @Override
    public void init() {
        logger = org.apache.log4j.Logger.getRootLogger();
        logger.trace(className + " is initializing.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");       
        
        HttpSession session = req.getSession();
        Family f = (Family) session.getAttribute("family");

        try {
            int db = new MessageDAO().findUnreadsSize(f.getUsername());
            String size = String.valueOf(db);        
            
            String newMessage = "0";
            String subject = null;
            String from = null;
            ArrayList<Message> messages = Message.getAllInstances(f.getUsername());
            
            for (Message m : messages) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.SECOND, -5);
                if (m.getCreated().after(cal.getTime())) {
                    newMessage = "1";
                    subject = m.getSubject();
                    from = m.getFrom().getUsername();
                    break;
                }
            }
                        
            resp.getWriter().write(size + "`" + newMessage + "`" + subject + "`" + from);
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(403);
    }
}
