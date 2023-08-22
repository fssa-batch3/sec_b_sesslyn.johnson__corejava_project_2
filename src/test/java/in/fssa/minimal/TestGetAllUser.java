package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.User;
import in.fssa.minimal.service.UserService;

class TestGetAllUser {

	@Test
	@Order(1)
	void testGetAllUser() throws PersistenceException{
		UserService userService = new UserService();
		Set<User> arr = userService.getAll();
		System.out.println(arr);
	}

	@Test
	@Order(2)
	void testGetUserById() throws ValidationException, PersistenceException {
		UserService userService = new UserService();
		User arr = userService.findById(1);
		System.out.println(arr);
	}

	@Test
	@Order(3)
	void testGetUserByIdLessThanZero() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			User arr = userService.findById(-5);
		});
		String expectedMessage = "ID cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(4)
	void testGetUserByNonExistingId() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			User arr = userService.findById(5000);
		});
		String expectedMessage = "Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(5)
	void testGetUserByEmailId() throws ValidationException, PersistenceException {
		UserService userService = new UserService();
		User arr = userService.findByEmail("ruby@gmail.com");
		System.out.println(arr);
	}

	@Test
	@Order(6)
	void testGetUserByNonExistingEmail() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			User arr = userService.findByEmail("sesslyn2004@gmail.com");
		});
		String expectedMessage = "Email doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(7)
	void testUpdateUser() throws ValidationException, PersistenceException {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setName("Pappu");
		newUser.setPassword("Sess@2608");
		newUser.setPhoneNumber(6381040926L);
		userService.update(1, newUser);
	}

	@Test
	@Order(8)
	void testUpdateSpecificFields() throws ValidationException, PersistenceException {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setPassword("Sess@2608");
		newUser.setDesigner(false);
		userService.update(1, newUser);
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

	@Test
	@Order(10)
	void testDeleteUser() throws ValidationException, PersistenceException {
		UserService userService = new UserService();
		userService.delete(5);
	}

	@Test
	@Order(11)
	void testDeleteWithNonExistingId() throws ValidationException {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.delete(5000);
		});
		String expectedMessage = "Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(12)
	void testGetAllDesigner() throws PersistenceException {
		UserService userService = new UserService();
		Set<User> arr = userService.getAllDesigner();
		System.out.println(arr);
	}

	@Test
	@Order(13)
	void testGetDesignerById() throws ValidationException, PersistenceException {
		UserService userService = new UserService();
		User arr = userService.findDesignerById(2);
		System.out.println(arr);
	}

	@Test
	@Order(14)
	void testGetDesignerByNonExistingId() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			User arr = userService.findDesignerById(3);
		});
		String expectedMessage = "Designer Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
}
