package business;

import dao.HomeworkDAO;
import errors.ClassNotFound;
import errors.UserNotFound;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Homework {
    private int id;
    private Lesson lesson;
    private String content;
    private Date created;
    private Mark mark;
    private Date markRegistered;
    private boolean done;

    public Homework() {
    }

    public Homework(int id, Lesson lesson, String content, Date created, Mark mark, Date markRegistered, boolean done) {
        this.id = id;
        this.lesson = lesson;
        this.content = content;
        this.created = created;
        this.mark = mark;
        this.markRegistered = markRegistered;
        this.done = done;
    }

    // <editor-fold desc="Getter&Setter" defaultstate="collapsed">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public Date getMarkRegistered() {
        return markRegistered;
    }

    public void setMarkRegistered(Date markRegistered) {
        this.markRegistered = markRegistered;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
    // </editor-fold>
    
    public static Homework getInstance(int id) throws SQLException, UserNotFound, ClassNotFound {
        return new HomeworkDAO().find(id);
    }
    
    public static Homework getInstance(int id, Student s) throws SQLException, UserNotFound, ClassNotFound {
        return new HomeworkDAO().find(id, s);
    }
    
    public static ArrayList<Homework> getAllInstances(Student s) throws SQLException, UserNotFound, ClassNotFound {
        return new HomeworkDAO().findAll(s);
    }
    
    public static ArrayList<Homework> getAllInstances(Lesson l) throws SQLException, UserNotFound, ClassNotFound {
        return new HomeworkDAO().findAll(l);
    }
    
    public static ArrayList<Homework> getAllInstances(Student s, Lesson l) throws SQLException, UserNotFound, ClassNotFound {
        return new HomeworkDAO().findAll(s, l);
    }
    
    public ArrayList<Student> findStudents() throws SQLException, UserNotFound {
        return new HomeworkDAO(this).findStudents();
    }
    
    public void add() throws SQLException {
        new HomeworkDAO(this).add();
    }
    
    public void addGrade(Student s, int grade) throws SQLException {
        new HomeworkDAO(this).addGrade(s, grade);
    }

    public void remove() throws SQLException {
        new HomeworkDAO(this).remove();
    }
    
    public void checkDone(Student s) throws SQLException {
        new HomeworkDAO(this).checkDone(s);
    }
    
    public void checkUnDone(Student s) throws SQLException {
        new HomeworkDAO(this).checkUnDone(s);
    }
    
    @Override
    public String toString() {
        return "Homework{" + "id=" + id + ", lesson=" + lesson + ", content=" + content + ", created=" + created + ", mark=" + mark + ", markRegistered=" + markRegistered + ", done=" + done + '}';
    }   

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Homework other = (Homework) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
        
}
