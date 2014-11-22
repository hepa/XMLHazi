package filters.student;

import business.Attendance;
import business.Lesson;
import business.Grade;
import business.Homework;
import business.Student;
import errors.ClassNotFound;
import errors.UserNotFound;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

@WebFilter("/student/lesson.jsp")
public class LessonDetails implements Filter {

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

        Student s = (Student)request.getSession().getAttribute("student");
        
        String all = request.getParameter("all");

        if (all == null) {
            try {
                // Lesson details
                Lesson l = Lesson.getInstance(Integer.parseInt(req.getParameter("id")));
                req.setAttribute("lesson", l);                
                
                // Grades
                ArrayList<Grade> grades = Grade.getAllInstances(s, l);
                req.setAttribute("grades", grades);
                
                // Homeworks
                ArrayList<Homework> homeworks = Homework.getAllInstances(s, l);
                req.setAttribute("hws", homeworks);
                
                // Attendances
                ArrayList<Attendance> attendances = Attendance.getAllInstances(s, l);
                req.setAttribute("attendances", attendances);
            } catch (SQLException | UserNotFound | ClassNotFound ex) {
                logger.error(ex.getMessage());
            }
        } else {
            
        }

        fc.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}
