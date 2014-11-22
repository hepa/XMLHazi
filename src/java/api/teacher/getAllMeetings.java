package api.teacher;

import business.Meeting;
import business.Class;
import errors.UserNotFound;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;

@WebServlet("/api/teacher/getAllMeetings")
public class getAllMeetings extends HttpServlet {

    private org.apache.log4j.Logger logger;
    private final String className = getClass().getName();

    @Override
    public void init() {
        logger = org.apache.log4j.Logger.getRootLogger();
        logger.trace(className + " is initializing.");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        try {
            Class c = Class.getInstance(Integer.parseInt(req.getParameter("classId")));
            ArrayList<Meeting> meetings = Meeting.getAllInstances(c);

            Gson gson = new Gson();
            JsonObject myObj = new JsonObject();
            
            JsonElement meetingsObj = gson.toJsonTree(meetings);
            myObj.add("meetings", meetingsObj);

            resp.getWriter().print(myObj.toString());
        } catch (SQLException | UserNotFound ex) {
            logger.error(ex.getMessage());
        }
    }
}
