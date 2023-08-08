package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import org.junit.jupiter.api.Test;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.User;
import in.fssa.minimal.service.UserService;

public class TestGetAllUser {
	@Test
	public void getAllUser() {
		UserService userService = new UserService();
		Set<User> arr = userService.getAll();
		System.out.println(arr);
	}

	@Test
	public void getUserById() throws ValidationException {
		UserService userService = new UserService();
		User arr = userService.findById(2);
		System.out.println(arr);
	}

	@Test
	public void testWitNonExistingId() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			User arr = userService.findById(10);
		});
		String expectedMessage = "Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void getUserByEmailId() throws ValidationException {
		UserService userService = new UserService();
		User arr = userService.findByEmail("sesslyn@gmail.com");
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
	public void testUpdateUser() throws ValidationException {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setName("Sess");
		newUser.setPassword("Pappu@2608");
		newUser.setDesigner(true);
		newUser.setPhoneNumber(6381040928L);
		userService.update(1, newUser);
	}
	
	@Test
	public void testUpdateSpecificFields() throws ValidationException {
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
			userService.update(10, newUser);
		});
		String expectedMessage = "Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));	
	}
	
	@Test
	public void testDeleteUser() throws ValidationException {
		UserService userService = new UserService();
		userService.delete(4);
	}

	@Test
	public void testDeleteWithNonExistingId() throws ValidationException {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.delete(7);
		});
		String expectedMessage = "Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));	
	}

	
	@Test
	public void getAllDesigner() {
		UserService userService = new UserService();
		Set<User> arr = userService.getAllDesigner();
		System.out.println(arr);
	}

	@Test
	public void getDesignerById() throws ValidationException {
		UserService userService = new UserService();
		User arr = userService.findDesignerById(4);
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