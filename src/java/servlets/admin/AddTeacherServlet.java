package servlets.admin;

import business.Account;
import business.Teacher;
import business.City;
import dao.TeacherDAO;
import data.LoginDB;

import java.io.*;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.log4j.Logger;

public class AddTeacherServlet extends HttpServlet {

    private Logger logger;
    private final String className = getClass().getName();

    @Override
    public void init() {
        logger = Logger.getRootLogger();
        logger.trace(className + " is initializing.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();

        String username = req.getParameter("username");
        String password = req.getParameter("password");        
        boolean active = (req.getParameter("active").equals("true")) ? true : false;                

        try {
            Teacher t = new Teacher();
            t.setIdCardNumber(req.getParameter("idCardNumber"));
            t.setGender(req.getParameter("gender"));
            t.setFirstName(req.getParameter("firstName"));
            t.setMiddleName(req.getParameter("middleName"));
            t.setLastName(req.getParameter("lastName"));
            t.setEmail(req.getParameter("email"));
            t.setMobileNumber(req.getParameter("mobileNumber"));
            t.setPhoneNumber(req.getParameter("phoneNumber"));
            try {
                t.setDateOfBirth(new Date(req.getParameter("dateOfBirth")));
            } catch (IllegalArgumentException iae) {
                req.setAttribute("errorMessage", "Hibás dátum!");                
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/addTeacher.jsp");
                rd.forward(req, resp);
            }
            t.setUsername(username);
            t.setCity(new City(Integer.parseInt(req.getParameter("zipCode")), "asdf"));
            t.setAddress(req.getParameter("address"));

            //t.setAccount(new Account(Integer.parseInt(req.getParameter("account")), 0));

            t.add(password, active);
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
            req.setAttribute("errorMessage", "Hozzáadás sikertelen!");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/addTeacher.jsp");
            rd.forward(req, resp);
        }
        req.setAttribute("successMessage", "Hozzáadás sikeres!");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/main.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }
}
