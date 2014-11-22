package filters.teacher;

import business.Student;
import business.Class;
import business.Person;
import dao.ClassDAO;
import dao.StudentDAO;
import errors.UserNotFound;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class Message implements Filter {

    private FilterConfig filterConfig;
    private Logger logger;

    @Override
    public void init(FilterConfig fc) throws ServletException {
        filterConfig = fc;
        logger = Logger.getRootLogger();
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String studentId = req.getParameter("studentId");
        String classId = req.getParameter("classId");

        if (classId != null) {
            Class c = new Class();
            c.setId(Integer.parseInt(classId));
            try {
                c.findStudents();
                req.setAttribute("students", c.getStudents());
            } catch (SQLException | UserNotFound ex) {
                logger.error(ex.getMessage());
            }
        }
        if (studentId != null) {
            try {
                Student s = Student.getInstance(studentId);
                req.setAttribute("s", s);
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }
        }

        ArrayList<Person> persons;
        try {
            persons = Person.getAllNames();
            req.setAttribute("persons", persons);
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }

        fc.doFilter(req, resp);

    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}
