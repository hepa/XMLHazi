package filters.teacher;

import business.Meeting;
import business.MeetingType;
import business.Class;
import dao.MeetingDAO;
import dao.MeetingTypeDAO;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.log4j.Logger;

public class AddMeeting implements Filter {

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
        Class c = (Class)session.getAttribute("my_class");
        try {
            ArrayList<MeetingType> types = MeetingType.getAllInstances();
            ArrayList<Meeting> meetings = Meeting.getAllInstances(c);
            
            logger.debug(meetings.size());
            
            req.setAttribute("tipusok", types);
            req.setAttribute("prev", meetings);
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
