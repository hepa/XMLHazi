package dao;

import business.Mark;
import java.sql.SQLException;

public class MarkDAO extends DefaultDAO {
    private Mark m;

    public MarkDAO() {
        m = null;
    }

    public MarkDAO(Mark m) {
        this.m = m;
    }        
    
    public Mark find(int id) throws SQLException {
        try {
            ps = c.prepareStatement("SELECT * FROM `mark` WHERE `id` = ?");
            ps.setInt(1, id);
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                m = new Mark(id, rs.getString("desc"));                
            }
            
            return m;
        } finally {
            closeStatementAndSet();
        }
    }
    
    public Mark find(String desc) throws SQLException {
        try {
            ps = c.prepareStatement("SELECT * FROM `mark` WHERE `desc` = ?");
            ps.setString(1, desc);
            
            logger.trace(ps);
            rs = ps.executeQuery();
                        
            while (rs.next()) {
                m = new Mark(rs.getInt("id"), desc);
            }
            
            return m;
        } finally {
            closeStatementAndSet();
        }
    }
}
