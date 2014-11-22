package servlets.teacher;

import business.Message;
import business.Person;
import business.Class;
import business.Student;
import business.Teacher;
import dao.ClassDAO;
import dao.StudentDAO;
import dao.TeacherDAO;
import errors.UserNotFound;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.apache.log4j.Logger;
import util.Link;

@WebServlet("/teacher/sendMessage")
public class SendMessageServlet extends HttpServlet {

    Logger logger;
    private final String className = getClass().getName();

    @Override
    public void init() {
        logger = Logger.getRootLogger();
        logger.trace(className + " is initializing.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        Teacher t = (Teacher) session.getAttribute("teacher");

        ArrayList<Person> tos = new ArrayList<>();
        String[] usernames = req.getParameterValues("to");
        for (String username : usernames) {
            Person p = new Person();
            p.setUsername(username);
            tos.add(p);
        }

        Message m = new Message();
        m.setSubject(req.getParameter("subject"));
        m.setBody(req.getParameter("body"));
        m.setTo(tos);
        m.setFrom(t);
        try {
            m.send();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }

        resp.sendRedirect("/Neptun/teacher/main.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
