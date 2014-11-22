package api.family;

import business.Account;
import java.io.*;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;

@WebServlet("/api/family/accountDetails")
public class accountDetails extends HttpServlet {

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
            Account a = Account.getInstance(Integer.parseInt(req.getParameter("number")));      
            a.findTransactions();

            Gson gson = new Gson();
            JsonObject myObj = new JsonObject();
            
            JsonElement accountObj = gson.toJsonTree(a);
            myObj.add("account", accountObj);

            resp.getWriter().print(myObj.toString());
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }
}
