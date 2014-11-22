package business;

import dao.GradeDAO;
import errors.ClassNotFound;
import errors.UserNotFound;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Grade implements Comparable<Grade> {
    private int id;
    private Student student;
    private Lesson lesson;
    private Examtype examtype;
    private Mark mark;
    private Date created;

    public Grade() {
    }

    public Grade(int id, Student student, Lesson lesson, Examtype examtype, Mark mark, Date created) {
        this.id = id;
        this.student = student;
        this.lesson = lesson;
        this.examtype = examtype;
        this.mark = mark;
        this.created = created;
    }

    // <editor-fold desc="Getter&Setter" defaultstate="collapsed">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Examtype getExamtype() {
        return examtype;
    }

    public void setExamtype(Examtype examtype) {
        this.examtype = examtype;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
    
    // </editor-fold>
    
    public static ArrayList<Grade> getAllInstances(Student s) throws SQLException, UserNotFound, ClassNotFound {
        return new GradeDAO().findAll(s);
    }
    
    public static ArrayList<Grade> getAllInstances(Student s, Lesson l) throws SQLException {
        return new GradeDAO().findAll(s, l);
    }
    
    public void add(Teacher t) throws SQLException {
        new GradeDAO(this).add(t);
    }
    
    public void remove() throws SQLException {
        new GradeDAO(this).remove();
    }

    @Override
    public String toString() {
        return "Grade{" + "id=" + id + ", student=" + student + ", lesson=" + lesson + ", examtype=" + examtype + ", mark=" + mark + ", created=" + created + '}';
    }        

    @Override
    public int compareTo(Grade t) {
        return t.getCreated().compareTo(this.getCreated());
    }
}
