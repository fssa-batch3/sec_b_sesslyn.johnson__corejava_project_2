package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.User;
import in.fssa.minimal.service.UserService;
@TestMethodOrder(OrderAnnotation.class)
class TestReadUser {
	@Test
    @Order(1)
    void testGetAllUser() {
        assertDoesNotThrow(() -> {
            UserService userService = new UserService();
            Set<User> arr = userService.getAllUser();
            System.out.println(arr);
        });
    }
    
    @Test 
    @Order(2)
    void testGetUserById() {
        assertDoesNotThrow(() -> {
            UserService userService = new UserService();
            User arr = userService.findByUserId(3);
            System.out.println(arr);
        });
    }

	@Test
	@Order(3)
	void testGetUserByIdLessThanZero() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			User arr = userService.findByUserId(-5);
		});
		String expectedMessage = "User Id cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(4)
	void testGetUserByNonExistingId() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			User arr = userService.findByUserId(5000);
		});
		String expectedMessage = "User Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	 @Test
	    @Order(5)
	    void testGetUserByEmailId() {
	        assertDoesNotThrow(() -> {
	            UserService userService = new UserService();
	            User arr = userService.findByEmail("ash@gmail.com");
	            System.out.println(arr);
	        });
	    }

	@Test
	@Order(6)
	void testGetUserByNonExistingEmail() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			User arr = userService.findByEmail("sesslyn2002@gmail.com");
		});
		String expectedMessage = "Email doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
    @Order(7)
    void testGetAllDesigner() {
        assertDoesNotThrow(() -> {
            UserService userService = new UserService();
            Set<User> arr = userService.getAllDesigner();
            System.out.println(arr);
        });
    }

    @Test
    @Order(8)
    void testGetDesignerById() {
        assertDoesNotThrow(() -> {
            UserService userService = new UserService();
            User arr = userService.findByDesignerId(2);
            System.out.println(arr);
        });
    }


	@Test
	@Order(9)
	void testGetDesignerByNonExistingId() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			User arr = userService.findByDesignerId(3);
		});
		String expectedMessage = "Designer Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
}