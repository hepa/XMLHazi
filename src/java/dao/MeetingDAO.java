package dao;

import business.Class;
import business.Meeting;
import business.MeetingType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.Date;

public class MeetingDAO extends DefaultDAO {
    
    private Meeting m;

    public MeetingDAO() {
        m = null;
    }       

    public MeetingDAO(Meeting m) {
        this.m = m;
    }        
    
    public ArrayList<Meeting> findAll(Class cl) throws SQLException {
        try {
            ps = c.prepareStatement("SELECT * FROM `meeting` WHERE `class_id` = ?");
            ps.setInt(1, cl.getId());
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            ArrayList<Meeting> meetings = new ArrayList<>();
            while (rs.next()) {
                MeetingType mt = MeetingType.getInstance(rs.getInt("type_id"));
                meetings.add(new Meeting(rs.getTimestamp("date_from"), rs.getTimestamp("date_to"), mt, rs.getString("comment")));
            }
            
            return meetings;
        } finally {
            closeStatementAndSet();
        }
    } 
    
    public Meeting find(int classId, Date dateFrom) throws SQLException {
        try {
            ps = c.prepareStatement("SELECT * FROM `meeting` WHERE `class_id` = ? AND `date_from` = ?");
            ps.setInt(1, classId);
            ps.setTimestamp(2, new Timestamp(dateFrom.getTime()));
            
            logger.trace(ps);
            rs = ps.executeQuery();
                        
            while (rs.next()) {
                m = new Meeting();
                m.setFrom(new Timestamp(dateFrom.getTime()));
                m.setTo(rs.getTimestamp("date_to"));
                m.setType(MeetingType.getInstance(rs.getInt("type_id")));
                m.setComment(rs.getString("comment"));
            }
            
            return m;
        } finally {
            closeStatementAndSet();
        }
    }
    
    public void add(Class cl) throws SQLException {        
        try {
            ps = c.prepareStatement("INSERT INTO `meeting` VALUES(?,?,?,?,?)");
            ps.setInt(1, cl.getId());
            ps.setTimestamp(2, new Timestamp(m.getFrom().getTime()));
            ps.setTimestamp(3, new Timestamp(m.getTo().getTime()));
            ps.setInt(4, m.getType().getId());
            ps.setString(5, m.getComment());                             
            
            logger.trace(ps);
            ps.executeUpdate();            
        } finally {
            closeStatementAndSet();
        }
    }
    
    public void remove(Class cl) throws SQLException {        
        try {
            ps = c.prepareStatement("DELETE FROM `meeting` WHERE `class_id` = ? AND `date_from` = ?");
            ps.setInt(1, cl.getId());
            ps.setTimestamp(2, new Timestamp(m.getFrom().getTime()));                        
            
            logger.trace(ps);
            ps.executeUpdate();            
        } finally {
            closeStatementAndSet();
        }
    }
}
