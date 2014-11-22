package business;

import business.Class;
import dao.StudentDAO;
import errors.AccountNotFound;
import errors.ClassNotFound;
import errors.UserNotFound;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


public class Student extends Person {

    private String studentIdNumber;
    private Date dateOfJoin;
    private boolean status;
    private Class form;
    private ArrayList<Homework> homeworks;
    private Settings settings;
    private Family family;

    public Student() {
        this.studentIdNumber = "";
        this.dateOfJoin = new Date();
        this.status = false;   
        this.form = new Class();
        this.settings = new Settings();
    }

    public Student(String studentIdNumber, Date dateOfJoin, boolean status, Class form, ArrayList<Homework> homeworks, Settings settings, Family family) {
        this.studentIdNumber = studentIdNumber;
        this.dateOfJoin = dateOfJoin;
        this.status = status;
        this.form = form;
        this.homeworks = homeworks;
        this.settings = settings;
        this.family = family;
    }

    // <editor-fold desc="Getter&Setter" defaultstate="collapsed">

    public String getStudentIdNumber() {
        return studentIdNumber;
    }

    public void setStudentIdNumber(String studentIdNumber) {
        this.studentIdNumber = studentIdNumber;
    }

    public Date getDateOfJoin() {
        return dateOfJoin;
    }

    public void setDateOfJoin(Date dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Class getForm() {
        return form;
    }

    public void setForm(Class form) {
        this.form = form;
    }

    public ArrayList<Homework> getHomeworks() {
        return homeworks;
    }

    public void setHomeworks(ArrayList<Homework> homeworks) {
        this.homeworks = homeworks;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }
    // </editor-fold>
    
    public static Student getInstance(String studentIdNumber) throws SQLException {
        return new StudentDAO().find(studentIdNumber);
    }
    
    public static ArrayList<Student> getAllInstances() throws SQLException {
        return new StudentDAO().findAll();
    }
    
    public void findClass() throws SQLException, ClassNotFound, UserNotFound, AccountNotFound {
        this.setForm(new StudentDAO(this).findClass());
    }
    
    public void findHomeworks() throws SQLException, UserNotFound, ClassNotFound {
        this.setHomeworks(new StudentDAO(this).findHomeworks());
    }
    
    public void findHomeworks(int id) throws SQLException, UserNotFound, ClassNotFound {
        this.setHomeworks(new StudentDAO(this).findHomeworks(id));
    }    
    
    public void findSettings() throws SQLException {
        this.setSettings(Settings.getInstance(this));
    }
    
    public void findFamily() throws SQLException {
        this.setFamily(Family.getInstance(this));
    }
    
    public void add(String password, boolean active) throws SQLException {
        new StudentDAO(this).add(password, active);
    }
    
    @Override
    public String toString() {
        return "Student{" + "studentIdNumber=" + studentIdNumber + ", dateOfJoin=" + dateOfJoin + ", status=" + status + ", form=" + form + ", homeworks=" + homeworks + '}';
    }    
        
}
