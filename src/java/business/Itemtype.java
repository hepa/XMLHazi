package business;

import dao.ItemtypeDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class Itemtype {
    private int id;
    private String name;
    private String description;

    public Itemtype() {
    }

    public Itemtype(int id, String name, String description) {
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

    public static Itemtype getInstance(int id) throws SQLException {
        return new ItemtypeDAO().find(id);
    }
    
    public static ArrayList<Itemtype> getAllInstances() throws SQLException {        
        return new ItemtypeDAO().findAll();
    }
    
    @Override
    public String toString() {
        return "Itemtype{" + "id=" + id + ", name=" + name + ", description=" + description + '}';
    }        
    
}
