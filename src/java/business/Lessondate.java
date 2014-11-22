package business;

import dao.LessondateDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class Lessondate {
    private String day;
    private String hour;
    private String minutes;

    public Lessondate() {
    }

    public Lessondate(String day, String hour, String minutes) {
        this.day = day;
        this.hour = hour;
        this.minutes = minutes;
    }

    // <editor-fold desc="Getter&Setter" defaultstate="collapsed">
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }
    // </editor-fold>
    
    public static ArrayList<Lessondate> getInstance(Lesson l) throws SQLException {
        return new LessondateDAO().find(l);
    }

    @Override
    public String toString() {
        return "Lessondate{" + "day=" + day + ", hour=" + hour + ", minutes=" + minutes + '}';
    }        
}
