package in.fssa.minimal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Design;
import in.fssa.minimal.util.ConnectionUtil;

public class DesignDAO {
	private static final String COLUMN_ID = "id";
	private static final String COLUMN_NAME = "NAME";
	private static final String COLUMN_DESCRIPTION = "description";
	private static final String COLUMN_LOCATION = "location";
	private static final String COLUMN_STYLEID = "style_id";
	private static final String COLUMN_CREATEDBY = "created_by";

	/**
	 * Creates a new design entity in the database.
	 *
	 * @param newDesign The design to be created.
	 * @throws PersistenceException If a database error occurs during creation.
	 */
	
	public void create(Design newDesign) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO designs ( name, description,location,style_id,created_by ) VALUES (?,?,?,?,?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, newDesign.getName());
			ps.setString(2, newDesign.getDescription());
			ps.setString(3, newDesign.getLocation());
			ps.setInt(4, newDesign.getStyleId());
			ps.setInt(5, newDesign.getCreatedBy());
			ps.executeUpdate();
			System.out.println("Design has been created successfully");

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	/**
	 * Updates an existing design entity in the database.
	 *
	 * @param id            The ID of the design to be updated.
	 * @param updatedDesign The updated design data.
	 * @throws PersistenceException If a database error occurs during update.
	 */
	public void update(int id, Design updatedDesign) throws PersistenceException {
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
			if (updatedDesign.getCreatedBy() != 0) {
				queryBuilder.append("created_by = ?, ");
				values.add(updatedDesign.getCreatedBy());
			}

			queryBuilder.setLength(queryBuilder.length() - 2);

			queryBuilder.append(" WHERE id = ?");
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(queryBuilder.toString());

			for (int i = 0; i < values.size(); i++) {
				ps.setObject(i + 1, values.get(i));
			}
			ps.setInt(values.size() + 1, id);

			ps.executeUpdate();
			System.out.println("Design has been updated successfully");
		} catch (SQLException e) {

			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, null);
		}
	}

	/**
	 * Retrieves all design entities from the database.
	 *
	 * @return A set of all designs in the database.
	 * @throws PersistenceException If a database error occurs during retrieval.
	 */
	
	
	public Set<Design> findAllDesign() throws RuntimeException, PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<Design> designList = new HashSet<>();
		try {
			String query = "SELECT * FROM designs";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Design design = new Design();
				design.setId(rs.getInt(COLUMN_ID));
				design.setName(rs.getString(COLUMN_NAME));
				design.setDescription(rs.getString(COLUMN_DESCRIPTION));
				design.setLocation(rs.getString(COLUMN_LOCATION));
				design.setStyleId(rs.getInt(COLUMN_STYLEID));
				design.setCreatedBy(rs.getInt(COLUMN_CREATEDBY));
				designList.add(design);
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return designList;
	}

	/**
	 * Retrieves a design entity by its ID from the database.
	 *
	 * @param id The ID of the design to retrieve.
	 * @return The retrieved design entity.
	 * @throws PersistenceException If a database error occurs during retrieval.
	 */
	public Design findByDesignId(int id) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Design design = null;
		try {
			String query = "SELECT * FROM designs WHERE id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				design = new Design();
				design.setId(rs.getInt(COLUMN_ID));
				design.setName(rs.getString(COLUMN_NAME));
				design.setDescription(rs.getString(COLUMN_DESCRIPTION));
				design.setLocation(rs.getString(COLUMN_LOCATION));
				design.setStyleId(rs.getInt(COLUMN_STYLEID));
				design.setCreatedBy(rs.getInt(COLUMN_CREATEDBY));
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return design;
	}

	/**
	 * Retrieves all design entities created by a specific designer from the
	 * database.
	 *
	 * @param id The ID of the designer.
	 * @return A set of designs created by the specified designer.
	 * @throws PersistenceException If a database error occurs during retrieval.
	 */
	public Set<Design> findAllDesignsByDesignerId(int id) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Design design = null;
		Set<Design> designList = new HashSet<>();
		try {
			String query = "SELECT * FROM designs WHERE created_by = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				design = new Design();
				design.setId(rs.getInt(COLUMN_ID));
				design.setName(rs.getString(COLUMN_NAME));
				design.setDescription(rs.getString(COLUMN_DESCRIPTION));
				design.setLocation(rs.getString(COLUMN_LOCATION));
				design.setStyleId(rs.getInt(COLUMN_STYLEID));
				design.setCreatedBy(rs.getInt(COLUMN_CREATEDBY));
				designList.add(design);
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return designList;
	}

	/**
	 * Checks if a design ID exists in the database.
	 *
	 * @param id The ID to check for existence.
	 * @throws ValidationException If the design ID doesn't exist.
	 * @throws PersistenceException 
	 */
	public static void checkIdExists(int id) throws ValidationException, PersistenceException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			String query = "SELECT * FROM designs WHERE id = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setInt(1, id);
			rs = pre.executeQuery();
			if (!rs.next()) {
				throw new ValidationException("Design Id doesn't exist");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
	}

	/**
	 * Checks if a designer has any designs in the database.
	 *
	 * @param createdBy The designer's ID to check.
	 * @throws ValidationException If the designer has no designs.
	 * @throws PersistenceException 
	 */
	public static void checkCreatedByExists(int createdBy) throws ValidationException, PersistenceException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			String query = "SELECT * FROM designs WHERE created_by = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setInt(1, createdBy);
			rs = pre.executeQuery();
			if (!rs.next()) {
				throw new ValidationException("Designers doesn't have any design yet");
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
	}
}
