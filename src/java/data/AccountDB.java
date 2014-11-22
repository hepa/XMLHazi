package data;

import business.Account;
import errors.AccountNotFound;

import java.sql.*;

public class AccountDB {
    public static Account getAccount(int number) throws AccountNotFound, SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection c = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = c.prepareStatement("SELECT * FROM `account` WHERE `number` = ?");
            ps.setInt(1, number);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                return new Account(rs.getInt("number"), rs.getInt("balance"), null);
            } else {
                throw new AccountNotFound();
            }         
        }
        finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);            
            pool.freeConnection(c);
        }
    }
    
    public static int addAccount(Account a) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection c = pool.getConnection();
        PreparedStatement ps = null;        

        try {
            ps = c.prepareStatement("INSERT INTO `account` VALUES(?,?)");
            ps.setInt(1, a.getNumber());
            ps.setInt(2, a.getBalance());
            return ps.executeUpdate();                           
        }
        finally {            
            DBUtil.closePreparedStatement(ps);            
            pool.freeConnection(c);
        }
    }
    
    public static int removeAccount(Account a) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection c = pool.getConnection();
        PreparedStatement ps = null;        

        try {
            ps = c.prepareStatement("DELETE FROM `account` WHERE `number` = ?");
            ps.setInt(1, a.getNumber());            
            return ps.executeUpdate();                           
        }
        finally {            
            DBUtil.closePreparedStatement(ps);            
            pool.freeConnection(c);
        }
    }
    
    public static int changeBalance(Account a, int amount) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection c = pool.getConnection();
        PreparedStatement ps = null;        

        try {
            ps = c.prepareStatement("UPDATE `account` SET `balance` = ? WHERE `number` = ?");
            ps.setInt(1, amount);            
            ps.setInt(2, a.getNumber());            
            return ps.executeUpdate();                           
        }
        finally {            
            DBUtil.closePreparedStatement(ps);            
            pool.freeConnection(c);
        }
    }
    
    public static int deposit(Account a, int amount) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection c = pool.getConnection();
        PreparedStatement ps = null;        

        try {
            ps = c.prepareStatement("UPDATE `account` SET `balance` = `balance` + ? WHERE `number` = ?");
            ps.setInt(1, amount);            
            ps.setInt(2, a.getNumber());            
            return ps.executeUpdate();                           
        }
        finally {            
            DBUtil.closePreparedStatement(ps);            
            pool.freeConnection(c);
        }
    }
    
    public static int withdraw(Account a, int amount) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection c = pool.getConnection();
        PreparedStatement ps = null;        

        try {
            ps = c.prepareStatement("UPDATE `account` SET `balance` = `balance` - ? WHERE `number` = ?");
            ps.setInt(1, amount);            
            ps.setInt(2, a.getNumber());            
            return ps.executeUpdate();                           
        }
        finally {            
            DBUtil.closePreparedStatement(ps);            
            pool.freeConnection(c);
        }
    }    
    
}
