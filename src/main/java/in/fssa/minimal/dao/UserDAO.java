package in.fssa.minimal.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import in.fssa.minimal.enums.GenderEnum;
import in.fssa.minimal.enums.RoleEnum;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ServiceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.interfaces.UserInterface;
import in.fssa.minimal.model.User;
import in.fssa.minimal.service.UserService;
import in.fssa.minimal.util.Logger;
import in.fssa.minimal.util.Password;
import in.fssa.minimal.util.ConnectionUtil;
 
public class UserDAO implements UserInterface {

	/**
	 * Retrieves a set of all active users from the database.
	 *
	 * @return A set containing all active User objects in the database.
	 * @throws PersistenceException If a database-related error occurs during retrieval.
	 * @throws ValidationException If validation of retrieved data fails.
	 */
	@Override
	public List<User> findAll() throws PersistenceException, ValidationException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null; 
		List<User> userList = new ArrayList<>();
		try {
			String query = "SELECT id,name,email,image,phone_number,date_of_birth,gender,role,"
					+ "is_active,experience,designer_description,gst_number,aadhar_number,shop_address "
					+ "FROM users WHERE is_active = 1"; 
			conn = ConnectionUtil.getConnection(); 
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setImage(rs.getString("image"));
				user.setPhoneNumber(rs.getLong("phone_number"));
				Date dateOfBirth = rs.getDate("date_of_birth");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				if (dateOfBirth != null) {
					user.setDateOfBirth(dateFormat.format(dateOfBirth));
				} else {
					user.setDateOfBirth(null);
				}
				String gender = rs.getString("gender");
				if (gender != null) {
					gender = GenderEnum.getGenderString(gender);
				}
				user.setGender(gender);
				String role = rs.getString("role");
				if (role != null) {
					role = RoleEnum.getRoleString(role);
				}
				user.setRole(role);

				user.setActive(rs.getBoolean("is_active"));

				user.setAadhar_number(rs.getLong("aadhar_number"));
				user.setGst_number(rs.getString("gst_number"));
				user.setShop_address(rs.getString("shop_address"));

				user.setDesigner_description(rs.getString("designer_description"));
				user.setExperience(rs.getInt("experience"));

				userList.add(user);
			}
		} catch (SQLException e) {
			Logger.error(e);
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
	 * @return The User object corresponding to the provided user ID, or null if not found.
	 * @throws PersistenceException If a database-related error occurs during retrieval.
	 * @throws ValidationException If validation of retrieved data fails.
	 */
	@Override
	public User findById(int userId) throws PersistenceException, ValidationException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			String query = "SELECT id,name,email,image,phone_number,date_of_birth,gender,"
					+ "role,is_active,experience,designer_description,gst_number,aadhar_number,shop_address "
					+ "FROM users WHERE is_active = 1 AND id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPhoneNumber(rs.getLong("phone_number"));
				Date dateOfBirth = rs.getDate("date_of_birth");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				if (dateOfBirth != null) {
					user.setDateOfBirth(dateFormat.format(dateOfBirth));
				} else {
					user.setDateOfBirth(null);
				}
				String gender = rs.getString("gender");
				if (gender != null) {
					gender = GenderEnum.getGenderString(gender);
				}
				user.setGender(gender);
				String role = rs.getString("role");
				if (role != null) {
					role = RoleEnum.getRoleString(role);
				}
				user.setRole(role);
				user.setImage(rs.getString("image"));
				user.setActive(rs.getBoolean("is_active"));

				user.setAadhar_number(rs.getLong("aadhar_number"));
				user.setGst_number(rs.getString("gst_number"));
				user.setShop_address(rs.getString("shop_address"));

				user.setDesigner_description(rs.getString("designer_description"));
				user.setExperience(rs.getInt("experience"));

			}
		} catch (SQLException e) {
			Logger.error(e);
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
	 * @return The User object corresponding to the provided email address, or null if not found.
	 * @throws PersistenceException If a database-related error occurs during retrieval.
	 * @throws ValidationException If validation of retrieved data fails.
	 */
	@Override
	public User findByEmail(String email) throws PersistenceException, ValidationException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			String query = "SELECT id,name,email,image,password,phone_number,date_of_birth,gender,role,is_active"
					+ ",experience,designer_description,gst_number,aadhar_number,shop_address "
					+ "FROM users WHERE is_active = 1 AND email = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, email); 
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setImage(rs.getString("image"));
				user.setPassword(rs.getString("password"));
				user.setPhoneNumber(rs.getLong("phone_number"));
				Date dateOfBirth = rs.getDate("date_of_birth");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				if (dateOfBirth != null) {
					user.setDateOfBirth(dateFormat.format(dateOfBirth));
				} else {
					user.setDateOfBirth(null);
				}
				String gender = rs.getString("gender");
				if (gender != null) {
					gender = GenderEnum.getGenderString(gender);
				}
				user.setGender(gender);
				String role = rs.getString("role");
				if (role != null) {
					role = RoleEnum.getRoleString(role);
				}
				user.setRole(role);
				user.setActive(rs.getBoolean("is_active"));

				user.setAadhar_number(rs.getLong("aadhar_number"));
				user.setGst_number(rs.getString("gst_number"));
				user.setShop_address(rs.getString("shop_address"));

				user.setDesigner_description(rs.getString("designer_description"));
				user.setExperience(rs.getInt("experience"));

			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return user;
	}

	/**
	 * Creates a new user in the database.
	 *
	 * @param newUser The User object containing the details of the new user to create.
	 * @throws PersistenceException If a database-related error occurs during creation.
	 * @throws ValidationException If validation of user data fails.
	 */
	@Override
	public void create(User newUser) throws PersistenceException, ValidationException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO users (name, email, password, role,"
					+ " phone_number, experience, image,designer_description, gst_number, aadhar_number,"
					+ " shop_address) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, newUser.getName());
			ps.setString(2, newUser.getEmail());
			String hashPassword = Password.encryptPassword(newUser.getPassword());
			ps.setString(3, hashPassword);
			ps.setString(4, RoleEnum.getRole(newUser.getRole()));
			ps.setLong(5, newUser.getPhoneNumber());
 
			if (RoleEnum.getRole(newUser.getRole()) == "d") {
				ps.setInt(6, newUser.getExperience());
				ps.setString(7, newUser.getImage());
				ps.setString(8, newUser.getDesigner_description());
				ps.setNull(9, Types.BIGINT);
				ps.setNull(10, Types.BIGINT);
				ps.setNull(11, Types.VARCHAR);
			} else if (RoleEnum.getRole(newUser.getRole()) == "s") {
				ps.setNull(6, Types.VARCHAR);
				ps.setNull(7, Types.VARCHAR);
				ps.setNull(8, Types.VARCHAR);
				ps.setString(9, newUser.getGst_number());
				ps.setLong(10, newUser.getAadhar_number());
				ps.setString(11, newUser.getShop_address());
			} else {
				ps.setNull(6, Types.VARCHAR);
				ps.setNull(7, Types.VARCHAR);
				ps.setNull(8, Types.VARCHAR);
				ps.setNull(9, Types.BIGINT);
				ps.setNull(10, Types.BIGINT);
				ps.setNull(11, Types.VARCHAR);
			}

			ps.executeUpdate();
			Logger.info("User has been created successfully");
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	/**
	 * Updates the details of an existing user in the database.
	 *
	 * @param userId      The ID of the user to update.
	 * @param updatedUser The User object containing the updated details for the user.
	 * @throws PersistenceException If a database-related error occurs during the update.
	 * @throws ValidationException If validation of updated user data fails.
	 * @throws ServiceException If a service-related error occurs during the update.
	 */
	public void update(int userId, User updatedUser)
			throws PersistenceException, ValidationException, ServiceException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			StringBuilder queryBuilder = new StringBuilder("UPDATE users SET ");
			List<Object> values = new ArrayList<>();

			if (updatedUser.getName() != null) {
				queryBuilder.append("name = ?, ");
				values.add(updatedUser.getName());
			}
			if (updatedUser.getImage() != null) {
				queryBuilder.append("image = ?, ");
				values.add(updatedUser.getImage());
			}
			if (updatedUser.getPhoneNumber() != 0) {
				queryBuilder.append("phone_number = ?, ");
				values.add(updatedUser.getPhoneNumber());
			}
			if (updatedUser.getDateOfBirth() != null) {
				queryBuilder.append("date_of_birth = ?, ");
				java.sql.Date dateOfBirth = java.sql.Date.valueOf(updatedUser.getDateOfBirth());
				values.add(dateOfBirth);
			}
			if (updatedUser.getGender() != null) {
				queryBuilder.append("gender = ?, ");
				values.add(GenderEnum.getGender(updatedUser.getGender()));
			}
			String role = updatedUser.getRole();
			if (role.equals("seller")) {
				queryBuilder.append("gst_number = ?, ");
				queryBuilder.append("shop_address = ?, ");
				values.add(updatedUser.getGst_number());
				values.add(updatedUser.getShop_address());
			} else if (role.equals("designer")) {
				queryBuilder.append("experience = ?, ");
				queryBuilder.append("designer_description = ?, ");
				values.add(updatedUser.getExperience());
				values.add(updatedUser.getDesigner_description());
			}

			queryBuilder.setLength(queryBuilder.length() - 2);
			queryBuilder.append(" WHERE is_active = 1 AND id = ?");
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(queryBuilder.toString());

			for (int i = 0; i < values.size(); i++) {
				ps.setObject(i + 1, values.get(i));
			}
			ps.setInt(values.size() + 1, userId);

			ps.executeUpdate();
			Logger.info("User has been updated successfully");
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	/**
	 * Deletes a user from the database.
	 *
	 * @param userId The ID of the user to delete.
	 * @throws PersistenceException If a database-related error occurs during deletion.
	 */
	@Override
	public void delete(int userId) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "UPDATE users SET is_active = 0 WHERE id = ? AND is_active = 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, userId);
			ps.executeUpdate();
			Logger.info("User has been deleted successfully");
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	/**
	 * Retrieves a set of all active designer users from the database.
	 *
	 * @return A set containing all active designer User objects in the database.
	 * @throws PersistenceException If a database-related error occurs during retrieval.
	 * @throws ValidationException If validation of retrieved data fails.
	 */
	public List<User> findAllDesigner() throws PersistenceException, ValidationException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> userList = new ArrayList<>();
		try {
			String query = "SELECT id, name, email, image, phone_number, date_of_birth, gender, role, is_active, experience, designer_description "
					+ "FROM users WHERE role = 'd' AND is_active = 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setImage(rs.getString("image"));
				user.setPhoneNumber(rs.getLong("phone_number"));
				Date dateOfBirth = rs.getDate("date_of_birth");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				if (dateOfBirth != null) {
					user.setDateOfBirth(dateFormat.format(dateOfBirth));
				} else {
					user.setDateOfBirth(null);
				}
				String gender = rs.getString("gender");
				if (gender != null) {
					gender = GenderEnum.getGenderString(gender);
				}
				user.setGender(gender);
				String role = rs.getString("role");
				if (role != null) {
					role = RoleEnum.getRoleString(role);
				}
				user.setRole(role);

				user.setActive(rs.getBoolean("is_active"));

				user.setDesigner_description(rs.getString("designer_description"));
				user.setExperience(rs.getInt("experience"));

				userList.add(user);
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return userList;
	}

	/**
	 * Retrieves a set of all active seller users from the database.
	 *
	 * @return A set containing all active seller User objects in the database.
	 * @throws PersistenceException If a database-related error occurs during retrieval.
	 * @throws ValidationException If validation of retrieved data fails.
	 */
	public List<User> findAllSeller() throws PersistenceException, ValidationException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> userList = new ArrayList<>();
		try {
			String query = "SELECT id,name,email,image,phone_number,date_of_birth,gender,role,is_active,gst_number,aadhar_number,shop_address "
					+ " FROM users WHERE role = 's' AND is_active = 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setImage(rs.getString("image"));
				user.setPhoneNumber(rs.getLong("phone_number"));
				Date dateOfBirth = rs.getDate("date_of_birth");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				if (dateOfBirth != null) {
					user.setDateOfBirth(dateFormat.format(dateOfBirth));
				} else {
					user.setDateOfBirth(null);
				}
				String gender = rs.getString("gender");
				if (gender != null) {
					gender = GenderEnum.getGenderString(gender);
				}
				user.setGender(gender);
				String role = rs.getString("role");
				if (role != null) {
					role = RoleEnum.getRoleString(role);
				}
				user.setRole(role);
				user.setActive(rs.getBoolean("is_active"));
				user.setAadhar_number(rs.getLong("aadhar_number"));
				user.setGst_number(rs.getString("gst_number"));
				user.setShop_address(rs.getString("shop_address"));

				userList.add(user);
			}
		} catch (SQLException e) {
			Logger.error(e);
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
	 * @return The User object corresponding to the provided designer user ID, or null if not found.
	 * @throws PersistenceException If a database-related error occurs during retrieval.
	 * @throws ValidationException If validation of retrieved data fails.
	 */
	public User findDesignerById(int userId) throws PersistenceException, ValidationException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			String query = "SELECT id,name,email,image,phone_number,date_of_birth,gender,role,is_active,experience,designer_description "
					+ " FROM users WHERE role = 'd' AND is_active = 1 AND id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setImage(rs.getString("image"));
				user.setPhoneNumber(rs.getLong("phone_number"));
				Date dateOfBirth = rs.getDate("date_of_birth");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				if (dateOfBirth != null) {
					user.setDateOfBirth(dateFormat.format(dateOfBirth));
				} else {
					user.setDateOfBirth(null);
				}
				String gender = rs.getString("gender");
				if (gender != null) {
					gender = GenderEnum.getGenderString(gender);
				}
				user.setGender(gender);
				String role = rs.getString("role");
				if (role != null) {
					role = RoleEnum.getRoleString(role);
				}
				user.setRole(role);
				user.setActive(rs.getBoolean("is_active"));

				user.setDesigner_description(rs.getString("designer_description"));
				user.setExperience(rs.getInt("experience"));

			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return user;
	}

	/**
	 * Retrieves a seller user from the database based on their user ID.
	 *
	 * @param sellerId The ID of the seller user to retrieve.
	 * @return The User object corresponding to the provided seller user ID, or null if not found.
	 * @throws PersistenceException If a database-related error occurs during retrieval.
	 * @throws ValidationException If validation of retrieved data fails.
	 */
	public User findSellerById(int sellerId) throws PersistenceException, ValidationException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			String query = "SELECT id,name,email,image,phone_number,date_of_birth,gender,role,is_active,gst_number,aadhar_number,shop_address "
					+ " FROM users WHERE role = 's' AND is_active = 1 AND id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, sellerId);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setImage(rs.getString("image"));
				user.setPhoneNumber(rs.getLong("phone_number"));
				Date dateOfBirth = rs.getDate("date_of_birth");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				if (dateOfBirth != null) {
					user.setDateOfBirth(dateFormat.format(dateOfBirth));
				} else {
					user.setDateOfBirth(null);
				}
				String gender = rs.getString("gender");
				if (gender != null) {
					gender = GenderEnum.getGenderString(gender);
				}
				user.setGender(gender);
				String role = rs.getString("role");
				if (role != null) {
					role = RoleEnum.getRoleString(role);
				}
				user.setRole(role);
				user.setActive(rs.getBoolean("is_active"));

				user.setAadhar_number(rs.getLong("aadhar_number"));
				user.setGst_number(rs.getString("gst_number"));
				user.setShop_address(rs.getString("shop_address"));

			}
		} catch (SQLException e) {
			Logger.error(e);
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
	 * @throws ValidationException If the email address already exists or doesn't match the specified criteria.
	 * @throws PersistenceException If a database-related error occurs during the validation process.
	 */
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
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
	}

	/**
	 * Checks if a user ID exists in the users table.
	 *
	 * @param userId The ID to check.
	 * @throws ValidationException If the ID doesn't exist or doesn't match the specified criteria.
	 * @throws PersistenceException If a database-related error occurs during the validation process.
	 */
	public static void checkIdExists(int userId) throws ValidationException, PersistenceException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			String query = "SELECT id FROM users WHERE is_active = 1 AND id = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setInt(1, userId);
			rs = pre.executeQuery();

			if (!rs.next()) {
				throw new ValidationException("User Id doesn't exist");
			}
		} catch (SQLException e) {
			Logger.error(e); 
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
	}

	/**
	 * Checks if an email address exists for an active user.
	 *
	 * @param email The email address to check.
	 * @throws ValidationException If the email address doesn't exist or doesn't match the specified criteria.
	 * @throws PersistenceException If a database-related error occurs during the validation process.
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
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
	}

	/**
	 * Checks if an Aadhar number already exists for an active seller user.
	 *
	 * @param aadharNumber The Aadhar number to check.
	 * @throws ValidationException If the Aadhar number already exists.
	 * @throws PersistenceException If a database-related error occurs during the validation process.
	 */
	public static void checkAadharNumberExists(long aadharNumber) throws ValidationException, PersistenceException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			String query = "SELECT aadhar_number FROM users WHERE is_active = 1 AND role='s' AND aadhar_number = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setLong(1, aadharNumber);
			rs = pre.executeQuery();
			if (rs.next()) {
				throw new ValidationException("Aadhar Number already exists");
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
	}

	/**
	 * Checks if a designer's ID exists and is associated with an active designer user.
	 *
	 * @param userId The ID to check.
	 * @throws ValidationException If the designer's ID doesn't exist or doesn't match the specified criteria.
	 * @throws PersistenceException If a database-related error occurs during the validation process.
	 */
	public static void checkDesignerIdExists(int userId) throws ValidationException, PersistenceException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			String query = "SELECT id FROM users WHERE is_active = 1 AND role = 'd' AND id = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setInt(1, userId);
			rs = pre.executeQuery();
			if (!rs.next()) {
				throw new ValidationException("Designer Id doesn't exist");
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
	}

	/**
	 * Checks if a seller's ID exists and is associated with an active seller user.
	 *
	 * @param sellerId The ID to check.
	 * @throws ValidationException If the seller's ID doesn't exist or doesn't match the specified criteria.
	 * @throws PersistenceException If a database-related error occurs during the validation process.
	 */
	public static void checkSellerIdExists(int sellerId) throws ValidationException, PersistenceException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			String query = "SELECT id FROM users WHERE is_active = 1 AND role = 's' AND id = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setInt(1, sellerId); 
			rs = pre.executeQuery();
			if (!rs.next()) {
				throw new ValidationException("Seller Id doesn't exist");
			} 
		} catch (SQLException e) { 
			Logger.error(e); 
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, pre, rs); 
		}
	}

	/**
	 * Retrieves the ID of the last updated non-designer user who is active.
	 *
	 * @return The ID of the last updated non-designer user.
	 * @throws PersistenceException If a database error occurs while retrieving the ID.
	 */
	public static int getLastUpdatedUserId() throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int userId = 0;
		try {
			String query = "SELECT id FROM users WHERE is_active = 1 AND role = 'u' ORDER BY id DESC LIMIT 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				userId = rs.getInt("id"); 
			}  
		} catch (SQLException e) {
			Logger.error(e);
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
	 * @throws PersistenceException If a database error occurs while retrieving the ID.
	 */
	public static int getLastUpdatedDesignerId() throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int designerId = 0;
		try {
			String query = "SELECT id FROM users WHERE is_active = 1 AND role = 'd' ORDER BY created_at DESC LIMIT 1";
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

	/**
	 * Retrieves the ID of the last updated seller who is active.
	 *
	 * @return The ID of the last updated seller.
	 * @throws PersistenceException If a database error occurs while retrieving the ID.
	 */
	public static int getLastUpdatedSellerId() throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int sellerId = 0;
		try {
			String query = "SELECT id FROM users WHERE is_active = 1 AND role = 's' ORDER BY created_at DESC LIMIT 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				sellerId = rs.getInt("id");
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return sellerId;
	}

	/**
	 * Retrieves a user from the database based on their user ID for appointment booking.
	 *
	 * @param userId The ID of the user to retrieve.
	 * @return The User object corresponding to the provided user ID, or null if not found.
	 * @throws PersistenceException If a database-related error occurs during retrieval.
	 * @throws ValidationException If validation of retrieved data fails.
	 */
	public User findByUserIdForAppointment(int userId) throws PersistenceException, ValidationException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			String query = "SELECT id,name,email,image,phone_number,date_of_birth,gender,role,is_active "
					+ "FROM users WHERE id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, userId); 
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setImage(rs.getString("image"));
				user.setPhoneNumber(rs.getLong("phone_number"));
				Date dateOfBirth = rs.getDate("date_of_birth");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				if (dateOfBirth != null) {
					user.setDateOfBirth(dateFormat.format(dateOfBirth));
				} else {
					user.setDateOfBirth(null);
				}
				String gender = rs.getString("gender");
				if (gender != null) {
					gender = GenderEnum.getGenderString(gender);
				}
				user.setGender(gender);
				String role = rs.getString("role");
				if (role != null) {
					role = RoleEnum.getRoleString(role);
				}
				user.setRole(role);
				user.setActive(rs.getBoolean("is_active"));
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return user;
	}

}