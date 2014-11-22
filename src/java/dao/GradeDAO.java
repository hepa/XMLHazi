package dao;

import business.Examtype;
import business.Grade;
import business.Lesson;
import business.Mark;
import business.Student;
import business.Teacher;
import errors.ClassNotFound;
import errors.UserNotFound;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class GradeDAO extends DefaultDAO {
    private Grade g;

    public GradeDAO() {
        g = null;
    }

    public GradeDAO(Grade g) {
        this.g = g;
    }    
    
    public ArrayList<Grade> findAll(Student s) throws SQLException, UserNotFound, ClassNotFound {
        try {
            ps = c.prepareStatement("SELECT * FROM `grade` WHERE `student_id` = ?");
            ps.setString(1, s.getStudentIdNumber());
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            ArrayList<Grade> grades = new ArrayList<>();
            while (rs.next()) {
                g = new Grade();
                g.setId(rs.getInt("id"));
                g.setCreated(rs.getTimestamp("date"));
                g.setExamtype(Examtype.getInstance(rs.getInt("exam_type_id")));
                g.setLesson(Lesson.getInstance(rs.getInt("lesson_id")));
                g.setMark(Mark.getInstance(rs.getInt("mark_id")));
                g.setStudent(s);
                grades.add(g);
            }
            
            return grades;
        } finally {
            closeStatementAndSet();
        }
    }
    
    public ArrayList<Grade> findAll(Student s, Lesson l) throws SQLException {
        try {
            ps = c.prepareStatement("SELECT * FROM `grade` WHERE `student_id` = ? AND `lesson_id` = ?");
            ps.setString(1, s.getStudentIdNumber());
            ps.setInt(2, l.getId());
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            ArrayList<Grade> grades = new ArrayList<>();
            while (rs.next()) {
                g = new Grade();
                g.setId(rs.getInt("id"));
                g.setCreated(rs.getTimestamp("date"));
                g.setExamtype(Examtype.getInstance(rs.getInt("exam_type_id")));
                g.setLesson(l);
                g.setMark(Mark.getInstance(rs.getInt("mark_id")));
                g.setStudent(s);
                grades.add(g);
            }
            
            return grades;
        } finally {
            closeStatementAndSet();
        }
    }
    
    public boolean add(Teacher t) throws SQLException {
//        try {
//            ps = c.prepareStatement("INSERT INTO `grade` (`student_id`, `lesson_id`, `exam_type_id`, `mark_id`, `date`) VALUES(?,?,?,?,NOW())");
//            ps.setString(1, s.getStudentIdNumber());
//            ps.setInt(2, l.getId());
//            ps.setInt(3, examtype.getId());
//            ps.setInt(4, m.getId());            
//            
//            logger.trace(ps);
//            ps.executeUpdate();
//        } finally {
//            closeStatementAndSet();
//        }
        CallableStatement stmt = c.prepareCall("{? = call addGrade (?,?,?,?,NOW(),?)}");
        try {            
            stmt.registerOutParameter(1, java.sql.Types.TINYINT);
            stmt.setString(2, g.getStudent().getStudentIdNumber());
            stmt.setInt(3, g.getLesson().getId());
            stmt.setInt(4, g.getExamtype().getId());
            stmt.setInt(5, g.getMark().getId());            
            stmt.setString(6, t.getIdCardNumber());
            
            logger.trace(stmt);
            stmt.execute();
            return !stmt.getBoolean(1);
        } finally {
            closeStatementAndSet();
            stmt.close();
        }
    }
    
    public void remove() throws SQLException {
        try {
            ps = c.prepareStatement("DELETE FROM `grade` WHERE `id` = ?");
            ps.setInt(1, g.getId());
            
            logger.trace(ps);
            ps.executeUpdate();
        } finally {
            closeStatementAndSet();
        }
    }
}
