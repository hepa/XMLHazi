package servlets.student;

import business.Homework;
import business.Student;
import dao.HomeworkDAO;
import dao.StudentDAO;
import errors.ClassNotFound;
import errors.UserNotFound;
import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.log4j.Logger;

public class CheckHomeworkDoneServlet extends HttpServlet {

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
            
            Homework hw = new Homework();
            hw.setId(Integer.parseInt(req.getParameter("homeworkId")));

            if (req.getParameter("done") != null) {
                hw.checkDone(s);
            } else {
                hw.checkUnDone(s);
            }

            resp.sendRedirect("/Neptun/student/main.jsp");
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(403);
    }
}
