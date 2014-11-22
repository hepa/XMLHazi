package data;

import java.sql.*;
import org.apache.log4j.Logger;

public class DBUtil {
    private static Logger logger = Logger.getRootLogger();
    
    public static void closeStatement(Statement s) {
        try {
            if (s != null) {
                s.close();
            }
        } catch(SQLException e) {
            logger.error(e.getMessage());
        }
    }
    
    public static void closePreparedStatement(PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch(SQLException e) {
            logger.error(e.getMessage());
        } 
    }
    
    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch(SQLException e) {
            logger.error(e.getMessage());
        } 
    }
}
