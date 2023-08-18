package in.fssa.minimal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.model.Style;
import in.fssa.minimal.util.ConnectionUtil;

public class StyleDAO {
	/**
	 * 
	 * @param newStyle
	 * @throws PersistenceException
	 */
	public void create(Style newStyle) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO styles ( name ) VALUES (?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, newStyle.getName());
			ps.executeUpdate();
			System.out.println("Style has been created successfully");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	/**
	 * 
	 * @param id
	 * @param updatedStyle
	 * @throws PersistenceException
	 */
	public void update(int id, Style updatedStyle) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "UPDATE styles SET name = ? WHERE id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, updatedStyle.getName());
			ps.setInt(2, id);
			ps.executeUpdate();
			System.out.println("Style has been updated successfully");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, null);
		}
	}
	
	
}
