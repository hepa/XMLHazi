package servlets.admin;

import business.Student;
import business.Class;
import dao.ClassDAO;
import util.Link;
import dao.StudentDAO;
import dao.StudentInClassDAO;
import errors.ClassNotFound;
import errors.UserNotFound;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.log4j.Logger;


public class AddStudentToClassServlet extends HttpServlet {

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

        String cName = req.getParameter("class");
        String[] studentsId = req.getParameterValues("students");
        ArrayList<Student> studentsList = new ArrayList();                               
        
        for (String id : studentsId) {            
            try {
                studentsList.add(Student.getInstance(id));
            } catch (SQLException ex) {                            
                logger.error(ex.getMessage());
            }
        }
        
        ClassDAO cdao = new ClassDAO();
        StudentInClassDAO sicdao = new StudentInClassDAO();                
        try {
            Class c = Class.getInstance(Integer.parseInt(cName));
            sicdao.addStudentToClass(studentsList, c);
            
            req.setAttribute("successMessage", "Hozzáadás sikeres!");
            Link.forward(this, "/admin/addStudentToClass.jsp", req, resp);
        } catch (SQLException | UserNotFound ex) {
            logger.error(ex.getMessage());
        }
                
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {        
    }
}
