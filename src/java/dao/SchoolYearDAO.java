package dao;

import business.SchoolYear;
import java.sql.SQLException;

public class SchoolYearDAO extends DefaultDAO {
    private SchoolYear sy;

    public SchoolYearDAO() {
        sy = null;
    }
    
    public SchoolYear find(int id) throws SQLException {
        try {
            ps = c.prepareStatement("SELECT * FROM `school_year` WHERE `id` = ?");
            ps.setInt(1, id);
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                sy = new SchoolYear();
                sy.setId(id);
                sy.setFrom(rs.getDate("date_from"));
                sy.setTo(rs.getDate("date_to"));
                sy.setName(rs.getString("year"));
            }
            
            return this.sy;
        } finally {
            closeStatementAndSet();
        }
    }
    
    public SchoolYear find(String name) throws SQLException {
        try {
            ps = c.prepareStatement("SELECT * FROM `school_year` WHERE `year` = ?");
            ps.setString(1, name);
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                sy = new SchoolYear();
                sy.setId(rs.getInt("id"));
                sy.setFrom(rs.getDate("date_from"));
                sy.setTo(rs.getDate("date_to"));
                sy.setName(name);
            }
            
            return this.sy;
        } finally {
            closeStatementAndSet();
        }
    }
}
