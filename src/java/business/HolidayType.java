package business;

import dao.HolidayTypeDAO;
import java.sql.SQLException;

public class HolidayType {
    private int id;
    private String name;

    public HolidayType() {
    }

    public HolidayType(int id, String name) {
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
    
    public static HolidayType getInstance(int id) throws SQLException {
        return new HolidayTypeDAO().find(id);
    }

    @Override
    public String toString() {
        return "HolidayType{" + "id=" + id + ", name=" + name + '}';
    }        
}
