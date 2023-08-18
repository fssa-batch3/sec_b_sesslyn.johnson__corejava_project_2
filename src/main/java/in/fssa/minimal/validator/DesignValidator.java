package in.fssa.minimal.validator;

import java.util.regex.Pattern;

import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Design;
import in.fssa.minimal.util.StringUtil;

public class DesignValidator {
	
	private static final String NAME_PATTERN = "^[A-Za-z][A-Za-z\\\\s]*$";

	/**
	 * 
	 * @param design
	 * @throws ValidationException
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
	 * 
	 * @param name
	 * @throws ValidationException
	 */
	public static void validateName(String name) throws ValidationException {
		StringUtil.rejectIfInvalidString(name, "Name");
	}
	/**
	 * 
	 * @param description
	 * @throws ValidationException
	 */
	public static void validateDescription(String description) throws ValidationException {
		StringUtil.rejectIfInvalidString(description, "Description");
		int l = description.length();
		if(l <= 30 ) {
			throw new ValidationException("Description doesn't match the length");
		}
	}
	/**
	 * 
	 * @param location
	 * @throws ValidationException
	 */
	public static void validateLocation(String location) throws ValidationException {
		StringUtil.rejectIfInvalidString(location, "City name");
		if (!Pattern.matches(NAME_PATTERN, location)) {
			throw new ValidationException("City name doesn't match the pattern");
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
