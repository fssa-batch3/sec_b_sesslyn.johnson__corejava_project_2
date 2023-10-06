package in.fssa.minimal.util;

import org.mindrot.jbcrypt.BCrypt;

public class Password {
	/**
	 * Encrypts a plain text password using the BCrypt hashing algorithm.
	 *
	 * @param plainPassword The plain text password to be encrypted.
	 * @return The encrypted password as a BCrypt hash.
	 */
	public static String encryptPassword(String plainPassword) {
		return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
	}
}
 