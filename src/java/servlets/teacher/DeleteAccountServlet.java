package servlets.teacher;

import business.Account;
import business.Class;
import business.Meeting;
import business.MeetingType;
import dao.AccountDAO;
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

public class DeleteAccountServlet extends HttpServlet {

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
        
        Account a = new Account();
        a.setNumber(Integer.parseInt(req.getParameter("number")));
        
        try {
            new AccountDAO(a).remove();
            resp.sendRedirect("/Neptun/teacher/about");
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(403);
    }
}
