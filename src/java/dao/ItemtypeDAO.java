package dao;

import business.Itemtype;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemtypeDAO extends DefaultDAO {

    private Itemtype itemtype;

    public ItemtypeDAO() {
    }

    public ItemtypeDAO(Itemtype itemtype) {
        this.itemtype = itemtype;
    }

    public Itemtype find(int id) throws SQLException {
        try {
            ps = c.prepareStatement("SELECT * FROM `item_type` WHERE `id` = ?");
            ps.setInt(1, id);

            logger.trace(ps);
            rs = ps.executeQuery();

            while (rs.next()) {
                itemtype = new Itemtype(id, rs.getString("name"), rs.getString("desc"));
            }

            return itemtype;
        } finally {
            closeStatementAndSet();
        }
    }

    public ArrayList<Itemtype> findAll() throws SQLException {
        try {
            ps = c.prepareStatement("SELECT * FROM `item_type`");

            logger.trace(ps);
            rs = ps.executeQuery();

            ArrayList<Itemtype> types = new ArrayList<>();
            while (rs.next()) {
                itemtype = new Itemtype();
                itemtype.setId(rs.getInt("id"));
                itemtype.setName(rs.getString("name"));
                itemtype.setDescription(rs.getString("desc"));

                types.add(itemtype);
            }

            return types;
        } finally {
            closeStatementAndSet();
        }
    }
}
