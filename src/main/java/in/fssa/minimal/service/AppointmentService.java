package in.fssa.minimal.service;

import java.util.Set;
import in.fssa.minimal.dao.AppointmentDAO;
import in.fssa.minimal.dto.AppointmentRespondDto;
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
	public void create(Appointment newAppointment) throws ValidationException, ServiceException {
	    try {
	        AppointmentValidator.validate(newAppointment);
	        AppointmentDAO appointmentDao = new AppointmentDAO();
	        appointmentDao.create(newAppointment);
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
	public Set<AppointmentRespondDto> getAll() throws ServiceException, ValidationException {
	    try {
	        AppointmentDAO appDto = new AppointmentDAO();
	        Set<AppointmentRespondDto> appList = appDto.findAll();
	        for (AppointmentRespondDto app : appList) {
	            System.out.println(app);
	        }
	        return appList;
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
	public Set<AppointmentRespondDto> getAllByStatus(String status) throws ValidationException, ServiceException {
	    try {
	        AppointmentValidator.validateStatus(status);
	        AppointmentDAO appDto = new AppointmentDAO();
	        Set<AppointmentRespondDto> appList = appDto.findAllByStatus(status);
	        for (AppointmentRespondDto app : appList) {
	            System.out.println(app);
	        }
	        return appList;
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
	public AppointmentRespondDto findById(int appId) throws ValidationException, ServiceException {
	    try {
	        AppointmentValidator.validateIdExists(appId);
	        AppointmentDAO appDao = new AppointmentDAO();
	        return appDao.findById(appId);
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
	        AppointmentDAO appDao = new AppointmentDAO();
	        appDao.updateRequestStatus(id, status);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while updating appointment status.", e);
	    }
	}

}
