package in.fssa.minimal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Style;
import in.fssa.minimal.util.ConnectionUtil;

public class StyleDAO {
	 /**
     * Creates a new style with the provided name.
     *
     * @param newStyle The Style object containing the name of the new style.
     * @throws PersistenceException If a database error occurs while creating the style.
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
     * Updates the name of an existing style.
     *
     * @param id          The ID of the style to update.
     * @param updatedStyle The Style object containing the updated name.
     * @throws PersistenceException If a database error occurs while updating the style.
     */
	public void update(int id, Style updateStyle) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "UPDATE styles SET name = ? WHERE id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, updateStyle.getName());
			ps.setInt(2, id);
			ps.executeUpdate();
			System.out.println("Style has been updated successfully");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}
	
	 /**
     * Checks if a style with the given name already exists.
     *
     * @param name The name of the style to check for existence.
     * @throws ValidationException If a style with the same name already exists.
     * @throws PersistenceException If a database error occurs while checking the existence.
     */
	public static void checkNameExists(String name) throws ValidationException, PersistenceException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			String query = "SELECT name FROM styles WHERE name = ?";
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
     * Checks if a style with the given ID exists.
     *
     * @param id The ID of the style to check for existence.
     * @throws ValidationException If a style with the provided ID doesn't exist.
     * @throws PersistenceException If a database error occurs while checking the existence.
     */
	public static void checkIdExists(int id) throws ValidationException, PersistenceException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			String query = "SELECT id FROM styles WHERE id = ?";
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
