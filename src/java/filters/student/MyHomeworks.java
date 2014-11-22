package filters.student;

import business.Grade;
import business.Homework;
import business.Student;
import errors.ClassNotFound;
import errors.UserNotFound;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import org.apache.log4j.Logger;

@WebFilter("/student/myHomeworks.jsp")
public class MyHomeworks implements Filter {

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
            ArrayList<Homework> hws = Homework.getAllInstances(s);

            Collections.sort(hws, new Comparator<Homework>() {
                @Override
                public int compare(Homework t, Homework t1) {
                    return t1.getCreated().compareTo(t.getCreated());
                }
            });

            req.setAttribute("hws", hws);
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
