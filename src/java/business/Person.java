package business;

import dao.PersonDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Person {
    private String idCardNumber;
    private String username;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private Date dateOfBirth;
    private String email;
    private String address;
    private String mobileNumber;
    private City city;

    public Person() {
    }

    public Person(String username, String firstName, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }        

    public Person(String username) {
        this.username = username;
        this.firstName = username;
        this.lastName = "";
    }        

    public Person(String idCardNumber, String identifier, String firstName, String middleName, String lastName, String gender, Date dateOfBirth, String email, String address, String mobileNumber, City city) {
        this.idCardNumber = idCardNumber;
        this.username = identifier;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.city = city;
    }  

    // <editor-fold desc="Getter&Setter" defaultstate="collapsed">
    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }       

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }        

    // </editor-fold>
    
    public static ArrayList<Person> getAllNames() throws SQLException {
        return new PersonDAO().findAllNames();
    }
    
    @Override
    public String toString() {
        return "Person{" + "idCardNumber=" + idCardNumber + ", identifier=" + username + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", email=" + email + ", address=" + address + ", city=" + city + '}';
    }   

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Person other = (Person) obj;
        if (!Objects.equals(this.idCardNumber, other.idCardNumber)) {
            return false;
        }
        return true;
    }   
        
}
