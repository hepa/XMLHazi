package business;

import dao.MarkDAO;
import java.sql.SQLException;

public class Mark {
    private int id;
    private String description;

    public Mark() {
    }

    public Mark(int id, String description) {
        this.id = id;
        this.description = description;
    }

    // <editor-fold desc="Getter&Setter" defaultstate="collapsed">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    // </editor-fold>
    
    public static Mark getInstance(int id) throws SQLException {
        return new MarkDAO().find(id);
    }
    
    public static Mark getInstance(String desc) throws SQLException {
        return new MarkDAO().find(desc);
    }

    @Override
    public String toString() {
        return "Mark{" + "id=" + id + ", description=" + description + '}';
    }        
}
