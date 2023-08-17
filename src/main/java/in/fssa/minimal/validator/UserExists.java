package in.fssa.minimal.validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.util.ConnectionUtil;

public class UserExists {

	public static void emailExists(String email) throws ValidationException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			String query = "Select * From users Where email = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setString(1, email);
			rs = pre.executeQuery();
			if (rs.next()) {
				throw new ValidationException("Email already exists");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException();
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
	}
   
	public static void checkIdExists(int id) throws ValidationException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			String query = "Select * From users Where id = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setInt(1, id);
			rs = pre.executeQuery();
			if (!rs.next()) {
				throw new ValidationException("Id doesn't exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException();
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
	}
	
	public static void checkEmailExists(String email) throws ValidationException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			String query = "Select * From users Where is_active = 1 AND email = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setString(1, email);
			rs = pre.executeQuery();
			if (!rs.next()) {
				throw new ValidationException("Email doesn't exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException();
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
	}
	
	public static void checkDesignerIdExists(int id) throws ValidationException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			String query = "Select * From users Where is_active = 1 AND is_designer = 1 AND id = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setInt(1, id);
			rs = pre.executeQuery();
			if (!rs.next()) {
				throw new ValidationException("Designer Id doesn't exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException();
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
	}
	
}
