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
import in.fssa.minimal.interfaces.UserInterface;
import in.fssa.minimal.model.User;
import in.fssa.minimal.util.ConnectionUtil;

public class UserDAO implements UserInterface{
	@Override
	public Set<User> findAll() throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<User> userList = new HashSet<>();
		try {
			String query = "SELECT * FROM users WHERE is_active = 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setPhoneNumber(rs.getLong("phone_number"));
				user.setActive(rs.getBoolean("is_active"));
				user.setDesigner(rs.getBoolean("is_designer"));
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

	@Override
	public User findById(int userId) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			String query = "SELECT * FROM users WHERE is_active = 1 AND id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setPhoneNumber(rs.getLong("phone_number"));
				user.setActive(rs.getBoolean("is_active"));
				user.setDesigner(rs.getBoolean("is_designer"));
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

	@Override
	public User findByEmail(String email) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			String query = "SELECT * FROM users WHERE is_active = 1 AND email = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setPhoneNumber(rs.getLong("phone_number"));
				user.setActive(rs.getBoolean("is_active"));
				user.setDesigner(rs.getBoolean("is_designer"));
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
	        if (updatedUser.isDesigner() != false) {
	            // Check if the field is updated, otherwise keep the existing value
	            Boolean newValue = updatedUser.isDesigner();
	            Boolean oldValue = getDesignerValueFromDatabase(id);
	            if (newValue != oldValue) {
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
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	        throw new PersistenceException(e);
	    } finally {
	        ConnectionUtil.close(conn, ps, null);
	    }
	}

	private Boolean getDesignerValueFromDatabase(int userId) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean r = false;
		try {
			String query = "SELECT is_designer FROM users WHERE is_active = 1 AND id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			if(!rs.next()) {
				r = rs.getBoolean("is_designer");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return r;
	
	}

	@Override
	public void delete(int userId) throws PersistenceException {
		 Connection conn = null;
		    PreparedStatement ps = null;
		    try {
		    	String query = "UPDATE users SET is_active = false WHERE id = ?";
		        conn = ConnectionUtil.getConnection();
		        ps = conn.prepareStatement(query);    
		        ps.setInt(1, userId);
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

	

	@Override
    public Set<User> findAllDesigner() throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<User> userList = new HashSet<>();
		try {
			String query = "SELECT * FROM users WHERE is_active = 1 AND is_designer = 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setPhoneNumber(rs.getLong("phone_number"));
				user.setActive(rs.getBoolean("is_active"));
				user.setDesigner(rs.getBoolean("is_designer"));
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
	
	@Override
	public User findDesignerById(int userId) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			String query = "SELECT * FROM users WHERE is_active = 1 AND is_designer = 1 AND id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setPhoneNumber(rs.getLong("phone_number"));
				user.setActive(rs.getBoolean("is_active"));
				user.setDesigner(rs.getBoolean("is_designer"));
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
	
}
