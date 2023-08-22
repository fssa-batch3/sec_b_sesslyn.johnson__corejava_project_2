package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Set;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import in.fssa.minimal.dto.AppointmentRespondDto;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.service.AppointmentService;

@TestMethodOrder(OrderAnnotation.class)
public class TestGetAllAppointment {

	@Test
	@Order(1)
	void testGetAllAppointment() throws ValidationException, PersistenceException {
		AppointmentService appService = new AppointmentService();
		Set<AppointmentRespondDto> arr = appService.getAll();
	}

	@Test
	@Order(2)
	void testGetAllByStatus() throws ValidationException, PersistenceException {
		AppointmentService appService = new AppointmentService();
		Set<AppointmentRespondDto> arr = appService.getAllByStatus("approved");
	}

	@Test
	@Order(3)
	void testGetAllByStatusNull() {
		AppointmentService appService = new AppointmentService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			Set<AppointmentRespondDto> arr = appService.getAllByStatus(null);
		});
		String expectedMessage = "Status cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(4)
	void testGetAllByStatusEmpty() {
		AppointmentService appService = new AppointmentService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			Set<AppointmentRespondDto> arr = appService.getAllByStatus("");
		});
		String expectedMessage = "Status cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(5)
	void testGetAllByStatusPattern() {
		AppointmentService appService = new AppointmentService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			Set<AppointmentRespondDto> arr = appService.getAllByStatus("completed");
		});
		String expectedMessage = "Invalid status value. The status can only be one of:"
				+ " waiting_list, approved, rejected";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(6)
	void testGetAppointmentById() throws ValidationException, PersistenceException {
		AppointmentService appService = new AppointmentService();
		AppointmentRespondDto arr = appService.findById(1);
		System.out.println(arr);
	}

	@Test
	@Order(7)
	void testUpdateRequestStatus() throws ValidationException, PersistenceException {
		AppointmentService appService = new AppointmentService();
		appService.updateRequestStatus(1, "approved");
	}

	@Test
	@Order(8)
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
