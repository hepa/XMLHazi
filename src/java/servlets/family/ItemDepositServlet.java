package servlets.family;

import business.Account;
import business.Family;
import business.Homework;
import business.Item;
import business.Student;
import java.io.*;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.apache.log4j.Logger;

@WebServlet("/family/itemDeposit")
public class ItemDepositServlet extends HttpServlet {

    Logger logger;
    private final String className = getClass().getName();

    @Override
    public void init() {
        logger = Logger.getRootLogger();
        logger.trace(className + " is initializing.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            Family f = (Family) session.getAttribute("family");
            f.setAccount(Account.getInstance(f.getAccount().getNumber()));

            Item i = Item.getInstance(Integer.parseInt(req.getParameter("id")));

            if (i.getAmount() > f.getAccount().getBalance()) {
                resp.getWriter().print("error");
            } else {
                i.deposit(f);
            }

        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
