package filters.teacher;

import business.Examtype;
import business.Lesson;
import business.Student;
import business.Grade;
import business.Teacher;
import dao.ExamtypeDAO;
import dao.GradeDAO;
import dao.LessonDAO;
import dao.StudentDAO;
import errors.AccountNotFound;
import errors.ClassNotFound;
import errors.UserNotFound;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class GradeFilter implements Filter {

    private FilterConfig filterConfig;
    private Logger logger;

    @Override
    public void init(FilterConfig fc) throws ServletException {
        filterConfig = fc;
        logger = Logger.getRootLogger();
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc) throws IOException, ServletException {
        try {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) resp;
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");

            HttpSession session = request.getSession();
            Teacher t = (Teacher) session.getAttribute("teacher");
            
            Student s = Student.getInstance(req.getParameter("studentId"));

            String lessonId = req.getParameter("lessonId");
            if (lessonId != null) {
                Lesson l = Lesson.getInstance(Integer.parseInt(lessonId));

                ArrayList<Lesson> teacherLessons = Lesson.getAllInstances(t);
                if (!teacherLessons.contains(l)) {
                    response.sendRedirect("/Neptun/teacher/main.jsp?errorMessage=Neki nem írhatsz be jegyet!");
                }

                try {                    
                    ArrayList<Grade> grades = Grade.getAllInstances(s, l);
                    req.setAttribute("grades", grades);
                } catch (SQLException ex) {
                    logger.error(ex.getMessage());
                }
            } else {
                ArrayList<Lesson> lessons = Lesson.getAllInstances(t);

                try {
                    s.findClass();
                    ArrayList<Lesson> sl = Lesson.getAllInstances(s.getForm());
                    ArrayList<Lesson> common = new ArrayList<>();
                    
                    for (Lesson l : lessons) {
                        if (sl.contains(l)) {
                            common.add(l);
                        }
                    }
                 
                    req.setAttribute("lessons", common);
                } catch (SQLException | ClassNotFound | UserNotFound | AccountNotFound ex) {
                    logger.error(ex.getMessage());
                }                

                ArrayList<Grade> grades = new GradeDAO().findAll(s);
                req.setAttribute("grades", grades);
            }

            req.setAttribute("student", s);
            ArrayList<Examtype> examtypes = Examtype.getAllInstances();
            req.setAttribute("examtypes", examtypes);

            fc.doFilter(req, resp);
        } catch (SQLException | UserNotFound | ClassNotFound ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}
