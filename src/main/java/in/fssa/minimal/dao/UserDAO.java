package in.fssa.minimal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.interfaces.UserInterface;
import in.fssa.minimal.model.User;
import in.fssa.minimal.util.ConnectionUtil;

public class UserDAO implements UserInterface {
	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_EMAIL = "email";
	private static final String COLUMN_PASSWORD = "password";
	private static final String COLUMN_PHONENUMBER = "phone_number";
	private static final String COLUMN_ISACTIVE = "is_active";
	private static final String COLUMN_ISDESIGNER = "is_designer";
	private static final String COLUMN_ID = "id";

	/**
	 * Retrieves a set of all active users from the database.
	 *
	 * @return A set containing all active User objects in the database.
	 * @throws PersistenceException If a database-related error occurs during
	 *                              retrieval.
	 */
	@Override
	public Set<User> findAll() throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<User> userList = new HashSet<>();
		try {
			String query = "SELECT id,name,email,password,phone_number,is_active,is_designer "
					+ "FROM users WHERE is_active = 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt(COLUMN_ID));
				user.setName(rs.getString(COLUMN_NAME));
				user.setEmail(rs.getString(COLUMN_EMAIL));
				user.setPassword(rs.getString(COLUMN_PASSWORD));
				user.setPhoneNumber(rs.getLong(COLUMN_PHONENUMBER));
				user.setActive(rs.getBoolean(COLUMN_ISACTIVE));
				user.setDesigner(rs.getBoolean(COLUMN_ISDESIGNER));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return userList;
	}

	/**
	 * Retrieves a user from the database based on their user ID.
	 *
	 * @param userId The ID of the user to retrieve.
	 * @return The User object corresponding to the provided user ID.
	 * @throws PersistenceException If a database-related error occurs during
	 *                              retrieval.
	 */
	@Override
	public User findById(int userId) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			String query = "SELECT id,name,email,password,phone_number,is_active,is_designer "
					+ "FROM users WHERE is_active = 1 AND id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt(COLUMN_ID));
				user.setName(rs.getString(COLUMN_NAME));
				user.setEmail(rs.getString(COLUMN_EMAIL));
				user.setPassword(rs.getString(COLUMN_PASSWORD));
				user.setPhoneNumber(rs.getLong(COLUMN_PHONENUMBER));
				user.setActive(rs.getBoolean(COLUMN_ISACTIVE));
				user.setDesigner(rs.getBoolean(COLUMN_ISDESIGNER));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return user;
	}

	/**
	 * Retrieves a user from the database based on their email address.
	 *
	 * @param email The email address of the user to retrieve.
	 * @return The User object corresponding to the provided email address.
	 * @throws PersistenceException If a database-related error occurs during
	 *                              retrieval.
	 */
	@Override
	public User findByEmail(String email) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			String query = "SELECT id,name,email,password,phone_number,is_active,is_designer "
					+ "FROM users WHERE is_active = 1 AND email = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt(COLUMN_ID));
				user.setName(rs.getString(COLUMN_NAME));
				user.setEmail(rs.getString(COLUMN_EMAIL));
				user.setPassword(rs.getString(COLUMN_PASSWORD));
				user.setPhoneNumber(rs.getLong(COLUMN_PHONENUMBER));
				user.setActive(rs.getBoolean(COLUMN_ISACTIVE));
				user.setDesigner(rs.getBoolean(COLUMN_ISDESIGNER));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return user;
	}

	/**
	 * Creates a new user in the database.
	 *
	 * @param newUser The User object containing the details of the new user to
	 *                create.
	 * @throws PersistenceException If a database-related error occurs during
	 *                              creation.
	 */

	@Override
	public void create(User newUser) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO users (name, email, password, is_designer, phone_number) VALUES (?,?,?,?,?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, newUser.getName());
			ps.setString(2, newUser.getEmail());
			ps.setString(3, newUser.getPassword());
			ps.setBoolean(4, newUser.isDesigner());
			ps.setLong(5, newUser.getPhoneNumber());
			ps.executeUpdate();
			System.out.println("User has been created successfully");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	/**
	 * Updates the details of an existing user in the database.
	 *
	 * @param id          The ID of the user to update.
	 * @param updatedUser The User object containing the updated details for the
	 *                    user.
	 * @throws PersistenceException If a database-related error occurs during the
	 *                              update.
	 */
	@Override
	public void update(int id, User updatedUser) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			StringBuilder queryBuilder = new StringBuilder("UPDATE users SET ");
			List<Object> values = new ArrayList<>();

			if (updatedUser.getName() != null) {
				queryBuilder.append("name = ?, ");
				values.add(updatedUser.getName());
			}
			if (updatedUser.getPassword() != null) {
				queryBuilder.append("password = ?, ");
				values.add(updatedUser.getPassword());
			}
			if (updatedUser.getPhoneNumber() != 0) {
				queryBuilder.append("phone_number = ?, ");
				values.add(updatedUser.getPhoneNumber());
			}
			if (updatedUser.isDesigner()) {
				// Check if the field is updated, otherwise keep the existing value
				Boolean newValue = updatedUser.isDesigner();
				Boolean oldValue = getDesignerValueFromDatabase(id);
				if (!newValue.equals(oldValue)) {
					queryBuilder.append("is_designer = ?, ");
					values.add(newValue);
				}
			}

			queryBuilder.setLength(queryBuilder.length() - 2);

			queryBuilder.append(" WHERE is_active = 1 AND id = ?");
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(queryBuilder.toString());

			for (int i = 0; i < values.size(); i++) {
				ps.setObject(i + 1, values.get(i));
			}
			ps.setInt(values.size() + 1, id);

			ps.executeUpdate();
			System.out.println("User has been updated successfully");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, null);
		}
	}


	private Boolean getDesignerValueFromDatabase(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Deletes a user from the database.
	 *
	 * @param userId The ID of the user to delete.
	 * @throws PersistenceException If a database-related error occurs during
	 *                              deletion.
	 */
	@Override
	public void delete(int id) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "UPDATE users SET is_active = 0 WHERE id = ? AND is_active = 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
			System.out.println("User has been deleted successfully");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, null);
		}
	}

	/**
	 * Retrieves a set of all active designer users from the database.
	 *
	 * @return A set containing all active designer User objects in the database.
	 * @throws PersistenceException If a database-related error occurs during
	 *                              retrieval.
	 */
	public Set<User> findAllDesigner() throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<User> userList = new HashSet<>();
		try {
			String query = "SELECT id,name,email,password,phone_number,is_active,is_designer "
					+ "FROM users WHERE is_active = 1 AND is_designer = 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt(COLUMN_ID));
				user.setName(rs.getString(COLUMN_NAME));
				user.setEmail(rs.getString(COLUMN_EMAIL));
				user.setPassword(rs.getString(COLUMN_PASSWORD));
				user.setPhoneNumber(rs.getLong(COLUMN_PHONENUMBER));
				user.setActive(rs.getBoolean(COLUMN_ISACTIVE));
				user.setDesigner(rs.getBoolean(COLUMN_ISDESIGNER));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return userList;
	}

	/**
	 * Retrieves a designer user from the database based on their user ID.
	 *
	 * @param userId The ID of the designer user to retrieve.
	 * @return The User object corresponding to the provided designer user ID.
	 * @throws PersistenceException If a database-related error occurs during
	 *                              retrieval.
	 */
	public User findDesignerById(int id) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			String query = "SELECT id,name,email,password,phone_number,is_active,is_designer "
					+ "FROM users WHERE is_active = 1 AND is_designer = 1 AND id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt(COLUMN_ID));
				user.setName(rs.getString(COLUMN_NAME));
				user.setEmail(rs.getString(COLUMN_EMAIL));
				user.setPassword(rs.getString(COLUMN_PASSWORD));
				user.setPhoneNumber(rs.getLong(COLUMN_PHONENUMBER));
				user.setActive(rs.getBoolean(COLUMN_ISACTIVE));
				user.setDesigner(rs.getBoolean(COLUMN_ISDESIGNER));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return user;
	}

	/**
	 * Checks if an email address already exists in the users table.
	 *
	 * @param email The email address to check.
	 * @throws ValidationException  If the email address already exists or doesn't
	 *                              match the specified criteria.
	 * @throws PersistenceException If a database-related error occurs during the
	 *                              validation process.
	 */
	// Catch
	public static void emailExists(String email) throws ValidationException, PersistenceException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			String query = "SELECT email FROM users WHERE email = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setString(1, email);
			rs = pre.executeQuery();
			if (rs.next()) {
				throw new ValidationException("Email already exists.Try with a new email id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Email already exists.Try with a new email id");
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
	}

	/**
	 * Checks if a user ID exists in the users table.
	 *
	 * @param id The ID to check.
	 * @throws ValidationException  If the ID doesn't exist or doesn't match the
	 *                              specified criteria.
	 * @throws PersistenceException If a database-related error occurs during the
	 *                              validation process.
	 */
	public static void checkIdExists(int id) throws ValidationException, PersistenceException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			String query = "SELECT id FROM users WHERE is_active = 1 AND id = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setInt(1, id);
			rs = pre.executeQuery();

			if (!rs.next()) {
				throw new ValidationException("Id doesn't exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Id doesn't exist");
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
	}

	/**
	 * Checks if an email address exists for an active user.
	 *
	 * @param email The email address to check.
	 * @throws ValidationException  If the email address doesn't exist or doesn't
	 *                              match the specified criteria.
	 * @throws PersistenceException If a database-related error occurs during the
	 *                              validation process.
	 */
	public static void checkEmailExists(String email) throws ValidationException, PersistenceException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			String query = "SELECT email FROM users WHERE is_active = 1 AND email = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setString(1, email);
			rs = pre.executeQuery();
			if (!rs.next()) {
				throw new ValidationException("Email doesn't exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Email doesn't exist");
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
	}

	/**
	 * Checks if a designer's ID exists and is associated with an active designer
	 * user.
	 *
	 * @param id The ID to check.
	 * @throws ValidationException  If the designer's ID doesn't exist or doesn't
	 *                              match the specified criteria.
	 * @throws PersistenceException If a database-related error occurs during the
	 *                              validation process.
	 */
	public static void checkDesignerIdExists(int id) throws ValidationException, PersistenceException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			String query = "SELECT id FROM users WHERE is_active = 1 AND is_designer = 1 AND id = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setInt(1, id);
			rs = pre.executeQuery();
			if (!rs.next()) {
				throw new ValidationException("Designer Id doesn't exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Designer Id doesn't exist");
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
	}

	/**
	 * Retrieves the ID of the last updated non-designer user who is active.
	 *
	 * @return The ID of the last updated non-designer user.
	 * @throws PersistenceException If a database error occurs while retrieving the
	 *                              ID.
	 */
	public static int getLastUpdatedUserId() throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int userId = 0;
		try {
			String query = "SELECT id FROM users WHERE is_active = 1 AND is_designer = 0 ORDER BY created_at DESC LIMIT 1";
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

	/**
	 * Retrieves the ID of the last updated designer who is active.
	 *
	 * @return The ID of the last updated designer.
	 * @throws PersistenceException If a database error occurs while retrieving the
	 *                              ID.
	 */
	public static int getLastUpdatedDesignerId() throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int designerId = 0;
		try {
			String query = "SELECT id,name,email,password,phone_number,is_active,is_designer "
					+ "FROM users WHERE is_active = 1 AND is_designer = 1 ORDER BY created_at DESC LIMIT 1";
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