package filters.teacher;

import business.Lesson;
import business.Class;
import business.Teacher;
import dao.ClassDAO;
import dao.LessonDAO;
import errors.ClassNotFound;
import errors.UserNotFound;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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

        Teacher t = (Teacher)request.getSession().getAttribute("teacher");
        
        String all = request.getParameter("all");

        if (all == null) {
            try {
                Lesson l = Lesson.getInstance(Integer.parseInt(req.getParameter("id")));
                req.setAttribute("lesson", l);

                Class c = l.getForm();
                c.findStudents();

                req.setAttribute("students", c.getStudents());
                req.setAttribute("date", new Date());
            } catch (SQLException | UserNotFound | ClassNotFound ex) {
                logger.error(ex.getMessage());
            }
        } else {
            try {
                ArrayList<Lesson> lessons = Lesson.getAllInstances(t);
                
                req.setAttribute("lessons", lessons);
            } catch (SQLException | UserNotFound | ClassNotFound ex) {
                logger.error(ex.getMessage());
            }
        }

        fc.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}
