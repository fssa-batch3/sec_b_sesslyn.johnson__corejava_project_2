package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.service.AppointmentService;
@TestMethodOrder(OrderAnnotation.class)
class TestUpdateAppointment {
	@Test
	@Order(1)
	void testUpdateRequestStatus() {
		AppointmentService appService = new AppointmentService();
		assertDoesNotThrow(() -> {
			appService.updateRequestStatus(1, "approved");
		});
	}

	@Test
	@Order(2)
	void testUpdateRequestStatusWithStatusPattern() throws ValidationException {
		AppointmentService appService = new AppointmentService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			appService.updateRequestStatus(1, "rejected");
		});
		String expectedMessage = "Approved appointment cannot be re update";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
}
