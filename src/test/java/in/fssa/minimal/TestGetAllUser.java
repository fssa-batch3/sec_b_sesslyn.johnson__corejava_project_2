package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import org.junit.jupiter.api.Test;

import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.User;
import in.fssa.minimal.service.UserService;

public class TestGetAllUser {
	
	@Test
	public void getAllUser() throws PersistenceException {
		UserService userService = new UserService();
		Set<User> arr = userService.getAll();
		System.out.println(arr);
	}

	@Test
	public void getUserById() throws ValidationException, PersistenceException {
		UserService userService = new UserService();
		User arr = userService.findById(1);
		System.out.println(arr);
	}

	
	@Test
	public void testWithIdLessThanZero() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			User arr = userService.findById(-5);
		});
		String expectedMessage = "Id can't be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testWitNonExistingId() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			User arr = userService.findById(20);
		});
		String expectedMessage = "Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void getUserByEmailId() throws ValidationException, PersistenceException {
		UserService userService = new UserService();
		User arr = userService.findByEmail("john@gmail.com");
		System.out.println(arr);
	}

	@Test
	public void testWitNonExistingEmail() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			User arr = userService.findByEmail("sesslyn2004@gmail.com");
		});
		String expectedMessage = "Email doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateUser() throws ValidationException, PersistenceException {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setName("Sesslyn");
		newUser.setPassword("Sess@2608");
		newUser.setPhoneNumber(6381040926L);
		userService.update(1, newUser);
	}
	
	@Test
	public void testUpdateSpecificFields() throws ValidationException, PersistenceException {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setPassword("Sess@2608");
		newUser.setDesigner(false);
		userService.update(1, newUser);
	}

	@Test
	public void testUpdateWithNonExistingId() throws ValidationException {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			User newUser = new User();
			newUser.setPassword("Sess@2608");
			newUser.setDesigner(false);
			userService.update(20, newUser);
		});
		String expectedMessage = "Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));	
	}
	
	@Test
	public void testDeleteUser() throws ValidationException, PersistenceException {
		UserService userService = new UserService();
		userService.delete(1);
	}

	@Test
	public void testDeleteWithNonExistingId() throws ValidationException {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.delete(20);
		});
		String expectedMessage = "Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));	
	}

	
	@Test
	public void getAllDesigner() throws PersistenceException {
		UserService userService = new UserService();
		Set<User> arr = userService.getAllDesigner();
		System.out.println(arr);
	}

	@Test
	public void getDesignerById() throws ValidationException, PersistenceException {
		UserService userService = new UserService();
		User arr = userService.findDesignerById(2);
		System.out.println(arr);
	}
	
	@Test
	public void testDesignerWitNonExistingId() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			User arr = userService.findDesignerById(5);
		});
		String expectedMessage = "Designer Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
}
