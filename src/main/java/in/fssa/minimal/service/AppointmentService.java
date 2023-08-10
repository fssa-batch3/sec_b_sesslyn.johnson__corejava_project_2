package in.fssa.minimal.service;

import java.util.Set;
import in.fssa.minimal.dao.AppointmentDAO;
import in.fssa.minimal.dto.AppointmentRespondDto;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Appointment;
import in.fssa.minimal.validator.AppointmentExists;
import in.fssa.minimal.validator.AppointmentValidator;
import in.fssa.minimal.validator.UserExists;


public class AppointmentService {
	
	public void create(Appointment newAppointment) throws ValidationException, PersistenceException {
		AppointmentValidator.Validate(newAppointment);
		UserExists.checkIdExists(newAppointment.getFromUser());
		UserExists.checkDesignerIdExists(newAppointment.getToUser());
		AppointmentExists.checkFromUserHasUpcomingAppointments(newAppointment.getFromUser());
		AppointmentExists.checkToUserHasAppointmentAtSameDateTime(newAppointment.getToUser(), newAppointment.getDate(),
				newAppointment.getTime());
		AppointmentDAO appointmentDao = new AppointmentDAO();
		appointmentDao.create(newAppointment);
	}
	
	public Set<AppointmentRespondDto> getAll() throws ValidationException, PersistenceException {
		AppointmentDAO appDto = new AppointmentDAO();
		Set<AppointmentRespondDto> appList = appDto.findAll();
		for (AppointmentRespondDto app : appList) {
			System.out.println(app);
		}
		return appList;
	}
	
	public Set<AppointmentRespondDto> getAllByStatus(String status) throws ValidationException, PersistenceException {
		AppointmentValidator.validateStatus(status);
		AppointmentDAO appDto = new AppointmentDAO();
		Set<AppointmentRespondDto> appList = appDto.findAllByStatus(status);
		for (AppointmentRespondDto app : appList) {
			System.out.println(app);
		}
		return appList;
	}
	
	public AppointmentRespondDto findById(int appId) throws ValidationException, PersistenceException {
		AppointmentValidator.validateId(appId);
		AppointmentExists.checkIdExists(appId);
		AppointmentDAO appDao = new AppointmentDAO();
		return appDao.findById(appId);
	}
	
	public void updateRequestStatus(int id, String status) throws ValidationException, PersistenceException {
		AppointmentValidator.validateId(id);
		AppointmentValidator.validateUpdateStatus(status);
		AppointmentExists.checkIdExists(id);
		AppointmentDAO appDao = new AppointmentDAO();
		appDao.updateRequestStatus(id, status);	
	}
	

}
