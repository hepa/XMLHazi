package login;

import business.Family;
import business.Student;
import business.Teacher;
import dao.StudentDAO;
import dao.TeacherDAO;
import data.*;
import errors.AccountNotFound;
import errors.ClassNotFound;
import errors.ProfileInactive;
import errors.PasswordsDontMatch;
import errors.UserNotFound;
import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class LoginServlet extends HttpServlet {

    private Logger logger;
    private final String className = getClass().getName();

    @Override
    public void init() {
        logger = Logger.getRootLogger();
        logger.trace(className + " is initializing.");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("Hozzáférés megtagadva!");
        logger.warn(className + " was requested by the doGet() method.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();

        String source = req.getParameter("source");
        String username = req.getParameter("identifier");
        String password = req.getParameter("password");

        Login login = new Login(username, password);

        switch (source) {
            case "student": {
                try {
                    StudentDAO sdao = new StudentDAO();
                    HashMap<String, String> hmap = new HashMap<>();
                    hmap.put("username", username);
                    Student s = sdao.find(hmap);
                    s.findClass();
                    logger.debug(username + ":" + password);
                    if (login.Authenticate()) {
                        session.setAttribute("student", s);
                        resp.sendRedirect("http://localhost:8080/Neptun/student/main.jsp");
                    }
                } catch (UserNotFound | PasswordsDontMatch | ProfileInactive | ClassNotFound | AccountNotFound ex) {
                    logger.warn(ex.getMessage() + " (" + username + ", " + password + ")");
                    req.setAttribute("errorMessage", ex.getMessage());
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
                    rd.forward(req, resp);
                } catch (SQLException sqle) {
                    logger.error(sqle.getMessage());
                }
                break;
            }
            case "teacher": {
                try {
                    TeacherDAO tdao = new TeacherDAO();
                    HashMap<String, String> hmap = new HashMap<>();
                    hmap.put("username", username);
                    Teacher t = tdao.find(hmap);
                    t.findClass();                                        
                    
                    if (login.Authenticate()) {
                        session.setAttribute("teacher", t);
                        resp.sendRedirect("http://localhost:8080/Neptun/teacher/main.jsp");
                    }
                } catch (UserNotFound | PasswordsDontMatch | ProfileInactive | SQLException ex) {
                    logger.warn(ex.getMessage() + " (" + username + ", " + password + ")");
                    req.setAttribute("errorMessage", ex.getMessage());
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
                    rd.forward(req, resp);
                }
                break;
            }
            case "family": {
                try {
                    Family f = Family.getInstance(username);   
                    logger.debug(username + ":" + password);
                    if (login.Authenticate()) {
                        session.setAttribute("family", f);
                        resp.sendRedirect("http://localhost:8080/Neptun/family/main.jsp");
                    }
                } catch (UserNotFound | PasswordsDontMatch | ProfileInactive | SQLException ex) {
                    logger.error(ex.getMessage());                    
                }
                break;
            }
            default: {
                out.println("Hozzáférés megtagadva!");
            }
        }
    }
}
