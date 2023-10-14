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
import in.fssa.minimal.util.RandomValue;
@TestMethodOrder(OrderAnnotation.class)
class TestCreateUser {

	@Test 
	@Order(1) 
	 void testCreateUserWithValidInput() {
	    UserService userService = new UserService(); 
	    User newUser = new User();
	    String randomString = RandomValue.generateRandomString(8); 
	    newUser.setName("Sesslyn");
	    newUser.setEmail(randomString + "@" + "gmail.com");
	    newUser.setPassword("Jenusha@2303");
	    newUser.setImage("https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bWFsZSUyMHByb2ZpbGV8ZW58MHx8MHx8fDA%3D&w=1000&q=80");
	    newUser.setPhoneNumber(9863456787L); 
	    newUser.setRole("user");

	    assertDoesNotThrow(() -> {
	        userService.createUser(newUser); 
	    });
	} 
	
	@Test
	@Order(2)
	 void testCreateDesignerWithValidInput() {
	    UserService userService = new UserService();
	    User newUser = new User();
	    String randomString = RandomValue.generateRandomString(8); 
	    newUser.setName("Sesslyn");
	    newUser.setEmail(randomString + "@" + "gmail.com");
	    newUser.setPassword("Jenusha@2303");
	    newUser.setImage("https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bWFsZSUyMHByb2ZpbGV8ZW58MHx8MHx8fDA%3D&w=1000&q=80");
	    newUser.setPhoneNumber(9863456787L);
	    newUser.setRole("designer");
	    newUser.setExperience(16);
        newUser.setDesigner_description("Experienced interior designer with a proven track record of transforming spaces"
		+ " into functional and aesthetically pleasing environments. Proficient in space planning, color coordination,"
		+ " and material selection. Known for delivering creative solutions that exceed client expectations. "
		+ "Strong communication and project management skills.");
	    assertDoesNotThrow(() -> {
	        userService.createDesigner(newUser);
	    });
	}
	
	
	@Test
	@Order(3)
	 void testCreateSellerWithValidInput() {
	    UserService userService = new UserService();
	    User newUser = new User();
	    String randomString = RandomValue.generateRandomString(8); 
	    newUser.setName("Sesslyn");
	    newUser.setEmail(randomString + "@" + "gmail.com");
	    newUser.setPassword("Jenusha@2303");
	    newUser.setImage("https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bWFsZSUyMHByb2ZpbGV8ZW58MHx8MHx8fDA%3D&w=1000&q=80");
	    newUser.setPhoneNumber(9863456787L);
	    newUser.setRole("seller");
	   newUser.setAadhar_number(RandomValue.generateRandomAadharNumber());
	   newUser.setGst_number("06ABCDE1234F1Z5");
	   newUser.setShop_address("108 Gandhi street, Valasaravakkam, Chennai-600096");
	    assertDoesNotThrow(() -> {
	        userService.createSeller(newUser);
	    });
	}
	
	

	@Test
	@Order(4)
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
	@Order(5)
	 void testCreateUserWithNameNull() {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setName(null);
		newUser.setEmail("sam@gmail.com");
		newUser.setPassword("Sam@2303");
		newUser.setImage("https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bWFsZSUyMHByb2ZpbGV8ZW58MHx8MHx8fDA%3D&w=1000&q=80");
		newUser.setPhoneNumber(9923456787L);
		 newUser.setRole("user");

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});
		String expectedMessage = "User Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(6)
	 void testCreateUserWithNameEmpty() {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setName("");
		newUser.setEmail("sam@gmail.com");
		newUser.setPassword("Sam@2303");
		  newUser.setImage("https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bWFsZSUyMHByb2ZpbGV8ZW58MHx8MHx8fDA%3D&w=1000&q=80");
		  
		newUser.setPhoneNumber(9923456787L);
		 newUser.setRole("user");

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});
		String expectedMessage = "User Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(7)
	 void testCreateUserWithLessCharacters() {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setName("Se");
		newUser.setEmail("sam@gmail.com");
		newUser.setPassword("Sam@2303");
		  newUser.setImage("https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bWFsZSUyMHByb2ZpbGV8ZW58MHx8MHx8fDA%3D&w=1000&q=80");
		  
		newUser.setPhoneNumber(9923456787L);
		 newUser.setRole("user");

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});
		String expectedMessage = "User Name should be at least 3 characters long";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	@Order(8)
	 void testCreateUserWithNamePattern() {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setName("S78s");
		newUser.setEmail("sam@gmail.com");
		newUser.setPassword("Sam@2303");
		  newUser.setImage("https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bWFsZSUyMHByb2ZpbGV8ZW58MHx8MHx8fDA%3D&w=1000&q=80");
		  
		newUser.setPhoneNumber(9923456787L);
		 newUser.setRole("user");

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});
		String expectedMessage = "User Name should only contain alphabetic characters and allow only one space between words";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(9)
	 void testCreateUserWithEmailNull() {
		UserService userService = new UserService();

		User newUser = new User();
		newUser.setName("Sam");
		newUser.setEmail(null);
		newUser.setPassword("Sam@2303");
		  newUser.setImage("https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bWFsZSUyMHByb2ZpbGV8ZW58MHx8MHx8fDA%3D&w=1000&q=80");
		  
		newUser.setPhoneNumber(9923456787L);
		 newUser.setRole("user");

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});
		String expectedMessage = "Email cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(10)
	 void testCreateUserWithEmailEmpty() {
		UserService userService = new UserService();

		User newUser = new User();
		newUser.setName("Sam");
		newUser.setEmail("");
		newUser.setPassword("Sam@2303");
		  newUser.setImage("https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bWFsZSUyMHByb2ZpbGV8ZW58MHx8MHx8fDA%3D&w=1000&q=80");
		  
		newUser.setPhoneNumber(9923456787L);
		 newUser.setRole("user");

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});
		String expectedMessage = "Email cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(11)
	 void testCreateUserWithEmailPattern() {
		UserService userService = new UserService();

		User newUser = new User();
		newUser.setName("Sam Victor");
		newUser.setEmail("sess@");
		newUser.setPassword("Sam@2303");
		  newUser.setImage("https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bWFsZSUyMHByb2ZpbGV8ZW58MHx8MHx8fDA%3D&w=1000&q=80");
		  
		newUser.setPhoneNumber(9923456787L);
		 newUser.setRole("user");

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});
		String expectedMessage = "Invalid email format. Please provide a valid email address";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(12)
	 void testCreateUserWithEmailExists() {
		UserService userService = new UserService();

		User newUser = new User();
		newUser.setName(" Pappu");
		newUser.setEmail("sesslyn@gmail.com");
		newUser.setPassword("Sess@1512");
		  newUser.setImage("https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bWFsZSUyMHByb2ZpbGV8ZW58MHx8MHx8fDA%3D&w=1000&q=80");
		  
		newUser.setPhoneNumber(6381040916L);
		 newUser.setRole("user");
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});
		String expectedMessage = "Email already exists.Try with a new email id";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(13)
	 void testCreateUserWithPasswordNull() {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setName("Sam");
		newUser.setEmail("bcde@gmail.com");
		newUser.setPassword(null);
		  newUser.setImage("https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bWFsZSUyMHByb2ZpbGV8ZW58MHx8MHx8fDA%3D&w=1000&q=80");
		  
		newUser.setPhoneNumber(9923456787L);
		 newUser.setRole("user");

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});
		String expectedMessage = "Password cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(14)
	 void testCreateUserWithPasswordEmpty() {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setName("Sam");
		newUser.setEmail("bcde@gmail.com");
		newUser.setPassword("");
		  newUser.setImage("https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bWFsZSUyMHByb2ZpbGV8ZW58MHx8MHx8fDA%3D&w=1000&q=80");
		  
		newUser.setPhoneNumber(9923456787L);
		 newUser.setRole("user");

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});
		String expectedMessage = "Password cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(15)
	 void testCreateUserWithInvalidPasswordPattern() {
	    UserService userService = new UserService();
	    User newUser = new User();
	    newUser.setName("Sam");
	    newUser.setEmail("joel@gmail.com");
	    newUser.setPassword("Sam23890"); 
	    newUser.setImage("https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bWFsZSUyMHByb2ZpbGV8ZW58MHx8MHx8fDA%3D&w=1000&q=80");
		  
	    newUser.setPhoneNumber(9923456787L);
	    newUser.setRole("user");

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
	@Order(16)
	 void testCreateUserWithPasswordLength() {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setName("Sam");
		newUser.setEmail("zxya@gmail.com");
		newUser.setPassword("Sam23");
		  newUser.setImage("https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bWFsZSUyMHByb2ZpbGV8ZW58MHx8MHx8fDA%3D&w=1000&q=80");
		  
		newUser.setPhoneNumber(9923456787L);
		 newUser.setRole("user");

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});
		String expectedMessage = "Password should be at least 8 characters long";
		String actualMessage = exception.getMessage();
		 assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	@Order(17)
	 void testCreateUserWithNegativePhoneNumber() {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setName("Sam");
		newUser.setEmail("bcde@gmail.com");
		newUser.setPassword("Sam@2303");
		  newUser.setImage("https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bWFsZSUyMHByb2ZpbGV8ZW58MHx8MHx8fDA%3D&w=1000&q=80");
		  
		newUser.setPhoneNumber(-2);
		 newUser.setRole("user");
		ValidationException exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});

		String expectedMessage = "Phone number cannot be zero or negative";
		String actualMessage = exception.getMessage();

		 assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	@Order(18)
	 void testCreateUserWithAllZeroPhoneNumber() {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setName("Sam");
		newUser.setEmail("bcde@gmail.com");
		newUser.setPassword("Sam@2303");
		  newUser.setImage("https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bWFsZSUyMHByb2ZpbGV8ZW58MHx8MHx8fDA%3D&w=1000&q=80");
		  
		newUser.setPhoneNumber(98760567890L);
		 newUser.setRole("user");

		ValidationException exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});

		String expectedMessage = "Phone number should be exactly 10 digits long";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(19)
	 void testCreateUserWithInvalidPhoneNumber() {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setName("Sam");
		newUser.setEmail("bcde@gmail.com");
		newUser.setPassword("Sam@2303");
		  newUser.setImage("https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bWFsZSUyMHByb2ZpbGV8ZW58MHx8MHx8fDA%3D&w=1000&q=80");
		  
		newUser.setPhoneNumber(3895673456L);
		 newUser.setRole("user");

		ValidationException exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});

		String expectedMessage = "Invalid phone number format. Make sure not to include +91";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	
	@Test
	@Order(20)
	 void testCreateUserWithInvalidImageUrl() {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setName("Sam");
		newUser.setEmail("bcde@gmail.com");
		newUser.setPassword("Sam@2303");
		  newUser.setImage("wp-content/uploads/2021/07/headshot-for-startup.jpg");
		newUser.setPhoneNumber(3895673456L);
		 newUser.setRole("user");

		ValidationException exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});

		String expectedMessage = "Invalid image format. Please provide a valid image url.";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	@Order(21)
	 void testCreateDesignerWithInValidExperience() {
	    UserService userService = new UserService();
	    User newUser = new User();
	    String randomString = RandomValue.generateRandomString(8); 
	    newUser.setName("Sesslyn");
	    newUser.setEmail(randomString + "@" + "gmail.com");
	    newUser.setPassword("Jenusha@2303");
	    newUser.setImage("https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bWFsZSUyMHByb2ZpbGV8ZW58MHx8MHx8fDA%3D&w=1000&q=80");
	    newUser.setPhoneNumber(9863456787L);
	    newUser.setRole("designer");
	    newUser.setExperience(-2);
        newUser.setDesigner_description("Experienced interior designer with a proven track record of transforming spaces"
		+ " into functional and aesthetically pleasing environments. Proficient in space planning, color coordination,"
		+ " and material selection. Known for delivering creative solutions that exceed client expectations. "
		+ "Strong communication and project management skills.");
        ValidationException exception = assertThrows(ValidationException.class, () -> {
			userService.createDesigner(newUser);
		});

		String expectedMessage = "Experience cannot be less than zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	@Test 
	@Order(22)
	 void testCreateSellerWithLessDigitAadharNo() {
	    UserService userService = new UserService();
	    User newUser = new User();
	    String randomString = RandomValue.generateRandomString(8); 
	    newUser.setName("Sesslyn");
	    newUser.setEmail(randomString + "@" + "gmail.com");
	    newUser.setPassword("Jenusha@2303");
	    newUser.setImage("https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bWFsZSUyMHByb2ZpbGV8ZW58MHx8MHx8fDA%3D&w=1000&q=80");
	    newUser.setPhoneNumber(9863456787L);
	    newUser.setRole("seller");
	   newUser.setAadhar_number(4567765489L);
	   newUser.setGst_number("06ABCDE1234F1Z5");
	   newUser.setShop_address("108 Gandhi street, Valasaravakkam, Chennai-600096");
	   ValidationException exception = assertThrows(ValidationException.class, () -> {
			userService.createSeller(newUser);
		});

		String expectedMessage = "Aadhar Number must have exactly 12 digits";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	@Order(23)
	 void testCreateSellerWithOneAsAFirstDigitInAadhar() {
	    UserService userService = new UserService();
	    User newUser = new User();
	    String randomString = RandomValue.generateRandomString(8); 
	    newUser.setName("Sesslyn");
	    newUser.setEmail(randomString + "@" + "gmail.com");
	    newUser.setPassword("Jenusha@2303");
	    newUser.setImage("https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bWFsZSUyMHByb2ZpbGV8ZW58MHx8MHx8fDA%3D&w=1000&q=80");
	    newUser.setPhoneNumber(9863456787L);
	    newUser.setRole("seller");
	   newUser.setAadhar_number(156776548978L);
	   newUser.setGst_number("06ABCDE1234F1Z5");
	   newUser.setShop_address("108 Gandhi street, Valasaravakkam, Chennai-600096");
	   ValidationException exception = assertThrows(ValidationException.class, () -> {
			userService.createSeller(newUser);
		});

		String expectedMessage = "The first digit of Aadhar Number cannot be 0 or 1";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
       

	@Test
	@Order(24)
	 void testCreateSellerWithSameAllDigits() {
	    UserService userService = new UserService();
	    User newUser = new User();
	    String randomString =RandomValue.generateRandomString(8); 
	    newUser.setName("Sesslyn");
	    newUser.setEmail(randomString + "@" + "gmail.com");
	    newUser.setPassword("Jenusha@2303");
	    newUser.setImage("https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bWFsZSUyMHByb2ZpbGV8ZW58MHx8MHx8fDA%3D&w=1000&q=80");
	    newUser.setPhoneNumber(9863456787L);
	    newUser.setRole("seller");
	   newUser.setAadhar_number(888888888888L);
	   newUser.setGst_number("06ABCDE1234F1Z5");
	   newUser.setShop_address("108 Gandhi street, Valasaravakkam, Chennai-600096");
	   ValidationException exception = assertThrows(ValidationException.class, () -> {
			userService.createSeller(newUser);
		});

		String expectedMessage = "Aadhar Number cannot have all 12 digits the same";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	@Order(25)
	 void testCreateSellerWithInvalidPatternGstNumber() {
	    UserService userService = new UserService();
	    User newUser = new User();
	    String randomString = RandomValue.generateRandomString(8); 
	    newUser.setName("Sesslyn");
	    newUser.setEmail(randomString + "@" + "gmail.com");
	    newUser.setPassword("Jenusha@2303");
	    newUser.setImage("https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bWFsZSUyMHByb2ZpbGV8ZW58MHx8MHx8fDA%3D&w=1000&q=80");
	    newUser.setPhoneNumber(9863456787L);
	    newUser.setRole("seller");
	   newUser.setAadhar_number(897654675498L);
	   newUser.setGst_number("12ABCDE12345A6");
	   newUser.setShop_address("108 Gandhi street, Valasaravakkam, Chennai-600096");
	   ValidationException exception = assertThrows(ValidationException.class, () -> {
			userService.createSeller(newUser);
		});

		String expectedMessage = "GST Number doesn't match the pattern";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
}