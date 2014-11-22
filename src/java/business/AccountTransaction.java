package business;

import java.util.Date;

public class AccountTransaction {
    private int id;
    private Date created;
    private String description;

    public AccountTransaction() {
    }

    public AccountTransaction(int id, Date created, String description) {
        this.id = id;
        this.created = created;
        this.description = description;
    }

    // <editor-fold desc="Getter&Setter" defaultstate="collapsed">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    //</editor-fold>
    
    

    @Override
    public String toString() {
        return "AccountTransaction{" + "id=" + id + ", created=" + created + ", description=" + description + '}';
    }        
}
