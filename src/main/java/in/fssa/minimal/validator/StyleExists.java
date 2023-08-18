package in.fssa.minimal.validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.util.ConnectionUtil;

public class StyleExists {
	/**
	 * 
	 * @param name
	 * @throws ValidationException
	 * @throws PersistenceException 
	 */
	public static void nameExists(String name) throws ValidationException, PersistenceException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			String query = "Select * From styles Where name = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setString(1, name);
			rs = pre.executeQuery();
			if (rs.next()) {
				throw new ValidationException("Style Name already exists");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
	}
	
	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 * @throws PersistenceException 
	 */
	public static void checkIdExists(int id) throws ValidationException, PersistenceException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			String query = "Select * From styles Where id = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setInt(1, id);
			rs = pre.executeQuery();
			if (!rs.next()) {
				throw new ValidationException("Style Id doesn't exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
	}

}
