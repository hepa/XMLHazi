package dao;

import business.Family;
import business.Settings;
import business.Student;
import java.sql.SQLException;

public class SettingsDAO extends DefaultDAO {
    private Settings settings;

    public SettingsDAO() {
    }

    public SettingsDAO(Settings settings) {
        this.settings = settings;
    }
    
    public Settings find(Student s) throws SQLException {
        try {
            ps = c.prepareStatement("SELECT * FROM `student_settings` WHERE `student_id` = ?");
            ps.setString(1, s.getStudentIdNumber());
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                settings = new Settings();
                settings.setEnabledMessageAboutGrade(rs.getBoolean("message_grade"));
                settings.setEnabledEmailAboutGrade(rs.getBoolean("email_grade"));
                settings.setEnabledMessageAboutHomework(rs.getBoolean("message_homework"));
                settings.setEnabledEmailAboutHomework(rs.getBoolean("email_homework"));
                settings.setEnabledMessageAboutHomeworkGrade(rs.getBoolean("message_homework_grade"));
                settings.setEnabledEmailAboutHomeworkGrade(rs.getBoolean("email_homework_grade"));
                settings.setEnabledMessageAboutItem(rs.getBoolean("message_item"));
                settings.setEnabledEmailAboutItem(rs.getBoolean("email_item"));
                settings.setEnabledMessageAboutAttendance(rs.getBoolean("message_attendance"));
                settings.setEnabledEmailAboutAttendance(rs.getBoolean("email_attendance"));
            }
            
            return settings;
        } finally {
            closeStatementAndSet();
        }
    }
    
    public Settings find(Family f) throws SQLException {        
        try {
            ps = c.prepareStatement("SELECT * FROM `family_settings` WHERE `family_id` = ?");
            ps.setInt(1, f.getId());
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                settings = new Settings();
                settings.setEnabledMessageAboutGrade(rs.getBoolean("message_grade"));
                settings.setEnabledEmailAboutGrade(rs.getBoolean("email_grade"));
                settings.setEnabledMessageAboutHomework(rs.getBoolean("message_homework"));
                settings.setEnabledEmailAboutHomework(rs.getBoolean("email_homework"));
                settings.setEnabledMessageAboutHomeworkGrade(rs.getBoolean("message_homework_grade"));
                settings.setEnabledEmailAboutHomeworkGrade(rs.getBoolean("email_homework_grade"));
                settings.setEnabledMessageAboutItem(rs.getBoolean("message_item"));
                settings.setEnabledEmailAboutItem(rs.getBoolean("email_item"));
                settings.setEnabledMessageAboutAttendance(rs.getBoolean("message_attendance"));
                settings.setEnabledEmailAboutAttendance(rs.getBoolean("email_attendance"));
            }
            
            return settings;
        } finally {
            closeStatementAndSet();
        }
    }
    
    public void update(Student s) throws SQLException {
        try {
            ps = c.prepareStatement("UPDATE `student_settings "
                    + "SET `message_grade` = ?,"
                    + "SET `email_grade` = ?,"
                    + "SET `message_homework = ?,"
                    + "SET `email_homework = ?,"
                    + "SET `message_homework_grade` = ?,"
                    + "SET `email_homework_grade` = ?,"
                    + "SET `message_item` = ?,"
                    + "SET `email_item` = ?,"
                    + "SET `message_attendance` = ?,"
                    + "SET `email_attendance` = ? "
                    + "WHERE `student_id` = ?");
            ps.setBoolean(1, settings.isEnabledMessageAboutGrade());
            ps.setBoolean(2, settings.isEnabledEmailAboutGrade());
            ps.setBoolean(3, settings.isEnabledMessageAboutHomework());
            ps.setBoolean(4, settings.isEnabledEmailAboutHomework());
            ps.setBoolean(5, settings.isEnabledMessageAboutHomeworkGrade());
            ps.setBoolean(6, settings.isEnabledEmailAboutHomeworkGrade());
            ps.setBoolean(7, settings.isEnabledMessageAboutItem());
            ps.setBoolean(8, settings.isEnabledEmailAboutItem());
            ps.setBoolean(9, settings.isEnabledMessageAboutAttendance());
            ps.setBoolean(10, settings.isEnabledEmailAboutAttendance());
            ps.setString(11, s.getStudentIdNumber());
            
            logger.trace(ps);
            ps.executeUpdate();
        } finally {
            closeStatementAndSet();
        }
    }
    
    public void update(Student s, String attribute, boolean value) throws SQLException {        
        try {
            ps = c.prepareStatement("UPDATE `student_settings` "
                    + "SET " + attribute + " = ? "
                    + "WHERE `student_id` = ?");            
            ps.setBoolean(1, value);
            ps.setString(2, s.getStudentIdNumber());
            
            logger.debug(ps);
            ps.executeUpdate();
        } finally {
            closeStatementAndSet();
        }
    }
    
    public void update(Family f, String attribute, boolean value) throws SQLException {        
        try {
            ps = c.prepareStatement("UPDATE `family_settings` "
                    + "SET " + attribute + " = ? "
                    + "WHERE `family_id` = ?");            
            ps.setBoolean(1, value);
            ps.setInt(2, f.getId());
            
            logger.debug(ps);
            ps.executeUpdate();
        } finally {
            closeStatementAndSet();
        }
    }
}
