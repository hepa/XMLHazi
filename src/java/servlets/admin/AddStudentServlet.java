package servlets.admin;

import business.City;
import business.Student;
import dao.StudentDAO;
import java.io.*;
import java.sql.SQLException;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.log4j.Logger;

public class AddStudentServlet extends HttpServlet {

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
            Student s = new Student();
            s.setStudentIdNumber(req.getParameter("studentIdNumber").toString());
            s.setIdCardNumber(req.getParameter("idCardNumber"));
            s.setGender(req.getParameter("gender"));
            s.setFirstName(req.getParameter("firstName"));
            s.setMiddleName(req.getParameter("middleName"));
            s.setLastName(req.getParameter("lastName"));
            s.setEmail(req.getParameter("email"));
            s.setMobileNumber(req.getParameter("mobileNumber"));
            s.setDateOfBirth(new Date(req.getParameter("dateOfBirth")));
            s.setDateOfJoin(new Date(req.getParameter("dateOfJoin")));
            s.setStatus(Boolean.valueOf(req.getParameter("status")));
            s.setUsername(username);
            s.setCity(new City(Integer.parseInt(req.getParameter("zipCode")), "asdf"));
            s.setAddress(req.getParameter("address"));

            s.add(password, active);            
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
            req.setAttribute("errorMessage", ex.getMessage());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/addStudent.jsp");
            rd.forward(req, resp);
        } catch (IllegalArgumentException iae) {            
            logger.error(iae.getLocalizedMessage());
            req.setAttribute("errorMessage", iae.getLocalizedMessage());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/addStudent.jsp");
            rd.forward(req, resp);
        }
        req.setAttribute("successMessage", "Hozzáadás sikeres!");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/addStudent.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
