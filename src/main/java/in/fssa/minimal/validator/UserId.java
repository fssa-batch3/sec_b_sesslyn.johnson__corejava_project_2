package in.fssa.minimal.validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.util.ConnectionUtil;

public class UserId {

	public static int getLastUpdatedUserId() throws PersistenceException {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    int userId = 0;
	    try {
	        String query = "SELECT id FROM users WHERE is_active = 1 AND is_designer = 0 ORDER BY modified_at DESC LIMIT 1";
	        conn = ConnectionUtil.getConnection();
	        ps = conn.prepareStatement(query);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            userId = rs.getInt("id");   
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	        throw new PersistenceException(e);
	    } finally {
	        ConnectionUtil.close(conn, ps, rs);
	    }
	    return userId;
	}

	
	public static int getLastUpdatedDesignerId() throws PersistenceException {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    int designerId = 0;
	    try {
	        String query = "SELECT * FROM users WHERE is_active = 1 AND is_designer = 1 ORDER BY modified_at DESC LIMIT 1";
	        conn = ConnectionUtil.getConnection();
	        ps = conn.prepareStatement(query);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	        	designerId = rs.getInt("id");   
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	        throw new PersistenceException(e);
	    } finally {
	        ConnectionUtil.close(conn, ps, rs);
	    }
	    return designerId;
	}
}
