package business;

import dao.MeetingDAO;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Meeting {    
    private Timestamp from;
    private Timestamp to;
    private MeetingType type;
    private String comment;

    public Meeting() {
    }

    public Meeting(Timestamp from, Timestamp to, MeetingType type, String comment) {        
        this.from = from;
        this.to = to;
        this.type = type;
        this.comment = comment;
    }

    // <editor-fold desc="Getter&Setter" defaultstate="collapsed">
    public Date getFrom() {
        return from;
    }

    public void setFrom(Timestamp from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Timestamp to) {
        this.to = to;
    }

    public MeetingType getType() {
        return type;
    }

    public void setType(MeetingType type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    // </editor-fold>
    
    public static Meeting getInstance(int classId, Date dateFrom) throws SQLException {
        return new MeetingDAO().find(classId, dateFrom);
    }
    
    public static ArrayList<Meeting> getAllInstances(Class cl) throws SQLException {
        return new MeetingDAO().findAll(cl);
    }
    
    public void add(Class cl) throws SQLException {
        new MeetingDAO(this).add(cl);
    }
    
    public void remove(Class cl) throws SQLException {
        new MeetingDAO(this).remove(cl);
    }        
    
    @Override
    public String toString() {
        return "Meeting{" + "from=" + from + ", to=" + to + ", type=" + type + ", comment=" + comment + '}';
    }      
    
}
