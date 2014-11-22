package servlets.family;

import business.Family;
import servlets.student.*;
import business.Message;
import business.Person;
import business.Student;
import errors.AccountNotFound;
import errors.ClassNotFound;
import errors.UserNotFound;
import java.io.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.apache.log4j.Logger;

@WebServlet("/family/reportAttendance")
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
            
            Family f = (Family)req.getSession().getAttribute("family");
            f.getStudent().findClass();
            
            new Message("Hiányzás bejelentése",
                    "<b>" + f.getStudent().getFirstName() + " " + f.getStudent().getLastName() + "</b> hiányzást jelentett be <b>" + formatFrom + " - " + formatTo + "</b> időszakra. <br /><br /> További megjegyzés: " + comment,
                    f.getStudent().getForm().getFormMaster(), new Person(f.getUsername(), f.getFather().getFirstName(), f.getFather().getLastName())).send();
                    
        } catch (SQLException | ClassNotFound | UserNotFound | AccountNotFound | ParseException ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(403);
    }
}
