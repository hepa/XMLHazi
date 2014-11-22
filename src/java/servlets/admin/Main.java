package servlets.admin;

import business.Student;
import business.Teacher;
import dao.StudentDAO;
import dao.TeacherDAO;
import errors.ClassNotFound;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;

public class Main extends HttpServlet {

    private org.apache.log4j.Logger logger;
    private final String className = getClass().getName();

    @Override
    public void init() {
        logger = org.apache.log4j.Logger.getRootLogger();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Student> students;
        ArrayList<Teacher> teachers;
        try {
            StudentDAO sdao = new StudentDAO();

            students = sdao.findAll();
            req.setAttribute("students", students);

            TeacherDAO tdao = new TeacherDAO();
            teachers = tdao.findAll();
            req.setAttribute("teachers", teachers);

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/main.jsp");
            rd.forward(req, resp);
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
