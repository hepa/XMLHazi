package business;

import dao.ClassRoomDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class ClassRoom {
    private String name;
    private String description;

    public ClassRoom() {
    }

    public ClassRoom(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // <editor-fold desc="Getter&Setter" defaultstate="collapsed">
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //</editor-fold>
    
    public static ClassRoom getInstance(String name) throws SQLException {
        return new ClassRoomDAO().find(name);
    }
    
    public static ArrayList<ClassRoom> getAllInstances() throws SQLException {
        return new ClassRoomDAO().findAll();
    }
    
    public void add() throws SQLException {
        new ClassRoomDAO(this).add();
    }
    
    public void remove() throws SQLException {
        new ClassRoomDAO(this).remove();
    }
    
    public void update() throws SQLException {
        new ClassRoomDAO(this).update();
    }
    
    @Override
    public String toString() {
        return "ClassRoom{" + "name=" + name + ", description=" + description + '}';
    }        

    @Override
    public int hashCode() {
        int hash = 5;
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
        final ClassRoom other = (ClassRoom) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
        
}
