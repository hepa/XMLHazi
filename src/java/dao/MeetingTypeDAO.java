package dao;

import business.MeetingType;
import java.sql.SQLException;
import java.util.ArrayList;

public class MeetingTypeDAO extends DefaultDAO {

    public ArrayList<MeetingType> findAll() throws SQLException {
        try {
            ps = c.prepareStatement("SELECT * FROM `meeting_type`");
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            ArrayList<MeetingType> meetings = new ArrayList<>();
            
            while (rs.next()) {
                meetings.add(new MeetingType(rs.getInt("id"), rs.getString("type"), rs.getString("desc")));
            }
            
            return meetings;
        } finally {
            closeStatementAndSet();
        }
                
    }
    
    public MeetingType find(int id) throws SQLException {
        try {
            ps = c.prepareStatement("SELECT * FROM `meeting_type` WHERE id = ?");
            ps.setInt(1, id);
            
            logger.trace(ps);
            rs = ps.executeQuery();                        
            
            MeetingType t = null;
            while (rs.next()) {
                t = new MeetingType(id, rs.getString("type"), rs.getString("desc"));               
            }                                    
            
            return t;
        } finally {
            closeStatementAndSet();
        }
    }
}
