package api.family;

import business.Account;
import business.Family;
import business.Message;
import java.io.*;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import java.util.Collections;

@WebServlet("/api/family/inbox")
public class Inbox extends HttpServlet {

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
        
        Family f = (Family)req.getSession().getAttribute("family");

        try {
            ArrayList<Message> messages = Message.getAllInstances(f.getUsername());
            Collections.sort(messages);

            Gson gson = new Gson();
            JsonObject myObj = new JsonObject();
            
            JsonElement messagesObj = gson.toJsonTree(messages);
            myObj.add("messages", messagesObj);            

            resp.getWriter().print(myObj.toString());
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }
}
