package business;

import dao.LessonDAO;
import errors.ClassNotFound;
import errors.UserNotFound;
import java.sql.SQLException;
import java.util.ArrayList;

public class Lesson {
    private int id;
    private Subject subject;
    private Teacher teacher;
    private SchoolYear year;
    private Class form;
    private ClassRoom classRoom;
    private ArrayList<Lessondate> dates;

    public Lesson() {
    }

    public Lesson(int id, Subject subject, Teacher teacher, SchoolYear year, Class form, ClassRoom classRoom, ArrayList<Lessondate> date) {
        this.id = id;
        this.subject = subject;
        this.teacher = teacher;
        this.year = year;
        this.form = form;
        this.classRoom = classRoom;
        this.dates = date;
    }

    // <editor-fold desc="Getter&Setter" defaultstate="collapsed">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public SchoolYear getYear() {
        return year;
    }

    public void setYear(SchoolYear year) {
        this.year = year;
    }

    public Class getForm() {
        return form;
    }

    public void setForm(Class form) {
        this.form = form;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    public ArrayList<Lessondate> getDates() {
        return dates;
    }

    public void setDates(ArrayList<Lessondate> date) {
        this.dates = date;
    }        
    // </editor-fold>
    
    public static Lesson getInstance(int id) throws SQLException, UserNotFound, ClassNotFound {
        return new LessonDAO().find(id);
    }
    
    public static ArrayList<Lesson> getAllInstances(Teacher t) throws SQLException, UserNotFound, ClassNotFound {
        return new LessonDAO().findAll(t);
    }
    
    public static ArrayList<Lesson> getAllInstances(Class cl) throws SQLException, UserNotFound, ClassNotFound {
        return new LessonDAO().findAll(cl);
    }
    
    public static ArrayList<Lesson> getAllInstances(ClassRoom cr) throws SQLException, UserNotFound, ClassNotFound {
        return new LessonDAO().findAll(cr);
    }
    
    public static ArrayList<Lesson> getAllInstances(String day) throws SQLException, UserNotFound, ClassNotFound {
        return new LessonDAO().findAll(day);
    }
    
    public void findClass() throws SQLException, UserNotFound {
        this.setForm(new LessonDAO(this).findClass());
    }
    
    public ArrayList<Homework> findHomeworks() throws SQLException {
        return new LessonDAO(this).findHomeworks();
    }

    @Override
    public String toString() {
        return "Lesson{" + "id=" + id + ", subject=" + subject + ", teacher=" + teacher + ", year=" + year + ", form=" + form + ", classRoom=" + classRoom + ", date=" + dates + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
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
        final Lesson other = (Lesson) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
     
}
