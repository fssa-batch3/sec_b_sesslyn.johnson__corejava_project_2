package in.fssa.minimal.service;

import java.util.Set;
import in.fssa.minimal.dao.AppointmentDAO;
import in.fssa.minimal.dto.AppointmentRespondDTO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ServiceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Appointment;
import in.fssa.minimal.util.Logger;
import in.fssa.minimal.validator.AppointmentValidator;

public class AppointmentService {
	/**
	 * Creates a new appointment based on the provided Appointment object, after
	 * validating various constraints.
	 *
	 * @param newAppointment The Appointment object representing the new appointment
	 *                       to be created.
	 * @throws ValidationException If the provided appointment data is invalid.
	 * @throws ServiceException    If a service-related error occurs during the
	 *                             operation.
	 */
	public void createAppointment(Appointment newAppointment) throws ValidationException, ServiceException {
		try { 
			AppointmentValidator.validateAppointment(newAppointment);
			AppointmentDAO appointmentDAO = new AppointmentDAO();  
			appointmentDAO.create(newAppointment); 
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred while creating appointment", e);
		} 
	} 

	/**
	 * Retrieves a set of AppointmentRespondDTO objects representing all appointments.
	 *
	 * @return A set of AppointmentRespondDTO objects representing all appointments.
	 * @throws ServiceException If a service-related error occurs during the operation.
	 * @throws ValidationException If validation of retrieved data fails.
	 */
	public Set<AppointmentRespondDTO> getAllAppointment() throws ServiceException, ValidationException {
		try {
			AppointmentDAO appointmentDTO = new AppointmentDAO();
			Set<AppointmentRespondDTO> appointmentList = appointmentDTO.findAll();
			return appointmentList;
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred while retrieving all appointments", e);
		}
	}

	/**
	 * Retrieves a set of AppointmentRespondDto objects representing appointments
	 * with a specific status.
	 *
	 * @param status The status of appointments to retrieve.
	 * @return A set of AppointmentRespondDto objects representing appointments with
	 *         the specified status.
	 * @throws ValidationException If validation of status data fails.
	 * @throws ServiceException    If a service-related error occurs during the
	 *                             operation.
	 */
	public Set<AppointmentRespondDTO> getAllAppointmentByStatus(String status)
			throws ValidationException, ServiceException {
		try {
			AppointmentValidator.validateStatus(status);
			AppointmentDAO appointmentDTO = new AppointmentDAO();
			Set<AppointmentRespondDTO> appointmentList = appointmentDTO.findAllAppointmentByStatus(status);
			return appointmentList;
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred while retrieving appointments by their status", e);
		}
	}

	/**
	 * Retrieves a set of appointment responses associated with a specific user ID
	 * as the sender.
	 *
	 * @param fromUserId The user ID of the sender for whom to retrieve
	 *                   appointments.
	 * @return A set of {@link AppointmentRespondDTO} objects representing the
	 *         retrieved appointments.
	 * @throws ValidationException If the provided 'fromUserId' is invalid or does
	 *                             not meet validation criteria.
	 * @throws ServiceException    If an error occurs while retrieving the
	 *                             appointments from the database.
	 */
	public Set<AppointmentRespondDTO> getAllAppointmentByFromUserId(int fromUserId)
			throws ValidationException, ServiceException {
		try {
			AppointmentValidator.validateFromUserId(fromUserId);
			AppointmentDAO appointmentDTO = new AppointmentDAO();
			Set<AppointmentRespondDTO> appointmentList = appointmentDTO.findAllAppointmentByFromUserId(fromUserId);
			return appointmentList;
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred while retrieving appointments by their status", e);
		}
	}

	
	/**
	 * Retrieves a set of appointment responses associated with a specific designer ID
	 * as the sender.
	 *
	 * @param toUserId The designer ID of the sender for whom to retrieve
	 *                   appointments.
	 * @return A set of {@link AppointmentRespondDTO} objects representing the
	 *         retrieved appointments.
	 * @throws ValidationException If the provided 'toUserId' is invalid or does
	 *                             not meet validation criteria.
	 * @throws ServiceException    If an error occurs while retrieving the
	 *                             appointments from the database.
	 */
	public Set<AppointmentRespondDTO> getAllAppointmentByToUserId(int toUserId)
			throws ValidationException, ServiceException {
		try {
			AppointmentValidator.validateToUserId(toUserId);
			AppointmentDAO appointmentDTO = new AppointmentDAO();
			Set<AppointmentRespondDTO> appointmentList = appointmentDTO.findAllAppointmentByToUserId(toUserId);
			return appointmentList;
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred while retrieving appointments by their status", e);
		}
	}
	
	/**
	 * Retrieves detailed information about an appointment by its ID.
	 *
	 * @param appId The ID of the appointment to retrieve.
	 * @return An AppointmentRespondDto object representing the requested
	 *         appointment.
	 * @throws ValidationException If validation of ID data fails.
	 * @throws ServiceException    If a service-related error occurs during the
	 *                             operation.
	 */
	public AppointmentRespondDTO findByAppointmentId(int appointmentId) throws ValidationException, ServiceException {
		try {
			AppointmentValidator.validateIdExists(appointmentId);
			AppointmentDAO appointmentDAO = new AppointmentDAO();
			return appointmentDAO.findById(appointmentId);
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred while retrieving appointment by appointment id", e);
		}
	}

	/**
	 * Updates the request status of a specific appointment.
	 *
	 * @param id     The ID of the appointment to update.
	 * @param status The new status to set for the appointment.
	 * @throws ValidationException If validation of ID or status data fails.
	 * @throws ServiceException    If a service-related error occurs during the
	 *                             operation.
	 */
	public void updateAppointmentRequestStatus(int appointmentId, String status)
			throws ValidationException, ServiceException {
		try {
			AppointmentValidator.validateIdExists(appointmentId);
			AppointmentValidator.validateUpdateStatus(status);
			AppointmentDAO appointmentDAO = new AppointmentDAO();
			appointmentDAO.updateRequestStatus(appointmentId, status);
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred while updating appointment status", e);
		}
	}

}