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

public class DeleteStudentFromClassServlet extends HttpServlet {

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

        ClassDAO cdao = new ClassDAO();
        HashMap<String, String> hMap = new HashMap<>();
        try {
            ArrayList<Class> classes = cdao.findAll();
            ArrayList<Student> students = new ArrayList<>();
            for (Class c : classes) {
                StudentInClassDAO sicdao = new StudentInClassDAO();
                String[] marked = req.getParameterValues(c.getName());
                if (marked != null) {
                    for (String s : marked) {                                              
                        students.add(Student.getInstance(s));                        
                    }
                }
                sicdao.removeStudentFromClass(students, c);
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
