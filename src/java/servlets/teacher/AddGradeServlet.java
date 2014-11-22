package servlets.teacher;

import business.Examtype;
import business.Grade;
import business.Lesson;
import business.Mark;
import business.Message;
import business.Person;
import business.Student;
import business.Teacher;
import errors.ClassNotFound;
import errors.UserNotFound;

import java.io.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;

public class AddGradeServlet extends HttpServlet {

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
            String lId = req.getParameter("lessonId");
            String studentId = req.getParameter("studentId");

            HttpSession session = req.getSession();
            Teacher t = (Teacher) session.getAttribute("teacher");

            Student s = Student.getInstance(studentId);
            s.findSettings();

            Grade g = new Grade();
            g.setStudent(s);

            if (lId != null && !lId.isEmpty()) {
                int lessonId = Integer.parseInt(lId);

                Lesson l = Lesson.getInstance(lessonId);
                Examtype examtype = Examtype.getInstance(Integer.parseInt(req.getParameter("examType")));
                Mark m = Mark.getInstance(Integer.parseInt(req.getParameter("grade")));

                g.setExamtype(examtype);
                g.setLesson(l);
                g.setMark(m);

                g.add(t);

                if (s.getSettings().isEnabledMessageAboutGrade()) {
                    new Message(
                            "Új jegybeírás történt az alábbi tárgyra: " + l.getSubject().getName(),
                            "Az alábbi tárgyra '<b>" + l.getSubject().getName() + "</b>', az alábbi típusú '<b>" + examtype.getName() + "</b>' <b>" + m.getDescription() + " (" + m.getId() + ")</b> eredmény lett beírva. <br /><br />Módosítva: " + new SimpleDateFormat("yyyy.MM.dd HH:mm").format(new Date()),
                            s, new Person("Rendszerüzenet")).send();
                }
                
                s.findFamily();                
                s.getFamily().findSettings();                         
                
                if (s.getFamily().getSettings().isEnabledMessageAboutGrade()) {                    
                    new Message(
                            "Új jegybeírás történt az alábbi tárgyra: " + l.getSubject().getName(),
                            "Az alábbi tárgyra '<b>" + l.getSubject().getName() + "</b>', az alábbi típusú '<b>" + examtype.getName() + "</b>' <b>" + m.getDescription() + " (" + m.getId() + ")</b> eredmény lett beírva. <br /><br />Módosítva: " + new SimpleDateFormat("yyyy.MM.dd HH:mm").format(new Date()),
                            new Person(s.getFamily().getUsername()), new Person("Rendszerüzenet")).send();
                }
                
                if (s.getSettings().isEnabledEmailAboutGrade()) {
                    // E-mail
                }
                
                resp.sendRedirect("/Neptun/teacher/grade.jsp?lessonId=" + l.getId() + "&studentId=" + s.getStudentIdNumber() + "&successMessage=Jegy sikeresen hozzáadva.");
            } else {
                int lessonId = Integer.parseInt(req.getParameter("lesson"));

                Lesson l = Lesson.getInstance(lessonId);
                Examtype examtype = Examtype.getInstance(Integer.parseInt(req.getParameter("examType")));
                Mark m = Mark.getInstance(Integer.parseInt(req.getParameter("grade")));

                g.setExamtype(examtype);
                g.setLesson(l);
                g.setMark(m);

                g.add(t);

                if (s.getSettings().isEnabledMessageAboutGrade()) {
                    new Message(
                            "Új jegybeírás történt az alábbi tárgyra: " + l.getSubject().getName(),
                            "Az alábbi tárgyra '<b>" + l.getSubject().getName() + "</b>', az alábbi típusú '<b>" + examtype.getName() + "</b>' <b>" + m.getDescription() + " (" + m.getId() + ")</b> eredmény lett beírva. <br /><br />Módosítva: " + new SimpleDateFormat("yyyy.MM.dd HH:mm").format(new Date()),
                            s, new Person("Rendszerüzenet")).send();
                }
                
                if (s.getSettings().isEnabledEmailAboutGrade()) {
                    //E-mail
                }
                
                s.findFamily();                
                s.getFamily().findSettings();                              
                
                if (s.getFamily().getSettings().isEnabledMessageAboutGrade()) {                    
                    new Message(
                            "Új jegybeírás történt az alábbi tárgyra: " + l.getSubject().getName(),
                            "Az alábbi tárgyra '<b>" + l.getSubject().getName() + "</b>', az alábbi típusú '<b>" + examtype.getName() + "</b>' <b>" + m.getDescription() + " (" + m.getId() + ")</b> eredmény lett beírva. <br /><br />Módosítva: " + new SimpleDateFormat("yyyy.MM.dd HH:mm").format(new Date()),
                            new Person(s.getFamily().getUsername()), new Person("Rendszerüzenet")).send();
                }

                resp.sendRedirect("/Neptun/teacher/grade.jsp?studentId=" + s.getStudentIdNumber() + "&successMessage=Jegy sikeresen hozzáadva.");
            }

        } catch (ClassNotFound | UserNotFound | SQLException ex) {
            logger.error(ex.getMessage());
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(403);
    }
}
