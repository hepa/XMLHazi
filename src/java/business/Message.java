package business;

import dao.MessageDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Message implements Comparable<Message> {

    private int id;
    private String subject;
    private String body;
    private Date created;
    private ArrayList<Person> to;
    private Person from;
    private boolean read;

    public Message() {
    }

    public Message(int id, String subject, String body, Date created, ArrayList<Person> to, Person from, boolean read) {
        this.id = id;
        this.subject = subject;
        this.body = body;
        this.created = created;
        this.to = to;
        this.from = from;
        this.read = read;
    } 

    public Message(String subject, String body, ArrayList<Person> to, Person from) {
        this.subject = subject;
        this.body = body;
        this.to = to;
        this.from = from;
    }
        

    public Message(String subject, String body, Person to, Person from) {
        this.subject = subject;
        this.body = body;
        ArrayList<Person> tos = new ArrayList<>();
        tos.add(to);
        this.to = tos;
        this.from = from;
    }        
    
    // <editor-fold desc="Getter&Setter" defaultstate="collapsed">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public ArrayList<Person> getTo() {
        return to;
    }

    public void setTo(ArrayList<Person> to) {
        this.to = to;
    }

    public Person getFrom() {
        return from;
    }

    public void setFrom(Person from) {
        this.from = from;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }        
    
    // </editor-fold>
    
    public static Message getInstance(int id) throws SQLException {
        return new MessageDAO().find(id);
    }
    
    public static ArrayList<Message> getAllInstances(String username) throws SQLException {
        return new MessageDAO().findAll(username);
    }

    public void send() throws SQLException {
        new MessageDAO(this).send();
    }
    
    public void markRead() throws SQLException {
        new MessageDAO(this).markRead();
    }
    
    public void markUnRead() throws SQLException {
        new MessageDAO(this).markUnRead();
    }
    
    public void delete() throws SQLException {
        new MessageDAO(this).delete();
    }

    @Override
    public String toString() {
        return "Message{" + "id=" + id + ", subject=" + subject + ", body=" + body + ", created=" + created + ", to=" + to + ", from=" + from + ", read=" + read + '}';
    }    

    @Override
    public int compareTo(Message t) {
        return t.getCreated().compareTo(created);
    }
}
