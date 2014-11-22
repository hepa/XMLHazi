package business;

import dao.SubjectDAO;
import java.sql.SQLException;

public class Subject {
    private int id;
    private String name;
    private String description;

    public Subject() {
    }

    public Subject(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    // </editor-fold>
    
    public static Subject getInstance(int id) throws SQLException {
        return new SubjectDAO().find(id);
    }
    
    public static Subject getInstance(String name) throws SQLException {
        return new SubjectDAO().find(name);
    }
    
    @Override
    public String toString() {
        return "Subject{" + "id=" + id + ", name=" + name + ", description=" + description + '}';
    }        
}
