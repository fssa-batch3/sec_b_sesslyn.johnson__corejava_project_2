package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import in.fssa.minimal.dao.AddressDAO;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.service.AddressService;

class TestDeleteAddress {
	@Test
	@Order(1)
	void testDeleteAddress() {
		assertDoesNotThrow(() -> {
			AddressService addressService = new AddressService();
			addressService.deleteAddress(1);
			AddressDAO.reactivateAddress(1);
		});
	}

	@Test
	@Order(2)
	void testGetAddressByIdLessThanZero() {
		AddressService addressService = new AddressService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.deleteAddress(-5);
		});
		String expectedMessage = "Address Id cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(3)
	void testGetAddressByInvalidId() {
		AddressService addressService = new AddressService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			addressService.deleteAddress(5000);
		});
		String expectedMessage = "Address Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

}
