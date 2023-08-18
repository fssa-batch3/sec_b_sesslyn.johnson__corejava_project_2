package in.fssa.minimal.validator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.regex.Pattern;

import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Appointment;
import in.fssa.minimal.util.StringUtil;

public class AppointmentValidator {
	private static final String EMAIL_PATTERN = "^[a-zA-Z0-9]+([a-zA-Z0-9_+\\-\\. ]*[a-zA-Z0-9]+)?@[a-zA-Z0-9]+([a-zA-Z0-9\\-\\.]*[a-zA-Z0-9])?\\.[a-zA-Z]{2,}$";
/**
 * 
 * @param appointment
 * @throws ValidationException
 */
	public static void Validate(Appointment appointment) throws ValidationException {
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
 * 
 * @param id
 * @throws ValidationException
 */
	public static void validateId(int id) throws ValidationException {
		if (id <= 0) {
			throw new ValidationException("Id can't be less than or equal to zero");
		}
	}
/**
 * 
 * @param email
 * @throws ValidationException
 */
	public static void validateEmail(String email) throws ValidationException {
		StringUtil.rejectIfInvalidString(email, "Email");
		if (!Pattern.matches(EMAIL_PATTERN, email)) {
			throw new ValidationException("Email doesn't match the pattern");
		}
	}
/**
 * 
 * @param phoneNumber
 * @throws ValidationException
 */
	public static void validatePhoneNumber(long phoneNumber) throws ValidationException {
		String phoneNumberStr = String.valueOf(phoneNumber);

		if (phoneNumberStr.length() != 10) {
			throw new ValidationException("PhoneNumber doesn't match the length");
		}

		if (phoneNumber < 6000000000L || phoneNumber >= 10000000000L) {
			throw new ValidationException("PhoneNumber doesn't match the pattern");
		}
	}
/**
 * 
 * @param status
 * @throws ValidationException
 */
	public static void validateStatus(String status) throws ValidationException {
		StringUtil.rejectIfInvalidString(status, "Status");
		if ("approved".equalsIgnoreCase(status) || "rejected".equalsIgnoreCase(status)
				|| "waiting_list".equalsIgnoreCase(status)) {
			return;
		} else {
			throw new ValidationException("Status doesn't match the expected values");
		}
	}
	/**
	 * 
	 * @param status
	 * @throws ValidationException
	 */
	public static void validateUpdateStatus(String status) throws ValidationException {
		StringUtil.rejectIfInvalidString(status, "Status");
		if ("approved".equalsIgnoreCase(status)) {
			return;
		} else {
			throw new ValidationException("Status doesn't match the expected values");
		}
	}
	/**
	 * 
	 * @param date
	 * @throws ValidationException
	 */
	public static void validateDate(String date) throws ValidationException {
	    StringUtil.rejectIfInvalidString(date, "Date");
	    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate dueDate;

	    try {
	        dueDate = LocalDate.parse(date, inputFormatter);
	    } catch (DateTimeParseException e) {
	        throw new ValidationException("Invalid date or Invalid date format ( yyyy-MM-dd)");
	    }

	    String formattedDate = dueDate.format(inputFormatter);
	    LocalDate currentDate = LocalDate.now();

	    if (dueDate.isBefore(currentDate) || dueDate.equals(currentDate) || dueDate.isAfter(currentDate.plusDays(90))) {
	        throw new ValidationException("Invalid date or Invalid date format ( yyyy-MM-dd)");
	    }
	}


	public static void validateTime(String time) throws ValidationException {
	    StringUtil.rejectIfInvalidString(time, "Time");
	    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	    try {
	        LocalTime dueTime = LocalTime.parse(time, inputFormatter);

	        LocalTime minTime = LocalTime.parse("08:00:00");
	        LocalTime maxTime = LocalTime.parse("20:00:00");

	        if (dueTime.isBefore(minTime) || dueTime.isAfter(maxTime)) {
	            throw new ValidationException("Invalid time or Invalid time format ( HH:mm:ss)");
	        }
	    } catch (DateTimeParseException e) {
	        throw new ValidationException("Invalid time or Invalid time format ( HH:mm:ss)");
	    }
	}

}
