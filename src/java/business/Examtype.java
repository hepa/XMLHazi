package business;

import dao.ExamtypeDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class Examtype {
    private int id;
    private String name;
    private String description;

    public Examtype() {
    }

    public Examtype(int id, String name, String description) {
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
    
    public static Examtype getInstance(int id) throws SQLException {
        return new ExamtypeDAO().find(id);
    }
    
    public static ArrayList<Examtype> getAllInstances() throws SQLException {
        return new ExamtypeDAO().findAll();
    }
    
    @Override
    public String toString() {
        return "Examtype{" + "id=" + id + ", name=" + name + ", description=" + description + '}';
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
        final Examtype other = (Examtype) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
        
}
