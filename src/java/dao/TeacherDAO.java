package dao;

import business.Account;
import business.City;
import business.Class;
import business.Teacher;
import errors.CityNotFound;
import errors.UserNotFound;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import util.MD5;

public class TeacherDAO extends DefaultDAO {

    private Teacher t;

    public TeacherDAO() {
        t = null;
    }

    public TeacherDAO(Teacher t) {
        this.t = t;
    }        
    
    public Teacher find(String idCardNumber) throws SQLException {
        try {
            ps = c.prepareStatement("SELECT * FROM `teacher` WHERE `id` = ?");
            ps.setString(1, idCardNumber);
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                t = new Teacher();
                
                if (rs.getString("zip_code") != null) {
                    try {
                        t.setCity(City.getInstance(rs.getString("zip_code")));
                    } catch (CityNotFound ex) {
                        t.setCity(null);
                    }
                }
                t.setIdCardNumber(idCardNumber);
                t.setGender(rs.getString("gender"));
                t.setFirstName(rs.getString("first_name"));
                t.setMiddleName(rs.getString("middle_name"));
                t.setLastName(rs.getString("last_name"));
                t.setEmail(rs.getString("email_address"));
                t.setMobileNumber(rs.getString("mobile"));
                t.setPhoneNumber(rs.getString("phone"));
                t.setDateOfBirth(rs.getDate("date_of_birth"));
                t.setUsername(rs.getString("username"));
                t.setAddress(rs.getString("address"));
                t.setAccount(new Account(rs.getInt("account_number"), 0, null));
            }
            
            return t;
        } finally {
            closeStatementAndSet();
        }
    }

    public Teacher find(HashMap<String, String> keys) throws UserNotFound, SQLException {
        t = new Teacher();

        int mapSize = keys.size();
        Iterator<String> ik = keys.keySet().iterator();
        Iterator<String> iv = keys.values().iterator();
        StringBuilder sb = new StringBuilder("SELECT * FROM `teacher` WHERE ");

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
            }

            rs = ps.executeQuery();            

            if (rs.next()) {                
                if (rs.getString("zip_code") != null) {
                    try {
                        t.setCity(City.getInstance(rs.getString("zip_code")));
                    } catch (CityNotFound ex) {
                        t.setCity(null);
                    }
                }
                t.setIdCardNumber(rs.getString("id"));
                t.setGender(rs.getString("gender"));
                t.setFirstName(rs.getString("first_name"));
                t.setMiddleName(rs.getString("middle_name"));
                t.setLastName(rs.getString("last_name"));
                t.setEmail(rs.getString("email_address"));
                t.setMobileNumber(rs.getString("mobile"));
                t.setPhoneNumber(rs.getString("phone"));
                t.setDateOfBirth(rs.getDate("date_of_birth"));
                t.setUsername(rs.getString("username"));
                t.setAddress(rs.getString("address"));
                t.setAccount(new Account(rs.getInt("account_number"), 0, null));

            } else {
                throw new UserNotFound();
            }
        } finally {
            closeStatementAndSet();
        }

        return t;
    }

    public ArrayList<Teacher> findAll() throws SQLException {
        ArrayList<Teacher> teachers = new ArrayList<>();

        try {
            ps = c.prepareStatement("SELECT * FROM `teacher`");
            rs = ps.executeQuery();

            while (rs.next()) {                
                Teacher t = new Teacher();

                if (rs.getString("zip_code") != null) {
                    try {
                        t.setCity(City.getInstance(rs.getString("zip_code")));
                    } catch (CityNotFound ex) {
                        t.setCity(null);
                    }
                }

                t.setIdCardNumber(rs.getString("id"));
                t.setGender(rs.getString("gender"));
                t.setFirstName(rs.getString("first_name"));
                t.setMiddleName(rs.getString("middle_name"));
                t.setLastName(rs.getString("last_name"));
                t.setEmail(rs.getString("email_address"));
                t.setMobileNumber(rs.getString("mobile"));
                t.setPhoneNumber(rs.getString("phone"));
                t.setDateOfBirth(rs.getDate("date_of_birth"));
                t.setUsername(rs.getString("username"));
                t.setAddress(rs.getString("address"));
                t.setAccount(new Account(rs.getInt("account_number"), 0, null));

                teachers.add(t);
            }
        } finally {
            closeStatementAndSet();
        }

        return teachers;
    }
    
    public Class findClass() throws SQLException, UserNotFound {
        try {
            ps = c.prepareStatement("SELECT `id` FROM `class` WHERE `teacher_id` = ?");
            ps.setString(1, t.getIdCardNumber());
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            Class cl = null;
            while (rs.next()) {
                cl = Class.getInstance(rs.getInt(1));
            }
            
            return cl;
        } finally {
            closeStatementAndSet();
        }
    }

    public void add(String password, boolean active) throws SQLException {
        try {
            c.setAutoCommit(false);
            try (PreparedStatement psLogin = c.prepareStatement("INSERT INTO `login` (`username`, `password`, `active`) VALUES(?,?,?)")) {
                psLogin.setString(1, t.getUsername());
                psLogin.setString(2, MD5.Convert(password));
                if (active) {
                    psLogin.setInt(3, 1);
                } else {
                    psLogin.setInt(3, 0);
                }

                logger.info(psLogin.executeUpdate() + " row(s) has been inserted into neptun.login.");
            }

            ps = c.prepareStatement("INSERT INTO `teacher` (`id`, `gender`, `first_name`, `middle_name`, `last_name`, `phone`, `mobile`, `email_address`, `date_of_birth`, `username`, `zip_code`, `address` ) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, t.getIdCardNumber());
            ps.setString(2, t.getGender());
            ps.setString(3, t.getFirstName());
            ps.setString(4, t.getMiddleName());
            ps.setString(5, t.getLastName());
            ps.setString(6, t.getPhoneNumber());
            ps.setString(7, t.getMobileNumber());
            ps.setString(8, t.getEmail());
            ps.setDate(9, new Date(t.getDateOfBirth().getYear(), t.getDateOfBirth().getMonth(), t.getDateOfBirth().getDay()));
            ps.setString(10, t.getUsername());
            ps.setInt(11, t.getCity().getZipCode());
            ps.setString(12, t.getAddress());
            //ps.setInt(13, t.getAccount().getNumber());
            //ps.setString(13, "");

            logger.trace(ps.toString());
            logger.info(ps.executeUpdate() + " row(s) has been inserted into neptun.teacher.");

            c.commit();
            c.setAutoCommit(true);
        } finally {
            closeStatementAndSet();
        }
    }

    public void remove(HashMap<String, String> keys) throws SQLException {
        try {
            c.setAutoCommit(false);

            String username = null;
            int mapSize = keys.size();
            Iterator<String> ik = keys.keySet().iterator();
            Iterator<String> iv = keys.values().iterator();
            StringBuilder sb = new StringBuilder("DELETE FROM `teacher` WHERE ");

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
                logger.info("Username has been selected from neptun.teacher for deleting purposes.");
                while (rs.next()) {
                    username = rs.getString("username");
                }
            }
            ps.executeUpdate();
            logger.info("Teacher has been deleted from neptun.teacher.");

            try (PreparedStatement psLogin = c.prepareStatement("DELETE FROM `login` WHERE `username` = ?")) {
                psLogin.setString(1, username);
                psLogin.executeUpdate();
                logger.info(username + "'s login has been deleted from neptun.login.");
            }

            c.commit();
            c.setAutoCommit(true);
        } finally {
            closeStatementAndSet();
        }
    }

    public void update(HashMap<String, String> newValues, HashMap<String, String> conditions) throws SQLException {
        int mapSizeNewValues = newValues.size();
        int mapSizeConditions = conditions.size();
        Iterator<String> nik = newValues.keySet().iterator();
        Iterator<String> niv = newValues.values().iterator();
        Iterator<String> cik = conditions.keySet().iterator();
        Iterator<String> civ = conditions.values().iterator();
        StringBuilder sb = new StringBuilder("UPDATE `teacher` SET ");

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
        try {
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
        } finally {
            closeStatementAndSet();
        }
    }
}
