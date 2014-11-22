package dao;

import business.City;
import business.Student;
import business.Class;
import business.Homework;
import business.Mark;
import dao.CityDAO;
import errors.AccountNotFound;
import errors.CityNotFound;
import errors.ClassNotFound;
import errors.UserNotFound;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import util.MD5;

public class StudentDAO extends DefaultDAO {

    private Student student;

    public StudentDAO() {
        student = null;
    }

    public StudentDAO(Student student) {
        this.student = student;
    }        

    public Student find(String studentIdNumber) throws SQLException {
        try {
            ps = c.prepareStatement("SELECT * FROM `student` WHERE `id` = ?");
            ps.setString(1, studentIdNumber);
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                student = new Student();                
                if (rs.getString("zip_code") != null) {
                    try {
                        student.setCity(City.getInstance(rs.getString("zip_code")));
                    } catch (CityNotFound ex) {
                        student.setCity(null);
                    }
                }
                student.setStudentIdNumber(studentIdNumber);
                student.setIdCardNumber(rs.getString("id_card_number"));
                student.setGender(rs.getString("gender"));
                student.setFirstName(rs.getString("first_name"));
                student.setMiddleName(rs.getString("middle_name"));
                student.setLastName(rs.getString("last_name"));
                student.setEmail(rs.getString("email_address"));
                student.setMobileNumber(rs.getString("mobile"));
                student.setDateOfBirth(rs.getDate("date_of_birth"));
                student.setDateOfJoin(rs.getDate("date_of_join"));
                student.setStatus(rs.getBoolean("status"));
                student.setUsername(rs.getString("username"));
                student.setAddress(rs.getString("address"));
            }
            
            return student;
        } finally {
            closeStatementAndSet();
        }
    }        
    
    public Student find(HashMap<String, String> keys) throws UserNotFound, SQLException {
        student = new Student();                   

        int mapSize = keys.size();
        Iterator<String> ik = keys.keySet().iterator();
        Iterator<String> iv = keys.values().iterator();
        
        HashMap<String, String> newKeys = new HashMap<>();
        for (int i = 0; i < mapSize; i++) {
            String value = iv.next();
            String key = ik.next();
            if (!value.isEmpty()) {                
                newKeys.put(key, value);
            } 
        }
        
        mapSize = newKeys.size();
        ik = newKeys.keySet().iterator();
        iv = newKeys.values().iterator();
        
        StringBuilder sb = new StringBuilder("SELECT * FROM `student` WHERE ");

        for (int i = 0; i < mapSize; i++) {
            sb.append("`").append(ik.next()).append("`").append("= ?");
            if (mapSize > 1 && i != mapSize - 1) {
                sb.append(" AND ");
            }
        }


        try {
            ps = c.prepareStatement(sb.toString());
            for (int i = 1; i <= mapSize; i++) {
                String value = iv.next();
                ps.setString(i, value);
                logger.trace(value);
            }

            logger.trace(ps);
            rs = ps.executeQuery();

            if (rs.next()) {                
                if (rs.getString("zip_code") != null) {
                    try {
                        student.setCity(City.getInstance(rs.getString("zip_code")));
                    } catch (CityNotFound ex) {
                        student.setCity(null);
                    }
                }
                student.setStudentIdNumber(rs.getString("id"));
                student.setIdCardNumber(rs.getString("id_card_number"));
                student.setGender(rs.getString("gender"));
                student.setFirstName(rs.getString("first_name"));
                student.setMiddleName(rs.getString("middle_name"));
                student.setLastName(rs.getString("last_name"));
                student.setEmail(rs.getString("email_address"));
                student.setMobileNumber(rs.getString("mobile"));
                student.setDateOfBirth(rs.getDate("date_of_birth"));
                student.setDateOfJoin(rs.getDate("date_of_join"));
                student.setStatus(rs.getBoolean("status"));
                student.setUsername(rs.getString("username"));
                student.setAddress(rs.getString("address"));
            } else {
                throw new UserNotFound();
            }
        } finally {
            closeStatementAndSet();
        }
        
        return student;
    }
    
    public ArrayList<Student> search(HashMap<String, String> keys) throws SQLException {                           
        int mapSize = keys.size();                
        
        Iterator<String> ik = keys.keySet().iterator();
        Iterator<String> iv = keys.values().iterator();
        
        HashMap<String, String> newKeys = new HashMap<>();
        for (int i = 0; i < mapSize; i++) {
            String value = iv.next();
            String key = ik.next();
            if (!value.isEmpty()) {                
                newKeys.put(key, value);
            } 
        }
        
        mapSize = newKeys.size();
        
        if (mapSize == 0) {
            return null;
        }
        
        ik = newKeys.keySet().iterator();
        iv = newKeys.values().iterator();
        
        StringBuilder sb = new StringBuilder("SELECT * FROM `student` s, `students_in_class` sc WHERE s.id = sc.student_id AND ");

        for (int i = 0; i < mapSize; i++) {
            sb.append("lower(`").append(ik.next()).append("`) ").append("LIKE ?");
            if (mapSize > 1 && i != mapSize - 1) {
                sb.append(" AND ");
            }
        }

        ArrayList<Student> students = new ArrayList<>();
        try {
            ps = c.prepareStatement(sb.toString());
            for (int i = 1; i <= mapSize; i++) {
                String value = iv.next();
                ps.setString(i, "%" + value + "%");
                logger.trace(value);
            }

            logger.trace(ps);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                student = new Student();                
                if (rs.getString("zip_code") != null) {
                    try {
                        student.setCity(City.getInstance(rs.getString("zip_code")));
                    } catch (CityNotFound ex) {
                        student.setCity(null);
                    }
                }
                student.setStudentIdNumber(rs.getString("id"));
                student.setIdCardNumber(rs.getString("id_card_number"));
                student.setGender(rs.getString("gender"));
                student.setFirstName(rs.getString("first_name"));
                student.setMiddleName(rs.getString("middle_name"));
                student.setLastName(rs.getString("last_name"));
                student.setEmail(rs.getString("email_address"));
                student.setMobileNumber(rs.getString("mobile"));
                student.setDateOfBirth(rs.getDate("date_of_birth"));
                student.setDateOfJoin(rs.getDate("date_of_join"));
                student.setStatus(rs.getBoolean("status"));
                student.setUsername(rs.getString("username"));
                student.setAddress(rs.getString("address"));                                
                
                students.add(student);
            } 
        } finally {
            closeStatementAndSet();
        }
        
        return students;
    }

    public ArrayList<Student> findAll() throws SQLException {
        ArrayList<Student> students = new ArrayList<>();

        try {
            ps = c.prepareStatement("SELECT * FROM `student`");
            
            logger.trace(ps);
            rs = ps.executeQuery();

            while (rs.next()) {
                Student s = new Student();                

                if (rs.getString("zip_code") != null) {
                    try {
                        s.setCity(City.getInstance(rs.getString("zip_code")));
                    } catch (CityNotFound ex) {
                        s.setCity(null);
                    }
                }

                s.setStudentIdNumber(rs.getString("id"));
                s.setIdCardNumber(rs.getString("id_card_number"));
                s.setGender(rs.getString("gender"));
                s.setFirstName(rs.getString("first_name"));
                s.setMiddleName(rs.getString("middle_name"));
                s.setLastName(rs.getString("last_name"));
                s.setEmail(rs.getString("email_address"));
                s.setMobileNumber(rs.getString("mobile"));
                s.setDateOfBirth(rs.getDate("date_of_birth"));
                s.setDateOfJoin(rs.getDate("date_of_join"));
                s.setStatus(rs.getBoolean("status"));
                s.setUsername(rs.getString("username"));
                s.setAddress(rs.getString("address"));

                students.add(s);
            }
        } finally {
            closeStatementAndSet();
        }

        return students;
    }
    
    public Class findClass() throws SQLException, ClassNotFound, UserNotFound, AccountNotFound {
        try {
            ps = c.prepareStatement("SELECT * FROM `students_in_class` WHERE `student_id` = ?");
            ps.setString(1, student.getStudentIdNumber());
            
            logger.trace(ps);
            rs = ps.executeQuery();
                
            Class cl = null;
            while (rs.next()) {                
                cl = Class.getInstance(rs.getInt("class_id"));                                
            }
            
            return cl;
        } finally {
            closeStatementAndSet();
        }
    }
    
    public ArrayList<Homework> findHomeworks() throws SQLException, UserNotFound, ClassNotFound {
        try {
            ps = c.prepareStatement("SELECT * FROM `students_homeworks` WHERE `student_id` = ?");
            ps.setString(1, student.getStudentIdNumber());
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            ArrayList<Homework> hws = new ArrayList<>();
            while (rs.next()) {
                Homework hw = Homework.getInstance(rs.getInt("homework_id"));
                hw.setDone(rs.getBoolean("done"));
                hw.setMark(Mark.getInstance(rs.getInt("mark_id")));
                hw.setMarkRegistered(rs.getDate("mark_registered"));
                
                hws.add(hw);
            }
                        
            return hws;
        } finally {
            closeStatementAndSet();
        }
    }
    
    public ArrayList<Homework> findHomeworks(int id) throws SQLException, UserNotFound, ClassNotFound {
        try {
            ps = c.prepareStatement("SELECT * FROM `students_homeworks` WHERE `student_id` = ? AND `homework_id` = ?");
            ps.setString(1, student.getStudentIdNumber());
            ps.setInt(2, id);
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            ArrayList<Homework> hws = new ArrayList<>();
            while (rs.next()) {
                Homework hw = Homework.getInstance(id);
                hw.setDone(rs.getBoolean("done"));
                hw.setMark(Mark.getInstance(rs.getInt("mark_id")));
                hw.setMarkRegistered(rs.getDate("mark_registered"));
                
                hws.add(hw);
            }
                        
            return hws;
        } finally {
            closeStatementAndSet();
        }
    }

    public void add(String password, boolean active) throws SQLException {
        c.setAutoCommit(false);
        try (PreparedStatement psLogin = c.prepareStatement("INSERT INTO `login` (`username`, `password`, `active`) VALUES(?,?,?)")) {
            psLogin.setString(1, student.getUsername());
            psLogin.setString(2, MD5.Convert(password));
            if (active) {
                psLogin.setInt(3, 1);
            } else {
                psLogin.setInt(3, 0);
            }

            logger.info(psLogin.executeUpdate() + " row(s) has been inserted into neptun.login.");
        }

        ps = c.prepareStatement("INSERT INTO `student` VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        ps.setString(1, student.getStudentIdNumber());
        ps.setString(2, student.getIdCardNumber());
        ps.setString(3, student.getGender());
        ps.setString(4, student.getFirstName());
        ps.setString(5, student.getMiddleName());
        ps.setString(6, student.getLastName());
        ps.setString(7, student.getEmail());
        ps.setString(8, student.getMobileNumber());
        ps.setDate(9, new Date(student.getDateOfBirth().getYear(), student.getDateOfBirth().getMonth(), student.getDateOfBirth().getDay()));
        ps.setDate(10, new Date(student.getDateOfJoin().getYear(), student.getDateOfJoin().getMonth(), student.getDateOfJoin().getDay()));
        ps.setInt(11, (student.isStatus()) ? 1 : 0);
        ps.setString(12, student.getUsername());
        ps.setInt(13, student.getCity().getZipCode());
        ps.setString(14, student.getAddress());

        logger.info(ps.executeUpdate() + " row(s) has been inserted into neptun.student.");

        c.commit();
        c.setAutoCommit(true);
        closeStatementAndSet();
    }

    public void remove(HashMap<String, String> keys) throws SQLException {
        c.setAutoCommit(false);

        String username = null;
        int mapSize = keys.size();
        Iterator<String> ik = keys.keySet().iterator();
        Iterator<String> iv = keys.values().iterator();
        StringBuilder sb = new StringBuilder("DELETE FROM `student` WHERE ");

        for (int i = 0; i < mapSize; i++) {
            sb.append("`").append(ik.next()).append("`").append("= ?");
            if (mapSize > 1 && i != mapSize - 1) {
                sb.append(" AND ");
            }
        }

        ps = c.prepareStatement(sb.toString());
        try (PreparedStatement psSelect = c.prepareStatement(sb.replace(0, 6, "SELECT `username`").toString())) {
            for (int i = 1; i <= mapSize; i++) {
                String value = iv.next();
                ps.setString(i, value);
                psSelect.setString(i, value);
            }

            rs = psSelect.executeQuery();
            logger.info("Username has been selected from neptun.student for deleting purposes.");
            while (rs.next()) {
                username = rs.getString("username");
            }
        }
        ps.executeUpdate();
        logger.info("Student has been deleted from neptun.student.");

        try (PreparedStatement psLogin = c.prepareStatement("DELETE FROM `login` WHERE `username` = ?")) {
            psLogin.setString(1, username);
            psLogin.executeUpdate();
            logger.info(username + "'s login has been deleted from neptun.login.");
        }

        c.commit();
        c.setAutoCommit(true);
        closeStatementAndSet();
    }

    public void update(HashMap<String, String> newValues, HashMap<String, String> conditions) throws SQLException {
        int mapSizeNewValues = newValues.size();
        int mapSizeConditions = conditions.size();
        Iterator<String> nik = newValues.keySet().iterator();
        Iterator<String> niv = newValues.values().iterator();
        Iterator<String> cik = conditions.keySet().iterator();
        Iterator<String> civ = conditions.values().iterator();
        StringBuilder sb = new StringBuilder("UPDATE `student` SET ");

        for (int i = 0; i < mapSizeNewValues; i++) {
            sb.append("`").append(nik.next()).append("`").append("= ?");
            if (mapSizeNewValues > 1 && i != mapSizeNewValues - 1) {
                sb.append(", ");
            }
        }

        sb.append(" WHERE ");
        for (int i = 0; i < mapSizeConditions; i++) {
            sb.append("`").append(cik.next()).append("`").append("= ?");
            if (mapSizeConditions > 1 && i != mapSizeConditions - 1) {
                sb.append(" AND ");
            }
        }

        logger.trace(sb);
        ps = c.prepareStatement(sb.toString());
        for (int i = 1; i <= mapSizeNewValues; i++) {
            String value = niv.next();
            ps.setString(i, value);
        }
        for (int i = mapSizeNewValues + 1; i <= (mapSizeConditions + mapSizeNewValues); i++) {
            String value = civ.next();
            ps.setString(i, value);
        }
        logger.info(ps.executeUpdate() + " row(s) has been updated in neptun.student.");
        closeStatementAndSet();
    }
}
