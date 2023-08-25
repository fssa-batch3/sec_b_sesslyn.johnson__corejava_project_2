package in.fssa.minimal.validator;

import java.util.regex.Pattern;

import in.fssa.minimal.dao.DesignDAO;
import in.fssa.minimal.dao.StyleDAO;
import in.fssa.minimal.dao.UserDAO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ServiceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Design;
import in.fssa.minimal.util.StringUtil;

public class DesignValidator {

	private static final String NAME_PATTERN = "^[A-Za-z][A-Za-z\\\\s]*$";
	/**
	 * Validates the provided design object.
	 *
	 * @param design The design object to be validated.
	 * @throws ValidationException If the design object is invalid.
	 * @throws ServiceException   If a service-related error occurs during validation.
	 */
	public static void validateDesign(Design design) throws ValidationException, ServiceException {
	    if (design == null) {
	        throw new ValidationException("Design object cannot be null");
	    }
	    validateName(design.getName());
	    validateDescription(design.getDescription());
	    validateLocation(design.getLocation());
	    validateStyleId(design.getStyleId());
	    validateCreatedById(design.getCreatedBy());
	}

	/**
	 * Validates the name of a design.
	 *
	 * @param name The name to be validated.
	 * @throws ValidationException If the name is invalid or empty.
	 */
	public static void validateName(String designName) throws ValidationException {
	    StringUtil.rejectIfInvalidString(designName, "Design Name");
	}

	/**
	 * Validates the description of a design.
	 *
	 * @param description The description to be validated.
	 * @throws ValidationException If the description is invalid or too short.
	 */
	public static void validateDescription(String description) throws ValidationException {
	    StringUtil.rejectIfInvalidString(description, "Description");
	    int length = description.length();
	    if (length < 30) {
	        throw new ValidationException("Description should be at least 30 characters long");
	    }
	}

	/**
	 * Validates the location of a design.
	 *
	 * @param location The location to be validated.
	 * @throws ValidationException If the location is invalid.
	 */
	public static void validateLocation(String location) throws ValidationException {
	    StringUtil.rejectIfInvalidString(location, "City name");
	    if (!Pattern.matches(NAME_PATTERN, location)) {
	        throw new ValidationException("City Name should only contain alphabetic characters");
	    }
	}

	/**
	 * Validates the provided user ID for design creation.
	 *
	 * @param id The user ID to be validated.
	 * @throws ValidationException If the ID is invalid or not associated with a designer.
	 * @throws ServiceException   If a service-related error occurs during validation.
	 */
	public static void validateCreatedById(int id) throws ValidationException, ServiceException {
	    try {
	        if (id <= 0) {
	            throw new ValidationException("User ID cannot be less than or equal to zero");
	        }
	        UserDAO.checkDesignerIdExists(id);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred during created by id validation.", e);
	    }
	}

	/**
	 * Validates the provided designer ID.
	 *
	 * @param id The designer ID to be validated.
	 * @throws ValidationException If the ID is invalid or not associated with a designer.
	 * @throws ServiceException   If a service-related error occurs during validation.
	 */
	public static void validateDesignerId(int id) throws ValidationException, ServiceException {
	    try {
	        if (id <= 0) {
	            throw new ValidationException("Designer ID cannot be less than or equal to zero");
	        }
	        UserDAO.checkDesignerIdExists(id);
	        DesignDAO.checkCreatedByExists(id);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred during designer id validation.", e);
	    }
	}

	/**
	 * Validates a design ID.
	 *
	 * @param id The design ID value to be validated.
	 * @throws ValidationException If the ID is invalid (less than or equal to zero).
	 * @throws ServiceException   If a service-related error occurs during validation.
	 */
	public static void validateDesignId(int id) throws ValidationException, ServiceException {
	    try {
	        if (id <= 0) {
	            throw new ValidationException("ID cannot be less than or equal to zero");
	        }
	        DesignDAO.checkIdExists(id);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred during design id validation.", e);
	    }
	}

	/**
	 * Validates a style name.
	 *
	 * @param name The style name to be validated.
	 * @throws ValidationException If the style name is invalid or empty.
	 * @throws ServiceException   If a service-related error occurs during validation.
	 */
	public static void validateStyleName(String styleName) throws ValidationException, ServiceException {
	    try {
	        StringUtil.rejectIfInvalidString(styleName, "Style Name");
	        StyleDAO.checkNameExists(styleName);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred during validation.", e);
	    }
	}

	/**
	 * Validates a style ID.
	 *
	 * @param id The style ID value to be validated.
	 * @throws ValidationException If the style ID is invalid (less than or equal to zero).
	 * @throws ServiceException   If a service-related error occurs during validation.
	 */
	public static void validateStyleId(int styleId) throws ValidationException, ServiceException {
	    try {
	        if (styleId <= 0) {
	            throw new ValidationException("ID cannot be less than or equal to zero");
	        }
	        StyleDAO.checkIdExists(styleId);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred during validation.", e);
	    }
	}


}
