package dao;

import business.Account;
import business.City;
import business.Family;
import business.Person;
import business.Student;
import errors.CityNotFound;
import errors.UserNotFound;
import java.sql.SQLException;

public class FamilyDAO extends DefaultDAO {
    private Family f;

    public FamilyDAO() {
    }

    public FamilyDAO(Family f) {
        this.f = f;
    }
    
    public Family find(String username) throws SQLException, UserNotFound {
        try {
            ps = c.prepareStatement("SELECT * FROM `family` WHERE `username` = ?");
            ps.setString(1, username);
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                f = new Family();
                f.setUsername(username);
                f.setId(rs.getInt("id"));
                f.setAddress(rs.getString("address"));
                f.setEmail(rs.getString("email_address"));
                f.setPhoneNumber(rs.getString("phone"));
                f.setStudent(Student.getInstance(rs.getString("student_id")));
                if (rs.getString("zip_code") != null) {
                    try {
                        f.setCity(City.getInstance(rs.getString("zip_code")));
                    } catch (CityNotFound ex) {
                        f.setCity(null);
                    }
                }
                f.setAccount(new Account(rs.getInt("account_number"), 0, null));
                
                String motherName = rs.getString("mother_name");
                String[] smn = motherName.split(" ");
                Person mother = new Person();
                mother.setFirstName(smn[0]);
                mother.setLastName(smn[1]);
                f.setMother(mother);
                
                String fatherName = rs.getString("father_name");
                String[] fmn = fatherName.split(" ");
                Person father = new Person();
                father.setFirstName(fmn[0]);
                father.setLastName(fmn[1]);
                f.setFather(father);                                
            } else {
                throw new UserNotFound();
            }
            
            return f;
        } finally {
            closeStatementAndSet();
        }
    }
    
    public Family find(int id) throws SQLException {
        try {
            ps = c.prepareStatement("SELECT * FROM `family` WHERE `id` = ?");
            ps.setInt(1, id);
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                f = new Family();
                f.setUsername(rs.getString("username"));
                f.setId(id);
                f.setAddress(rs.getString("address"));
                f.setEmail(rs.getString("email_address"));
                f.setPhoneNumber(rs.getString("phone"));
                f.setStudent(Student.getInstance(rs.getString("student_id")));
                if (rs.getString("zip_code") != null) {
                    try {
                        f.setCity(City.getInstance(rs.getString("zip_code")));
                    } catch (CityNotFound ex) {
                        f.setCity(null);
                    }
                }
                f.setAccount(new Account(rs.getInt("account_number"), 0, null));
                
                String motherName = rs.getString("mother_name");
                String[] smn = motherName.split(" ");
                Person mother = new Person();
                mother.setFirstName(smn[0]);
                mother.setLastName(smn[1]);
                f.setMother(mother);
                
                String fatherName = rs.getString("father_name");
                String[] fmn = fatherName.split(" ");
                Person father = new Person();
                father.setFirstName(fmn[0]);
                father.setLastName(fmn[1]);
                f.setFather(father);                                
            }
            
            return f;
        } finally {
            closeStatementAndSet();
        }
    }
    
    public Family find(Student s) throws SQLException {
        try {
            ps = c.prepareStatement("SELECT `id` FROM `family` WHERE `student_id` = ?");
            ps.setString(1, s.getStudentIdNumber());
            
            logger.trace(ps);
            rs = ps.executeQuery();
            while (rs.next()) {
                f = Family.getInstance(rs.getInt(1));                
            }
            
            return f;
        } finally {
            closeStatementAndSet();
        }
    }
    
    public void addAccount(Account a) throws SQLException {
        try {
            ps = c.prepareStatement("UPDATE `family` SET `account_number` = ? WHERE `id` = ?");
            ps.setInt(1, a.getNumber());
            ps.setInt(2, f.getId());
            
            logger.trace(ps);
            ps.executeUpdate();
        } finally {
            closeStatementAndSet();
        }
    }
}
