package in.fssa.minimal.util;

import in.fssa.minimal.exception.ValidationException;

public class StringUtil {
	/**
	 * A utility method to validate a string input and throw a ValidationException if it's null or empty.
	 *
	 * @param input      The string input to be validated.
	 * @param inputName  The name of the input being validated (used in the exception message).
	 * @throws ValidationException If the input string is null or empty.
	 */
	 public static void rejectIfInvalidString(String input, String inputName) throws ValidationException{
    	 if(input == null || "".equals(input.trim())) {
    		 throw new ValidationException(inputName.concat(" cannot be null or empty"));
    	 }
     } 
     
    
}
