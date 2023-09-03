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
class TestCreateUser {

	@Test
	@Order(1)
	 void testCreateUserWithValidInput() {
	    UserService userService = new UserService();
	    User newUser = new User();
	    String randomString = generateRandomString(8); 
	    newUser.setName("Sesslyn");
	    newUser.setEmail(randomString + "@" + "gmail.com");
	    newUser.setPassword("Jenusha@2303");
	    newUser.setPhoneNumber(9863456787L);
	    newUser.setDesigner(false);

	    assertDoesNotThrow(() -> {
	        userService.createUser(newUser);
	    });
	}
	
	@Test
	@Order(2)
	 void testCreateDesignerWithValidInput() {
	    UserService userService = new UserService();
	    User newUser = new User();
	    String randomString = generateRandomString(8); 
	    newUser.setName("Sesslyn");
	    newUser.setEmail(randomString + "@" + "gmail.com");
	    newUser.setPassword("Jenusha@2303");
	    newUser.setPhoneNumber(9863456787L);
	    newUser.setDesigner(true);

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

	@Test
	@Order(3)
	 void testCreateUserWithInvalidInput() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(null);
		});
		String expectedMessage = "User object cannot be null";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(4)
	 void testCreateUserWithNameNull() {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setName(null);
		newUser.setEmail("sam@gmail.com");
		newUser.setPassword("Sam@2303");
		newUser.setPhoneNumber(9923456787L);
		newUser.setDesigner(false);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});
		String expectedMessage = "User Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(5)
	 void testCreateUserWithNameEmpty() {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setName("");
		newUser.setEmail("sam@gmail.com");
		newUser.setPassword("Sam@2303");
		newUser.setPhoneNumber(9923456787L);
		newUser.setDesigner(false);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});
		String expectedMessage = "User Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(6)
	 void testCreateUserWithLessCharacters() {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setName("Se");
		newUser.setEmail("sam@gmail.com");
		newUser.setPassword("Sam@2303");
		newUser.setPhoneNumber(9923456787L);
		newUser.setDesigner(false);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});
		String expectedMessage = "User Name should be at least 3 characters long";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	@Order(7)
	 void testCreateUserWithNamePattern() {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setName("S78s");
		newUser.setEmail("sam@gmail.com");
		newUser.setPassword("Sam@2303");
		newUser.setPhoneNumber(9923456787L);
		newUser.setDesigner(false);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});
		String expectedMessage = "User Name should only contain alphabetic characters and allow only one space between words";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(8)
	 void testCreateUserWithEmailNull() {
		UserService userService = new UserService();

		User newUser = new User();
		newUser.setName("Sam");
		newUser.setEmail(null);
		newUser.setPassword("Sam@2303");
		newUser.setPhoneNumber(9923456787L);
		newUser.setDesigner(false);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});
		String expectedMessage = "Email cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(9)
	 void testCreateUserWithEmailEmpty() {
		UserService userService = new UserService();

		User newUser = new User();
		newUser.setName("Sam");
		newUser.setEmail("");
		newUser.setPassword("Sam@2303");
		newUser.setPhoneNumber(9923456787L);
		newUser.setDesigner(false);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});
		String expectedMessage = "Email cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(10)
	 void testCreateUserWithEmailPattern() {
		UserService userService = new UserService();

		User newUser = new User();
		newUser.setName("Sam Victor");
		newUser.setEmail("sess@");
		newUser.setPassword("Sam@2303");
		newUser.setPhoneNumber(9923456787L);
		newUser.setDesigner(false);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});
		String expectedMessage = "Invalid email format. Please provide a valid email address";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(11)
	 void testCreateUserWithEmailExists() {
		UserService userService = new UserService();

		User newUser = new User();
		newUser.setName(" Pappu");
		newUser.setEmail("sesslyn@gmail.com");
		newUser.setPassword("Sess@1512");
		newUser.setPhoneNumber(6381040916L);
		newUser.setDesigner(false);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});
		String expectedMessage = "Email already exists.Try with a new email id";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(12)
	 void testCreateUserWithPasswordNull() {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setName("Sam");
		newUser.setEmail("bcde@gmail.com");
		newUser.setPassword(null);
		newUser.setPhoneNumber(9923456787L);
		newUser.setDesigner(false);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});
		String expectedMessage = "Password cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(13)
	 void testCreateUserWithPasswordEmpty() {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setName("Sam");
		newUser.setEmail("bcde@gmail.com");
		newUser.setPassword("");
		newUser.setPhoneNumber(9923456787L);
		newUser.setDesigner(false);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});
		String expectedMessage = "Password cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(14)
	 void testCreateUserWithInvalidPasswordPattern() {
	    UserService userService = new UserService();
	    User newUser = new User();
	    newUser.setName("Sam");
	    newUser.setEmail("joel@gmail.com");
	    newUser.setPassword("Sam23890"); 
	    newUser.setPhoneNumber(9923456787L);
	    newUser.setDesigner(false);

	    Exception exception = assertThrows(ValidationException.class, () -> {
	        userService.createUser(newUser);
	    });
	    
	    String expectedMessage = "Password must have at least 8 characters and "
	    		+ "contain at least one uppercase letter, one lowercase letter, "
	    		+ "and one special character";
	    String actualMessage = exception.getMessage();
	    
	    assertEquals(expectedMessage,actualMessage);
	}


	@Test
	@Order(15)
	 void testCreateUserWithPasswordLength() {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setName("Sam");
		newUser.setEmail("zxya@gmail.com");
		newUser.setPassword("Sam23");
		newUser.setPhoneNumber(9923456787L);
		newUser.setDesigner(false);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});
		String expectedMessage = "Password should be at least 8 characters long";
		String actualMessage = exception.getMessage();
		 assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	@Order(16)
	 void testCreateUserWithNegativePhoneNumber() {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setName("Sam");
		newUser.setEmail("bcde@gmail.com");
		newUser.setPassword("Sam@2303");
		newUser.setPhoneNumber(-2);
		newUser.setDesigner(false);

		ValidationException exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});

		String expectedMessage = "Phone number cannot be zero or negative";
		String actualMessage = exception.getMessage();

		 assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	@Order(17)
	 void testCreateUserWithAllZeroPhoneNumber() {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setName("Sam");
		newUser.setEmail("bcde@gmail.com");
		newUser.setPassword("Sam@2303");
		newUser.setPhoneNumber(98760567890L);
		newUser.setDesigner(false);

		ValidationException exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});

		String expectedMessage = "Phone number should be exactly 10 digits long";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(18)
	 void testCreateUserWithInvalidPhoneNumber() {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setName("Sam");
		newUser.setEmail("bcde@gmail.com");
		newUser.setPassword("Sam@2303");
		newUser.setPhoneNumber(3895673456L);
		newUser.setDesigner(false);

		ValidationException exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});

		String expectedMessage = "Invalid phone number format. Make sure not to include +91";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
}