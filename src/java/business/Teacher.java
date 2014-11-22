package business;

import business.Class;
import dao.TeacherDAO;
import errors.UserNotFound;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Teacher extends Person {
    private String phoneNumber;
    private Account account;
    private Class form;
    
    public Teacher() {
    }

    public Teacher(String phoneNumber, Account account, Class form, String idCardNumber, String identifier, String firstName, String middleName, String lastName, String gender, Date dateOfBirth, String email, String address, String mobileNumber, City city) {
        super(idCardNumber, identifier, firstName, middleName, lastName, gender, dateOfBirth, email, address, mobileNumber, city);
        this.phoneNumber = phoneNumber;
        this.account = account;
        this.form = form;
    }   

    // <editor-fold desc="Getter&Setter" defaultstate="collapsed">
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
    public Class getForm() {
        return form;
    }

    public void setForm(Class form) {
        this.form = form;
    }
    
    // </editor-fold>
    
    public static Teacher getInstance(String idCardNumber) throws SQLException {
       return new TeacherDAO().find(idCardNumber);
    }
    
    public static ArrayList<Teacher> getAllInstances() throws SQLException {
        return new TeacherDAO().findAll();
    }
    
    public void add(String password, boolean active) throws SQLException {
        new TeacherDAO(this).add(password, active);
    }
    
    public void findClass() throws SQLException, UserNotFound {
        this.setForm(new TeacherDAO(this).findClass());
    }

    @Override
    public String toString() {
        return super.toString() + " Teacher{" + "phoneNumber=" + phoneNumber + ", creditCard=" + account + '}';
    }
    
    
        
}
