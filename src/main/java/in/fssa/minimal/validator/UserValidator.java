package in.fssa.minimal.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

import in.fssa.minimal.dao.UserDAO;
import in.fssa.minimal.enums.GenderEnum;
import in.fssa.minimal.enums.RoleEnum;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ServiceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.User;
import in.fssa.minimal.util.StringUtil;

public class UserValidator {

	// Regular expression patterns for validation
	private static final String NAME_PATTERN = "^[A-Za-z]+(\\s[A-Za-z]+)?$";
	private static final String EMAIL_PATTERN = "^[a-zA-Z0-9]+([a-zA-Z0-9_+\\-\\. ]*[a-zA-Z0-9]+)?@[a-zA-Z0-9]+([a-zA-Z0-9\\-\\.]*[a-zA-Z0-9])?\\.[a-zA-Z]{2,}$";
	private static final String PATTERN = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}";
	private static final String IMAGE_PATTERN = "^https?:\\/\\/(?:www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&\\/=]*)$";
	 private static final String GST_PATTERN = "\\d{2}[A-Z]{5}\\d{4}[A-Z]{1}[A-Z\\d]{1}[Z]{1}[A-Z\\d]{1}";
	/**
	 * Validates a User object by checking its attributes including name, email,
	 * password, and phone number.
	 *
	 * @param user The User object to validate.
	 * @throws ValidationException  If any of the attributes do not meet the
	 *                              required validation criteria.
	 * @throws PersistenceException If an error occurs during data persistence
	 *                              checks.
	 * @throws ServiceException
	 */
	public static void validateUser(User user) throws ValidationException, ServiceException {
		if (user == null) {
			throw new ValidationException("User object cannot be null");
		}
		validateName(user.getName());
		validateEmailCreate(user.getEmail());
		validatePassword(user.getPassword());
		if (user.getImage() != null) {
			validateImage(user.getImage());
		}
		validatePhoneNumber(user.getPhoneNumber());
	}
	
	public static void validateDesigner(User user) throws ValidationException{
		validateExperience("Experience",user.getExperience());
		StringUtil.rejectIfInvalidString(user.getDesigner_description(), "Designer Description");	
	}
	
	public static void validateSeller(User user) throws ValidationException, ServiceException {
		validateAadharNumber(user.getAadhar_number());
		validateGstNumber(user.getGst_number());
		StringUtil.rejectIfInvalidString(user.getShop_address(), "Shop Address");	
	}
	

	public static void validateGstNumber(String gstNumber) throws ValidationException {
		StringUtil.rejectIfInvalidString(gstNumber, "Gst Number");
	    if (!gstNumber.matches(GST_PATTERN)) {
	        throw new ValidationException("GST Number doesn't match the pattern");
	    }

	}
	
	public static void validateAadharNumber(long aadharNumber) throws ValidationException, ServiceException {
	    String aadharStr = String.valueOf(aadharNumber);
	    if (aadharStr.length() != 12) {
	        throw new ValidationException("Aadhar Number must have exactly 12 digits");
	    }
	    char firstDigit = aadharStr.charAt(0);
	    if (firstDigit == '0' || firstDigit == '1') {
	        throw new ValidationException("The first digit of Aadhar Number cannot be 0 or 1");
	    }
	    boolean allDigitsSame = true;
	    for (int i = 1; i < aadharStr.length(); i++) {
	        if (aadharStr.charAt(i) != firstDigit) {
	            allDigitsSame = false;
	            break;
	        }
	    }
	    
	    
	    if (allDigitsSame) {
	        throw new ValidationException("Aadhar Number cannot have all 12 digits the same");
	    }
		try {
			UserDAO.checkAadharNumberExists(aadharNumber);
		}  catch (PersistenceException e) {
			throw new ServiceException("Error occurred during validation", e);
		}
	}
	
	
	public static void validateExperience(String experience, int id) throws ValidationException {
		if (id < 0) {
			throw new ValidationException(experience + " cannot be less than zero");
		}
	}

	/**
	 * Validates a name to ensure it only contains alphabetic characters.
	 *
	 * @param name The name to validate.
	 * @throws ValidationException If the name does not match the required format.
	 */
	public static void validateName(String userName) throws ValidationException {
		
		StringUtil.rejectIfInvalidString(userName, "User Name");
		userName = userName.trim();
		if (userName.length() < 3) {
			throw new ValidationException("User Name should be at least 3 characters long");
		}
		if (!Pattern.matches(NAME_PATTERN, userName)) {
			throw new ValidationException(
					"User Name should only contain alphabetic characters and allow only one space between words");
		}
	}

	/**
	 * Validates an email address using a regular expression pattern.
	 *
	 * @param email The email address to validate.
	 * @throws ValidationException If the email address does not match the required
	 *                             format or if it already exists.
	 */
	public static void validateAllEmail(String email) throws ValidationException {
		StringUtil.rejectIfInvalidString(email, "Email");
		if (!email.matches(EMAIL_PATTERN)) {
			throw new ValidationException("Invalid email format. Please provide a valid email address");
		}
	}

	/**
	 * Validates an email address using a regular expression pattern.
	 *
	 * @param email The email address to validate.
	 * @throws ValidationException  If the email address does not match the required
	 *                              format or if it already exists.
	 * @throws PersistenceException If an error occurs during data persistence
	 *                              checks.
	 * @throws ServiceException
	 */
	public static void validateEmail(String email) throws ValidationException, ServiceException {
		try {
			validateAllEmail(email);
			UserDAO.checkEmailExists(email);
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred during validation", e);
		}
	}

	/**
	 * Validates an email address using a regular expression pattern.
	 *
	 * @param email The email address to validate.
	 * @throws ValidationException  If the email address does not match the required
	 *                              format or if it already exists.
	 * @throws ServiceException
	 * @throws PersistenceException If an error occurs during data persistence
	 *                              checks.
	 */
	public static void validateEmailCreate(String email) throws ValidationException, ServiceException {
		try {
			validateAllEmail(email);
			UserDAO.emailExists(email);
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred during validation", e);
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
	 * Validates an ID value.
	 *
	 * @param id The ID to be validated.
	 * @throws ValidationException If the ID is not valid (less than or equal to
	 *                             zero).
	 */
	public static void validateId(String name, int id) throws ValidationException {
		if (id <= 0) {
			throw new ValidationException(name + " cannot be less than or equal to zero");
		}
	}

	/**
	 * Validates an ID to ensure it is not zero or negative.
	 *
	 * @param id The ID to validate.
	 * @throws ValidationException  If the ID is invalid or does not exist.
	 * @throws ServiceException
	 * @throws PersistenceException If an error occurs during data persistence
	 *                              checks.
	 */
	public static void validateUserId(int userId) throws ValidationException, ServiceException {
		try {
			validateId("User Id", userId);
			UserDAO.checkIdExists(userId);
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred during validation", e);
		}
	}

	/**
	 * Validates an ID to ensure it is not zero or negative.
	 *
	 * @param id The ID to validate.
	 * @throws ValidationException  If the ID is invalid or does not exist.
	 * @throws ServiceException
	 * @throws PersistenceException If an error occurs during data persistence
	 *                              checks.
	 */
	public static void validateDesignerId(int designerId) throws ValidationException, ServiceException {
		try {
			validateId("Designer Id", designerId);
			UserDAO.checkDesignerIdExists(designerId);
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred during validation", e);
		}
	}
	
	public static void validateSellerId(int sellerId) throws ValidationException, ServiceException {
		try {
			validateId("Seller Id", sellerId);
			UserDAO.checkSellerIdExists(sellerId);
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred during validation", e);
		}
	}

	public static void validateImage(String image) throws ValidationException, ServiceException {
		if (!image.matches(IMAGE_PATTERN)) {
			throw new ValidationException("Invalid image format. Please provide a valid image url.");
		}
	}

	public static void validateDateOfBirth(String dateOfBirth) throws ValidationException, ServiceException {
	    StringUtil.rejectIfInvalidString(dateOfBirth, "Date Of Birth");
	    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate dueDate;

	    try {
	        dueDate = LocalDate.parse(dateOfBirth, inputFormatter);
	    } catch (DateTimeParseException e) {
	        throw new ValidationException("Invalid date format (yyyy-MM-dd)");
	    }

	    LocalDate currentDate = LocalDate.now();
	    LocalDate minimumAgeDate = currentDate.minusYears(18);

	    if (dueDate.isAfter(currentDate) || dueDate.equals(currentDate)) {
	        throw new ValidationException("Invalid date. The date can't be today or future.");
	    }

	    if (dueDate.isAfter(minimumAgeDate)) {
	        throw new ValidationException("Users must be at least 18 years old.");
	    }
	}


	public static void validateGender(String gender) throws ValidationException, ServiceException {
		StringUtil.rejectIfInvalidString(gender, "Gender");
		if (GenderEnum.getGender(gender).equals("non")) {
			throw new ValidationException("Invalid Gender type");
		}
	}
	
	public static void validateRole(String role) throws ValidationException, ServiceException {
		StringUtil.rejectIfInvalidString(role, "Role");
		if (RoleEnum.getRole(role).equals("non")) {
			throw new ValidationException("Invalid Role type");
		}
	}

}
