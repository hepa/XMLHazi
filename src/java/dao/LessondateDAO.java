package dao;

import business.Lesson;
import business.Lessondate;
import java.sql.SQLException;
import java.util.ArrayList;

public class LessondateDAO extends DefaultDAO {
    private Lessondate date;

    public LessondateDAO() {
        date = null;
    }
    
    public ArrayList<Lessondate> find(Lesson l) throws SQLException {
        try {
            ps = c.prepareStatement("SELECT * FROM `lesson_date` WHERE lesson_id = ?");
            ps.setInt(1, l.getId());
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            ArrayList<Lessondate> dates = new ArrayList<>();
            while (rs.next()) {
                date = new Lessondate();
                date.setDay(rs.getString("day"));
                date.setHour(rs.getString("hour"));
                date.setMinutes(rs.getString("minutes"));
                dates.add(date);
            }
            
            return dates;
        } finally {
            closeStatementAndSet();
        }
    }
}
