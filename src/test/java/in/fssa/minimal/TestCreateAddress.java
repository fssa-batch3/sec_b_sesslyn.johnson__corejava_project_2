package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Address;
import in.fssa.minimal.service.AddressService;
@TestMethodOrder(OrderAnnotation.class)
class TestCreateAddress {
	@Test 
	@Order(1) 
	 void testCreateAddressWithValidInput() {
	    AddressService addressService = new AddressService(); 
	    Address newAddress = new Address();
	    newAddress.setName("Sesslyn");
	    newAddress.setEmail("sesslyn@gmail.com");
	    newAddress.setPhoneNumber(6382040916l); 
	    newAddress.setAddress("12/333b water tank street");
	    newAddress.setCity("Tirunelveli"); 
	    newAddress.setState("TamilNadu");
	    newAddress.setCountry("India");
	    newAddress.setPincode(627425);
	    newAddress.setTitle("Own House");
	    newAddress.setUserId(1);
	    assertDoesNotThrow(() -> { 
	        addressService.createAddress(newAddress); 
	    });
	}
	
	@Test
	@Order(2)
	 void testCreateAddressWithInvalidInput() {
		AddressService addressService = new AddressService(); 
		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.createAddress(null);
		});
		String expectedMessage = "Address object cannot be null";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	@Order(3)
	 void testCreateAddressWithShortName() {
		AddressService addressService = new AddressService(); 
	    Address newAddress = new Address();
	    newAddress.setName("Se");
	    newAddress.setEmail("sesslyn@gmail.com");
	    newAddress.setPhoneNumber(6382040916l);
	    newAddress.setAddress("12/333b water tank street");
	    newAddress.setCity("Tirunelveli");
	    newAddress.setState("TamilNadu");
	    newAddress.setCountry("India");
	    newAddress.setPincode(627425);
	    newAddress.setTitle("Own House");
	    newAddress.setUserId(1);

		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.createAddress(newAddress);
		});
		String expectedMessage = "User Name should be at least 3 characters long";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	@Order(4)
	 void testCreateAddressWithNumericName() {
		AddressService addressService = new AddressService(); 
	    Address newAddress = new Address();
	    newAddress.setName("Se34sd");
	    newAddress.setEmail("sesslyn@gmail.com");
	    newAddress.setPhoneNumber(6382040916l);
	    newAddress.setAddress("12/333b water tank street");
	    newAddress.setCity("Tirunelveli");
	    newAddress.setState("TamilNadu");
	    newAddress.setCountry("India");
	    newAddress.setPincode(627425);
	    newAddress.setTitle("Own House");
	    newAddress.setUserId(1);

		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.createAddress(newAddress);
		});
		String expectedMessage = "User Name should only contain alphabetic characters and allow only one space between words";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	@Order(5)
	 void testCreateAddressWithInvalidEmail() {
		AddressService addressService = new AddressService(); 
	    Address newAddress = new Address();
	    newAddress.setName("Sesslyn");
	    newAddress.setEmail("sesslyngmailcom");
	    newAddress.setPhoneNumber(6382040916l);
	    newAddress.setAddress("12/333b water tank street");
	    newAddress.setCity("Tirunelveli");
	    newAddress.setState("TamilNadu");
	    newAddress.setCountry("India");
	    newAddress.setPincode(627425);
	    newAddress.setTitle("Own House");
	    newAddress.setUserId(1);

		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.createAddress(newAddress);
		});
		String expectedMessage = "Invalid email format. Please provide a valid email address";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	@Order(6)
	 void testCreateAddressWithZeroPhoneNumber() {
		AddressService addressService = new AddressService(); 
	    Address newAddress = new Address();
	    newAddress.setName("Sesslyn");
	    newAddress.setEmail("sesslyn@gmail.com");
	    newAddress.setPhoneNumber(0);
	    newAddress.setAddress("12/333b water tank street");
	    newAddress.setCity("Tirunelveli");
	    newAddress.setState("TamilNadu");
	    newAddress.setCountry("India");
	    newAddress.setPincode(627425);
	    newAddress.setTitle("Own House");
	    newAddress.setUserId(1);

		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.createAddress(newAddress);
		});
		String expectedMessage = "Phone number cannot be zero or negative";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(7)
	 void testCreateAddressWithMoreDigitsPhoneNumber() {
		AddressService addressService = new AddressService(); 
	    Address newAddress = new Address();
	    newAddress.setName("Sesslyn");
	    newAddress.setEmail("sesslyn@gmail.com");
	    newAddress.setPhoneNumber(995346546789l);
	    newAddress.setAddress("12/333b water tank street");
	    newAddress.setCity("Tirunelveli");
	    newAddress.setState("TamilNadu");
	    newAddress.setCountry("India");
	    newAddress.setPincode(627425);
	    newAddress.setTitle("Own House");
	    newAddress.setUserId(1);

		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.createAddress(newAddress);
		});
		String expectedMessage = "Phone number should be exactly 10 digits long";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	@Order(8)
	 void testCreateAddressWithInvalidPhoneNumber() {
		AddressService addressService = new AddressService(); 
	    Address newAddress = new Address();
	    newAddress.setName("Sesslyn");
	    newAddress.setEmail("sesslyn@gmail.com");
	    newAddress.setPhoneNumber(1234567890l);
	    newAddress.setAddress("12/333b water tank street");
	    newAddress.setCity("Tirunelveli");
	    newAddress.setState("TamilNadu");
	    newAddress.setCountry("India");
	    newAddress.setPincode(627425);
	    newAddress.setTitle("Own House");
	    newAddress.setUserId(1);

		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.createAddress(newAddress);
		});
		String expectedMessage = "Invalid phone number format. Make sure not to include +91";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(9)
	 void testCreateAddressWithEmptyAddress() {
		AddressService addressService = new AddressService(); 
	    Address newAddress = new Address();
	    newAddress.setName("Sesslyn");
	    newAddress.setEmail("sesslyn@gmail.com");
	    newAddress.setPhoneNumber(9952393568l);
	    newAddress.setAddress("  ");
	    newAddress.setCity("Tirunelveli");
	    newAddress.setState("TamilNadu");
	    newAddress.setCountry("India");
	    newAddress.setPincode(627425);
	    newAddress.setTitle("Own House");
	    newAddress.setUserId(1);

		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.createAddress(newAddress);
		});
		String expectedMessage = "Address cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	@Order(10)
	 void testCreateAddressWithNumericCity() {
		AddressService addressService = new AddressService(); 
	    Address newAddress = new Address();
	    newAddress.setName("Sesslyn");
	    newAddress.setEmail("sesslyn@gmail.com");
	    newAddress.setPhoneNumber(9952393568l);
	    newAddress.setAddress("12/333b water tank street");
	    newAddress.setCity("Tirunelveli345");
	    newAddress.setState("TamilNadu");
	    newAddress.setCountry("India");
	    newAddress.setPincode(627425);
	    newAddress.setTitle("Own House");
	    newAddress.setUserId(1);

		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.createAddress(newAddress);
		});
		String expectedMessage = "City should only contain alphabets and spaces";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	@Order(11)
	 void testCreateAddressWithNumericState() {
		AddressService addressService = new AddressService(); 
	    Address newAddress = new Address();
	    newAddress.setName("Sesslyn");
	    newAddress.setEmail("sesslyn@gmail.com");
	    newAddress.setPhoneNumber(9952393568l);
	    newAddress.setAddress("12/333b water tank street");
	    newAddress.setCity("Tirunelveli");
	    newAddress.setState("Tamil67Nadu");
	    newAddress.setCountry("India");
	    newAddress.setPincode(627425);
	    newAddress.setTitle("Own House");
	    newAddress.setUserId(1);

		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.createAddress(newAddress);
		});
		String expectedMessage = "State should only contain alphabets and spaces";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	@Order(12)
	 void testCreateAddressWithNumericCountry() {
		AddressService addressService = new AddressService(); 
	    Address newAddress = new Address();
	    newAddress.setName("Sesslyn");
	    newAddress.setEmail("sesslyn@gmail.com");
	    newAddress.setPhoneNumber(9952393568l);
	    newAddress.setAddress("12/333b water tank street");
	    newAddress.setCity("Tirunelveli");
	    newAddress.setState("TamilNadu");
	    newAddress.setCountry("New56 Zealand");
	    newAddress.setPincode(627425);
	    newAddress.setTitle("Own House");
	    newAddress.setUserId(1);

		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.createAddress(newAddress);
		});
		String expectedMessage = "Country should only contain alphabets and spaces";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	@Order(13)
	 void testCreateAddressWithInvalidPicode() {
		AddressService addressService = new AddressService(); 
	    Address newAddress = new Address();
	    newAddress.setName("Sesslyn");
	    newAddress.setEmail("sesslyn@gmail.com");
	    newAddress.setPhoneNumber(9952393568l);
	    newAddress.setAddress("12/333b water tank street");
	    newAddress.setCity("Tirunelveli");
	    newAddress.setState("TamilNadu");
	    newAddress.setCountry("New Zealand");
	    newAddress.setPincode(22745);
	    newAddress.setTitle("Own House");
	    newAddress.setUserId(1);

		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.createAddress(newAddress);
		});
		String expectedMessage = "Pincode should start with 6 and have 6 digits";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(14)
	 void testCreateAddressWithNumericTitle() {
		AddressService addressService = new AddressService(); 
	    Address newAddress = new Address();
	    newAddress.setName("Sesslyn");
	    newAddress.setEmail("sesslyn@gmail.com");
	    newAddress.setPhoneNumber(9952393568l);
	    newAddress.setAddress("12/333b water tank street");
	    newAddress.setCity("Tirunelveli");
	    newAddress.setState("TamilNadu");
	    newAddress.setCountry("New Zealand");
	    newAddress.setPincode(627425);
	    newAddress.setTitle("Own 67House");
	    newAddress.setUserId(1);

		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.createAddress(newAddress);
		});
		String expectedMessage = "Title should only contain alphabets and spaces";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	@Order(14)
	 void testCreateAddressWithInvalidUser() {
		AddressService addressService = new AddressService(); 
	    Address newAddress = new Address();
	    newAddress.setName("Sesslyn");
	    newAddress.setEmail("sesslyn@gmail.com");
	    newAddress.setPhoneNumber(9952393568l);
	    newAddress.setAddress("12/333b water tank street");
	    newAddress.setCity("Tirunelveli");
	    newAddress.setState("TamilNadu");
	    newAddress.setCountry("New Zealand");
	    newAddress.setPincode(627425);
	    newAddress.setTitle("Own House");
	    newAddress.setUserId(1000);

		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.createAddress(newAddress);
		});
		String expectedMessage = "User Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

}
