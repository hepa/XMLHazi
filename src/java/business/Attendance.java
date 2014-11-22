package business;

import dao.AttendanceDAO;
import errors.ClassNotFound;
import errors.UserNotFound;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Attendance {
    private int id;
    private Date created;
    private Lesson lesson;
    private Student student;
    private Status status;
    private String description;

    public Attendance() {
    }

    public Attendance(int id, Date created, Lesson lesson, Student student, Status status, String description) {
        this.id = id;
        this.created = created;
        this.lesson = lesson;
        this.student = student;
        this.status = status;
        this.description = description;
    }

    // <editor-fold desc="Getter&Setter" defaultstate="collapsed">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //</editor-fold>
    
    public static ArrayList<Attendance> getAllInstances(Lesson l) throws SQLException, UserNotFound, ClassNotFound {
        return new AttendanceDAO().findAll(l);
    }
    
    public static ArrayList<Attendance> getAllInstances(Student s, Lesson l) throws SQLException, UserNotFound, ClassNotFound {
        return new AttendanceDAO().findAll(s, l);
    }
    
    public void add() throws SQLException {
        new AttendanceDAO(this).add();
    }
    
    @Override
    public String toString() {
        return "Attendance{" + "id=" + id + ", created=" + created + ", lesson=" + lesson + ", student=" + student + ", status=" + status + ", description=" + description + '}';
    }
    
}
