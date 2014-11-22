package business;

import dao.HolidayDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Holiday {
    private int id;
    private SchoolYear year;
    private HolidayType type;
    private Date from;
    private Date to;
    private String description;

    public Holiday() {
    }

    public Holiday(int id, SchoolYear year, HolidayType type, Date from, Date to, String description) {
        this.id = id;
        this.year = year;
        this.type = type;
        this.from = from;
        this.to = to;
        this.description = description;
    }

    // <editor-fold desc="Getter&Setter" defaultstate="collapsed">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SchoolYear getYear() {
        return year;
    }

    public void setYear(SchoolYear year) {
        this.year = year;
    }

    public HolidayType getType() {
        return type;
    }

    public void setType(HolidayType type) {
        this.type = type;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    // </editor-fold>
    
    public static ArrayList<Holiday> getAllInstances(SchoolYear year) throws SQLException {
        return new HolidayDAO().findAll(year);
    }

    @Override
    public String toString() {
        return "Holiday{" + "id=" + id + ", year=" + year + ", type=" + type + ", from=" + from + ", to=" + to + ", description=" + description + '}';
    }        
}
