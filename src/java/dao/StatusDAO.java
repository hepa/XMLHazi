package dao;

import business.Status;
import java.sql.SQLException;

public class StatusDAO extends DefaultDAO {
    private Status s;

    public StatusDAO() {
    }
    
    public Status find(String name) throws SQLException {
        try {
            ps = c.prepareStatement("SELECT * FROM `status` WHERE `name` = ?");
            ps.setString(1, name);
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                s = new Status();
                s.setId(rs.getInt("id"));
                s.setName(name);
            }
            
            return s;
        } finally {
            closeStatementAndSet();
        }
    }
    
    public Status find(int id) throws SQLException {
        try {
            ps = c.prepareStatement("SELECT * FROM `status` WHERE `id` = ?");
            ps.setInt(1, id);
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                s = new Status();
                s.setId(id);
                s.setName(rs.getString("name"));
            }
            
            return s;
        } finally {
            closeStatementAndSet();
        }
    }
}
