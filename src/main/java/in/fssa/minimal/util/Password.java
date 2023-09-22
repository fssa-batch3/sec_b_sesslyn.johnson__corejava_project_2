package in.fssa.minimal.util;

import org.mindrot.jbcrypt.BCrypt;

public class Password {
	public static String encryptPassword(String plainPassword) {
		return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
	}
}
