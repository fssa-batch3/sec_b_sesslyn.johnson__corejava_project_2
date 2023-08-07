package in.fssa.minimal.validator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Appointment;
import in.fssa.minimal.util.StringUtil;

public class AppointmentValidator {
	private static final String EMAIL_PATTERN = "^[a-zA-Z0-9]+([a-zA-Z0-9_+\\-\\. ]*[a-zA-Z0-9]+)?@[a-zA-Z0-9]+([a-zA-Z0-9\\-\\.]*[a-zA-Z0-9])?\\.[a-zA-Z]{2,}$";

	public static void Validate(Appointment appointment) throws ValidationException {
		if (appointment == null) {
			throw new ValidationException("Appointment object can not be null");
		}
		validateId(appointment.getId());
		validateId(appointment.getFromUser());
		validateId(appointment.getToUser());
		validateEmail(appointment.getEmail());
		validatePhoneNumber(appointment.getPhoneNumber());
		validateDate(appointment.getDate());
		validateTime(appointment.getTime());
	}

	public static void validateId(int id) throws ValidationException {
		if (id <= 0) {
			throw new ValidationException("Id can't be less than or equal to zero.");
		}
	}

	public static void validateEmail(String email) throws ValidationException {
		StringUtil.rejectIfInvalidString(email, "Email");
		if (!Pattern.matches(EMAIL_PATTERN, email)) {
			throw new ValidationException("Email doesn't match the pattern.");
		}
	}

	public static void validatePhoneNumber(long phoneNumber) throws ValidationException {
		String phoneNumberStr = String.valueOf(phoneNumber);

		if (phoneNumberStr.length() != 10) {
			throw new ValidationException("PhoneNumber doesn't match the length.");
		}

		if (phoneNumber <= 6000000001L && phoneNumber >= 9999999999L) {
			throw new ValidationException("PhoneNumber doesn't match the pattern.");
		}
	}

	public static void validateStatus(String status) throws ValidationException {
		StringUtil.rejectIfInvalidString(status, "Status");
		if ("approved".equalsIgnoreCase(status) || "rejected".equalsIgnoreCase(status)
				|| "waiting_list".equalsIgnoreCase(status)) {
			return;
		} else {
			throw new ValidationException("Status doesn't match the expected values.");
		}
	}

	public static void validateDate(String date) throws ValidationException {
		StringUtil.rejectIfInvalidString(date, "Date");
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dueDate = LocalDate.parse(date, inputFormatter);
		String formattedDate = dueDate.format(inputFormatter);
		System.out.println("Formatted Date: " + formattedDate);
		LocalDate currentDate = LocalDate.now();

		if (dueDate.isBefore(currentDate) || dueDate.equals(currentDate) || dueDate.isAfter(currentDate.plusDays(90))) {
			throw new ValidationException("Due date should be within 90 days from today");
		}

	}

	public static void validateTime(String time) throws ValidationException {
		StringUtil.rejectIfInvalidString(time, "Time");
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalTime dueTime = LocalTime.parse(time, inputFormatter);

		LocalTime minTime = LocalTime.parse("08:00:00");
		LocalTime maxTime = LocalTime.parse("20:00:00");

		if (dueTime.isBefore(minTime) || dueTime.isAfter(maxTime)) {
			throw new ValidationException("Time should be in the range between 08:00:00 and 20:00:00.");
		}
	}
}
