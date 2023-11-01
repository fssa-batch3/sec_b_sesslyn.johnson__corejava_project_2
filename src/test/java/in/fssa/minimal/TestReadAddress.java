package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Address;
import in.fssa.minimal.service.AddressService;

class TestReadAddress {
	@Test
    @Order(1)
    void testGetAllAddress() { 
        assertDoesNotThrow(() -> {
            AddressService addressService = new AddressService();
            List<Address> arr = addressService.getAllAddress();
            System.out.println(arr);
        });
    }
	
	@Test
    @Order(2)
    void testGetAllAddressByUserId() {
        assertDoesNotThrow(() -> {
            AddressService addressService = new AddressService();
            List<Address> arr = addressService.getAllAddressByUserId(1);
            System.out.println(arr);
        });
    }
	
	@Test
    @Order(3)
    void testAddressById() {
        assertDoesNotThrow(() -> {
            AddressService addressService = new AddressService();
            Address arr = addressService.getAddressById(1);
            System.out.println(arr);
        });
    }
     
	@Test
	@Order(4)
	void testGetAllAddressByInvalidUserId() {
		 AddressService addressService = new AddressService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			List<Address> arr = addressService.getAllAddressByUserId(500);
		});
		String expectedMessage = "User Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	@Order(5)
	void testGetAddressByInvalidId() {
		 AddressService addressService = new AddressService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			  Address arr = addressService.getAddressById(500);
		});
		String expectedMessage = "Address Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
}
