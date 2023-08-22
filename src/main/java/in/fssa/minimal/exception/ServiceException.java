package in.fssa.minimal.exception;

public class ServiceException extends Exception {

	/**
	 * Constructs a new ServiceException instance with the specified error message.
	 *
	 * @param message The error message describing the specific service-related
	 *                issue.
	 */

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
