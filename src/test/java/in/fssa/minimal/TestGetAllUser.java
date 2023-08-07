package in.fssa.minimal;

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
	public void getFindAll() throws ValidationException {
		UserService userService = new UserService();
		User arr = userService.findById(2);
		System.out.println(arr);
	}

	@Test
	public void getFindByEmailId() throws ValidationException {
		UserService userService = new UserService();
		User arr = userService.findByEmail("sesslyn@gmail.com");
		System.out.println(arr);
	}

	@Test
	public void testUpdateUser() throws ValidationException {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setPhoneNumber(8778061352L);
		newUser.setPassword("Ruby@2208");
		userService.update(3, newUser);
	}

	@Test
	public void testDeleteUser() throws ValidationException {
		UserService userService = new UserService();
		User newUser = new User();
		userService.delete(7);
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
}
