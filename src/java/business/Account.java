package business;

import dao.AccountDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class Account {

    private int number;
    private int balance;
    private ArrayList<AccountTransaction> transactions;

    public Account() {
    }

    public Account(int number, int balance, ArrayList<AccountTransaction> transactions) {
        this.number = number;
        this.balance = balance;
        this.transactions = transactions;
    }

    // <editor-fold desc="Getter&Setter" defaultstate="collapsed">
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public ArrayList<AccountTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<AccountTransaction> transactions) {
        this.transactions = transactions;
    }
    // </editor-fold>

    public static Account getInstance(int number) throws SQLException {
        return new AccountDAO().find(number);
    }

    public void findTransactions() throws SQLException {
        this.setTransactions(new AccountDAO(this).findTransactions());
    }

    public void add() throws SQLException {
        new AccountDAO(this).add();
    }

    public void remove() throws SQLException {
        new AccountDAO(this).remove();
    }

    public void deposit(int amount) throws SQLException {
        new AccountDAO(this).deposit(amount);
    }

    public void withdraw(int amount) throws SQLException {
        if ((this.balance - amount) < 0) {
            new AccountDAO(this).withdraw(this.balance);
        } else {
            new AccountDAO(this).withdraw(amount);
        }
    }

    @Override
    public String toString() {
        return "Account{" + "number=" + number + ", balance=" + balance + ", transactions=" + transactions + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if (this.number != other.number) {
            return false;
        }
        return true;
    }
        
}
