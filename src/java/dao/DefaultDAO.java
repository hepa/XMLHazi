package dao;

import data.ConnectionPool;
import data.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

public class DefaultDAO {
    protected static Logger logger = Logger.getRootLogger();
    protected ConnectionPool pool;
    protected Connection c;
    protected PreparedStatement ps;
    protected ResultSet rs;

    public DefaultDAO() {
        pool = ConnectionPool.getInstance();
        c = pool.getConnection();
        ps = null;
        rs = null;
    }
    
    protected void closeStatementAndSet() {
        DBUtil.closeResultSet(rs);
        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(c);
    }
    
}
