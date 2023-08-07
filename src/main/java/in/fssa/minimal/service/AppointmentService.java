package in.fssa.minimal.service;

import in.fssa.minimal.dao.AppointmentDAO;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Appointment;
import in.fssa.minimal.validator.AppointmentValidator;

public class AppointmentService {
	public void create(Appointment newAppointment) throws ValidationException  {
		AppointmentValidator.Validate(newAppointment);
		AppointmentDAO appointmentDao = new AppointmentDAO();
		appointmentDao.create(newAppointment);
	}
}
