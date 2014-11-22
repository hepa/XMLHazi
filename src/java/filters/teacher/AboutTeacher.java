package filters.teacher;

import business.Teacher;
import java.io.*;
import java.sql.SQLException;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import org.apache.log4j.Logger;

@WebFilter("/teacher/about.jsp")
public class AboutTeacher implements Filter {

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
        Teacher t = (Teacher) session.getAttribute("teacher");
        
        try {
            t = Teacher.getInstance(t.getIdCardNumber());
            req.setAttribute("my", t);
            
            Date lastLogin = new login.Login(t.getUsername(), null).getLoginTime();
            req.setAttribute("lastLogin", lastLogin);
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }

        fc.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}
