package business;

import dao.ItemDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Item {
    private int id;
    private Account account;
    private Itemtype itemtype;
    private long amount;
    private Date created;
    private boolean done;
    private Date payTime;

    public Item() {
    }

    public Item(int id, Account account, Itemtype itemtype, long amount, Date created, boolean done, Date payTime) {
        this.id = id;
        this.account = account;
        this.itemtype = itemtype;
        this.amount = amount;
        this.created = created;
        this.done = done;
        this.payTime = payTime;
    }

    // <editor-fold desc="Getter&Setter" defaultstate="collapsed">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Itemtype getItemtype() {
        return itemtype;
    }

    public void setItemtype(Itemtype itemtype) {
        this.itemtype = itemtype;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }    
    
    // </editor-fold>

    public static Item getInstance(int id) throws SQLException {
        return new ItemDAO().find(id);
    }
    
    public static ArrayList<Item> getAllInstances(Account a) throws SQLException {
        return new ItemDAO().findAll(a);
    }
    
    public static ArrayList<Item> getAllInstances(Student s) throws SQLException {
        return new ItemDAO().findAll(s);
    }
    
    public void add() throws SQLException {
        new ItemDAO(this).add();
    }
    
    public void remove() throws SQLException {
        new ItemDAO(this).remove();
    }
    
    public void deposit(Family f) throws SQLException {
        new ItemDAO(this).deposit(f);
        account.deposit((int)amount);
        f.getAccount().withdraw((int)amount);
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", account=" + account + ", itemtype=" + itemtype + ", amount=" + amount + ", created=" + created + ", done=" + done + ", payTime=" + payTime + '}';
    }       
        
}
