package in.fssa.minimal.validator;

import in.fssa.minimal.dao.AppointmentDAO;
import in.fssa.minimal.dao.UserDAO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Review;
import in.fssa.minimal.util.Logger;
import in.fssa.minimal.util.StringUtil;

public class ReviewValidator {
	
	/**
	 * Validates a new review object by checking its properties for correctness.
	 *
	 * @param newReview The Review object to be validated.
	 * @throws ValidationException If the review object or its properties are invalid.
	 */
	public static void validate(Review newReview) throws ValidationException{
		if(newReview == null) {
			throw new ValidationException("Review Object cannot be null");
		}
		validateDescription(newReview.getFeedback());
		validateAppointmentId(newReview.getAppointment_id());
		validateUserId(newReview.getFrom_user());
		validateId("Ratings",newReview.getRatings());
		validateDesignerId(newReview.getTo_user());
	}

	/**
	 * Validates an ID by checking if it's less than or equal to zero.
	 *
	 * @param name The name of the ID being validated.
	 * @param id   The ID to be validated.
	 * @throws ValidationException If the ID is less than or equal to zero.
	 */
	public static void validateId(String name, int id) throws ValidationException {
		if (id <= 0) {
			throw new ValidationException(name + " cannot be less than or equal to zero");
		}
	}
 
	/**
	 * Validates an appointment ID by checking if it's less than or equal to zero and if it exists in the database.
	 *
	 * @param appointmentId The appointment ID to be validated.
	 * @throws ValidationException If the appointment ID is invalid or doesn't exist in the database.
	 */
	public static void validateAppointmentId(int appointmentId) throws ValidationException {
		validateId("Appointment Id", appointmentId);
		try {
			AppointmentDAO.checkIdExists(appointmentId);
		} catch (PersistenceException e) {
			Logger.error(e);
		}
	}
	
	/**
	 * Validates a user ID by checking if it's less than or equal to zero and if it exists in the database.
	 *
	 * @param fromUser The user ID to be validated.
	 * @throws ValidationException If the user ID is invalid or doesn't exist in the database.
	 */
	public static void validateUserId(int fromUser) throws ValidationException {
		validateId("User Id", fromUser);
		try {
			UserDAO.checkIdExists(fromUser);
		} catch (PersistenceException e) {
			Logger.error(e);
		}
	}
	
	/**
	 * Validates a designer ID by checking if it's less than or equal to zero and if it exists in the database.
	 *
	 * @param toUser The designer ID to be validated.
	 * @throws ValidationException If the designer ID is invalid or doesn't exist in the database.
	 */
	public static void validateDesignerId(int toUser) throws ValidationException {
		validateId("Designer Id", toUser);
		try {
			UserDAO.checkDesignerIdExists(toUser);
		} catch (PersistenceException e) {
			Logger.error(e);
		}
	}

	/**
	 * Validates a description (feedback) by checking if it's a valid string.
	 *
	 * @param feedback The description (feedback) to be validated.
	 * @throws ValidationException If the description (feedback) is invalid.
	 */
	public static void validateDescription(String feedback) throws ValidationException {
		StringUtil.rejectIfInvalidString(feedback, "FeedBack");
	}
}
