package servlets.teacher;

import business.Homework;
import business.Lesson;
import business.Message;
import business.Person;
import business.Student;
import dao.HomeworkDAO;
import dao.LessonDAO;
import errors.ClassNotFound;
import errors.UserNotFound;
import java.io.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;

public class AddHomeworkServlet extends HttpServlet {

    private org.apache.log4j.Logger logger;
    private final String className = getClass().getName();

    @Override
    public void init() {
        logger = org.apache.log4j.Logger.getRootLogger();
        logger.trace(className + " is initializing.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        try {
            int lessonId = Integer.parseInt(req.getParameter("id"));
            Lesson l = Lesson.getInstance(lessonId);
            Homework hw = new Homework();
            hw.setContent(req.getParameter("content"));
            hw.setLesson(l);
            hw.add();

            l.findClass();
            l.getForm().findStudents();

            for (Student s : l.getForm().getStudents()) {
                s.findSettings();
                if (s.getSettings().isEnabledMessageAboutHomework()) {
                    new Message(
                            "Új házi feladat kiírása történt az alábbi tárgyra: " + l.getSubject().getName(),
                            "Az alábbi tárgyra '<b>" + l.getSubject().getName() + "</b>', új házi feladat került kiírásra. <br /><br />Módosítva: " + new SimpleDateFormat("yyyy.MM.dd HH:mm").format(new Date()),
                            s, new Person("Rendszerüzenet")).send();
                }

                s.findFamily();
                s.getFamily().findSettings();

                if (s.getFamily().getSettings().isEnabledMessageAboutHomeworkGrade()) {
                    new Message(
                            "Új házi feladat kiírása történt az alábbi tárgyra: " + l.getSubject().getName(),
                            "Az alábbi tárgyra '<b>" + l.getSubject().getName() + "</b>', új házi feladat került kiírásra. <br /><br />Módosítva: " + new SimpleDateFormat("yyyy.MM.dd HH:mm").format(new Date()),
                            new Person(s.getFamily().getUsername()), new Person("Rendszerüzenet")).send();
                    
                }
            }

            resp.sendRedirect("/Neptun/teacher/homeworkAdd.jsp?id=" + lessonId);
        } catch (SQLException | UserNotFound | ClassNotFound ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(403);
    }
}
