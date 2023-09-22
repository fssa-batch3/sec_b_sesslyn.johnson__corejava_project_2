package in.fssa.minimal.util;

import in.fssa.minimal.exception.ValidationException;

public class StringUtil {
	/**
	 * A utility method to validate a string input and throw a ValidationException
	 * if it's null or empty.
	 *
	 * @param input     The string input to be validated.
	 * @param inputName The name of the input being validated (used in the exception
	 *                  message).
	 * @throws ValidationException If the input string is null or empty.
	 */
	public static void rejectIfInvalidString(String input, String inputName) throws ValidationException {
		if (input == null || "".equals(input.trim())) {
			throw new ValidationException(inputName.concat(" cannot be null or empty"));
		}
	}

	public static String extractValue(String key, String description) {
	    if (description != null) {
	        String[] parts = description.split("\n"); // Split by newlines
	        for (String part : parts) {
	            // Split by colon, allowing for variable spaces
	            String[] keyValue = part.split(":", 2);
	            if (keyValue.length > 1 && keyValue[0].trim().equals(key)) {
	                return keyValue[1].trim();
	            }
	        }
	    }
	    return "";
	}


}
