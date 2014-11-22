package dao;

import business.Attendance;
import business.Lesson;
import business.Status;
import business.Student;
import errors.ClassNotFound;
import errors.UserNotFound;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceDAO extends DefaultDAO {
    private Attendance a;

    public AttendanceDAO() {
        a = null;
    }

    public AttendanceDAO(Attendance a) {
        this.a = a;
    }        
    
    public ArrayList<Attendance> findAll(Lesson l) throws SQLException, UserNotFound, ClassNotFound {
        try {
            ps = c.prepareStatement("SELECT * FROM `attendance` WHERE `lesson_id` = ?");
            ps.setInt(1, l.getId());
            
            logger.trace(ps);
            rs = ps.executeQuery();
                        
            ArrayList<Attendance> as = new ArrayList<>();            
            while (rs.next()) {                
                a = new Attendance();
                a.setCreated(new java.util.Date(rs.getDate("created").getTime()));
                a.setDescription(rs.getString("desc"));
                a.setId(rs.getInt("id"));
                a.setLesson(l);
                a.setStatus(Status.getInstance(rs.getInt("status")));
                a.setStudent(Student.getInstance(rs.getString("student_id")));                
                as.add(a);
            }
            
            return as;
        } finally {
            closeStatementAndSet();
        }
    }
    
    public ArrayList<Attendance> findAll(Student s, Lesson l) throws SQLException, UserNotFound, ClassNotFound {
        try {
            ps = c.prepareStatement("SELECT * FROM `attendance` WHERE `lesson_id` = ? AND `student_id` = ?");
            ps.setInt(1, l.getId());
            ps.setString(2, s.getStudentIdNumber());
            
            logger.trace(ps);
            rs = ps.executeQuery();
                        
            ArrayList<Attendance> as = new ArrayList<>();            
            while (rs.next()) {                
                a = new Attendance();
                a.setCreated(new java.util.Date(rs.getDate("created").getTime()));
                a.setDescription(rs.getString("desc"));
                a.setId(rs.getInt("id"));
                a.setLesson(l);
                a.setStatus(Status.getInstance(rs.getInt("status")));
                a.setStudent(s);                
                as.add(a);
            }
            
            return as;
        } finally {
            closeStatementAndSet();
        }
    }
    
    public void add() throws SQLException {
        try {
            ps = c.prepareStatement("INSERT INTO `attendance` (`created`, `lesson_id`, `student_id`, `status`, `desc`) VALUES(?,?,?,?,?)");
            ps.setDate(1, new Date(a.getCreated().getTime()));
            ps.setInt(2, a.getLesson().getId());
            ps.setString(3, a.getStudent().getStudentIdNumber());
            ps.setInt(4, a.getStatus().getId());
            ps.setString(5, a.getDescription());
            
            logger.trace(ps);
            ps.executeUpdate();            
        } finally {
            closeStatementAndSet();
        }
    }
}
