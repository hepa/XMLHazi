package dao;

import business.Examtype;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExamtypeDAO extends DefaultDAO {
    private Examtype e;

    public ExamtypeDAO() {
        e = null;
    }

    public ExamtypeDAO(Examtype e) {
        this.e = e;
    }        
    
    public Examtype find(int id) throws SQLException {
        try {
            ps = c.prepareStatement("SELECT * FROM `exam_type` WHERE `id` = ?");
            ps.setInt(1, id);
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                e = new Examtype(id, rs.getString("name"), rs.getString("desc"));                
            }
            
            return e;            
        } finally {
            closeStatementAndSet();
        }
    }
    
    public ArrayList<Examtype> findAll() throws SQLException {
        try {
            ps = c.prepareStatement("SELECT * FROM `exam_type`");
            
            logger.trace(ps);
            rs = ps.executeQuery();
            
            ArrayList<Examtype> examtypes = new ArrayList<>();
            while (rs.next()) {
                e = new Examtype(rs.getInt("id"), rs.getString("name"), rs.getString("desc"));
                examtypes.add(e);
            }
            
            return examtypes;
        } finally {
            closeStatementAndSet();
        }
    }
}
