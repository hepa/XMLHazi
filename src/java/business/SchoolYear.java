package business;

import dao.SchoolYearDAO;
import java.sql.SQLException;
import java.util.Date;

public class SchoolYear {
    private int id;
    private String name;
    private Date from;
    private Date to;

    public SchoolYear() {
    }

    public SchoolYear(int id, String name, Date from, Date to) {
        this.id = id;
        this.name = name;
        this.from = from;
        this.to = to;
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

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }
    // </editor-fold>
    
    public static SchoolYear getInstance(int id) throws SQLException {
        return new SchoolYearDAO().find(id);
    }
    
    public static SchoolYear getInstance(String name) throws SQLException {
        return new SchoolYearDAO().find(name);
    }

    @Override
    public String toString() {
        return "SchoolYear{" + "id=" + id + ", name=" + name + ", from=" + from + ", to=" + to + '}';
    }        
}
