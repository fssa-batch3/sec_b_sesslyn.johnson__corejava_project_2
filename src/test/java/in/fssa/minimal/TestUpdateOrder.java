package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;

import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.service.OrderService;

class TestUpdateOrder {
	@Test
	@Order(1)
	void testUpdateOrder() {
		assertDoesNotThrow(() -> {
			OrderService orderService = new OrderService();
			orderService.updateOrderRequestStatus(1, "Rejected");
		});
	}
	
	@Test
	@Order(2)
	void testUpdateOrderInvalidUserId() {
		assertDoesNotThrow(() -> {
			OrderService orderService = new OrderService();
			Exception exception = assertThrows(ValidationException.class, () -> {
				orderService.updateOrderRequestStatus(100, "Rejected");
			});
			String expectedMessage = "Order Id doesn't exist";
			String actualMessage = exception.getMessage();

			assertEquals(expectedMessage, actualMessage); 
		});
	}
 
	@Test 
	@Order(3)
	void testUpdateOrderInvalidStatus() {
		assertDoesNotThrow(() -> {
			OrderService orderService = new OrderService();
			Exception exception = assertThrows(ValidationException.class, () -> { 
				orderService.updateOrderRequestStatus(1, "Completed");
			});
			String expectedMessage = "Invalid status value. The status can only be one of: waiting_list,rejected,delivered,cancelled";
			String actualMessage = exception.getMessage();

			assertEquals(expectedMessage, actualMessage);
		});
	}
}
