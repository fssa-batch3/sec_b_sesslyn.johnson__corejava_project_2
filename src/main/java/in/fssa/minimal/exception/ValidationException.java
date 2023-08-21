package in.fssa.minimal.exception;

public class ValidationException extends Exception {
	/**
	 * Constructs a new ValidationException with the provided error message.
	 * This exception is typically used to indicate errors related to validation
	 * or verification of data, where the error message provides more information
	 * about the specific issue that occurred.
	 *
	 * @param message The error message describing the reason for the exception.
	 */
	public ValidationException(String message) {
		super(message);
	}
}
