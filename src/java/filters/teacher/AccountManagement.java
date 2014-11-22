package filters.teacher;

import business.Account;
import business.Item;
import business.Itemtype;
import dao.ItemtypeDAO;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import org.apache.log4j.Logger;

@WebFilter("/teacher/accountManagement.jsp")
public class AccountManagement implements Filter {

    private FilterConfig filterConfig;
    private Logger logger;

    @Override
    public void init(FilterConfig fc) throws ServletException {
        filterConfig = fc;
        logger = Logger.getRootLogger();
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
                
        int number = Integer.parseInt(req.getParameter("number"));
        String classID = req.getParameter("classId");
        
        Account a = null;
        try {
            a = Account.getInstance(number);
            a.findTransactions();
            req.setAttribute("account", a);                                    
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        
        if (classID != null) {            
            try {
                ArrayList<Itemtype> types = Itemtype.getAllInstances();
                req.setAttribute("types", types);
                
                ArrayList<Item> items = Item.getAllInstances(a);
                req.setAttribute("items", items);
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }            
        }
        
        fc.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}
