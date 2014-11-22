package filters;

import business.Teacher;
import business.Class;
import dao.ClassDAO;
import dao.TeacherDAO;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.log4j.Logger;
import util.Link;

public class AddClass implements Filter {

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
        TeacherDAO tdao = new TeacherDAO();
        try {
            ArrayList<Teacher> teachers = tdao.findAll();
            ClassDAO cdao = new ClassDAO();
            ArrayList<Class> classes = cdao.findAll();
            
            for (Class c : classes) {
                teachers.remove(c.getFormMaster());
            }

            req.setAttribute("teachers", teachers);

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
