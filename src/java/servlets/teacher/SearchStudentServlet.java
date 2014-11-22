package servlets.teacher;

import business.Student;
import business.Class;
import dao.StudentDAO;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import util.Link;

@WebServlet("/teacher/searchStudent")
public class SearchStudentServlet extends HttpServlet {

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

        String status = req.getParameter("status");
        String dob = req.getParameter("dob");
        String doj = req.getParameter("doj");
        String[] sdob = dob.split("/");
        String[] sdoj = doj.split("/");


        HashMap<String, String> hMap = new HashMap<>();
        hMap.put("first_name", req.getParameter("firstName"));
        hMap.put("last_name", req.getParameter("lastName"));
        hMap.put("gender", req.getParameter("gender"));
        hMap.put("zip_code", req.getParameter("zipCode"));
        if (!dob.isEmpty()) {
            hMap.put("date_of_birth", sdob[2] + "-" + sdob[0] + "-" + sdob[1]);
        }
        if (!doj.isEmpty()) {
            hMap.put("date_of_join", sdoj[2] + "-" + sdoj[0] + "-" + sdoj[1]);
        }
        hMap.put("email_address", req.getParameter("email"));
        hMap.put("class_id", req.getParameter("class"));
        if (!status.isEmpty()) {
            if (status.equals("false")) {
                hMap.put("status", String.valueOf(0));
            } else {
                hMap.put("status", String.valueOf(1));
            }
        }

        try {
            ArrayList<Student> students = new StudentDAO().search(hMap);           

            req.setAttribute("result", students);       
            req.setAttribute("classes", Class.getAllClassNames());
            Link.forward(this, "/teacher/searchStudent.jsp", req, resp);
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(403);
    }
        
}
