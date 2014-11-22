package dao;

import business.Account;
import business.Class;
import business.Student;
import business.Teacher;
import errors.ClassNotFound;
import errors.UserNotFound;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ClassDAO extends DefaultDAO {

    private Class cl;

    public ClassDAO() {
        cl = new Class();
    }

    public ClassDAO(Class cl) {
        this.cl = cl;
    }

    public Class find(HashMap<String, String> keys) throws SQLException, ClassNotFound {

        int mapSize = keys.size();
        Iterator<String> ik = keys.keySet().iterator();
        Iterator<String> iv = keys.values().iterator();
        StringBuilder sb = new StringBuilder("SELECT * FROM `class` WHERE ");

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

            logger.trace(ps);
            rs = ps.executeQuery();

            if (rs.next()) {
                TeacherDAO tdao = new TeacherDAO();
                AccountDAO adao = new AccountDAO();
                HashMap<String, String> hmap = new HashMap<>();
                hmap.put("id", rs.getString("teacher_id"));

                cl.setId(rs.getInt("id"));
                cl.setName(rs.getString("name"));
                try {
                    cl.setFormMaster(tdao.find(hmap));
                } catch (UserNotFound ex) {
                    logger.error(ex.getMessage());
                }
                cl.setAccount(adao.find(rs.getInt("credit_card_number")));
            } else {
                throw new ClassNotFound();
            }
        } finally {
            closeStatementAndSet();
        }

        return cl;
    }

    public ArrayList<Class> search(HashMap<String, String> keys) throws SQLException, ClassNotFound {

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

        StringBuilder sb = new StringBuilder("SELECT * FROM `class` c, `students_in_class` sc WHERE c.id = sc.class_id AND");

        for (int i = 0; i < mapSize; i++) {
            sb.append("`").append(ik.next()).append("`").append("= ?");
            if (mapSize > 1 && i != mapSize - 1) {
                sb.append(" AND ");
            }
        }

        ArrayList<Class> classes = new ArrayList<>();
        try {
            ps = c.prepareStatement(sb.toString());

            for (int i = 1; i <= mapSize; i++) {
                String value = iv.next();
                ps.setString(i, value);
            }

            logger.trace(ps);
            rs = ps.executeQuery();

            if (rs.next()) {      
                cl = new Class();
                cl.setId(rs.getInt("id"));
                cl.setName(rs.getString("name"));
                cl.setFormMaster(Teacher.getInstance(rs.getString("teacher_id")));
                cl.setAccount(Account.getInstance(rs.getInt("credit_card_number")));
                
                classes.add(cl);
            }
            
            return classes;
        } finally {            
            closeStatementAndSet();
        }       
    }

    public Class find(int id) throws SQLException, UserNotFound {
        try {
            ps = c.prepareStatement("SELECT * FROM `class` WHERE `id` = ?");
            ps.setInt(1, id);

            logger.trace(ps);
            rs = ps.executeQuery();

            HashMap<String, String> hMap = new HashMap<>();
            while (rs.next()) {
                cl = new Class();
                cl.setId(id);
                cl.setName(rs.getString("name"));
                hMap.put("id", rs.getString("teacher_id"));
                cl.setFormMaster(new TeacherDAO().find(hMap));
                cl.setAccount(new AccountDAO().find(rs.getInt("credit_card_number")));
            }

            return cl;
        } finally {
            closeStatementAndSet();
        }
    }

    public ArrayList<Class> findAll() throws SQLException {
        ArrayList<Class> classes = new ArrayList<>();

        try {
            ps = c.prepareStatement("SELECT * FROM `class`");

            logger.trace(ps);
            rs = ps.executeQuery();

            while (rs.next()) {
                Class o = new Class();
                o.setId(rs.getInt("id"));
                o.setName(rs.getString("name"));

                TeacherDAO tdao = new TeacherDAO();
                AccountDAO adao = new AccountDAO();
                HashMap<String, String> hmap = new HashMap<>();
                hmap.put("id", rs.getString("teacher_id"));

                try {
                    o.setFormMaster(tdao.find(hmap));
                } catch (UserNotFound ex) {
                    logger.error(ex.getMessage());
                }
                o.setAccount(adao.find(rs.getInt("credit_card_number")));

                PreparedStatement ps2 = c.prepareStatement("SELECT * FROM `students_in_class` WHERE `class_id` = ?");
                ps2.setInt(1, o.getId());
                ResultSet rs2 = ps2.executeQuery();
                ArrayList<Student> students = new ArrayList<>();

                while (rs2.next()) {
                    students.add(Student.getInstance(rs2.getString("student_id")));
                }

                rs2.close();
                ps2.close();

                o.setStudents(students);
                classes.add(o);
            }
        } finally {
            closeStatementAndSet();
        }

        return classes;
    }

    public ArrayList<Class> findAllNames() throws SQLException {
        try {
            ps = c.prepareStatement("SELECT `id`, `name` FROM `class`");

            logger.trace(ps);
            rs = ps.executeQuery();

            ArrayList<Class> classes = new ArrayList<>();
            while (rs.next()) {
                cl = new Class();
                cl.setId(rs.getInt(1));
                cl.setName(rs.getString(2));

                classes.add(cl);
            }

            return classes;
        } finally {
            closeStatementAndSet();
        }
    }

    public ArrayList<Student> findStudents() throws SQLException, UserNotFound {
        try {
            ps = c.prepareStatement("SELECT * FROM `students_in_class` WHERE `class_id` = ?");
            ps.setInt(1, cl.getId());

            logger.trace(ps);
            rs = ps.executeQuery();

            ArrayList<Student> students = new ArrayList<>();
            while (rs.next()) {
                students.add(Student.getInstance(rs.getString("student_id")));
            }

            return students;
        } finally {
            closeStatementAndSet();
        }
    }

    public void add() throws SQLException {        
        try {
            ps = c.prepareStatement("INSERT INTO `class` (`name`,`teacher_id`,`credit_card_number`) VALUES(?,?,?)");
            ps.setString(1, cl.getName());
            ps.setString(2, cl.getFormMaster().getIdCardNumber());
            try {
                ps.setInt(3, cl.getAccount().getNumber());

//                try {
//                    adao.find(cl.getAccount().getNumber());
//                } catch (AccountNotFound anf) {
//                    adao.add(cl.getAccount());
//                }

            } catch (NullPointerException npe) {
                ps.setNull(3, java.sql.Types.INTEGER);
            }

            logger.trace(ps);
            ps.executeUpdate();
        } finally {
            closeStatementAndSet();
        }
    }

    public void updateAccount() throws SQLException {
        try {
            ps = c.prepareStatement("UPDATE `class` SET `credit_card_number` = ? WHERE `id` = ?");
            ps.setInt(1, cl.getAccount().getNumber());
            ps.setInt(2, cl.getId());

            logger.trace(ps);
            ps.executeUpdate();
        } finally {
            closeStatementAndSet();
        }
    }
}
