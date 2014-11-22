package filters.student;

import business.Homework;
import business.Item;
import business.Lesson;
import business.Lessondate;
import business.Meeting;
import business.Student;
import enums.Day;
import errors.AccountNotFound;
import errors.ClassNotFound;
import errors.UserNotFound;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
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
        Student s = (Student) session.getAttribute("student");

        try {
            // Homeworks
            s.findHomeworks();
            ArrayList<Homework> hws = new ArrayList<>();
            for (Homework hw : s.getHomeworks()) {
                if (!hw.isDone()) {
                    if (hw.getContent().length() > 20) {
                        hw.setContent(hw.getContent().substring(1, 16) + "...");
                    }
                    hws.add(hw);
                }
            }

            req.setAttribute("hws", hws);

            // Lessons
            String today = Day.getEnum(new Date().getDay());

            ArrayList<Lesson> lessons = Lesson.getAllInstances(s.getForm());
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
            int day = new Date().getDay();            
            int tday;
            if (day == 0) {
                tday = 1;
            } else {
                tday = day + 1;
            }
                                    
            String tomorrow = Day.getEnum(tday);            
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

            // Items
            ArrayList<Item> items = Item.getAllInstances(s);

            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).isDone()) {
                    items.remove(i);
                    i--;
                }
            }

            req.setAttribute("items", items);
            
            // Meetings
            try {                
                s.findClass();
            } catch (SQLException | ClassNotFound | UserNotFound | AccountNotFound ex) {
                logger.error(ex.getMessage());
            }
            ArrayList<Meeting> meetings = Meeting.getAllInstances(s.getForm());
            for (int i=0; i<meetings.size(); i++) {
                if (meetings.get(i).getTo().before(new Date())) {
                    meetings.remove(i);
                    i--;
                }
            }
            req.setAttribute("meetings", meetings);
            
        } catch (SQLException | ClassNotFound | UserNotFound ex) {
            logger.error(ex.getMessage());
        }

        fc.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}
