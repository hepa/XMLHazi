package business;

import dao.MeetingTypeDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class MeetingType {
    private int id;
    private String name;
    private String description;

    public MeetingType() {
        this.id = 0;
        this.name = null;
        this.description = null;
    }

    public MeetingType(int id, String name, String description) {
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
    
    public static MeetingType getInstance(int id) throws SQLException {
        return new MeetingTypeDAO().find(id);
    }
    
    public static ArrayList<MeetingType> getAllInstances() throws SQLException {
        return new MeetingTypeDAO().findAll();
    }        

    @Override
    public String toString() {
        return "MeetingType{" + "id=" + id + ", name=" + name + ", description=" + description + '}';
    }

}
