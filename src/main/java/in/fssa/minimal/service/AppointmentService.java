package in.fssa.minimal.service;

import in.fssa.minimal.dao.AppointmentDAO;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Appointment;
import in.fssa.minimal.validator.AppointmentExists;
import in.fssa.minimal.validator.AppointmentValidator;
import in.fssa.minimal.validator.UserExists;

public class AppointmentService {
	public void create(Appointment newAppointment) throws ValidationException {
		AppointmentValidator.Validate(newAppointment);
		UserExists.checkIdExists(newAppointment.getFromUser());
		UserExists.checkDesignerIdExists(newAppointment.getToUser());
		AppointmentExists.checkFromUserHasUpcomingAppointments(newAppointment.getFromUser());
		AppointmentExists.checkToUserHasAppointmentAtSameDateTime(newAppointment.getToUser(), newAppointment.getDate(), newAppointment.getTime());
		AppointmentDAO appointmentDao = new AppointmentDAO();
		appointmentDao.create(newAppointment);
	}
}
