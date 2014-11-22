package dao;

import business.Account;
import business.Family;
import business.Item;
import business.Itemtype;
import business.Student;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAO extends DefaultDAO {
    private Item item;

    public ItemDAO() {
    }

    public ItemDAO(Item item) {
        this.item = item;
    }
    
    public Item find(int id) throws SQLException {
        try {
            ps = c.prepareStatement("SELECT * FROM `item` WHERE `id` = ?");
            ps.setInt(1, id);
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                item = new Item();
                item.setId(id);
                item.setItemtype(Itemtype.getInstance(rs.getInt("type_id")));
                item.setAmount(rs.getLong("amount"));
                item.setCreated(rs.getTimestamp("created"));
                item.setAccount(Account.getInstance(rs.getInt("account_number")));
            }
            
            return item;
        } finally {
            closeStatementAndSet();
        }
    }
    
    public ArrayList<Item> findAll(Account a) throws SQLException {
        try {            
            ps = c.prepareStatement("SELECT * FROM `item` WHERE `account_number` = ?");
            ps.setInt(1, a.getNumber());
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            ArrayList<Item> items = new ArrayList<>();
            while (rs.next()) {
                item = new Item();
                item.setAccount(a);
                item.setAmount(rs.getLong("amount"));
                item.setCreated(rs.getTimestamp("created"));
                item.setId(rs.getInt("id"));
                item.setItemtype(Itemtype.getInstance(rs.getInt("type_id")));
                
                items.add(item);
            }
            
            return items;            
        } finally {
            closeStatementAndSet();
        }
    }
    
    public ArrayList<Item> findAll(Student s) throws SQLException {
        try {
            ps = c.prepareStatement("SELECT * FROM `student_item` WHERE `student_id` = ?");
            ps.setString(1, s.getStudentIdNumber());
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            ArrayList<Item> items = new ArrayList<>();
            while (rs.next()) {
                item = Item.getInstance(rs.getInt("item_id"));
                item.setDone(rs.getBoolean("done"));
                item.setPayTime(rs.getTimestamp("date"));     
                
                items.add(item);
            }
            
            return items;
        } finally {
            closeStatementAndSet();
        }
    }
    
    public void add() throws SQLException {
        try {
            ps = c.prepareStatement("INSERT INTO `item` (`account_number`, `type_id`, `amount`) VALUES (?,?,?)");
            ps.setInt(1, item.getAccount().getNumber());
            ps.setInt(2, item.getItemtype().getId());
            ps.setLong(3, item.getAmount());
            
            logger.trace(ps);
            ps.executeUpdate();            
        } finally {
            closeStatementAndSet();
        }
    }
    
    public void remove() throws SQLException {
        try {
            ps = c.prepareStatement("DELETE FROM `item` WHERE `id` = ?");
            ps.setInt(1, item.getId());
            
            logger.debug(ps);
            ps.executeUpdate();
        } finally {
            closeStatementAndSet();
        }
    }
    
    public void deposit(Family f) throws SQLException {
        try {
            ps = c.prepareStatement("UPDATE `student_item` SET `done` = '1', `date` = CURRENT_TIMESTAMP() WHERE `student_id` = ? AND `item_id` = ?");
            ps.setString(1, f.getStudent().getStudentIdNumber());
            ps.setInt(2, item.getId());
            
            logger.debug(ps);            
            ps.executeUpdate();                        
        } finally {
            closeStatementAndSet();
        }
    }
}
