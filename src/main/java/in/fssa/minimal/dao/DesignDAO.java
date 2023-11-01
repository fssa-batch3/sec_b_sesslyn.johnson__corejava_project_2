package in.fssa.minimal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Design;
import in.fssa.minimal.util.ConnectionUtil;
import in.fssa.minimal.util.Logger;

public class DesignDAO {
 

	/**
	 * Creates a new design entity in the database.
	 *
	 * @param newDesign The design to be created.
	 * @return The ID of the newly created design entity.
	 * @throws PersistenceException If a database error occurs during creation.
	 */
	public int create(Design newDesign) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null; 
		ResultSet rs = null;
		int designId = -1;
		try {
			String query = "INSERT INTO designs ( name, description,location,style_id ) VALUES (?,?,?,?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, newDesign.getName());
			ps.setString(2, newDesign.getDescription());
			ps.setString(3, newDesign.getLocation());
			ps.setInt(4, newDesign.getStyleId());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if(rs.next()) {
				designId = rs.getInt(1);
			}
			Logger.info("Design has been created successfully");
			

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps,rs);
		}
		return designId;
	}

	/**
	 * Updates an existing design entity in the database.
	 *
	 * @param designId       The ID of the design to be updated.
	 * @param updatedDesign The updated design data.
	 * @throws PersistenceException If a database error occurs during update.
	 */
	public void update(int designId, Design updatedDesign) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			StringBuilder queryBuilder = new StringBuilder("UPDATE designs SET ");
			List<Object> values = new ArrayList<>();
			if (updatedDesign.getName() != null) {
				queryBuilder.append("name = ?, ");
				values.add(updatedDesign.getName());
			}
			if (updatedDesign.getDescription() != null) {
				queryBuilder.append("description = ?, ");
				values.add(updatedDesign.getDescription());
			}
			if (updatedDesign.getLocation() != null) {
				queryBuilder.append("location = ?, ");
				values.add(updatedDesign.getLocation());
			}
			if (updatedDesign.getStyleId() != 0) {
				queryBuilder.append("style_id = ?, ");
				values.add(updatedDesign.getStyleId());
			}
			queryBuilder.setLength(queryBuilder.length() - 2);
			queryBuilder.append(" WHERE id = ?");
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(queryBuilder.toString());
			for (int i = 0; i < values.size(); i++) {
				ps.setObject(i + 1, values.get(i));
			}
			ps.setInt(values.size() + 1, designId);
			ps.executeUpdate();
			Logger.info("Design has been updated successfully");
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}
	

/**
 * Retrieves all design entities from the database.
 *
 * @return A set of all designs in the database.
 * @throws RuntimeException   If there's a runtime error during retrieval.
 * @throws PersistenceException If a database error occurs during retrieval.
 */
	public List<Design> findAllDesign() throws RuntimeException, PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Design> designList = new ArrayList<>();
		try {
			String query = "SELECT id, name, description, location, style_id FROM designs";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Design design = new Design();
				design.setId(rs.getInt("id"));
				design.setName(rs.getString("name"));
				design.setDescription(rs.getString("description"));
				design.setLocation(rs.getString("location"));
				design.setStyleId(rs.getInt("style_id"));
				designList.add(design);
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return designList;
	}

	/**
	 * Retrieves a design entity by its ID from the database.
	 *
	 * @param designId The ID of the design to retrieve.
	 * @return The retrieved design entity.
	 * @throws PersistenceException If a database error occurs during retrieval.
	 */
	public Design findByDesignId(int designId) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Design design = null;
		try {
			String query = "SELECT id, name, description, location, style_id "
					+ "FROM designs WHERE id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, designId);
			rs = ps.executeQuery();
			if (rs.next()) {
				design = new Design();
				design.setId(rs.getInt("id"));
				design.setName(rs.getString("name"));
				design.setDescription(rs.getString("description"));
				design.setLocation(rs.getString("location"));
				design.setStyleId(rs.getInt("style_id"));
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return design;
	}


	/**
	 * Checks if a design ID exists in the database.
	 *
	 * @param designId The ID to check for existence.
	 * @throws ValidationException  If the design ID doesn't exist.
	 * @throws PersistenceException If a database error occurs during validation.
	 */
	public static void checkIdExists(int designId) throws ValidationException, PersistenceException {
	    Connection conn = null;
	    PreparedStatement pre = null;
	    ResultSet rs = null;

	    try {
	        String query = "SELECT id " +  // Add a space after "id"
	                "FROM designs WHERE id = ?";
	        conn = ConnectionUtil.getConnection();
	        pre = conn.prepareStatement(query);
	        pre.setInt(1, designId);
	        rs = pre.executeQuery();
	        if (!rs.next()) {
	            throw new ValidationException("Design Id doesn't exist");
	        }
	    } catch (SQLException e) {
	        Logger.error(e);
	        throw new PersistenceException(e);
	    } finally {
	        ConnectionUtil.close(conn, pre, rs);
	    }
	}

	
	/**
	 * Retrieves the ID of the last updated design entity in the database.
	 *
	 * @return The ID of the last updated design entity.
	 * @throws PersistenceException If a database error occurs during retrieval.
	 */
	public static int getLastUpdatedDesignId() throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int designerId = 0;
		try {
			String query = "SELECT id FROM designs ORDER BY created_at DESC LIMIT 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				designerId = rs.getInt("id");
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return designerId;
	}

}