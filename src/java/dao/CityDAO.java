package dao;

import business.City;
import errors.CityNotFound;
import java.sql.SQLException;

public class CityDAO extends DefaultDAO {
    private City cy;

    public CityDAO() {
        cy = null;
    }    

    public CityDAO(City cy) {
        this.cy = cy;
    }        
    
    public City find(int zip) throws CityNotFound, SQLException {         
        try {            
            ps = c.prepareStatement("SELECT * FROM `city` WHERE `zip_code` = ?");
            ps.setInt(1, zip);
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                cy = new City();
                cy.setName(rs.getString("name"));
                cy.setZipCode(zip);                
            } else {
                throw new CityNotFound();
            }
            
            return cy;
        } finally {
            closeStatementAndSet();
        }
    }
    
    public City find(String zip) throws CityNotFound, SQLException, NumberFormatException {
        return find(Integer.parseInt(zip));
    }
}
