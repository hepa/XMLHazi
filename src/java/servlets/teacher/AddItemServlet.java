package servlets.teacher;

import business.Account;
import business.Item;
import business.Itemtype;
import business.Class;
import business.Message;
import business.Person;
import business.Student;
import errors.UserNotFound;

import java.io.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/teacher/addItem")
public class AddItemServlet extends HttpServlet {

    private org.apache.log4j.Logger logger;
    private final String className = getClass().getName();

    @Override
    public void init() {
        logger = org.apache.log4j.Logger.getRootLogger();
        logger.trace(className + " is initializing.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Item i = new Item();
        Account a = new Account();
        a.setNumber(Integer.parseInt(req.getParameter("number")));

        try {
            Itemtype it = Itemtype.getInstance(Integer.parseInt(req.getParameter("type")));

            i.setItemtype(it);
            i.setAccount(a);
            i.setAmount(Long.parseLong(req.getParameter("amount")));
            i.add();

            ArrayList<Class> classes = Class.getAllInstances();
            for (Class c : classes) {
                if (c.getAccount().equals(a)) {
                    c.findStudents();
                    for (Student s : c.getStudents()) {
                        s.findSettings();
                        if (s.getSettings().isEnabledMessageAboutItem()) {
                            new Message(
                                    "Új befizetési költséged keletkezett.",
                                    "Az alábbi összeggel '<b>" + i.getAmount() + " Ft</b>', új befizetési kötelezettséged keletkezett az alábbi címmel: <b>" + i.getItemtype().getName() + "</b>",
                                    s, new Person("Rendszerüzenet")).send();
                        }
                        s.findFamily();
                        s.getFamily().findSettings();

                        if (s.getFamily().getSettings().isEnabledMessageAboutHomeworkGrade()) {
                            new Message(
                                    "Új befizetési költséged keletkezett.",
                                    "Az alábbi összeggel '<b>" + i.getAmount() + " Ft</b>', új befizetési kötelezettséged keletkezett az alábbi címmel: <b>" + i.getItemtype().getName() + "</b>",
                                    new Person(s.getFamily().getUsername()), new Person("Rendszerüzenet")).send();
                        }
                    }
                    break;
                }
            }
        } catch (SQLException | UserNotFound ex) {
            logger.error(ex.getMessage());
        }

        resp.sendRedirect("/Neptun/teacher/accountManagement.jsp?number=" + req.getParameter("number") + "&classId=" + req.getParameter("classId"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(403);
    }
}
