package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import in.fssa.minimal.dto.OrderRespondDTO;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.service.OrderService;

class TestReadOrder {
	@Test
    @Order(1)
    void testGetAllAddress() { 
        assertDoesNotThrow(() -> {
            OrderService orderService = new OrderService();
            Set<OrderRespondDTO> arr = orderService.getAllOrder();
            System.out.println(arr);
        });
    }
	
	@Test
    @Order(2)
    void testGetAllOrderByUserId() { 
        assertDoesNotThrow(() -> {
            OrderService orderService = new OrderService();
            Set<OrderRespondDTO> arr = orderService.getAllOrderByUserId(1);
            System.out.println(arr);
        });
    }
	
	@Test
	@Order(3)
	void testGetAllOrderByInvalidUserId() {
		OrderService orderService = new OrderService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			Set<OrderRespondDTO> arr = orderService.getAllOrderByUserId(100);
		});
		String expectedMessage = "User Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
    @Order(4)
    void testGetAllOrderBySellerId() { 
        assertDoesNotThrow(() -> {
            OrderService orderService = new OrderService();
            Set<OrderRespondDTO> arr = orderService.getAllOrderBySellerId(15);
            System.out.println(arr);
        });
    }
	
	@Test
	@Order(5)
	void testGetAllOrderByInvalidSellerId() {
		OrderService orderService = new OrderService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			Set<OrderRespondDTO> arr = orderService.getAllOrderBySellerId(100);
		});
		String expectedMessage = "Seller Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
    @Order(6)
    void testGetOrderById() { 
        assertDoesNotThrow(() -> {
            OrderService orderService = new OrderService();
            OrderRespondDTO arr = orderService.getOrderById(1);
            System.out.println(arr);
        });
    } 
	
	@Test
	@Order(7)
	void testGetOrderByInvalidId() {
		OrderService orderService = new OrderService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			OrderRespondDTO arr = orderService.getOrderById(100);
		});
		String expectedMessage = "Order Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
}
