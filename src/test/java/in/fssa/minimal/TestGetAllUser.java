package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import in.fssa.minimal.dao.UserDAO;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.User;
import in.fssa.minimal.service.UserService;
@TestMethodOrder(OrderAnnotation.class)
class TestGetAllUser {

	@Test
    @Order(1)
    void testGetAllUser() {
        assertDoesNotThrow(() -> {
            UserService userService = new UserService();
            Set<User> arr = userService.getAll();
            System.out.println(arr);
        });
    }
    
    @Test
    @Order(2)
    void testGetUserById() {
        assertDoesNotThrow(() -> {
            UserService userService = new UserService();
            User arr = userService.findById(1);
            System.out.println(arr);
        });
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
	    void testGetUserByEmailId() {
	        assertDoesNotThrow(() -> {
	            UserService userService = new UserService();
	            User arr = userService.findByEmail("ruby@gmail.com");
	            System.out.println(arr);
	        });
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

	@Test
	@Order(10)
	 void testCreateUserWithValidInput() {
	    UserService userService = new UserService();
	    User newUser = new User();
	    String randomString = generateRandomString(8); 
	    newUser.setName("Sesslyn");
	    newUser.setEmail(randomString + "@" + "google.com");
	    newUser.setPassword("Jenusha@2303");
	    newUser.setPhoneNumber(9863456787L);
	    newUser.setDesigner(false);

	    assertDoesNotThrow(() -> {
	        userService.create(newUser);
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
	    @Order(11)
	    void testDeleteUser() {
	        assertDoesNotThrow(() -> {
	            UserService userService = new UserService();
	            UserDAO app = new UserDAO();
	    	    int user = app.getLastUpdatedUserId();
	            userService.delete(user);
	        });
	    }


	@Test
	@Order(12)
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
    @Order(13)
    void testGetAllDesigner() {
        assertDoesNotThrow(() -> {
            UserService userService = new UserService();
            Set<User> arr = userService.getAllDesigner();
            System.out.println(arr);
        });
    }

    @Test
    @Order(14)
    void testGetDesignerById() {
        assertDoesNotThrow(() -> {
            UserService userService = new UserService();
            User arr = userService.findDesignerById(2);
            System.out.println(arr);
        });
    }


	@Test
	@Order(15)
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
