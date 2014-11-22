package servlets.student;

import business.Message;
import java.io.*;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.apache.log4j.Logger;

@WebServlet("/student/deleteMessage")
public class DeleteMessageServlet extends HttpServlet {

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

        int id = Integer.parseInt(req.getParameter("id"));
        try {
            Message m = Message.getInstance(id);
            m.delete();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
