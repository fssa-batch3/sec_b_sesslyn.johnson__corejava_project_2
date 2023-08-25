package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.User;
import in.fssa.minimal.service.UserService;

@TestMethodOrder(OrderAnnotation.class)
class TestUpdateUser {

	@Test
	@Order(1)
	void testUpdateUser() {
		assertDoesNotThrow(() -> {
			UserService userService = new UserService();
			User newUser = new User();
			String name = generateRandomString(5);
			String pass = generateRandomString(2);
			long generatedPhoneNumber = generateRandomPhoneNumber();
			newUser.setName(name);
			newUser.setPassword("Se" + pass + "@2608");

			newUser.setPhoneNumber(generatedPhoneNumber);
			userService.updateUser(1, newUser);
		});
	}

	@Test
	@Order(2)
	void testUpdateSpecificFields() {
	    assertDoesNotThrow(() -> {
	        UserService userService = new UserService();
	        User newUser = new User();
	        String name = generateRandomString(5);
	        newUser.setName(name);
	        userService.updateUser(3, newUser);
	    });
	}

	


	@Test
	@Order(3)
	void testUpdateWithNonExistingId() throws ValidationException {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			User newUser = new User();
			newUser.setPassword("Sess@2608");
			newUser.setDesigner(false);
			userService.updateUser(5000, newUser);
		});
		String expectedMessage = "Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	public static long generateRandomPhoneNumber() {
		long minPhoneNumber = 6000000000L;
		long maxPhoneNumber = 9999999999L;

		Random random = new Random();
		long generatedPhoneNumber = minPhoneNumber + (long) (random.nextDouble() * (maxPhoneNumber - minPhoneNumber));

		return generatedPhoneNumber;
	}

	private String generateRandomString(int length) {
	    String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    StringBuilder randomString = new StringBuilder();
	    Random random = new Random();

	    for (int i = 0; i < length; i++) {
	        int index = random.nextInt(characters.length());
	        randomString.append(characters.charAt(index));
	    }

	    return randomString.toString();
	}

}
