package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Address;
import in.fssa.minimal.service.AddressService;

class TestUpdateAddress {
	@Test
	@Order(1)
	void testUpdateAddress() {
		assertDoesNotThrow(() -> {
			AddressService addressService = new AddressService();
			Address newAddress = new Address();
			newAddress.setCity("Tirunelveli");
			newAddress.setState("TamilNadu");
			newAddress.setCountry("New Zealand");
			newAddress.setPincode(627425);
			newAddress.setTitle("Own House"); 
			newAddress.setUserId(1);
			addressService.updateAddress(1, newAddress);
		});
	}
 
	@Test
	@Order(2)
	void testUpdateAddressWithInvalidInput() {
		AddressService addressService = new AddressService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.updateAddress(1, null);
		});
		String expectedMessage = "Address object cannot be null";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(3)
	void testUpdateAddressWithZeroPhoneNumber() {
		AddressService addressService = new AddressService();
		Address newAddress = new Address();
		newAddress.setEmail("sesslyn@gmail.com");
		newAddress.setPhoneNumber(8976867584778l);
		newAddress.setAddress("12/333b water tank street");
		newAddress.setPincode(627425);
		newAddress.setTitle("Own House");
		newAddress.setUserId(1);

		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.updateAddress(1, newAddress);
		});
		String expectedMessage = "Phone number should be exactly 10 digits long";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(4)
	void testUpdateAddressWithInvalidId() {
		AddressService addressService = new AddressService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			Address newAddress = new Address();
			newAddress.setEmail("sesslyn@gmail.com");
			newAddress.setPhoneNumber(6381040916l);
			newAddress.setAddress("12/333b water tank street");
			newAddress.setPincode(627425);
			addressService.updateAddress(-2, newAddress);
		});
		String expectedMessage = "Address Id cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(5)
	void testUpdateDefaultStatus() {
	    assertDoesNotThrow(() -> {
	        AddressService addressService = new AddressService();
	        int userId = 1;
	        int addressId = 2;
	        addressService.updateDefaultStatus(userId, addressId);
	    });
	}

}
