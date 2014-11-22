package filters.student;

import business.ClassRoom;
import business.Lesson;
import business.Lessondate;
import business.Student;
import errors.ClassNotFound;
import errors.UserNotFound;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

@WebFilter("/student/classroom.jsp")
public class ClassroomDetails implements Filter {

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
        
        Student s = (Student)request.getSession().getAttribute("student");

        String className = req.getParameter("name");
        try {
            if (className != null) {
                ClassRoom cr = ClassRoom.getInstance(className);
                req.setAttribute("cr", cr);

                ArrayList<Lesson> lessons = Lesson.getAllInstances(s.getForm());
                ArrayList<Lesson> common = new ArrayList<>();
                for (Lesson l : lessons) {
                    if (l.getClassRoom().equals(cr)) {
                        common.add(l);
                    }
                }
                                                                
                req.setAttribute("lessons", common);
            } else {
                ArrayList<ClassRoom> classrooms = ClassRoom.getAllInstances();
                req.setAttribute("classrooms", classrooms);
            }
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
