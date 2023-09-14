package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
			newUser.setPhoneNumber(9952393567l);
			 newUser.setRole("user");
			userService.updateUser(1, newUser);
		});
	}
 
	@Test
	@Order(2)
	void testUpdateSpecificFields() {
		assertDoesNotThrow(() -> {
			UserService userService = new UserService();
			User newUser = new User();
			newUser.setPassword("Sess@2608");
			 newUser.setRole("user");
			userService.updateUser(1, newUser);
		});
	}

	@Test
	@Order(3)
	void testUpdateWithNonExistingId() throws ValidationException {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			User newUser = new User();
			newUser.setPassword("Sess@2608");
			 newUser.setRole("user");
			userService.updateUser(5000, newUser);
		});
		String expectedMessage = "User Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	@Order(4)
	void testUpdateWithTodayAsDateOfBirth() throws ValidationException {
	    UserService userService = new UserService();
	    Exception exception = assertThrows(ValidationException.class, () -> {
	        User newUser = new User();
	        newUser.setPassword("Sess@2608");
	        LocalDate currentDate = LocalDate.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        String currentDateStr = currentDate.format(formatter);
	        newUser.setDateOfBirth(currentDateStr);
	        userService.updateUser(1, newUser);
	    });
	    String expectedMessage = "Invalid date. The date can't be today or future.";
	    String actualMessage = exception.getMessage();

	    assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(5)
	void testUpdateWithFutureDateAsDateOfBirth() throws ValidationException {
	    UserService userService = new UserService();
	    Exception exception = assertThrows(ValidationException.class, () -> {
	        User newUser = new User();
	        newUser.setPassword("Sess@2608");
	        LocalDate currentDate = LocalDate.now();
	        LocalDate maxAllowedDate = currentDate.plusDays(2);
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        String currentDateStr = maxAllowedDate.format(formatter); // Corrected date formatting
	        newUser.setDateOfBirth(currentDateStr);
	        userService.updateUser(1, newUser);
	    });
	    String expectedMessage = "Invalid date. The date can't be today or future.";
	    String actualMessage = exception.getMessage();

	    assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(7)
	void testUpdateWithUnderageUser() throws ValidationException {
	    UserService userService = new UserService();
	    Exception exception = assertThrows(ValidationException.class, () -> {
	        User newUser = new User();
	        newUser.setPassword("Sess@2608");
	        newUser.setDateOfBirth("2010-06-15"); // Set a date of birth for an underage user
	        userService.updateUser(1, newUser);
	    });
	    String expectedMessage = "Users must be at least 18 years old.";
	    String actualMessage = exception.getMessage();

	    assertEquals(expectedMessage, actualMessage);
	}


	@Test
	@Order(7)
	void testUpdateWithInvalidRole() throws ValidationException {
	    UserService userService = new UserService();
	    Exception exception = assertThrows(ValidationException.class, () -> {
	        User newUser = new User();
	        newUser.setPassword("Sess@2608");
	        newUser.setRole("consumer"); 
	        userService.updateUser(1, newUser);
	    });
	    String expectedMessage = "Invalid Role type";
	    String actualMessage = exception.getMessage();

	    assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	@Order(8)
	void testUpdateWithInvalidGender() throws ValidationException {
	    UserService userService = new UserService();
	    Exception exception = assertThrows(ValidationException.class, () -> {
	        User newUser = new User();
	        newUser.setPassword("Sess@2608");
	        newUser.setGender("Trans"); 
	        userService.updateUser(1, newUser);
	    });
	    String expectedMessage = "Invalid Gender type";
	    String actualMessage = exception.getMessage();

	    assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	@Order(9)
	void testUpdateWithValidData() throws ValidationException {
		assertDoesNotThrow(() -> {
	    UserService userService = new UserService();
	        User newUser = new User();
	        newUser.setDateOfBirth("2004-12-15");
	        newUser.setGender("female"); 
	        newUser.setRole("seller");
	        userService.updateUser(1, newUser);
	    });
	}
}
