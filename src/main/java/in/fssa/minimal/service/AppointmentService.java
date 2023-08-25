package in.fssa.minimal.service;

import java.util.Set;
import in.fssa.minimal.dao.AppointmentDAO;
import in.fssa.minimal.dto.AppointmentRespondDTO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ServiceException;
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
	 * @throws ServiceException    If a service-related error occurs during the operation.
	 */
	public void createAppointment(Appointment newAppointment) throws ValidationException, ServiceException {
	    try {
	        AppointmentValidator.validate(newAppointment);
	        AppointmentDAO appointmentDAO = new AppointmentDAO();
	        appointmentDAO.create(newAppointment);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while creating appointment.", e);
	    }
	}

	/**
	 * Retrieves a set of AppointmentRespondDto objects representing all
	 * appointments.
	 *
	 * @return A set of AppointmentRespondDto objects representing all appointments.
	 * @throws ServiceException    If a service-related error occurs during the operation.
	 * @throws ValidationException 
	 */
	public Set<AppointmentRespondDTO> getAllAppointment() throws ServiceException, ValidationException {
	    try {
	        AppointmentDAO appointmentDTO = new AppointmentDAO();
	        Set<AppointmentRespondDTO> appointmentList = appointmentDTO.findAll();
	        for (AppointmentRespondDTO appointment : appointmentList) {
	            System.out.println(appointment);
	        }
	        return appointmentList;
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while retrieving appointments.", e);
	    }
	}

	/**
	 * Retrieves a set of AppointmentRespondDto objects representing appointments
	 * with a specific status.
	 *
	 * @param status The status of appointments to retrieve.
	 * @return A set of AppointmentRespondDto objects representing appointments with
	 *         the specified status.
	 * @throws ValidationException  If validation of status data fails.
	 * @throws ServiceException    If a service-related error occurs during the operation.
	 */
	public Set<AppointmentRespondDTO> getAllAppointmentByStatus(String status) throws ValidationException, ServiceException {
	    try {
	        AppointmentValidator.validateStatus(status);
	        AppointmentDAO appointmentDTO = new AppointmentDAO();
	        Set<AppointmentRespondDTO> appointmentList = appointmentDTO.findAllAppointmentByStatus(status);
	        for (AppointmentRespondDTO appointment : appointmentList) {
	            System.out.println(appointment);
	        }
	        return appointmentList;
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while retrieving appointments.", e);
	    }
	}

	/**
	 * Retrieves detailed information about an appointment by its ID.
	 *
	 * @param appId The ID of the appointment to retrieve.
	 * @return An AppointmentRespondDto object representing the requested
	 *         appointment.
	 * @throws ValidationException  If validation of ID data fails.
	 * @throws ServiceException    If a service-related error occurs during the operation.
	 */
	public AppointmentRespondDTO findById(int appointmentId) throws ValidationException, ServiceException {
	    try {
	        AppointmentValidator.validateIdExists(appointmentId);
	        AppointmentDAO appointmentDAO = new AppointmentDAO();
	        return appointmentDAO.findById(appointmentId);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while retrieving appointment details.", e);
	    }
	}

	/**
	 * Updates the request status of a specific appointment.
	 *
	 * @param id     The ID of the appointment to update.
	 * @param status The new status to set for the appointment.
	 * @throws ValidationException  If validation of ID or status data fails.
	 * @throws ServiceException    If a service-related error occurs during the operation.
	 */
	public void updateRequestStatus(int id, String status) throws ValidationException, ServiceException {
	    try {
	        AppointmentValidator.validateIdExists(id);
	        AppointmentValidator.validateUpdateStatus(status);
	        AppointmentDAO appointmentDAO = new AppointmentDAO();
	        appointmentDAO.updateRequestStatus(id, status);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while updating appointment status.", e);
	    }
	}

}
