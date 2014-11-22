package dao;

import business.Subject;
import java.sql.SQLException;

public class SubjectDAO extends DefaultDAO {
    private Subject s;

    public SubjectDAO() {
        s = null;
    }
        
    public Subject find(int id) throws SQLException {
        try {
            ps = c.prepareStatement("SELECT * FROM `subject` WHERE `id` = ?");
            ps.setInt(1, id);
            
            logger.trace(ps);
            rs = ps.executeQuery();
                        
            while (rs.next()) {
                s = new Subject();
                s.setId(id);
                s.setName(rs.getString("name"));
                s.setDescription(rs.getString("desc"));                
            }
            
            return this.s;
        } finally {
            closeStatementAndSet();
        }
    }
    
    public Subject find(String name) throws SQLException {
        try {
            ps = c.prepareStatement("SELECT * FROM `subject` WHERE `name` = ?");
            ps.setString(1, name);
            
            logger.trace(ps);
            rs = ps.executeQuery();
                        
            while (rs.next()) {
                s = new Subject();
                s.setId(rs.getInt("id"));
                s.setName(name);
                s.setDescription(rs.getString("desc"));                
            }
            
            return this.s;
        } finally {
            closeStatementAndSet();
        }
    }
}
