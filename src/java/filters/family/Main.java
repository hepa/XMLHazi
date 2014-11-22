package filters.family;

import business.ConsultingHour;
import business.Family;
import business.Grade;
import business.Homework;
import business.Item;
import business.Meeting;
import errors.AccountNotFound;
import errors.ClassNotFound;
import errors.UserNotFound;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import org.apache.log4j.Logger;

@WebFilter("/family/main.jsp")
public class Main implements Filter {

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
        
        Family f = (Family)request.getSession().getAttribute("family");
        
        try {
            // Items
            ArrayList<Item> items = Item.getAllInstances(f.getStudent());

            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).isDone()) {
                    items.remove(i);
                    i--;
                }
            }

            req.setAttribute("items", items);
            
            // Homeworks
            f.getStudent().findHomeworks();
            ArrayList<Homework> hws = new ArrayList<>();
            for (Homework hw : f.getStudent().getHomeworks()) {
                if (!hw.isDone()) {
                    if (hw.getContent().length() > 20) {
                        hw.setContent(hw.getContent().substring(1, 16) + "...");
                    }
                    hws.add(hw);
                }
            }

            req.setAttribute("hws", hws);
            
            // Last 10 grades
            ArrayList<Grade> grades = Grade.getAllInstances(f.getStudent());
            
            Collections.sort(grades);
            
            req.setAttribute("grades", grades.subList(0, 9));
            
            // Meetings
            try {                
                f.getStudent().findClass();
            } catch (SQLException | ClassNotFound | UserNotFound | AccountNotFound ex) {
                logger.error(ex.getMessage());
            }
            ArrayList<Meeting> meetings = Meeting.getAllInstances(f.getStudent().getForm());
            for (int i=0; i<meetings.size(); i++) {
                if (meetings.get(i).getTo().before(new Date())) {
                    meetings.remove(i);
                    i--;
                }
            }
            req.setAttribute("meetings", meetings);
            
            // Consults 
            ArrayList<ConsultingHour> consults = ConsultingHour.getAllInstances(f.getStudent().getForm().getFormMaster());
            req.setAttribute("consults", consults);            
            
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
