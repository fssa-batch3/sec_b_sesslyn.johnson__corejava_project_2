package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
	    @Order(7)
	    void testUpdateUser() {
	        assertDoesNotThrow(() -> {
	            UserService userService = new UserService();
	            User newUser = new User();
	            newUser.setName("Pappu");
	            newUser.setPassword("Sess@2608");
	            newUser.setPhoneNumber(6381040926L);
	            userService.update(1, newUser);
	        });
	    }

	    @Test
	    @Order(8)
	    void testUpdateSpecificFields() {
	        assertDoesNotThrow(() -> {
	            UserService userService = new UserService();
	            User newUser = new User();
	            newUser.setPassword("Sess@2608");
	            newUser.setDesigner(false);
	            userService.update(1, newUser);
	        });
	    }

	@Test
	@Order(9)
	void testUpdateWithNonExistingId() throws ValidationException {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			User newUser = new User();
			newUser.setPassword("Sess@2608");
			newUser.setDesigner(false);
			userService.update(5000, newUser);
		});
		String expectedMessage = "Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

}
