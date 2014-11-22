package servlets.student;

import business.Message;
import business.Student;
import java.io.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.apache.log4j.Logger;

@WebServlet("/student/reportAttendance")
public class ReportAttendance extends HttpServlet {

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
            Date from = new SimpleDateFormat("MM/dd/yyyy").parse(req.getParameter("from"));
            Date to = new SimpleDateFormat("MM/dd/yyyy").parse(req.getParameter("to"));
            String comment = req.getParameter("comment");
            
            String formatFrom = new SimpleDateFormat("yyyy.MM.dd").format(from);
            String formatTo = new SimpleDateFormat("yyyy.MM.dd").format(to);
            
            Student s = (Student)req.getSession().getAttribute("student");
            
            new Message("Hiányzás bejelentése",
                    "<b>" + s.getFirstName() + " " + s.getLastName() + "</b> hiányzást jelentett be <b>" + formatFrom + " - " + formatTo + "</b> időszakra. <br /><br /> További megjegyzés: " + comment,
                    s.getForm().getFormMaster(), s).send();
                    
        } catch (ParseException | SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(403);
    }
}
