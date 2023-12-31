package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import in.fssa.minimal.dao.UserDAO;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.User;
import in.fssa.minimal.service.UserService;
@TestMethodOrder(OrderAnnotation.class)
class TestDeleteUser { 
	
	@Test
	@Order(1)
	void testGetUserByIdLessThanZero() { 
		UserService userService = new UserService();
        UserDAO app = new UserDAO();
		Exception exception = assertThrows(ValidationException.class, () -> {
	            userService.deleteUser(-5);
		});
		String expectedMessage = "User Id cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
 
	 @Test
	    @Order(2)
	    void testDeleteUser() {
	        assertDoesNotThrow(() -> {
	            UserService userService = new UserService();
	            UserDAO app = new UserDAO();
	    	    int user = app.getLastUpdatedUserId();
	            userService.deleteUser(user);
	        });
	    }


	@Test
	@Order(3)
	void testDeleteWithNonExistingId() throws ValidationException {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.deleteUser(5000);
		});
		String expectedMessage = "User Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	@Order(4)
	 void testCreateUserWithValidInput() {
	    UserService userService = new UserService();
	    User newUser = new User();
	    String randomString = generateRandomString(8); 
	    newUser.setName("Sesslyn");
	    newUser.setEmail(randomString + "@" + "google.com");
	    newUser.setPassword("Jenusha@2303");
	    newUser.setImage("https://goodhomes.wwmindia.com/content/2020/apr/sarah-sham1587387881.jpg");
	    newUser.setPhoneNumber(9863456787L);
	    newUser.setRole("user");

	    assertDoesNotThrow(() -> {
	        userService.createUser(newUser);
	    });
	}
	
	private String generateRandomString(int length) {
	    String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	    StringBuilder randomString = new StringBuilder();
	    Random random = new Random();

	    for (int i = 0; i < length; i++) {
	        int index = random.nextInt(characters.length());
	        randomString.append(characters.charAt(index));
	    }

	    return randomString.toString();
	}
}