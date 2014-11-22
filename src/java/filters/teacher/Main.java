package filters.teacher;

import business.Teacher;
import business.Class;
import business.ConsultingHour;
import business.Lesson;
import business.Lessondate;
import dao.ClassDAO;
import enums.Day;
import errors.ClassNotFound;
import errors.UserNotFound;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.log4j.Logger;

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

        HttpSession session = request.getSession();
        Teacher t = (Teacher) session.getAttribute("teacher");

        HashMap<String, String> hMap = new HashMap<>();
        hMap.put("teacher_id", t.getIdCardNumber());
        ClassDAO cdao = new ClassDAO();
        try {
            Class c = cdao.find(hMap);
            try {
                c.findStudents();                
            } catch (SQLException | UserNotFound ex) {
                logger.error(ex.getMessage());
            }

            req.setAttribute("c", c);
            session.setAttribute("my_class", c);
        } catch (SQLException | ClassNotFound ex) {
            logger.error(ex.getMessage());
        }

        //Fogadóórák
        try {
            ArrayList<ConsultingHour> consults = ConsultingHour.getAllInstances(t);

            if (consults.isEmpty()) {
                req.setAttribute("consults", null);
            } else {
                req.setAttribute("consults", consults);
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        
        
        //Órák
        try {       
            // Today
            String today = Day.getEnum(new Date().getDay());
            
            ArrayList<Lesson> lessons = Lesson.getAllInstances(t);  
            ArrayList<Lesson> todayLessons = new ArrayList<>();
            
            if (today != null) {
                for (Lesson l : lessons) {
                    for (Lessondate ld : l.getDates()) {
                        if (ld.getDay().equals(today)) {
                            todayLessons.add(l);
                            break;
                        }
                    }
                }
            }
            
            req.setAttribute("lessons", todayLessons);
            
             // Lessons for tomorrow
            String tomorrow = Day.getEnum((new Date().getDay()) + 1);
            ArrayList<Lesson> tomorrowLessons = new ArrayList<>();
            
            if (tomorrow != null) {                
                for (Lesson l : lessons) {
                    for (Lessondate ld : l.getDates()) {
                        if (ld.getDay().equals(tomorrow)) {
                            tomorrowLessons.add(l);
                            break;
                        }
                    }
                }
            }

            req.setAttribute("tomorrowLessons", tomorrowLessons);

            req.setAttribute("lessons", todayLessons);
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
