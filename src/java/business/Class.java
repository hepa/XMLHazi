package business;

import dao.ClassDAO;
import errors.UserNotFound;
import java.sql.SQLException;
import java.util.ArrayList;

public class Class {
    private int id;
    private String name;
    private ArrayList<Student> students;
    private Teacher formMaster;
    private Account account;    

    public Class() {
        id = 0;
        name = null;
        students = null;
        formMaster = null;
        account = null;
    }

    public Class(int id, String name, ArrayList<Student> students, Teacher formMaster, Account account) {
        this.id = id;
        this.name = name;
        this.students = students;
        this.formMaster = formMaster;
        this.account = account;
    }

    // <editor-fold desc="Getter&Setter" defaultstate="collapsed">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public Teacher getFormMaster() {
        return formMaster;
    }

    public void setFormMaster(Teacher formMaster) {
        this.formMaster = formMaster;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
    // </editor-fold>
    
    public static Class getInstance(int id) throws SQLException, UserNotFound {
        return new ClassDAO().find(id);
    }
    
    public static ArrayList<Class> getAllInstances() throws SQLException {
        return new ClassDAO().findAll();
    }
    
    public static ArrayList<Class> getAllClassNames() throws SQLException {
        return new ClassDAO().findAllNames();
    }
    
    public void findStudents() throws SQLException, UserNotFound {
        this.setStudents(new ClassDAO(this).findStudents());
    }
    
    public void add() throws SQLException {
        new ClassDAO(this).add();
    }
    
    public void updateAccount() throws SQLException {
        new ClassDAO(this).updateAccount();
    }

    @Override
    public String toString() {
        return "Class{" + "id=" + id + ", name=" + name + ", students=" + students + ", formMaster=" + formMaster + ", account=" + account + '}';
    }            
    
}
