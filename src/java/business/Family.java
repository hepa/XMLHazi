package business;

import dao.FamilyDAO;
import errors.UserNotFound;
import java.sql.SQLException;

public class Family {
    private int id;
    private Person mother;
    private Person father;
    private String phoneNumber;
    private String email;
    private Student student;
    private String username;
    private City city;
    private String address;
    private Account account;
    private Settings settings;

    public Family() {
    }

    public Family(int id, Person mother, Person father, String phoneNumber, String email, Student student, String username, City city, String address, Account account, Settings settings) {
        this.id = id;
        this.mother = mother;
        this.father = father;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.student = student;
        this.username = username;
        this.city = city;
        this.address = address;
        this.account = account;
        this.settings = settings;
    }

    // <editor-fold desc="Getter&Setter" defaultstate="collapsed">

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getMother() {
        return mother;
    }

    public void setMother(Person mother) {
        this.mother = mother;
    }

    public Person getFather() {
        return father;
    }

    public void setFather(Person father) {
        this.father = father;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }
    // </editor-fold>
    
    public static Family getInstance(String username) throws SQLException, UserNotFound {
        return new FamilyDAO().find(username);
    }
    
    public static Family getInstance(int id) throws SQLException {
        return new FamilyDAO().find(id);
    }
    
    public static Family getInstance(Student s) throws SQLException {
        return new FamilyDAO().find(s);
    }
    
    public void findSettings() throws SQLException {
        this.setSettings(Settings.getInstance(this));
    }
    
    public void addAccount(Account a) throws SQLException {
        new FamilyDAO(this).addAccount(a);
    }

    @Override
    public String toString() {
        return "Family{" + "id=" + id + ", mother=" + mother + ", father=" + father + ", phoneNumber=" + phoneNumber + ", email=" + email + ", student=" + student + ", username=" + username + ", city=" + city + ", address=" + address + ", account=" + account + '}';
    }        
}
