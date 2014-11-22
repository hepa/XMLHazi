package dao;

import business.Holiday;
import business.SchoolYear;
import java.sql.SQLException;
import java.util.ArrayList;

public class HolidayDAO extends DefaultDAO {
    private Holiday holiday;

    public HolidayDAO() {
    }

    public HolidayDAO(Holiday holiday) {
        this.holiday = holiday;
    }
    
    public ArrayList<Holiday> findAll(SchoolYear year) throws SQLException {
        try {
            ps = c.prepareStatement("SELECT * FROM `holiday` WHERE `school_year_id` = ?");
            ps.setInt(1, year.getId());
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            ArrayList<Holiday> holidays = new ArrayList<>();
            while (rs.next()) {
                holiday = new Holiday();
                holiday.setFrom(rs.getDate("date_from"));
                holiday.setTo(rs.getDate("date_to"));
                holiday.setId(rs.getInt("id"));
                holiday.setYear(year);
                holiday.setDescription(rs.getString("desc"));
                holiday.setType(new HolidayTypeDAO().find(rs.getInt("type")));
                
                holidays.add(holiday);
            }
            
            return holidays;
        } finally {
            closeStatementAndSet();
        }
    }
}
