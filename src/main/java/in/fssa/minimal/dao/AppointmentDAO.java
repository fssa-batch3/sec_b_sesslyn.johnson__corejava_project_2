package in.fssa.minimal.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import in.fssa.minimal.dto.AppointmentRespondDto;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.interfaces.AppointmentInterface;
import in.fssa.minimal.model.Appointment;
import in.fssa.minimal.model.AppointmentEntity;
import in.fssa.minimal.model.User;
import in.fssa.minimal.service.UserService;
import in.fssa.minimal.util.ConnectionUtil;

public class AppointmentDAO {
/**
 * 
 * @param newAppointment
 * @throws PersistenceException
 */
	public void create(Appointment newAppointment) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO appointment (from_user, to_user, email, phone_number, status, date, time, address) VALUES (?,?,?,?,?,?,?,?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, newAppointment.getFromUser());
			ps.setInt(2, newAppointment.getToUser());
			ps.setString(3, newAppointment.getEmail());
			ps.setLong(4, newAppointment.getPhoneNumber());
			ps.setString(5, newAppointment.getStatus());

			java.sql.Date date = java.sql.Date.valueOf(newAppointment.getDate());
			ps.setDate(6, date);

			java.sql.Time time = java.sql.Time.valueOf(newAppointment.getTime());
			ps.setTime(7, time);

			if (newAppointment.getAddress() != null) {
				ps.setString(8, newAppointment.getAddress());
			} else {
				ps.setNull(8, Types.VARCHAR);
			}

			ps.executeUpdate();
			System.out.println("Appointment has been created successfully");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}
/**
 * 
 * @return
 * @throws ValidationException
 * @throws PersistenceException
 */
	public Set<AppointmentRespondDto> findAll() throws ValidationException, PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<AppointmentRespondDto> appointmentList = new HashSet<>();
		try {
			String query = "SELECT * FROM appointment";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			User from_user_obj = null;
			User to_user_obj = null;
			while (rs.next()) {
				int from_user = rs.getInt("from_user");
				int to_user = rs.getInt("to_user");

				from_user_obj = UserService.findById(from_user);
				to_user_obj = UserService.findById(from_user);

				AppointmentRespondDto app = new AppointmentRespondDto();
				app.setId(rs.getInt("id"));
				app.setFromUser(from_user_obj);
				app.setToUser(to_user_obj);
				app.setStatus(rs.getString("status"));
				Date date = rs.getDate("date");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				app.setDate(dateFormat.format(date));

				Time time = rs.getTime("time");
				SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
				app.setTime(timeFormat.format(time));

				app.setAddress(rs.getString("address"));
				appointmentList.add(app);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return appointmentList;
	}
/**
 * 
 * @param status
 * @return
 * @throws ValidationException
 * @throws PersistenceException
 */
	public Set<AppointmentRespondDto> findAllByStatus(String status) throws ValidationException, PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<AppointmentRespondDto> appointmentList = new HashSet<>();
		try {
			String query = "SELECT * FROM appointment WHERE status = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, status);
			rs = ps.executeQuery();
			User from_user_obj = null;
			User to_user_obj = null;
			while (rs.next()) {
				int from_user = rs.getInt("from_user");
				int to_user = rs.getInt("to_user");

				from_user_obj = UserService.findById(from_user);
				to_user_obj = UserService.findById(from_user);

				AppointmentRespondDto app = new AppointmentRespondDto();
				app.setId(rs.getInt("id"));
				app.setFromUser(from_user_obj);
				app.setToUser(to_user_obj);
				app.setStatus(rs.getString("status"));
				Date date = rs.getDate("date");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				app.setDate(dateFormat.format(date));

				Time time = rs.getTime("time");
				SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
				app.setTime(timeFormat.format(time));

				app.setAddress(rs.getString("address"));
				appointmentList.add(app);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return appointmentList;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ValidationException
	 * @throws PersistenceException
	 */
	public AppointmentRespondDto findById(int id) throws ValidationException, PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		AppointmentRespondDto app = null;
		User from_user_obj = null;
		User to_user_obj = null;
		try {
			String query = "SELECT * FROM appointment WHERE id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				app = new AppointmentRespondDto();
				int from_user = rs.getInt("from_user");
				int to_user = rs.getInt("to_user");

				from_user_obj = UserService.findById(from_user);
				to_user_obj = UserService.findById(from_user);

				app.setId(rs.getInt("id"));
				app.setFromUser(from_user_obj);
				app.setToUser(to_user_obj);
				app.setStatus(rs.getString("status"));
				Date date = rs.getDate("date");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				app.setDate(dateFormat.format(date));

				Time time = rs.getTime("time");
				SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
				app.setTime(timeFormat.format(time));

				app.setAddress(rs.getString("address"));
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return app;
	}
	
/**
 * 
 * @param id
 * @param status
 * @throws PersistenceException
 */
	public void updateRequestStatus(int id, String status) throws PersistenceException {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    try {
		        String query = "UPDATE appointment SET status = ? WHERE id = ?" ;
		        conn = ConnectionUtil.getConnection();
		        ps = conn.prepareStatement(query);
		        ps.setString(1, status);  
		        ps.setInt(2, id);           
		        ps.executeUpdate(); 
		        System.out.println("Appointment Status has been updated successfully");
		    } catch (SQLException e) {
		        e.printStackTrace();
		        System.out.println(e.getMessage());
		        throw new PersistenceException(e);
		    } finally {
		        ConnectionUtil.close(conn, ps, null);
		    }
	}
	
}