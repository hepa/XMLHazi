package filters.teacher;

import business.Grade;
import business.Lesson;
import business.Student;
import business.Teacher;
import dao.GradeDAO;
import errors.ClassNotFound;
import errors.UserNotFound;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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

public class StudentDetails implements Filter {

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
            
            HttpSession session = request.getSession();
            Teacher t = (Teacher)session.getAttribute("teacher");
            
            Student s = Student.getInstance(req.getParameter("id"));
            
            ArrayList<Lesson> lessons = Lesson.getAllInstances(t);

            ArrayList<Grade> common = new ArrayList<>();
            ArrayList<Grade> grades = new GradeDAO().findAll(s);
            for (Grade g : grades) {
                if (lessons.contains(g.getLesson())) {
                    common.add(g);
                }
            }
            
            req.setAttribute("grades", common);
            req.setAttribute("s", s);
            
            //Last login date
            Date lastLogin = new login.Login(s.getUsername(), null).getLoginTime();
            req.setAttribute("lastLogin", lastLogin);

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
