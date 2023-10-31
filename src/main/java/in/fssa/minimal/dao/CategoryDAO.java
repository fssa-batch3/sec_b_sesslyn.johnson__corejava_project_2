package in.fssa.minimal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Category;
import in.fssa.minimal.util.ConnectionUtil;
import in.fssa.minimal.util.Logger;

public class CategoryDAO {
	/**
	 * Retrieves a set of all active product types from the database.
	 *
	 * @return A set containing TypeEntity objects representing the product types.
	 * @throws PersistenceException If there is an issue with the database
	 *                              connection or query.
	 */

	public Set<Category> findAll() throws PersistenceException {

		Set<Category> categoryList = new HashSet<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "SELECT id, name, is_active FROM categories WHERE is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				category.setActive(rs.getBoolean("is_active"));
				categoryList.add(category);
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return categoryList;

	}

	/**
	 * Creates a new product type in the database.
	 *
	 * This method inserts a new product type into the 'types' table with the
	 * provided name, image URL, and category ID.
	 *
	 * @param newType An instance of the TypeEntity class representing the new
	 *                product type to be created.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

	public void create(Category newCategory) throws PersistenceException {

		Connection connection = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO categories (name) VALUES (?)";
			connection = ConnectionUtil.getConnection();
			ps = connection.prepareStatement(query);
			ps.setString(1, newCategory.getName());
			ps.executeUpdate();
			Logger.info("Category has been created successfully");
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(connection, ps);
		}

	}

	/**
	 * Updates an existing product type in the database.
	 *
	 * This method updates the attributes of an existing product type in the 'types'
	 * table with the provided type ID.
	 *
	 * @param id          The unique identifier of the product type to be updated.
	 * @param updatedType An instance of the TypeEntity class containing the updated
	 *                    attributes for the product type.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

	public void update(int id, Category updatedCategory) throws PersistenceException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE categories SET name=? WHERE is_active=1 AND id=?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, updatedCategory.getName());
			ps.setInt(2, updatedCategory.getId());
			ps.executeUpdate();
			Logger.info("Category has been updated successfully");
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	/**
	 * Deletes a product type from the database.
	 *
	 * This method marks a product type as inactive in the 'types' table based on
	 * the provided type ID.
	 *
	 * @param id The unique identifier of the product type to be deleted.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

	public void delete(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE categories SET is_active = 0 WHERE is_active = 1 AND id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
			Logger.info("Category has been deleted successfully");

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);

		} finally {
			ConnectionUtil.close(con, ps);
		}

	}
	
	public Category findCategoryById(int id) throws PersistenceException {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    Category category = null; 

	    try {
	        String query = "SELECT id, name, is_active FROM categories WHERE is_active=1 AND id = ?";
	        con = ConnectionUtil.getConnection();
	        ps = con.prepareStatement(query);
	        ps.setInt(1, id);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            category = new Category(); 
	            category.setId(rs.getInt("id"));
	            category.setName(rs.getString("name"));
	            category.setActive(rs.getBoolean("is_active"));
	        }
	    } catch (SQLException e) {
	        Logger.error(e);
	        throw new PersistenceException(e);

	    } finally {
	        ConnectionUtil.close(con, ps, rs);
	    }

	    return category;
	}

	/**
	 * Checks if a product type with a given name already exists in the database.
	 *
	 * This method queries the 'types' table to determine whether a product type
	 * with the specified name is already present and active in the database.
	 *
	 * @param name The name of the product type to be checked for existence.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 * @throws ValidationException  If a product type with the specified name
	 *                              already exists in the database.
	 */

	public static void checkCategoryExistWithName(String name) throws PersistenceException, ValidationException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT name FROM categories WHERE is_active=1 AND name = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, name);
			rs = ps.executeQuery();
			if (rs.next()) {
				throw new ValidationException("Category name already exists");
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}

	}

	/**
	 * Checks if a product type with a given ID exists and is active in the
	 * database.
	 *
	 * This method queries the 'types' table to determine whether a product type
	 * with the specified ID exists and is active in the database.
	 *
	 * @param id The ID of the product type to be checked for existence.
	 * @return A TypeEntity object representing the product type if it exists and is
	 *         active.
	 * @throws ValidationException  If no product type with the specified ID is
	 *                              found or if it is not active.
	 * @throws PersistenceException If a database error occurs or a SQL exception is
	 *                              thrown during the operation.
	 */

	public static void checkCategoryExistWithId(int id) throws ValidationException, PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Category category = null;
        boolean result = false;
		try {
			String query = "SELECT id, name FROM categories WHERE is_active=1 AND id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (!rs.next()) {
				throw new ValidationException("Category Id doesn't exist");
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
	
	}

	public static void reactivateCategory(int categoryId) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "UPDATE categories SET is_active = 1 WHERE is_active = 0 AND id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, categoryId);
			ps.executeUpdate();
			Logger.info("Category has been updated successfully");
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);

		} finally {
			ConnectionUtil.close(conn, ps);
		}

	}
}
