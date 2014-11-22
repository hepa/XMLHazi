package dao;

import business.Family;
import business.Message;
import business.Person;
import business.Student;
import business.Teacher;
import errors.UserNotFound;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageDAO extends DefaultDAO {

    private Message m;

    public MessageDAO() {
    }

    public MessageDAO(Message m) {
        this.m = m;
    }

    public void send() throws SQLException {
        try {
            ps = c.prepareStatement("INSERT INTO `message` (`subject`, `body`, `from`) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, m.getSubject());
            ps.setString(2, m.getBody());
            ps.setString(3, m.getFrom().getUsername());

            logger.debug(ps);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();

            ps = c.prepareStatement("INSERT INTO `login_message` (`login_name`,`message_id`) VALUES(?,?)");
            for (Person p : m.getTo()) {
                ps.setString(1, p.getUsername());
                ps.setInt(2, rs.getInt(1));

                logger.debug(ps);
                ps.executeUpdate();
            }
        } finally {
            closeStatementAndSet();
        }
    }

    public ArrayList<Message> findAll(String username) throws SQLException {
        try {
            ps = c.prepareStatement("SELECT * FROM `login_message` WHERE `login_name` = ?");
            ps.setString(1, username);

            logger.trace(ps);
            rs = ps.executeQuery();

            ArrayList<Message> messages = new ArrayList<>();
            while (rs.next()) {
                m = new MessageDAO().find(rs.getInt("message_id"));
                m.setRead(rs.getBoolean("read"));
                messages.add(m);
            }
            
            return messages;
        } finally {
            closeStatementAndSet();
        }
    }
    
    public int findUnreadsSize(String username) throws SQLException {
        try {
            ps = c.prepareStatement("SELECT count(*) FROM `login_message` WHERE `login_name` = ? AND `read` = 0");
            ps.setString(1, username);
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            int db = 0;
            while (rs.next()) {
                db = rs.getInt(1);
            }
            
            return db;
        } finally {
            closeStatementAndSet();
        }
    }

    public Message find(int id) throws SQLException {
        try {
            ps = c.prepareStatement("SELECT * FROM `message` WHERE `id` = ?");
            ps.setInt(1, id);

            logger.trace(ps);
            rs = ps.executeQuery();
                                   
            while (rs.next()) {
                m = new Message();
                m.setId(id);
                m.setBody(rs.getString("body"));
                m.setSubject(rs.getString("subject"));
                m.setCreated(new Date(rs.getTimestamp("created").getTime())); 
                
                HashMap<String, String> hMap = new HashMap<>();
                hMap.put("username", rs.getString("from"));
                
                try {
                    Student s = new StudentDAO().find(hMap);
                    m.setFrom(s);
                    break;
                } catch (UserNotFound ex) {                    
                }                                
                
                try {
                    Teacher t = new TeacherDAO().find(hMap);
                    m.setFrom(t);
                    break;
                } catch (UserNotFound ex) {                    
                }
                
                try {
                    Family f = Family.getInstance(rs.getString("from"));                                
                    m.setFrom(new Person(f.getUsername(), f.getFather().getFirstName(), f.getFather().getLastName()));
                    break;
                } catch (UserNotFound ex) {                    
                }
                
                m.setFrom(new Person(rs.getString("from")));
            }
            
            return m;
        } finally {
            closeStatementAndSet();
        }
    }
    
    public void markRead() throws SQLException {
        try {
            ps = c.prepareStatement("UPDATE `login_message` SET `read` = 1 WHERE `message_id` = ?");
            ps.setInt(1, m.getId());
            
            logger.trace(ps);
            ps.executeUpdate();
        } finally {
            closeStatementAndSet();
        }
    }
    
    public void markUnRead() throws SQLException {
        try {
            ps = c.prepareStatement("UPDATE `login_message` SET `read` = 0 WHERE `message_id` = ?");
            ps.setInt(1, m.getId());
            
            logger.trace(ps);
            ps.executeUpdate();
        } finally {
            closeStatementAndSet();
        }
    }
    
    public void delete() throws SQLException {
        try {
            ps = c.prepareStatement("DELETE FROM `login_message` WHERE `message_id` = ?");
            ps.setInt(1, m.getId());
            
            logger.trace(ps);
            ps.executeUpdate();
        } finally {
            closeStatementAndSet();
        }
    }
}
