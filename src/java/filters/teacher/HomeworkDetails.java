package filters.teacher;

import business.Homework;
import business.Student;
import dao.HomeworkDAO;
import dao.StudentDAO;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class HomeworkDetails implements Filter {

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
        
        int _homeworkId = Integer.parseInt(req.getParameter("id"));
        
        try {
            Homework hw = new HomeworkDAO().find(_homeworkId);                     
            req.setAttribute("hw", hw);
            
            ArrayList<Student> students = new HomeworkDAO(hw).findStudents();
            for (Student s : students) {
                s.findHomeworks(_homeworkId);
            }
            req.setAttribute("students", students);
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
