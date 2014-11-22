package dao;

import business.Homework;
import business.Lesson;
import business.Mark;
import business.Student;
import errors.ClassNotFound;
import errors.UserNotFound;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

public class HomeworkDAO extends DefaultDAO {
    private Homework homework;

    public HomeworkDAO() {
        homework = null;
    }

    public HomeworkDAO(Homework homework) {
        this.homework = homework;
    }        
    
    public Homework find(int id) throws SQLException, UserNotFound, ClassNotFound {
        try {
            ps = c.prepareStatement("SELECT * FROM `homework` WHERE `id` = ?");
            ps.setInt(1, id);
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                homework = new Homework();
                homework.setId(id);
                homework.setContent(rs.getString("content"));
                homework.setCreated(rs.getTimestamp("created"));
                homework.setLesson(Lesson.getInstance(rs.getInt("lesson_id")));
            }                        
            
            return homework;
        } finally {
            closeStatementAndSet();
        }
    }
    
    public Homework find(int id, Student s) throws SQLException, UserNotFound, ClassNotFound {
        try {
            ps = c.prepareStatement("SELECT * FROM `homework` WHERE `id` = ?");
            ps.setInt(1, id);
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                homework = new Homework();
                homework.setId(id);
                homework.setContent(rs.getString("content"));
                homework.setCreated(rs.getTimestamp("created"));
                homework.setLesson(Lesson.getInstance(rs.getInt("lesson_id")));
            }                        
            
            ps = c.prepareStatement("SELECT * FROM `students_homeworks` WHERE `homework_id` = ? AND `student_id` = ?");
            ps.setInt(1, id);
            ps.setString(2, s.getStudentIdNumber());
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            while (rs.next()) {                                
                homework.setMark(Mark.getInstance(rs.getInt("mark_id")));
                homework.setMarkRegistered(rs.getTimestamp("mark_registered"));
                homework.setDone(rs.getBoolean("done"));                
            }
            
            return homework;
        } finally {
            closeStatementAndSet();
        }
    }
    
    public ArrayList<Homework> findAll(Student s) throws SQLException, UserNotFound, ClassNotFound {
        try {
            ps = c.prepareStatement("SELECT * FROM `students_homeworks` WHERE `student_id` = ?");
            ps.setString(1, s.getStudentIdNumber());
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            ArrayList<Homework> hws = new ArrayList<>();
            while (rs.next()) {
                homework = Homework.getInstance(rs.getInt("homework_id"));                
                homework.setMark(Mark.getInstance(rs.getInt("mark_id")));
                homework.setMarkRegistered(rs.getTimestamp("mark_registered"));
                homework.setDone(rs.getBoolean("done"));
                hws.add(homework);
            }
            
            return hws;
        } finally {
            closeStatementAndSet();
        }
    }
    
    public ArrayList<Homework> findAll(Lesson l) throws SQLException, UserNotFound, ClassNotFound {
        try {
            ps = c.prepareStatement("SELECT * FROM `homework` WHERE `lesson_id` = ?");
            ps.setInt(1, l.getId());
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            ArrayList<Homework> hws = new ArrayList<>();
            while (rs.next()) {
                homework = new Homework();
                homework.setContent(rs.getString("content"));
                homework.setCreated(rs.getTimestamp("created"));
                homework.setId(rs.getInt("id"));
                homework.setLesson(l);
                hws.add(homework);
            }
            
            return hws;
        } finally {
            closeStatementAndSet();
        }
    }
    
    public ArrayList<Homework> findAll(Student s, Lesson l) throws SQLException, UserNotFound, ClassNotFound {
        try {
            ps = c.prepareStatement("SELECT * FROM `students_homeworks` WHERE `student_id` = ? AND `homework_id` IN (SELECT `id` FROM `homework` WHERE `lesson_id` = ?)");
            ps.setString(1, s.getStudentIdNumber());
            ps.setInt(2, l.getId());
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            ArrayList<Homework> hws = new ArrayList<>();
            while (rs.next()) {
                homework = Homework.getInstance(rs.getInt("homework_id"));                
                homework.setMark(Mark.getInstance(rs.getInt("mark_id")));
                homework.setMarkRegistered(rs.getTimestamp("mark_registered"));
                homework.setDone(rs.getBoolean("done"));
                hws.add(homework);
            }
            
            return hws;            
        } finally {
            closeStatementAndSet();
        }
    }
    
    public ArrayList<Student> findStudents() throws SQLException, UserNotFound {
        try {
            ps = c.prepareStatement("SELECT * FROM `students_homeworks` WHERE `homework_id` = ?");
            ps.setInt(1, homework.getId());
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            ArrayList<Student> students = new ArrayList<>();            
            while (rs.next()) {                
                students.add(Student.getInstance(rs.getString("student_id")));
            }
            
            return students;
        } finally {
            closeStatementAndSet();
        }
    }
    
    public void add() throws SQLException {
        try {
            ps = c.prepareStatement("INSERT INTO `homework` (`lesson_id`, `content`, `created`) VALUES (?,?,NOW())");
            ps.setInt(1, homework.getLesson().getId());
            ps.setString(2, homework.getContent());            
                    
            logger.trace(ps);
            ps.executeUpdate();                    
        } finally {
            closeStatementAndSet();
        }
    }
    
    public void addGrade(Student s, int grade) throws SQLException {
        try {
            ps = c.prepareStatement("UPDATE `students_homeworks` SET `mark_id` = ? ,`mark_registered` = NOW() WHERE `student_id` = ? AND `homework_id` = ?");
            ps.setInt(1, grade);
            ps.setString(2, s.getStudentIdNumber());
            ps.setInt(3, homework.getId());
            
            logger.trace(ps);
            ps.executeUpdate();            
        } finally {
            closeStatementAndSet();
        }
    }
    
    public void remove() throws SQLException {
        try {
            ps = c.prepareStatement("DELETE FROM `homework` WHERE `id` = ?");
            ps.setInt(1, homework.getId());            
                    
            logger.trace(ps);
            ps.executeUpdate();                    
        } finally {
            closeStatementAndSet();
        }
    }
       
    public void checkDone(Student s) throws SQLException {
        try {
            ps = c.prepareStatement("UPDATE `students_homeworks` SET `done` = 1 WHERE `homework_id` = ? AND `student_id` = ?");
            ps.setInt(1, homework.getId());
            ps.setString(2, s.getStudentIdNumber());
            
            logger.trace(ps);
            ps.executeUpdate();                        
        } finally {
            closeStatementAndSet();
        }
    }
    
    public void checkUnDone(Student s) throws SQLException {
        try {
            ps = c.prepareStatement("UPDATE `students_homeworks` SET `done` = 0 WHERE `homework_id` = ? AND `student_id` = ?");
            ps.setInt(1, homework.getId());
            ps.setString(2, s.getStudentIdNumber());
            
            logger.trace(ps);
            ps.executeUpdate();                        
        } finally {
            closeStatementAndSet();
        }
    }
}
