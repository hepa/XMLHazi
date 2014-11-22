package dao;

import business.ConsultingHour;
import business.Teacher;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConsultingHourDAO extends DefaultDAO {
    private ConsultingHour ch;

    public ConsultingHourDAO() {
        ch = null;
    }

    public ConsultingHourDAO(ConsultingHour ch) {
        this.ch = ch;
    }        
    
    public ConsultingHour find (Teacher t, String day, String from) throws SQLException {
        try {
            ps = c.prepareStatement("SELECT * FROM `consulting_hour` WHERE `teacher_id` = ? AND `day` = ? AND `from` = ?");
            ps.setString(1, t.getIdCardNumber());
            ps.setString(2, day);
            ps.setString(3, from);
            
            logger.trace(ps);
            ps.executeQuery();
            
            while (rs.next()) {
                ch = new ConsultingHour();
                ch.setDay(day);
                ch.setFrom(from);
                ch.setTeacher(t);
                ch.setTo(rs.getString("to"));                
            }
            
            return ch;
        } finally {
            closeStatementAndSet();
        }
    }
    
    public ArrayList<ConsultingHour> findAll(Teacher t) throws SQLException {
        try {
            ps = c.prepareStatement("SELECT * FROM `consulting_hour` WHERE `teacher_id` = ? ORDER BY 2");
            ps.setString(1, t.getIdCardNumber());
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            ArrayList<ConsultingHour> all = new ArrayList<>();            
            while(rs.next()) {
                all.add(new ConsultingHour(rs.getString("day"), rs.getString("from"), rs.getString("to"), t));
            }
            
            return all;
        } finally {
            closeStatementAndSet();
        }
    }
    
    public void add() throws SQLException {
        try {
            ps = c.prepareStatement("INSERT INTO `consulting_hour` VALUES(?,?,?,?)");
            ps.setString(1, ch.getTeacher().getIdCardNumber());
            ps.setString(2, ch.getDay());
            ps.setString(3, ch.getFrom());
            ps.setString(4, ch.getTo());
            
            logger.trace(ps);
            ps.executeUpdate();
        } finally {
            closeStatementAndSet();
        }
    }
    
    public void remove() throws SQLException {
        try {
            ps = c.prepareStatement("DELETE FROM `consulting_hour` WHERE `teacher_id` = ? AND `day` = ? AND `from` = ?");
            ps.setString(1, ch.getTeacher().getIdCardNumber());
            ps.setString(2, ch.getDay());
            ps.setString(3, ch.getFrom());
            
            logger.debug(ps);
            ps.executeUpdate();
        } finally {
            closeStatementAndSet();
        }
    }
}
