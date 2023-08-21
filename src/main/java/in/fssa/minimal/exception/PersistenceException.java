package in.fssa.minimal.exception;

import java.sql.SQLException;

public class PersistenceException extends Exception {
	/**
	 * This constructor creates a new instance of the PersistenceException class,
	 * which is used to indicate an exceptional situation related to data persistence.
	 * It takes a SQLException as its parameter, allowing the exception to be wrapped
	 * and propagated while preserving the information about the underlying database error.
	 *
	 * @param e The SQLException that caused or triggered this PersistenceException.
	 */
	public PersistenceException(SQLException e) {
		super(e);
	}
}
