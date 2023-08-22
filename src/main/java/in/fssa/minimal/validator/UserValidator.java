package in.fssa.minimal.validator;

import java.util.regex.Pattern;

import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.User;
import in.fssa.minimal.util.StringUtil;

public class UserValidator {

	// Regular expression patterns for validation
	private static final String NAME_PATTERN = "^[A-Za-z]{3,}$";
	private static final String EMAIL_PATTERN = "^[a-zA-Z0-9]+([a-zA-Z0-9_+\\-\\. ]*[a-zA-Z0-9]+)?@[a-zA-Z0-9]+([a-zA-Z0-9\\-\\.]*[a-zA-Z0-9])?\\.[a-zA-Z]{2,}$";
	private static final String PATTERN = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}";

	/**
	 * Validates a User object by checking its name, email, password, and phone
	 * number.
	 *
	 * @param user The User object to validate.
	 * @throws ValidationException If any of the attributes do not meet the required
	 *                             validation criteria.
	 */
	public static void validate(User user) throws ValidationException {
		if (user == null) {
			throw new ValidationException("User object cannot be null");
		}
		validateName(user.getName());
		validateEmail(user.getEmail());
		validatePassword(user.getPassword());
		validatePhoneNumber(user.getPhoneNumber());
	}

	/**
	 * Validates a name to ensure it only contains alphabetic characters.
	 *
	 * @param name The name to validate.
	 * @throws ValidationException If the name does not match the required format.
	 */
	public static void validateName(String name) throws ValidationException {
		StringUtil.rejectIfInvalidString(name, "Name");
		if(name.length() < 3) {
			throw new ValidationException("Name should be at least 3 characters long");
		}
		if (!Pattern.matches(NAME_PATTERN, name)) {
			throw new ValidationException("Name should only contain alphabetic characters");
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
	 * Validates a password by checking its length and character composition.
	 *
	 * @param password The password to validate.
	 * @throws ValidationException If the password does not meet the required
	 *                             criteria.
	 */
	public static void validatePassword(String password) throws ValidationException {
		StringUtil.rejectIfInvalidString(password, "Password");
		if (password.length() < 8) {
			throw new ValidationException("Password should be at least 8 characters long");
		}
		if (!Pattern.matches(PATTERN, password)) {
			throw new ValidationException(
					"Password must have at least 8 characters and contain at least one uppercase letter, one lowercase letter, and one special character");
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
	 * Validates an ID to ensure it is not zero or negative.
	 *
	 * @param id The ID to validate.
	 * @throws ValidationException If the ID is less than or equal to zero.
	 */
	public static void validateId(int id) throws ValidationException {
		if (id <= 0) {
			throw new ValidationException("ID cannot be less than or equal to zero");
		}
	}
}
