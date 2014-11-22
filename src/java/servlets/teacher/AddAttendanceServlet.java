package servlets.teacher;

import business.Attendance;
import business.Lesson;
import business.Message;
import business.Person;
import business.Status;
import business.Student;
import dao.AttendanceDAO;
import dao.LessonDAO;
import dao.StatusDAO;
import errors.ClassNotFound;
import errors.UserNotFound;

import java.io.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;

public class AddAttendanceServlet extends HttpServlet {

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

        String[] studentIdNumbers = req.getParameterValues("chck");

        if (studentIdNumbers != null) {
            String[] years = req.getParameterValues("year");
            String[] months = req.getParameterValues("month");
            String[] days = req.getParameterValues("day");
            String[] types = req.getParameterValues("type");
            String[] descs = req.getParameterValues("desc");

            int lessonId = Integer.parseInt(req.getParameter("lessonId"));

            ArrayList<Person> tos = new ArrayList<>();
            Attendance a;
            for (int i = 0; i < studentIdNumbers.length; i++) {
                try {
                    a = new Attendance();
                    int year = Integer.parseInt(years[i]) - 1900;
                    int month = Integer.parseInt(months[i]) - 1;
                    int day = Integer.parseInt(days[i]);

                    a.setCreated(new Date(year, month, day));
                    a.setDescription(descs[i]);
                    a.setStatus(Status.getInstance(types[i]));
                    a.setStudent(Student.getInstance(studentIdNumbers[i]));
                    a.setLesson(Lesson.getInstance(lessonId));

                    a.add();

                    Student s = Student.getInstance(studentIdNumbers[i]);
                    s.findSettings();
                    if (s.getSettings().isEnabledMessageAboutAttendance()) {
                        tos.add(s);
                        new Message(
                                "Új beírás történt a katalógusba.",
                                "Az alábbi órára '<b>" + a.getLesson().getSubject().getName() + " </b>', új katalógusbeli beírás történt az alábbi értékkel: <b>" + a.getStatus().getName() + "</b>. <br /><br />Módosítva: " + new SimpleDateFormat("yyyy.MM.dd HH:mm").format(new Date()),
                                s, new Person("Rendszerüzenet")).send();
                    }

                    s.findFamily();                    
                    s.getFamily().findSettings();
                    if (s.getFamily().getSettings().isEnabledMessageAboutAttendance()) {
                        new Message(
                                "Új beírás történt a katalógusba.",
                                "Az alábbi órára '<b>" + a.getLesson().getSubject().getName() + " </b>', új katalógusbeli beírás történt az alábbi értékkel: <b>" + a.getStatus().getName() + "</b>. <br /><br />Módosítva: " + new SimpleDateFormat("yyyy.MM.dd HH:mm").format(new Date()),
                                new Person(s.getFamily().getUsername()), new Person("Rendszerüzenet")).send();
                    }
                } catch (ClassNotFound | UserNotFound | SQLException ex) {
                    logger.error(ex.getMessage());
                }
            }
            
            

            resp.sendRedirect("/Neptun/teacher/attendance.jsp?id=" + lessonId + "&successMessage=Sikeresen hozzáadva.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(403);
    }
}
