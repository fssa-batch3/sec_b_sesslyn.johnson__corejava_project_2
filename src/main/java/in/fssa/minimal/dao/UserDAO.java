package in.fssa.minimal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import in.fssa.minimal.interfaces.UserInterface;
import in.fssa.minimal.model.User;
import in.fssa.minimal.util.ConnectionUtil;

public class UserDAO implements UserInterface{
	@Override
	public Set<User> findAll() throws RuntimeException {
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
			throw new RuntimeException();
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return userList;
	}

	@Override
	public User findById(int userId) {
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
			throw new RuntimeException();
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return user;
	}

	@Override
	public User findByEmail(String email) {
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
			throw new RuntimeException();
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return user;
	}
	
	@Override
	public void create(User newUser) {
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
		        throw new RuntimeException(e);
		    } finally {
		        ConnectionUtil.close(conn, ps);
		    }
	}

	@Override
	public void update(int id, User updatedUser) {
		 Connection conn = null;
		    PreparedStatement ps = null;
		    try {
		        String query = "UPDATE users SET name = ?, password = ?, is_designer = ?, phone_number = ? WHERE is_active = 1 AND id = ?" ;
		        conn = ConnectionUtil.getConnection();
		        ps = conn.prepareStatement(query);
		        ps.setString(1, updatedUser.getName());          
		        ps.setString(2, updatedUser.getPassword()); 
		        ps.setBoolean(3, updatedUser.isDesigner());
		        ps.setLong(4, updatedUser.getPhoneNumber());
		        ps.setInt(5, id);
		        ps.executeUpdate(); 
		        System.out.println("User has been updated successfully");
		    } catch (SQLException e) {
		        e.printStackTrace();
		        System.out.println(e.getMessage());
		        throw new RuntimeException(e);
		    } finally {
		        ConnectionUtil.close(conn, ps, null);
		    }
	}

	@Override
	public void delete(int userId) {
		 Connection conn = null;
		    PreparedStatement ps = null;
		    try {
		    	String query = "UPDATE users SET is_active = false WHERE is_active = 1 AND id = ?";
		        conn = ConnectionUtil.getConnection();
		        ps = conn.prepareStatement(query);    
		        ps.setInt(1, userId);
		        ps.executeUpdate(); 
		        System.out.println("User has been deleted successfully");
		    } catch (SQLException e) {
		        e.printStackTrace();
		        System.out.println(e.getMessage());
		        throw new RuntimeException(e);
		    } finally {
		        ConnectionUtil.close(conn, ps, null);
		    }
	}

	

	@Override
    public Set<User> findAllDesigner() throws RuntimeException {
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
			throw new RuntimeException();
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return userList;
	}
	
	@Override
	public User findDesignerById(int userId) {
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
			throw new RuntimeException();
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return user;
	}
	
}
