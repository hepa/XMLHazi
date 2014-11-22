package filters.student;

import business.Examtype;
import business.Grade;
import business.Lesson;
import business.Student;
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

@WebFilter("/student/myGrades.jsp")
public class MyGrades implements Filter {

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

        String lessonID = req.getParameter("lessonId");
        String examtype = req.getParameter("examtype");

        ArrayList<Lesson> lessons = null;
        ArrayList<Examtype> examtypes = null;
        ArrayList<Grade> grades = null;
        try {
            lessons = Lesson.getAllInstances(s.getForm());
            examtypes = Examtype.getAllInstances();
        } catch (SQLException | UserNotFound | ClassNotFound ex) {
            logger.error(ex.getMessage());
        }       

        if (lessonID == null) {
            try {
                grades = Grade.getAllInstances(s);
                Collections.sort(grades);
                req.setAttribute("grades", grades);
                
                req.setAttribute("lessons", lessons);
                req.setAttribute("examtypes", examtypes);
            } catch (SQLException | UserNotFound | ClassNotFound ex) {
                logger.error(ex.getMessage());
            }
        } else {
            try {
                Lesson l = Lesson.getInstance(Integer.parseInt(lessonID));
                grades = Grade.getAllInstances(s, l);
                req.setAttribute("grades", grades);
                
                req.setAttribute("lessons", lessons);
                req.setAttribute("examtypes", examtypes);
            } catch (SQLException | UserNotFound | ClassNotFound ex) {
                logger.error(ex.getMessage());
            }
        }
        
        if (examtype != null) {
            try {
                Examtype e = Examtype.getInstance(Integer.parseInt(examtype));
                
                if (grades == null) {                    
                    grades = Grade.getAllInstances(s);
                }
                
                for (int i=0; i<grades.size(); i++) {
                    if (!grades.get(i).getExamtype().equals(e)) {
                        grades.remove(i);
                        i--;
                    }
                }
                req.setAttribute("grades", grades);
            } catch (SQLException | UserNotFound | ClassNotFound ex) {
                logger.error(ex.getMessage());
            }
        } 

        fc.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}
