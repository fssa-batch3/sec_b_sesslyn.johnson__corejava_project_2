package in.fssa.minimal.validator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Appointment;
import in.fssa.minimal.util.StringUtil;

public class AppointmentValidator {
	private static final String EMAIL_PATTERN = "^[a-zA-Z0-9]+(?:[_+\\-. ][a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:[\\-\\.][a-zA-Z0-9]+)*\\.[a-zA-Z]{2,}$";

	

	/**
	 * Validates an Appointment object's properties.
	 *
	 * @param appointment The Appointment object to be validated.
	 * @throws ValidationException If any property of the Appointment object fails
	 *                             validation.
	 */
	public static void validate(Appointment appointment) throws ValidationException {
		if (appointment == null) {
			throw new ValidationException("Appointment object can not be null");
		}
		validateId(appointment.getFromUser());
		validateId(appointment.getToUser());
		validateEmail(appointment.getEmail());
		validatePhoneNumber(appointment.getPhoneNumber());
		validateStatus(appointment.getStatus());
		validateDate(appointment.getDate());
		validateTime(appointment.getTime());
	}

	/**
	 * Validates an ID value.
	 *
	 * @param id The ID to be validated.
	 * @throws ValidationException If the ID is not valid (less than or equal to
	 *                             zero).
	 */
	public static void validateId(int id) throws ValidationException {
		if (id <= 0) {
			throw new ValidationException("ID cannot be less than or equal to zero");
		}
	}

	/**
	 * Validates an email address using a regular expression pattern.
	 *
	 * @param email The email address to validate.
	 * @throws ValidationException If the email address does not match the required
	 *                             format.
	 */
	public static void validateEmail(String email) throws ValidationException {
	    StringUtil.rejectIfInvalidString(email, "Email");
	    if (!email.matches(EMAIL_PATTERN)) {
	        throw new ValidationException("Invalid email format. Please provide a valid email address");
	    }
	}

	/**
	 * Validates a phone number for length and format constraints.
	 *
	 * @param phoneNumber The phone number to validate.
	 * @throws ValidationException If the phone number does not meet the required
	 *                             criteria.
	 */
	public static void validatePhoneNumber(long phoneNumber) throws ValidationException {
		String phoneNumberStr = String.valueOf(phoneNumber);
		if (phoneNumber <= 0) {
			throw new ValidationException("Phone number cannot be zero or negative");
		}
		if (phoneNumberStr.length() != 10) {
			throw new ValidationException("Phone number should be exactly 10 digits long");
		}
		if (phoneNumber < 6000000000L || phoneNumber >= 10000000000L) {
			throw new ValidationException("Invalid phone number format. Make sure not to include +91");
		}
	}

	/**
	 * Validates the status parameter against expected values.
	 *
	 * @param status The status to be validated.
	 * @throws ValidationException If the status doesn't match the expected values.
	 */
	public static void validateStatus(String status) throws ValidationException {
	    StringUtil.rejectIfInvalidString(status, "Status");
	    if (!("approved".equalsIgnoreCase(status) || "rejected".equalsIgnoreCase(status)
	            || "waiting_list".equalsIgnoreCase(status))) {
	        throw new ValidationException(
	                "Invalid status value. The status can only be one of: waiting_list, approved, rejected");
	    }
	}


	/**
	 * Validates the status parameter for updating an appointment's status.
	 *
	 * @param status The status to be validated.
	 * @throws ValidationException If the status is not valid for updating.
	 */
	public static void validateUpdateStatus(String status) throws ValidationException {
	    StringUtil.rejectIfInvalidString(status, "Status");
	    if (!"approved".equalsIgnoreCase(status)) {
	        throw new ValidationException("Approved appointment cannot be re update");
	    }
	}

	/**
	 * Validates a date for format and range constraints.
	 *
	 * @param date The date to be validated in the "yyyy-MM-dd" format.
	 * @throws ValidationException If the date is not valid (format or range).
	 */
	public static void validateDate(String date) throws ValidationException {
		StringUtil.rejectIfInvalidString(date, "Date");
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dueDate;

		try {
			dueDate = LocalDate.parse(date, inputFormatter);
		} catch (DateTimeParseException e) {
			throw new ValidationException("Invalid date or Invalid date format (yyyy-MM-dd)");
		}

		LocalDate currentDate = LocalDate.now();
		LocalDate maxAllowedDate = currentDate.plusDays(90);

		if (dueDate.isBefore(currentDate) || dueDate.isAfter(maxAllowedDate)) {
			throw new ValidationException("Invalid date. The date should be within the next 90 days");
		}
	}

	/**
	 * Validates a time for format and range constraints.
	 *
	 * @param time The time to be validated in the "HH:mm:ss" format.
	 * @throws ValidationException If the time is not valid (format or range).
	 */
	public static void validateTime(String time) throws ValidationException {
		StringUtil.rejectIfInvalidString(time, "Time");
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalTime dueTime;
		try {
			dueTime = LocalTime.parse(time, inputFormatter);
		} catch (DateTimeParseException e) {
			throw new ValidationException("Invalid time or Invalid time format (HH:mm:ss)");
		}
		
		LocalTime minTime = LocalTime.parse("08:00:00");
		LocalTime maxTime = LocalTime.parse("20:00:00");

		if (dueTime.isBefore(minTime) || dueTime.isAfter(maxTime)) {
			throw new ValidationException("Invalid time. The time should be between 08:00:00 and 20:00:00");
		}

	}

}
