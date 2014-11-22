package business;

import dao.CityDAO;
import errors.CityNotFound;
import java.sql.SQLException;

public class City {
    private int zipCode;
    private String name;

    public City() {
    }

    public City(int zipCode, String name) {
        this.zipCode = zipCode;
        this.name = name;
    }

    // <editor-fold desc="Getter&Setter" defaultstate="collapsed">
    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }        
    //</editor-fold>
    
    public static City getInstance(int zipCode) throws CityNotFound, SQLException {
        return new CityDAO().find(zipCode);
    }
    
    public static City getInstance(String zipCode) throws CityNotFound, SQLException {
        return new CityDAO().find(zipCode);
    }

    @Override
    public String toString() {
        return "City{" + "zipCode=" + zipCode + ", name=" + name + '}';
    }
        
}
