package in.fssa.minimal.validator;

import java.util.regex.Pattern;

import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.User;
import in.fssa.minimal.util.StringUtil;

public class UserValidator {
	private static final String NAME_PATTERN = "^[A-Za-z][A-Za-z\\\\s]*$";
	private static final String EMAIL_PATTERN = "^[a-zA-Z0-9]+([a-zA-Z0-9_+\\-\\. ]*[a-zA-Z0-9]+)?@[a-zA-Z0-9]+([a-zA-Z0-9\\-\\.]*[a-zA-Z0-9])?\\.[a-zA-Z]{2,}$";
	private static final String PASSWORD_PATTERN = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}";

/**
 * 
 * @param user
 * @throws ValidationException
 */
	public static void Validate(User user) throws ValidationException {
		if (user == null) {
			throw new ValidationException("User object can not be null");
		}
		validateName(user.getName());
		validateEmail(user.getEmail());
		validatePassword(user.getPassword());
		validatePhoneNumber(user.getPhoneNumber());
	}
/**
 * 
 * @param name
 * @throws ValidationException
 */
	public static void validateName(String name) throws ValidationException {
		StringUtil.rejectIfInvalidString(name, "Name");
		if (!Pattern.matches(NAME_PATTERN, name)) {
			throw new ValidationException("Name doesn't match the pattern");
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
 * @param password
 * @throws ValidationException
 */
	public static void validatePassword(String password) throws ValidationException {
		StringUtil.rejectIfInvalidString(password, "Password");
		 if (password.length() < 8) {
		        throw new ValidationException("Password doesn't match the length");
		    }

		if (!Pattern.matches(PASSWORD_PATTERN, password)) {
			throw new ValidationException("Password doesn't match the pattern");
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
 * @param id
 * @throws ValidationException
 */
	public static void validateId(int id) throws ValidationException{
		if(id <= 0) {
			throw new ValidationException("Id can't be less than or equal to zero");
		}
	}

	
}
