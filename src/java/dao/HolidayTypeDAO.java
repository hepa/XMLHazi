package dao;

import business.HolidayType;
import java.sql.SQLException;

public class HolidayTypeDAO extends DefaultDAO {
    private HolidayType holidayType;

    public HolidayTypeDAO() {
    }

    public HolidayTypeDAO(HolidayType holidayType) {
        this.holidayType = holidayType;
    }
    
    public HolidayType find(int id) throws SQLException {
        try {
            ps = c.prepareStatement("SELECT * FROM `holiday_type` WHERE `id` = ?");
            ps.setInt(1, id);
            
            logger.trace(ps);
            rs = ps.executeQuery();
            while (rs.next()) {
                holidayType = new HolidayType(id, rs.getString("name"));
            }
            
            return holidayType;
        } finally {
            closeStatementAndSet();
        }
    }
}
