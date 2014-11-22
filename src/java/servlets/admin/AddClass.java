package servlets.admin;

import business.Account;
import business.Class;
import business.Teacher;
import dao.ClassDAO;
import dao.TeacherDAO;
import errors.UserNotFound;

import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.*;
import javax.servlet.http.*;

public class AddClass extends HttpServlet {

    private org.apache.log4j.Logger logger;
    private final String className = getClass().getName();

    @Override
    public void init() {
        logger = org.apache.log4j.Logger.getRootLogger();
        logger.trace(className + " is initializing.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {        
        Class c = new Class();

        try {
            c.setAccount(new Account(Integer.parseInt(req.getParameter("account_number")), 0, null));
        } catch (NumberFormatException nfe) {
            c.setAccount(null);
        }
        c.setName(req.getParameter("name"));        

        try {
            c.setFormMaster(Teacher.getInstance(req.getParameter("formMaster")));
            c.add();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }        

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
