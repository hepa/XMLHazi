package filters;

import business.Teacher;
import business.Class;
import business.Student;
import dao.ClassDAO;
import dao.StudentDAO;
import dao.TeacherDAO;
import errors.ClassNotFound;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.log4j.Logger;
import util.Link;

public class DeleteStudentFromClass implements Filter {

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
        
        StudentDAO sdao = new StudentDAO();
        ClassDAO cdao = new ClassDAO();
        try {
            ArrayList<Class> classes = cdao.findAll();
            req.setAttribute("classes", classes);            
            fc.doFilter(req, resp);
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }  
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}
