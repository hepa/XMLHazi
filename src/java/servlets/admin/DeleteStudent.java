package servlets.admin;

import dao.StudentDAO;

import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.*;
import javax.servlet.http.*;

public class DeleteStudent extends HttpServlet{
    
    private org.apache.log4j.Logger logger;
    private final String className = getClass().getName();

    @Override
    public void init() {
        logger = org.apache.log4j.Logger.getRootLogger();
        logger.trace(className + " is initializing.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {        
        try {
            StudentDAO sdao = new StudentDAO();
            HashMap<String, String> hMap = new HashMap<>();
            hMap.put("id", req.getParameter("id"));
            sdao.remove(hMap);            
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        req.setAttribute("successMessage", "Sikeresen törölve.");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/main");
        rd.forward(req, resp);        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {        
    }
    
    
}
