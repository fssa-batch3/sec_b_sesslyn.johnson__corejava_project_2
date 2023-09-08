package in.fssa.minimal.exception;

/**
 * Checked exception used to indicate service-related issues.
 */
public class ServiceException extends Exception {

    /**
     * Constructs a new ServiceException instance with the specified error message.
     *
     * @param message The error message describing the specific service-related issue.
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Constructs a new ServiceException instance with the specified error message and
     * a reference to the underlying cause of the exception.
     *
     * @param message The error message describing the specific service-related issue.
     * @param cause   The underlying cause of the exception.
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
