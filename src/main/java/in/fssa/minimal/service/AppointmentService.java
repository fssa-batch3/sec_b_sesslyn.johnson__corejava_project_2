package in.fssa.minimal.service;

import java.util.Set;
import in.fssa.minimal.dao.AppointmentDAO;
import in.fssa.minimal.dao.UserDAO;
import in.fssa.minimal.dto.AppointmentRespondDto;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Appointment;
import in.fssa.minimal.validator.AppointmentValidator;

public class AppointmentService {
	/**
	 * Creates a new appointment based on the provided Appointment object, after
	 * validating various constraints.
	 *
	 * @param newAppointment The Appointment object representing the new appointment
	 *                       to be created.
	 * @throws ValidationException  If the provided appointment data is invalid.
	 * @throws PersistenceException If a database error occurs while persisting the
	 *                              appointment.
	 */
	public void create(Appointment newAppointment) throws ValidationException, PersistenceException {
		AppointmentValidator.validate(newAppointment);
		UserDAO.checkIdExists(newAppointment.getFromUser());
		UserDAO.checkDesignerIdExists(newAppointment.getToUser());
		AppointmentDAO.checkFromUserHasUpcomingAppointments(newAppointment.getFromUser());
		AppointmentDAO.checkToUserHasAppointmentAtSameDateTime(newAppointment.getToUser(), newAppointment.getDate(),
				newAppointment.getTime());
		AppointmentDAO appointmentDao = new AppointmentDAO();
		appointmentDao.create(newAppointment);
	}

	/**
	 * Retrieves a set of AppointmentRespondDto objects representing all
	 * appointments.
	 *
	 * @return A set of AppointmentRespondDto objects representing all appointments.
	 * @throws ValidationException  If validation of appointment data fails.
	 * @throws PersistenceException If a database error occurs while retrieving
	 *                              appointments.
	 */
	public Set<AppointmentRespondDto> getAll() throws ValidationException, PersistenceException {
		AppointmentDAO appDto = new AppointmentDAO();
		Set<AppointmentRespondDto> appList = appDto.findAll();
		for (AppointmentRespondDto app : appList) {
			System.out.println(app);
		}
		return appList;
	}

	/**
	 * Retrieves a set of AppointmentRespondDto objects representing appointments
	 * with a specific status.
	 *
	 * @param status The status of appointments to retrieve.
	 * @return A set of AppointmentRespondDto objects representing appointments with
	 *         the specified status.
	 * @throws ValidationException  If validation of status data fails.
	 * @throws PersistenceException If a database error occurs while retrieving
	 *                              appointments.
	 */
	public Set<AppointmentRespondDto> getAllByStatus(String status) throws ValidationException, PersistenceException {
		AppointmentValidator.validateStatus(status);
		AppointmentDAO appDto = new AppointmentDAO();
		Set<AppointmentRespondDto> appList = appDto.findAllByStatus(status);
		for (AppointmentRespondDto app : appList) {
			System.out.println(app);
		}
		return appList;
	}

	/**
	 * Retrieves detailed information about an appointment by its ID.
	 *
	 * @param appId The ID of the appointment to retrieve.
	 * @return An AppointmentRespondDto object representing the requested
	 *         appointment.
	 * @throws ValidationException  If validation of ID data fails.
	 * @throws PersistenceException If a database error occurs while retrieving the
	 *                              appointment.
	 */
	public AppointmentRespondDto findById(int appId) throws ValidationException, PersistenceException {
		AppointmentValidator.validateId(appId);
		AppointmentDAO.checkIdExists(appId);
		AppointmentDAO appDao = new AppointmentDAO();
		return appDao.findById(appId);
	}

	/**
	 * Updates the request status of a specific appointment.
	 *
	 * @param id     The ID of the appointment to update.
	 * @param status The new status to set for the appointment.
	 * @throws ValidationException  If validation of ID or status data fails.
	 * @throws PersistenceException If a database error occurs while updating the
	 *                              status.
	 */
	public void updateRequestStatus(int id, String status) throws ValidationException, PersistenceException {
		AppointmentValidator.validateId(id);
		AppointmentValidator.validateUpdateStatus(status);
		AppointmentDAO.checkIdExists(id);
		AppointmentDAO appDao = new AppointmentDAO();
		appDao.updateRequestStatus(id, status);
	}

}
