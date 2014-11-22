package dao;

import business.Student;
import business.Class;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentInClassDAO extends DefaultDAO {

    private ArrayList<Student> students;
    private Class cl;

    public StudentInClassDAO() {
        students = new ArrayList();
        cl = new Class();
    }

    public void addStudentToClass(ArrayList<Student> students, Class cl) throws SQLException {
        try {
            ps = c.prepareStatement("INSERT INTO `students_in_class` VALUES(?,?)");
            ps.setInt(1, cl.getId());

            for (Student s : students) {
                ps.setString(2,s.getStudentIdNumber());
                
                logger.trace(ps);
                ps.executeUpdate();
            }
        } finally {
            closeStatementAndSet();
        }
    }
    
    public void removeStudentFromClass(ArrayList<Student> students, Class cl) throws SQLException {
        try {
            ps = c.prepareStatement("DELETE FROM `students_in_class` WHERE `student_id` = ?");
            for (Student s : students) {
                ps.setString(1, s.getStudentIdNumber());                
                
                logger.trace(ps);
                ps.executeUpdate();
            }            
        } finally {
            closeStatementAndSet();
        }
    }
}
