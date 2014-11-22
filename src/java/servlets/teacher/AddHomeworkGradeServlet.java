package servlets.teacher;

import business.Homework;
import business.Mark;
import business.Message;
import business.Person;
import business.Student;
import dao.HomeworkDAO;
import dao.StudentDAO;
import errors.ClassNotFound;
import errors.UserNotFound;
import java.io.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.*;
import javax.servlet.http.*;

public class AddHomeworkGradeServlet extends HttpServlet {

    private org.apache.log4j.Logger logger;
    private final String className = getClass().getName();

    @Override
    public void init() {
        logger = org.apache.log4j.Logger.getRootLogger();
        logger.trace(className + " is initializing.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");

            String[] studentIds = req.getParameterValues("studentId");
            String[] grades = req.getParameterValues("grade");

            Homework hw = Homework.getInstance(Integer.parseInt(req.getParameter("homeworkId")));

            for (int i = 0; i < studentIds.length; i++) {
                if (!grades[i].isEmpty()) {
                    Student s = Student.getInstance(studentIds[i]);
                    hw.addGrade(s, Integer.parseInt(grades[i]));
                    hw.setMark(Mark.getInstance(Integer.parseInt(grades[i])));

                    s.findSettings();
                    if (s.getSettings().isEnabledMessageAboutHomeworkGrade()) {
                        new Message(
                                "Új házi feladat jegybeírás történt az alábbi tárgyra: " + hw.getLesson().getSubject().getName(),
                                "Az alábbi tárgy '<b>" + hw.getLesson().getSubject().getName() + "</b>' házi feladatára, <b>" + hw.getMark().getDescription() + " (" + hw.getMark().getId() + ")</b> eredmény lett beírva. <br /><br />Módosítva: " + new SimpleDateFormat("yyyy.MM.dd HH:mm").format(new Date()),
                                s, new Person("Rendszerüzenet")).send();
                    }

                    s.findFamily();
                    s.getFamily().findSettings();
                    
                    if (s.getFamily().getSettings().isEnabledMessageAboutHomeworkGrade()) {
                        new Message(
                                "Új házi feladat jegybeírás történt az alábbi tárgyra: " + hw.getLesson().getSubject().getName(),
                                "Az alábbi tárgy '<b>" + hw.getLesson().getSubject().getName() + "</b>' házi feladatára, <b>" + hw.getMark().getDescription() + " (" + hw.getMark().getId() + ")</b> eredmény lett beírva. <br /><br />Módosítva: " + new SimpleDateFormat("yyyy.MM.dd HH:mm").format(new Date()),
                                new Person(s.getFamily().getUsername()), new Person("Rendszerüzenet")).send();
                    }    
                }
            }

            resp.sendRedirect("/Neptun/teacher/homeworkDetails.jsp?id=" + req.getParameter("homeworkId"));
        } catch (SQLException | UserNotFound | ClassNotFound ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(403);
    }
}
