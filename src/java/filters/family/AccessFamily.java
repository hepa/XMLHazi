package filters.family;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.log4j.Logger;

public class AccessFamily implements Filter {

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
        
        if (request.getSession().getAttribute("family") != null) {
            fc.doFilter(req, resp);
        } else {
            logger.warn("Access deined on " + request.getRequestURL());
            response.sendRedirect("/Neptun/login.jsp");      
        }
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}
