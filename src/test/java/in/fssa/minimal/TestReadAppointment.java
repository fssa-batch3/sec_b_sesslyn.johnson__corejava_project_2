package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import in.fssa.minimal.dto.AppointmentRespondDTO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.service.AppointmentService;
@TestMethodOrder(OrderAnnotation.class)
class TestReadAppointment {
	@Test
	@Order(1)
	void testGetAllAppointment() throws ValidationException, PersistenceException {
		AppointmentService appointmentService = new AppointmentService();
		assertDoesNotThrow(() -> {
			Set<AppointmentRespondDTO> arr = appointmentService.getAllAppointment();
		});
	}

	@Test
	@Order(2)
	void testGetAllByStatus() throws ValidationException, PersistenceException {
		AppointmentService appointmentService = new AppointmentService();
		assertDoesNotThrow(() -> {
			Set<AppointmentRespondDTO> arr = appointmentService.getAllAppointmentByStatus("approved");
		});
	}

	@Test
	@Order(3)
	void testGetAllByStatusNull() {
		AppointmentService appointmentService = new AppointmentService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			Set<AppointmentRespondDTO> arr = appointmentService.getAllAppointmentByStatus(null);
		});
		String expectedMessage = "Status cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(4)
	void testGetAllByStatusEmpty() {
		AppointmentService appointmentService = new AppointmentService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			Set<AppointmentRespondDTO> arr = appointmentService.getAllAppointmentByStatus("");
		});
		String expectedMessage = "Status cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(5)
	void testGetAllByStatusPattern() {
		AppointmentService appointmentService = new AppointmentService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			Set<AppointmentRespondDTO> arr = appointmentService.getAllAppointmentByStatus("completed");
		});
		String expectedMessage = "Invalid status value. The status can only be one of:"
				+ " waiting_list, approved, rejected";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(6)
	void testGetAppointmentById() {
		AppointmentService appointmentService = new AppointmentService();
		assertDoesNotThrow(() -> {
			AppointmentRespondDTO arr = appointmentService.findById(1);
			System.out.println(arr);
		});
	}
}
