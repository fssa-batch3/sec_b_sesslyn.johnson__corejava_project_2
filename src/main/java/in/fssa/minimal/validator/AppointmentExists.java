package in.fssa.minimal.validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.util.ConnectionUtil;

public class AppointmentExists {
	public static void checkFromUserHasUpcomingAppointments(int fromUser) throws ValidationException {
	    Connection conn = null;
	    PreparedStatement pre = null;
	    ResultSet rs = null;

	    try {
	        String query = "SELECT * FROM appointment WHERE from_user = ? AND date >= CURRENT_DATE";
	        conn = ConnectionUtil.getConnection();
	        pre = conn.prepareStatement(query);
	        pre.setInt(1, fromUser);
	        rs = pre.executeQuery();
	        if (rs.next()) {
	            throw new ValidationException("The appointment you have is yet to be completed. Please be patient");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	        throw new RuntimeException();
	    } finally {
	        ConnectionUtil.close(conn, pre, rs);
	    }
	}

	public static void checkToUserHasAppointmentAtSameDateTime(int toUser, String date, String time) throws ValidationException {
	    Connection conn = null;
	    PreparedStatement pre = null;
	    ResultSet rs = null;

	    try {
	        String query = "SELECT * FROM appointment WHERE to_user = ? AND date = ? AND time = ?";
	        conn = ConnectionUtil.getConnection();
	        pre = conn.prepareStatement(query);
	        pre.setInt(1, toUser);
	        java.sql.Date convertedDate = java.sql.Date.valueOf(date);
            pre.setDate(2, convertedDate);

            java.sql.Time convertedTime = java.sql.Time.valueOf(time);
            pre.setTime(3, convertedTime);
            
	        rs = pre.executeQuery();
	        if (rs.next()) {
	            throw new ValidationException("The designer has an appointment at that specific time. Please reschedule the appointment for a different time slot");
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
			String query = "Select * From appointment Where id = ?";
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

}
