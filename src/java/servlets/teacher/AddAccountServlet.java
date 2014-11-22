package servlets.teacher;

import business.Class;
import business.Account;
import business.Teacher;
import dao.AccountDAO;
import dao.ClassDAO;
import dao.TeacherDAO;

import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.*;
import javax.servlet.http.*;

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
        Teacher t = (Teacher) req.getSession().getAttribute("teacher");

        Account ac = new Account(Integer.parseInt(number), 0, null);
        
        //Creates the new account
        try {
            ac.add();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }

        //Assigns to the teacher or class
        if (classID == null) {            
            HashMap<String, String> newValues = new HashMap<>();
            newValues.put("account_number", number);
            HashMap<String, String> conditions = new HashMap<>();
            conditions.put("id", t.getIdCardNumber());

            try {                
                new TeacherDAO().update(newValues, conditions);
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }
            resp.sendRedirect("/Neptun/teacher/accountManagement.jsp?number=" + number);
        } else {            
            Class c = new Class();
            c.setId(Integer.parseInt(classID)); 
            c.setAccount(ac);
            try {
                c.updateAccount();
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }
            resp.sendRedirect("/Neptun/teacher/accountManagement.jsp?number=" + number + "&classId=" + classID);
        }                
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(403);
    }
}
