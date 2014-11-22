package servlets.teacher;

import business.ConsultingHour;
import business.Teacher;
import dao.ConsultingHourDAO;
import java.io.*;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;
import util.Link;

public class AddConsultingHourServlet extends HttpServlet {

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

        HttpSession session = req.getSession();
        Teacher t = (Teacher) session.getAttribute("teacher");

        try {
            ConsultingHour ch = new ConsultingHour();
            ch.setDay(req.getParameter("day"));
            ch.setTeacher(t);
            ch.setFrom(req.getParameter("from"));
            ch.setTo(req.getParameter("to"));
            ch.add();
            resp.sendRedirect("/Neptun/teacher/addConsultingHour.jsp");
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
            if (ex.getErrorCode() == 1062) {
                resp.sendRedirect("/Neptun/teacher/addConsultingHour.jsp?errorMessage=Már van ilyen.");
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(403);
    }
}
