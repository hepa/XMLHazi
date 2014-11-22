package business;

import dao.ConsultingHourDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConsultingHour {
    private String day;
    private String from;
    private String to;
    private Teacher teacher;

    public ConsultingHour() {
    }

    public ConsultingHour(String day, String from, String to, Teacher teacher) {
        this.day = day;
        this.from = from;
        this.to = to;
        this.teacher = teacher;
    }    

    // <editor-fold desc="Getter&Setter" defaultstate="collapsed">
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    
    //</editor-fold>
    
    public static ConsultingHour getInstance(Teacher t, String day, String from) throws SQLException {
        return new ConsultingHourDAO().find(t, day, from);
    }
    
    public static ArrayList<ConsultingHour> getAllInstances(Teacher t) throws SQLException {
        return new ConsultingHourDAO().findAll(t);
    }
    
    public void add() throws SQLException {
        new ConsultingHourDAO(this).add();
    }
    
    public void delete() throws SQLException {
        new ConsultingHourDAO(this).remove();
    }

    @Override
    public String toString() {
        return "ConsultingHour{" + "day=" + day + ", from=" + from + ", to=" + to + ", teacher=" + teacher + '}';
    }
   
}
