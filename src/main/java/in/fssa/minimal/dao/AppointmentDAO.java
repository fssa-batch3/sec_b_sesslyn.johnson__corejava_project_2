package in.fssa.minimal.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import in.fssa.minimal.dto.AppointmentRespondDTO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ServiceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Appointment;
import in.fssa.minimal.model.User;
import in.fssa.minimal.service.UserService;
import in.fssa.minimal.util.ConnectionUtil;
import in.fssa.minimal.util.Logger;

public class AppointmentDAO {

	/** 
	 * This method is responsible for creating a new appointment record in the
	 * database.
	 *
	 * @param newAppointment The appointment to be created.
	 * @throws PersistenceException If a database error occurs while creating the
	 *                              appointment. 
	 * @throws
	 */
	public void create(Appointment newAppointment) throws PersistenceException {
		Connection conn = null; 
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO appointments (from_user, to_user,email,phone_number, status, date, time, address) VALUES (?,?,?,?,?,?,?,?)";
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
				ps.setNull(8, java.sql.Types.VARCHAR);
			}

			ps.executeUpdate();
			Logger.info("Appointment has been created successfully");

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	/**
	 * Retrieves all appointments from the database.
	 *
	 * @return A set of AppointmentRespondDto objects representing all appointments.
	 * @throws ValidationException  If validation of appointment data fails.
	 * @throws PersistenceException If a database error occurs while retrieving
	 *                              appointments.
	 * @throws ServiceException     If a service-related error occurs during the
	 *                              operation.
	 */
	public Set<AppointmentRespondDTO> findAll() throws ValidationException, PersistenceException, ServiceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<AppointmentRespondDTO> appointmentList = new HashSet<>();
		try {
			String query = "SELECT id, from_user, to_user, email, phone_number, status, date, time, address"
					+ " FROM appointments";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			User fromUserObj = null;
			User toUserObj = null;
			while (rs.next()) {
				int fromUser = rs.getInt("from_user");
				int toUser = rs.getInt("to_user");

				fromUserObj = UserService.findByUserIdForAppointment(fromUser);
				toUserObj = UserService.findByUserIdForAppointment(toUser);

				AppointmentRespondDTO appointmentRespondDTO = new AppointmentRespondDTO();
				appointmentRespondDTO.setId(rs.getInt("id"));
				appointmentRespondDTO.setFromUser(fromUserObj);
				appointmentRespondDTO.setToUser(toUserObj);
				appointmentRespondDTO.setEmail(rs.getString("email"));
				appointmentRespondDTO.setPhoneNumber(rs.getLong("phone_number"));
				appointmentRespondDTO.setStatus(rs.getString("status"));
				Date date = rs.getDate("date");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				appointmentRespondDTO.setDate(dateFormat.format(date));

				Time time = rs.getTime("time");
				SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
				appointmentRespondDTO.setTime(timeFormat.format(time));

				appointmentRespondDTO.setAddress(rs.getString("address"));
				appointmentList.add(appointmentRespondDTO);
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return appointmentList;
	}

	/**
	 * Retrieves appointments with a specific status from the database.
	 *
	 * @param status The status of appointments to retrieve.
	 * @return A set of AppointmentRespondDto objects representing appointments with
	 *         the specified status.
	 * @throws ValidationException  If validation of status data fails.
	 * @throws PersistenceException If a database error occurs while retrieving
	 *                              appointments.
	 * @throws ServiceException     If a service-related error occurs during the
	 *                              operation.
	 */
	public Set<AppointmentRespondDTO> findAllAppointmentByStatus(String status)
			throws ValidationException, PersistenceException, ServiceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<AppointmentRespondDTO> appointmentList = new HashSet<>();
		try {
			String query = "SELECT id, from_user, to_user, email, phone_number, status, date, time, address"
					+ " FROM appointments WHERE status = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, status);
			rs = ps.executeQuery();
			User fromUserObj = null;
			User toUserObj = null;
			while (rs.next()) {
				int fromUser = rs.getInt("from_user");
				int toUser = rs.getInt("to_user");
				fromUserObj = UserService.findByUserId(fromUser);
				toUserObj = UserService.findByUserId(toUser);

				AppointmentRespondDTO appointmentRespondDTO = new AppointmentRespondDTO();
				appointmentRespondDTO.setId(rs.getInt("id"));
				appointmentRespondDTO.setFromUser(fromUserObj);
				appointmentRespondDTO.setToUser(toUserObj);
				appointmentRespondDTO.setPhoneNumber(rs.getLong("phone_number"));
				appointmentRespondDTO.setStatus(rs.getString("status"));
				appointmentRespondDTO.setStatus(rs.getString("status"));
				Date date = rs.getDate("date");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				appointmentRespondDTO.setDate(dateFormat.format(date));

				Time time = rs.getTime("time");
				SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
				appointmentRespondDTO.setTime(timeFormat.format(time));

				appointmentRespondDTO.setAddress(rs.getString("address"));
				appointmentList.add(appointmentRespondDTO);
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return appointmentList;
	}

	/**
	 * Retrieves a set of appointment responses based on the provided 'fromUserId'.
	 *
	 * @param fromUserId The ID of the user initiating the appointments.
	 * @return A Set of AppointmentRespondDTO objects representing the appointments.
	 * @throws ValidationException  if the input validation fails.
	 * @throws PersistenceException if there's an issue with data persistence.
	 * @throws ServiceException     if a service-related error occurs.
	 */
	public Set<AppointmentRespondDTO> findAllAppointmentByFromUserId(int fromUserId)
			throws ValidationException, PersistenceException, ServiceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<AppointmentRespondDTO> appointmentList = new HashSet<>();
		try {
			String query = "SELECT id, from_user, to_user, email, phone_number, status, date, time, address"
					+ " FROM appointments WHERE from_user = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, fromUserId);
			rs = ps.executeQuery();
			User fromUserObj = null;
			User toUserObj = null;
			while (rs.next()) {
				int toUser = rs.getInt("to_user");
				fromUserObj = UserService.findByUserId(fromUserId);
				toUserObj = UserService.findByUserId(toUser);

				AppointmentRespondDTO appointmentRespondDTO = new AppointmentRespondDTO();
				appointmentRespondDTO.setId(rs.getInt("id"));
				appointmentRespondDTO.setFromUser(fromUserObj);
				appointmentRespondDTO.setToUser(toUserObj);
				appointmentRespondDTO.setEmail(rs.getString("email"));
				appointmentRespondDTO.setPhoneNumber(rs.getLong("phone_number"));
				appointmentRespondDTO.setStatus(rs.getString("status"));
				Date date = rs.getDate("date");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				appointmentRespondDTO.setDate(dateFormat.format(date));

				Time time = rs.getTime("time");
				SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
				appointmentRespondDTO.setTime(timeFormat.format(time));
				appointmentRespondDTO.setPhoneNumber(rs.getLong("phone_number"));
				appointmentRespondDTO.setStatus(rs.getString("status"));

				appointmentRespondDTO.setAddress(rs.getString("address"));
				appointmentList.add(appointmentRespondDTO);
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return appointmentList;
	}

	/**
	 * Retrieves a set of appointment responses based on the provided 'toUserId'.
	 *
	 * @param toUserId The ID of the user initiating the appointments.
	 * @return A Set of AppointmentRespondDTO objects representing the appointments.
	 * @throws ValidationException  if the input validation fails.
	 * @throws PersistenceException if there's an issue with data persistence.
	 * @throws ServiceException     if a service-related error occurs.
	 */
	public Set<AppointmentRespondDTO> findAllAppointmentByToUserId(int toUserId)
			throws ValidationException, PersistenceException, ServiceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<AppointmentRespondDTO> appointmentList = new HashSet<>();
		try {
			String query = "SELECT id, from_user, to_user, email, phone_number, status, date, time, address"
					+ " FROM appointments WHERE to_user = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, toUserId);
			rs = ps.executeQuery();
			User fromUserObj = null;
			User toUserObj = null;
			while (rs.next()) {
				int fromUser = rs.getInt("from_user");
				fromUserObj = UserService.findByUserIdForAppointment(fromUser);
				toUserObj = UserService.findByUserIdForAppointment(toUserId);

				AppointmentRespondDTO appointmentRespondDTO = new AppointmentRespondDTO();
				appointmentRespondDTO.setId(rs.getInt("id"));
				appointmentRespondDTO.setFromUser(fromUserObj);
				appointmentRespondDTO.setToUser(toUserObj);
				appointmentRespondDTO.setEmail(rs.getString("email"));
				appointmentRespondDTO.setPhoneNumber(rs.getLong("phone_number"));
				appointmentRespondDTO.setStatus(rs.getString("status"));
				Date date = rs.getDate("date");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				appointmentRespondDTO.setDate(dateFormat.format(date));

				Time time = rs.getTime("time");
				SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
				appointmentRespondDTO.setTime(timeFormat.format(time));
				appointmentRespondDTO.setStatus(rs.getString("status"));
				appointmentRespondDTO.setAddress(rs.getString("address"));
				appointmentList.add(appointmentRespondDTO);
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return appointmentList;
	}

	/**
	 * Retrieves a specific appointment by its ID from the database.
	 *
	 * @param id The ID of the appointment to retrieve.
	 * @return An AppointmentRespondDto object representing the requested
	 *         appointment.
	 * @throws ValidationException  If validation of ID data fails.
	 * @throws PersistenceException If a database error occurs while retrieving the
	 *                              appointment.
	 * @throws ServiceException     If a service-related error occurs during the
	 *                              operation.
	 */
	public AppointmentRespondDTO findById(int appointmentId)
			throws ValidationException, PersistenceException, ServiceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		AppointmentRespondDTO appointmentRespondDTO = null;
		User fromUserObj = null;
		User toUserObj = null;
		try {
			String query = "SELECT id, from_user, to_user, email, phone_number, status, date, time, address"
					+ " FROM appointments WHERE id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, appointmentId);
			rs = ps.executeQuery();
			if (rs.next()) {
				int fromUser = rs.getInt("from_user");
				int toUser = rs.getInt("to_user");
				fromUserObj = UserService.findByUserId(fromUser);
				toUserObj = UserService.findByUserId(toUser);
				appointmentRespondDTO = new AppointmentRespondDTO();
				appointmentRespondDTO.setPhoneNumber(rs.getLong("phone_number"));
				appointmentRespondDTO.setStatus(rs.getString("status"));
				
				appointmentRespondDTO.setId(rs.getInt("id"));
				appointmentRespondDTO.setFromUser(fromUserObj);
				appointmentRespondDTO.setToUser(toUserObj);
				appointmentRespondDTO.setStatus(rs.getString("status"));
				Date date = rs.getDate("date");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				appointmentRespondDTO.setDate(dateFormat.format(date));

				Time time = rs.getTime("time");
				SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
				appointmentRespondDTO.setTime(timeFormat.format(time));

				appointmentRespondDTO.setAddress(rs.getString("address"));

			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return appointmentRespondDTO;
	}

	/**
	 * Updates the status of a specific appointment in the database.
	 *
	 * @param id     The ID of the appointment to update.
	 * @param status The new status to set for the appointment.
	 * @throws PersistenceException If a database error occurs while updating the
	 *                              status.
	 */
	public void updateRequestStatus(int appointmentId, String status) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "UPDATE appointments SET status = ? WHERE id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, status);
			ps.setInt(2, appointmentId);
			ps.executeUpdate();
			Logger.info("Appointment Status has been updated successfully");
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	/**
	 * Checks if a user has upcoming appointments.
	 *
	 * @param fromUser The ID of the user to check.
	 * @throws ValidationException  If the user has an upcoming appointment.
	 * @throws PersistenceException If a database error occurs during the check.
	 */
	public static void checkFromUserHasUpcomingAppointments(int fromUser)
			throws ValidationException, PersistenceException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			String query = "SELECT id FROM appointments WHERE from_user = ? AND date >= CURRENT_DATE AND (status = 'waiting_list' OR status = 'approved')";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setInt(1, fromUser);
			rs = pre.executeQuery();
			if (rs.next()) {
				throw new ValidationException("The appointment you have is yet to be completed. Please be patient");
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
	}

	/**
	 * Checks if a user has an appointment at the same date and time.
	 *
	 * @param toUser The ID of the user to check.
	 * @param date   The date of the appointment to check.
	 * @param time   The time of the appointment to check.
	 * @throws ValidationException  If the user has an appointment at the same date
	 *                              and time.
	 * @throws PersistenceException If a database error occurs during the check.
	 */
	public static void checkToUserHasAppointmentAtSameDateTime(int toUser, String date, String time)
			throws ValidationException, PersistenceException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			String query = "SELECT id FROM appointments WHERE to_user = ? AND date = ? AND time = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setInt(1, toUser);
			java.sql.Date convertedDate = java.sql.Date.valueOf(date);
			pre.setDate(2, convertedDate);

			java.sql.Time convertedTime = java.sql.Time.valueOf(time);
			pre.setTime(3, convertedTime);

			rs = pre.executeQuery();
			if (rs.next()) {
				throw new ValidationException(
						"The designer has an appointment at that specific time. Please reschedule the appointment for a different time slot");
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
	}

	/**
	 * Checks if an appointment with the given ID exists in the database.
	 *
	 * @param id The ID of the appointment to check.
	 * @throws ValidationException  If the appointment with the specified ID doesn't
	 *                              exist.
	 * @throws PersistenceException If a database error occurs during the check.
	 */
	public static void checkIdExists(int appointmentId) throws ValidationException, PersistenceException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			String query = "Select id From appointments Where id = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setInt(1, appointmentId);
			rs = pre.executeQuery();
			if (!rs.next()) {
				throw new ValidationException("Id doesn't exist");
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
	}
}