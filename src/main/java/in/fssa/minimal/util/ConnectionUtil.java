package in.fssa.minimal.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.fssa.minimal.exception.PersistenceException;
import io.github.cdimascio.dotenv.Dotenv;

public class ConnectionUtil {

    /**
     * Establishes a database connection using environment variables or a local configuration.
     *
     * @return A database connection instance.
     */
	public static Connection getConnection() {

		String url;
		String userName;
		String passWord;

		if (System.getenv("CI") != null) {
			url = System.getenv("DATABASE_HOSTNAME");
			userName = System.getenv("DATABASE_USERNAME");
			passWord = System.getenv("DATABASE_PASSWORD");
		} else {
			Dotenv env = Dotenv.load();
			url = env.get("DATABASE_HOSTNAME");
			userName = env.get("DATABASE_USERNAME");
			passWord = env.get("DATABASE_PASSWORD");
		}

		Connection connection = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, userName, passWord);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return connection;
	}
	   /**
     * Closes a database connection and a prepared statement.
     *
     * @param connection The database connection to be closed.
     * @param ps The prepared statement to be closed.
	 * @throws PersistenceException 
     */
	public static void close(Connection connection, PreparedStatement ps) throws PersistenceException {
		try {
			if (ps != null) {
				ps.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}
	/**
     * Closes a database connection, a prepared statement, and a result set.
     *
     * @param connection The database connection to be closed.
     * @param ps The prepared statement to be closed.
     * @param rs The result set to be closed.
	 * @throws PersistenceException 
     */
	public static void close(Connection connection, PreparedStatement ps, ResultSet rs) throws PersistenceException {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}
}
