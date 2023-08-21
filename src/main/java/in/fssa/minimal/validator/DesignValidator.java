package in.fssa.minimal.validator;

import java.util.regex.Pattern;

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
	 */
	public static void Validate(Design design) throws ValidationException {
		if (design == null) {
			throw new ValidationException("Design object can not be null");
		}
		validateName(design.getName());
		validateDescription(design.getDescription());
		validateLocation(design.getLocation());
		validateId(design.getStyleId());
		validateId(design.getCreatedBy());
	}

	/**
	 * Validates the name of a design.
	 *
	 * @param name The name to be validated.
	 * @throws ValidationException If the name is invalid.
	 */
	public static void validateName(String name) throws ValidationException {
		StringUtil.rejectIfInvalidString(name, "Name");
	}

	/**
	 * Validates the description of a design.
	 *
	 * @param description The description to be validated.
	 * @throws ValidationException If the description is invalid.
	 */
	public static void validateDescription(String description) throws ValidationException {
		StringUtil.rejectIfInvalidString(description, "Description");
		int l = description.length();
		if (l <= 30) {
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
	 * Validates an ID value.
	 *
	 * @param id The ID value to be validated.
	 * @throws ValidationException If the ID is invalid.
	 */
	public static void validateId(int id) throws ValidationException {
		if (id <= 0) {
			throw new ValidationException("ID cannot be less than or equal to zero");
		}
	}

}
