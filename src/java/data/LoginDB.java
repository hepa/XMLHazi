package data;

import business.Student;
import business.Teacher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import util.MD5;

public class LoginDB {
    public static int addLogin(String username, String password, boolean active) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection c = pool.getConnection();
        PreparedStatement ps = null;        

        try {
            ps = c.prepareStatement("INSERT INTO `login` (`username`, `password`, `active`) VALUES(?,?,?)");
            ps.setString(1, username);            
            ps.setString(2, MD5.Convert(password));
            if (active) {
                ps.setInt(3, 1);
            } else {
                ps.setInt(3, 0);
            }
                        
            return ps.executeUpdate();
        } finally {            
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(c);
        }
    }
    
    public static int removeLogin(String username) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection c = pool.getConnection();
        PreparedStatement ps = null;  
        
        try {
            ps = c.prepareStatement("DELETE FROM `login` WHERE `username` = ?");
            ps.setString(1, username);
            return ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(c);
        }
    }
    
    public static int removeLogin(Student s) throws SQLException {
        return removeLogin(s.getUsername());
    } 
    
    public static int removeLogin(Teacher t) throws SQLException {
        return removeLogin(t.getUsername());
    }
}
