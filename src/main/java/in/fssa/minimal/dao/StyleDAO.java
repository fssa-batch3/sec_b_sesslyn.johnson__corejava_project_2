package in.fssa.minimal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Style;
import in.fssa.minimal.util.ConnectionUtil;
import in.fssa.minimal.util.Logger;

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
			Logger.info("Style has been created successfully");

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	/**
	 * Updates the name of an existing style.
	 *
	 * @param styleId      The ID of the style to update.
	 * @param updatedStyle The Style object containing the updated name.
	 * @throws PersistenceException If a database error occurs while updating the style.
	 */
	public void update(int styleId, Style updateStyle) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "UPDATE styles SET name = ? WHERE id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, updateStyle.getName());
			ps.setInt(2, styleId);
			ps.executeUpdate();
			Logger.info("Style has been updated successfully");
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}
	
	/**
	 * Checks if a style with the given name already exists.
	 *
	 * @param styleName The name of the style to check for existence.
	 * @throws ValidationException If a style with the same name already exists.
	 * @throws PersistenceException If a database error occurs while checking the existence.
	 */
	public static void checkNameExists(String styleName) throws ValidationException, PersistenceException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			String query = "SELECT name FROM styles WHERE name = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setString(1, styleName);
			rs = pre.executeQuery();
			if (rs.next()) {
				throw new ValidationException("Style Name already exists");
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
	}
	
	/**
	 * Checks if a style with the given ID exists.
	 *
	 * @param styleId The ID of the style to check for existence.
	 * @throws ValidationException If a style with the provided ID doesn't exist.
	 * @throws PersistenceException If a database error occurs while checking the existence.
	 */
	public static void checkIdExists(int styleId) throws ValidationException, PersistenceException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			String query = "SELECT id FROM styles WHERE id = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setInt(1, styleId);
			rs = pre.executeQuery();
			if (!rs.next()) {
				throw new ValidationException("Style Id doesn't exist");
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
	}
	
	/**
	 * Retrieves the ID of a style by its name.
	 *
	 * @param styleName The name of the style to retrieve the ID for.
	 * @return The ID of the style.
	 * @throws ValidationException If the style with the provided name doesn't exist.
	 * @throws PersistenceException If a database error occurs during retrieval.
	 */
	public int getStyleId(String styleName) throws ValidationException, PersistenceException {
	    Connection conn = null;
	    PreparedStatement pre = null;
	    ResultSet rs = null;
	    int id = 0;

	    try {
	        String query = "SELECT id FROM styles WHERE name = ?";
	        conn = ConnectionUtil.getConnection();
	        pre = conn.prepareStatement(query);
	        pre.setString(1, styleName);
	        rs = pre.executeQuery();
	        if (rs.next()) { 
	            id = rs.getInt("id");
	        }else {
	        	throw new ValidationException("Style Name doesn't exist");
	        }
	    } catch (SQLException e) {
	        Logger.error(e);
	        throw new PersistenceException(e);
	    } finally {
	        ConnectionUtil.close(conn, pre, rs);
	    }
	    return id;
	}
	
	/**
	 * Retrieves the name of a style by its ID.
	 *
	 * @param styleId The ID of the style to retrieve the name for.
	 * @return The name of the style.
	 * @throws ValidationException If the style with the provided ID doesn't exist.
	 * @throws PersistenceException If a database error occurs during retrieval.
	 */
	public String getStyleName(int styleId) throws ValidationException, PersistenceException {
	    Connection conn = null;
	    PreparedStatement pre = null;
	    ResultSet rs = null;
	    String styleName = null;

	    try {
	        String query = "SELECT name FROM styles WHERE id = ?";
	        conn = ConnectionUtil.getConnection();
	        pre = conn.prepareStatement(query);
	        pre.setInt(1, styleId);
	        rs = pre.executeQuery();
	        if (rs.next()) { 
	        	styleName = rs.getString("name");
	        }else {
	        	throw new ValidationException("Style Id doesn't exist");
	        }
	    } catch (SQLException e) {
	        Logger.error(e);
	        throw new PersistenceException(e);
	    } finally {
	        ConnectionUtil.close(conn, pre, rs);
	    }
	    return styleName;
	}
	
	/**
	 * Retrieves all style entities from the database.
	 *
	 * @return A set of all styles in the database.
	 * @throws PersistenceException If a database error occurs during retrieval.
	 */
	public List<Style> findAllStyle() throws RuntimeException, PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Style> styleList = new ArrayList<>();
		try {
			String query = "SELECT id, name FROM styles";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Style style = new Style();
				style.setId(rs.getInt("id"));
				style.setName(rs.getString("name"));
				styleList.add(style);
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return styleList;
	}


}