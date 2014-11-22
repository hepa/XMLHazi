package dao;

import business.Account;
import business.AccountTransaction;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountDAO extends DefaultDAO {
    private Account a;

    public AccountDAO() {
        a = null;
    }

    public AccountDAO(Account a) {
        this.a = a;
    }        
    
    public Account find(int number) throws SQLException {        
        try {            
            ps = c.prepareStatement("SELECT * FROM `account` WHERE `number` = ?");
            ps.setInt(1, number);
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                return new Account(rs.getInt("number"), rs.getInt("balance"), null);
            } else {
                return null;                
            }         
        }
        finally {
            closeStatementAndSet();
        }
    }
    
    public ArrayList<AccountTransaction> findTransactions() throws SQLException {
        try {
            ps = c.prepareStatement("SELECT * FROM `account_transaction_log` WHERE `account_number` = ?");
            ps.setInt(1, a.getNumber());
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            ArrayList<AccountTransaction> transactions = new ArrayList<>();
            while (rs.next()) {
                AccountTransaction at = new AccountTransaction();
                at.setId(rs.getInt("id"));
                at.setCreated(rs.getTimestamp("created"));
                at.setDescription(rs.getString("desc"));
                
                transactions.add(at);
            }
                        
            return transactions;
        } finally {
            closeStatementAndSet();
        }
    }
    
    public void add() throws SQLException {        
        try {
            ps = c.prepareStatement("INSERT INTO `account` VALUES(?,?)");
            ps.setInt(1, a.getNumber());
            ps.setInt(2, a.getBalance());
            
            logger.trace(ps);
            ps.executeUpdate();                           
        }
        finally {            
            closeStatementAndSet();
        }
    }
      
    public void remove() throws SQLException {
        logger.trace("The method has been invoked, with paramter: " + a.getNumber());
        try {
            ps = c.prepareStatement("DELETE FROM `account` WHERE `number` = ?");
            ps.setInt(1, a.getNumber());   
            
            logger.trace(ps);
            ps.executeUpdate();                           
        }
        finally {            
            closeStatementAndSet();
        }
    }
    
    public void deposit(int amount) throws SQLException {        
        try {
            ps = c.prepareStatement("UPDATE `account` SET `balance` = `balance` + ? WHERE `number` = ?");
            ps.setInt(1, amount);            
            ps.setInt(2, a.getNumber());
            
            logger.trace(ps);
            ps.executeUpdate();                           
        }
        finally {            
            closeStatementAndSet();
        }
    }
    
    public void withdraw(int amount) throws SQLException {        
        try {
            ps = c.prepareStatement("UPDATE `account` SET `balance` = `balance` - ? WHERE `number` = ?");
            ps.setInt(1, amount);            
            ps.setInt(2, a.getNumber());  
            
            logger.trace(ps);
            ps.executeUpdate();                           
        }
        finally {            
            closeStatementAndSet();
        }
    }
}
