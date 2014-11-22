package servlets.teacher;

import business.Class;
import business.Meeting;
import business.MeetingType;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;

public class AddMeetingServlet extends HttpServlet {

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

        try {
            HttpSession session = req.getSession();
            Class c = (Class) session.getAttribute("my_class");
            Date from = new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(req.getParameter("from"));
            Date to = new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(req.getParameter("to"));
            String comment = req.getParameter("comment");

            Gson gson = new Gson();
            JsonObject myObj = new JsonObject();

            try {
                MeetingType mtype = MeetingType.getInstance(Integer.parseInt(req.getParameter("type")));
                Meeting m = new Meeting(new Timestamp(from.getTime()), new Timestamp(to.getTime()), mtype, comment);
                m.add(c);

                JsonElement meetingObj = gson.toJsonTree(m);
                myObj.add("meeting", meetingObj);

                resp.getWriter().print(myObj.toString());

                //resp.sendRedirect("/Neptun/teacher/addMeeting.jsp?successMessage=Sikeresen hozzáadva.");                
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
                if (ex.getErrorCode() == 1062) {
                    JsonElement meetingObj = gson.toJsonTree("true");
                    myObj.add("error", meetingObj);

                    resp.getWriter().print(myObj.toString());
                }
            }
        } catch (ParseException ex) {
            logger.error(ex.getMessage());
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(403);
    }
}
