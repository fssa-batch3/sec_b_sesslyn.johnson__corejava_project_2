package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Order;
import in.fssa.minimal.service.OrderService;

@TestMethodOrder(OrderAnnotation.class)
class TestCreateOrder {
	@Test 
	@org.junit.jupiter.api.Order(1) 
	 void testCreateOrderWithValidInput() {
	    OrderService orderService = new OrderService(); 
	    Order newOrder = new Order();
	    newOrder.setPrice(45000);
	    newOrder.setQuantity(1);
	    newOrder.setPayment("Cash On Delivery");
	    newOrder.setStatus("Waiting_list");
	    newOrder.setUserId(1); 
	    newOrder.setSellerId(15);
	    newOrder.setAddressId(1);
	    newOrder.setProductId(2);
	    assertDoesNotThrow(() -> { 
	        orderService.createOrder(newOrder);
	    });
	} 
	
	@Test 
	@org.junit.jupiter.api.Order(2) 
	 void testCreateOrderWithInValidInput() {
		OrderService orderService = new OrderService(); 
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.createOrder(null);
		});
		String expectedMessage = "Order object cannot be null";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	} 
	
	@Test 
	@org.junit.jupiter.api.Order(3) 
	 void testCreateOrderWithInvalidPrice() {
		OrderService orderService = new OrderService(); 
	    Order newOrder = new Order();
	    newOrder.setPrice(250);
	    newOrder.setQuantity(1);
	    newOrder.setPayment("Cash On Delivery");
	    newOrder.setStatus("Waiting_list");
	    newOrder.setUserId(1);
	    newOrder.setSellerId(15);
	    newOrder.setAddressId(1);
	    newOrder.setProductId(2);
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.createOrder(newOrder);
		});
		String expectedMessage = "Price should be in the range between 300 and 10 crores (100 million)";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	} 
	
	@Test 
	@org.junit.jupiter.api.Order(4) 
	 void testCreateOrderWithInvalidQuantity() {
		OrderService orderService = new OrderService(); 
	    Order newOrder = new Order();
	    newOrder.setPrice(5000);
	    newOrder.setQuantity(0);
	    newOrder.setPayment("Cash On Delivery");
	    newOrder.setStatus("Waiting_list");
	    newOrder.setUserId(1);
	    newOrder.setSellerId(15);
	    newOrder.setAddressId(1);
	    newOrder.setProductId(2);
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.createOrder(newOrder);
		});
		String expectedMessage = "Quantity should be in the range between 1 and 99";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	} 
	
	@Test 
	@org.junit.jupiter.api.Order(5) 
	 void testCreateOrderWithInvalidPayment() {
		OrderService orderService = new OrderService(); 
	    Order newOrder = new Order();
	    newOrder.setPrice(5000);
	    newOrder.setQuantity(1);
	    newOrder.setPayment("Cash On Delivery");
	    newOrder.setStatus("approved");
	    newOrder.setUserId(1);
	    newOrder.setSellerId(15);
	    newOrder.setAddressId(1);
	    newOrder.setProductId(2);
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.createOrder(newOrder);
		});
		String expectedMessage = "Invalid status value. The status can only be one of: waiting_list,rejected,delivered,cancelled";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	} 
	
	@Test 
	@org.junit.jupiter.api.Order(6) 
	 void testCreateOrderWithInvalidUserId() {
		OrderService orderService = new OrderService(); 
	    Order newOrder = new Order();
	    newOrder.setPrice(5000);
	    newOrder.setQuantity(1);
	    newOrder.setPayment("Cash On Delivery");
	    newOrder.setStatus("Waiting_list");
	    newOrder.setUserId(1000);
	    newOrder.setSellerId(15);
	    newOrder.setAddressId(1);
	    newOrder.setProductId(2);
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.createOrder(newOrder);
		});
		String expectedMessage = "User Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	} 
	
	@Test 
	@org.junit.jupiter.api.Order(7) 
	 void testCreateOrderWithInvalidSellerId() {
		OrderService orderService = new OrderService(); 
	    Order newOrder = new Order();
	    newOrder.setPrice(5000);
	    newOrder.setQuantity(1);
	    newOrder.setPayment("Cash On Delivery");
	    newOrder.setStatus("Waiting_list");
	    newOrder.setUserId(1);
	    newOrder.setSellerId(1);
	    newOrder.setAddressId(1);
	    newOrder.setProductId(2);
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.createOrder(newOrder);
		});
		String expectedMessage = "Seller Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	} 
	
	@Test 
	@org.junit.jupiter.api.Order(8) 
	 void testCreateOrderWithInvalidAddressId() {
		OrderService orderService = new OrderService(); 
	    Order newOrder = new Order();
	    newOrder.setPrice(5000);
	    newOrder.setQuantity(1);
	    newOrder.setPayment("Cash On Delivery");
	    newOrder.setStatus("Waiting_list");
	    newOrder.setUserId(1);
	    newOrder.setSellerId(15);
	    newOrder.setAddressId(4);
	    newOrder.setProductId(2);
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.createOrder(newOrder);
		});
		String expectedMessage = "User Address Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	} 
	
	@Test 
	@org.junit.jupiter.api.Order(9) 
	 void testCreateOrderWithInvalidProductId() {
		OrderService orderService = new OrderService(); 
	    Order newOrder = new Order();
	    newOrder.setPrice(5000);
	    newOrder.setQuantity(1);
	    newOrder.setPayment("Cash On Delivery");
	    newOrder.setStatus("Waiting_list");
	    newOrder.setUserId(1);
	    newOrder.setSellerId(15);
	    newOrder.setAddressId(1);
	    newOrder.setProductId(30);
		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.createOrder(newOrder);
		});
		String expectedMessage = "Product doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	} 
}
