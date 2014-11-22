package servlets.family;

import business.Family;
import business.Message;
import business.Person;
import business.Teacher;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.apache.log4j.Logger;

@WebServlet("/family/sendMessage")
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
        Family f = (Family) session.getAttribute("family");

        ArrayList<Person> tos = new ArrayList<>();
        String[] usernames = req.getParameterValues("to[]");
                

        for (String username : usernames) {
            Person p = new Person();
            logger.debug(username);
            p.setUsername(username);
            tos.add(p);
        }

        Message m = new Message();
        m.setSubject(req.getParameter("subject"));
        m.setBody(req.getParameter("body"));
        m.setTo(tos);
        m.setFrom(new Person(f.getUsername(), f.getFather().getFirstName(), f.getFather().getLastName()));
        try {
            m.send();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }

        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
