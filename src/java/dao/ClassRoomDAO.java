package dao;

import business.ClassRoom;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClassRoomDAO extends DefaultDAO {
    private ClassRoom cr;

    public ClassRoomDAO() {
        cr = null;
    }

    public ClassRoomDAO(ClassRoom cr) {
        this.cr = cr;
    }        
    
    public ClassRoom find(String name) throws SQLException {
        try {
            ps = c.prepareStatement("SELECT * FROM `classroom` WHERE `name` = ?");
            ps.setString(1, name);
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                cr = new ClassRoom();
                cr.setName(name);
                cr.setDescription(rs.getString("desc"));
            }
            
            return this.cr;
        } finally {
            closeStatementAndSet();
        }
    }
    
    public ArrayList<ClassRoom> findAll() throws SQLException {
        try {
            ps = c.prepareStatement("SELECT * FROM `classroom`");
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            ArrayList<ClassRoom> classrooms = new ArrayList<>();
            while (rs.next()) {
                cr = new ClassRoom();
                cr.setName(rs.getString("name"));
                cr.setDescription(rs.getString("desc"));
                
                classrooms.add(cr);
            }
            
            return classrooms;
        } finally {
            closeStatementAndSet();
        }
    }
    
    public void add() throws SQLException {
        try {
            ps = c.prepareStatement("INSERT INTO `classroom` VALUES(?,?)");
            ps.setString(1, cr.getName());
            ps.setString(2, cr.getDescription());
            
            logger.trace(ps);
            ps.executeUpdate();
        } finally {
            closeStatementAndSet();
        }
    }
    
    public void remove() throws SQLException {
        try {
            ps = c.prepareStatement("DELETE FROM `classroom` WHERE `name` = ?");
            ps.setString(1, cr.getName());            
            
            logger.trace(ps);
            ps.executeUpdate();
        } finally {
            closeStatementAndSet();
        }
    }
    
    public void update() throws SQLException {
        try {
            ps = c.prepareStatement("UPDATE `classroom` SET `desc` = ? WHERE `name` = ?");
            ps.setString(1, cr.getDescription());
            ps.setString(2, cr.getName());
            
            logger.trace(ps);
            ps.executeUpdate();
        } finally {
            
        }
    }
}
