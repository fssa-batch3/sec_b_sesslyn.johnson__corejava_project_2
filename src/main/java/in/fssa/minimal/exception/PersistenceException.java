package in.fssa.minimal.exception;

import java.sql.SQLException;

/**
 * A custom exception class for indicating exceptional situations related to data persistence.
 * This exception is typically used to wrap and propagate SQL-related errors while preserving
 * information about the underlying database error.
 */
public class PersistenceException extends Exception {
    /**
     * Constructs a new instance of the PersistenceException class with the provided SQLException.
     *
     * @param e The SQLException that caused or triggered this PersistenceException.
     */
    public PersistenceException(SQLException e) {
        super(e.getMessage(), e);
    }
}
