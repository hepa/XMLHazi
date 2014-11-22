package login;

import data.ConnectionPool;
import data.DBUtil;
import errors.ProfileInactive;
import errors.PasswordsDontMatch;
import errors.UserNotFound;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.MD5;

public class Login implements ILogin {

    private String username;
    private String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public int updateLoginTime() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection c = pool.getConnection();
        PreparedStatement ps = null;

        try {
            ps = c.prepareStatement("UPDATE `login` SET `last_login` = NOW() WHERE `username` = ?");
            ps.setString(1, username);
            return ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(c);
        }
    }

    public Date getLoginTime() throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection c = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = c.prepareStatement("SELECT * FROM `login` WHERE `username` = ?");
            ps.setString(1, username);

            rs = ps.executeQuery();
            rs.next();
            return rs.getTimestamp("last_login");
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(c);
        }
    }

    @Override
    public boolean isActive() throws UserNotFound {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection c = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = c.prepareStatement("SELECT `active` FROM `login` WHERE `username` = ?");
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getBoolean(1);
            } else {
                throw new UserNotFound();
            }

        } catch (SQLException e) {
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(c);
        }
    }

    @Override
    public boolean Authenticate() throws UserNotFound, PasswordsDontMatch, ProfileInactive {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection c = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = c.prepareStatement("SELECT * FROM `login` WHERE `username` = ?");
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs.next()) {

                password = MD5.Convert(password);
                if (!rs.getString("password").equals(password)) {
                    throw new PasswordsDontMatch();
                }

                if (!rs.getBoolean("active")) {
                    throw new ProfileInactive();
                }

                updateLoginTime();
                return true;

            } else {
                throw new UserNotFound();
            }


        } catch (SQLException e) {
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(c);
        }
    }

    public void passwordChange(String newPassword) throws PasswordsDontMatch, SQLException {
        try {
            if (Authenticate()) {
                ConnectionPool pool = ConnectionPool.getInstance();
                Connection c = pool.getConnection();
                PreparedStatement ps = null;

                try {
                    ps = c.prepareStatement("UPDATE `login` SET `password` = ? WHERE `username` = ?");
                    ps.setString(1, MD5.Convert(newPassword));
                    ps.setString(2, username);

                    ps.executeUpdate();
                } finally {                    
                    DBUtil.closePreparedStatement(ps);
                    pool.freeConnection(c);
                }
            }
        } catch (UserNotFound | ProfileInactive ex) {
        }
    }
}
