package business;

import dao.StatusDAO;
import java.sql.SQLException;

public class Status {
    private int id;
    private String name;

    public Status() {
    }

    public Status(int id, String name) {
        this.id = id;
        this.name = name;
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
    // </editor-fold>
    
    public static Status getInstance(int id) throws SQLException {
        return new StatusDAO().find(id);
    }
    
    public static Status getInstance(String name) throws SQLException {
        return new StatusDAO().find(name);
    }
    
    @Override
    public String toString() {
        return "Status{" + "id=" + id + ", name=" + name + '}';
    }
        
}
