package filters.teacher;

import business.Class;
import business.Lesson;
import business.Student;
import business.Teacher;
import dao.ClassDAO;
import dao.LessonDAO;
import dao.StudentDAO;
import dao.TeacherDAO;
import errors.ClassNotFound;
import errors.UserNotFound;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class ClassDetails implements Filter {

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

        String classID = req.getParameter("id");

        try {
            if (classID != null) {
                HttpSession session = request.getSession();
                Teacher t = (Teacher) session.getAttribute("teacher");                
                Class c = Class.getInstance(Integer.parseInt(classID));
                c.findStudents();
                req.setAttribute("c", c);

                ArrayList<Lesson> lessons = Lesson.getAllInstances(t);
                req.setAttribute("lessons", lessons);
            } else {
                ArrayList<Class> classes = new ClassDAO().findAll();
                req.setAttribute("classes", classes);
                ArrayList<Student> students = new StudentDAO().findAll();
                req.setAttribute("students", students);
            }
        } catch (SQLException | UserNotFound | ClassNotFound ex) {
            logger.error(ex.getMessage());
        }

        fc.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}
