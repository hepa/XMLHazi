package servlets.family;

import servlets.teacher.*;
import business.Account;

import java.io.*;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/family/accountWithdraw")
public class AccountWithdraw extends HttpServlet {

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
        
        int number = Integer.parseInt(req.getParameter("number"));
        int amount = Integer.parseInt(req.getParameter("withdraw"));
                        
        try {
            Account a = Account.getInstance(number);
            a.withdraw(amount);
            
            resp.getWriter().print(a.getBalance() - amount);
            //resp.sendRedirect("accountManagement.jsp?number=" + number);            
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
