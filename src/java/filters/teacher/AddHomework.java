package filters.teacher;

import business.Meeting;
import business.MeetingType;
import business.Class;
import business.Homework;
import business.Lesson;
import dao.HomeworkDAO;
import dao.LessonDAO;
import dao.MeetingDAO;
import dao.MeetingTypeDAO;
import errors.ClassNotFound;
import errors.UserNotFound;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.log4j.Logger;

public class AddHomework implements Filter {

    private FilterConfig filterConfig;
    private Logger logger;

    @Override
    public void init(FilterConfig fc) throws ServletException {
        filterConfig = fc;
        logger = Logger.getRootLogger();
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc) throws IOException, ServletException {
        try {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) resp;

            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");

            int lessonId = Integer.parseInt(req.getParameter("id"));
            Lesson l = Lesson.getInstance(lessonId);
            ArrayList<Homework> hws = new HomeworkDAO().findAll(l);
            req.setAttribute("hws", hws);
            
            fc.doFilter(req, resp);
        } catch (SQLException | UserNotFound | ClassNotFound ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}
