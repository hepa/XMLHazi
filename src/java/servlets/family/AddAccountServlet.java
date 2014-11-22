package servlets.family;

import servlets.teacher.*;
import business.Class;
import business.Account;
import business.Family;
import business.Teacher;
import dao.AccountDAO;
import dao.ClassDAO;
import dao.TeacherDAO;

import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/family/addAccount")
public class AddAccountServlet extends HttpServlet {

    private org.apache.log4j.Logger logger;
    private final String className = getClass().getName();

    @Override
    public void init() {
        logger = org.apache.log4j.Logger.getRootLogger();
        logger.trace(className + " is initializing.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String number = req.getParameter("number");
        String classID = req.getParameter("classId");
        Family f = (Family) req.getSession().getAttribute("family");

        Account ac = new Account(Integer.parseInt(number), 0, null);

        //Creates the new account
        try {
            ac.add();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        
        try {
            f.addAccount(ac);
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        resp.sendRedirect("/Neptun/family/accountManagement.jsp?number=" + number);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(403);
    }
}
