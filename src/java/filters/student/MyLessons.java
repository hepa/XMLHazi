package filters.student;

import business.Grade;
import business.Lesson;
import business.Student;
import errors.ClassNotFound;
import errors.UserNotFound;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import org.apache.log4j.Logger;

@WebFilter("/student/myLessons.jsp")
public class MyLessons implements Filter {

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

        HttpSession session = request.getSession();
        Student s = (Student) session.getAttribute("student");
        
        try {                                    
            ArrayList<Lesson> lessons = Lesson.getAllInstances(s.getForm());
            req.setAttribute("lessons", lessons);
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
