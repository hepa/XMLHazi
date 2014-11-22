package servlets.admin;

import business.Account;
import business.Teacher;
import dao.AccountDAO;
import dao.StudentDAO;
import dao.TeacherDAO;

import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.*;
import javax.servlet.http.*;

public class AddAccountServlet extends HttpServlet{
    
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
        Teacher t = (Teacher)req.getSession().getAttribute("teacher");                
                
        TeacherDAO tdao = new TeacherDAO();
        
        HashMap<String, String> newValues = new HashMap<>();
        newValues.put("account_number", number);
        HashMap<String, String> conditions = new HashMap<>();
        conditions.put("id", t.getIdCardNumber());
        
        try {
            Account a = new Account(Integer.parseInt(number), 0, null);
            a.add();
            
            tdao.update(newValues, conditions);
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        req.setAttribute("successMessage", "Sikeresen hozzáadva.");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/teacher/about");
        rd.forward(req, resp);        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {        
    }
    
    
}
