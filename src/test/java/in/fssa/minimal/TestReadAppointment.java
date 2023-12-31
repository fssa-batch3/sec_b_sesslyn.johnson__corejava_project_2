package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

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
			List<AppointmentRespondDTO> arr = appointmentService.getAllAppointment();
			System.out.println(arr);
		});
	} 

	@Test
	@Order(2)
	void testGetAllByStatus() throws ValidationException, PersistenceException {
		AppointmentService appointmentService = new AppointmentService();
		assertDoesNotThrow(() -> {
			List<AppointmentRespondDTO> arr = appointmentService.getAllAppointmentByStatus("approved");
		});
	}

	@Test
	@Order(3) 
	void testGetAllByStatusNull() {
		AppointmentService appointmentService = new AppointmentService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			List<AppointmentRespondDTO> arr = appointmentService.getAllAppointmentByStatus(null);
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
			List<AppointmentRespondDTO> arr = appointmentService.getAllAppointmentByStatus("");
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
			List<AppointmentRespondDTO> arr = appointmentService.getAllAppointmentByStatus("accepted");
		});
		String expectedMessage = "Invalid status value. The status can only be one of:"
				+ " waiting_list, approved, rejected";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	@Order(6)
	void testGetAllAppointmentByFromUserId() throws ValidationException, PersistenceException {
		AppointmentService appointmentService = new AppointmentService();
		assertDoesNotThrow(() -> {
			List<AppointmentRespondDTO> arr = appointmentService.getAllAppointmentByFromUserId(3);
		});
	}

	@Test
	@Order(7)
	void testGetAllAppointmentByFromUserIdNotExist() {
		AppointmentService appointmentService = new AppointmentService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			List<AppointmentRespondDTO> arr = appointmentService.getAllAppointmentByFromUserId(1000);
		});
		String expectedMessage = "User Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	@Order(8)
	void testGetAllByToUserId() throws ValidationException, PersistenceException {
		AppointmentService appointmentService = new AppointmentService();
		assertDoesNotThrow(() -> {
			List<AppointmentRespondDTO> arr = appointmentService.getAllAppointmentByToUserId(2);
		});
	}

	@Test
	@Order(9)
	void testGetAllByToUserIdNotExist() {
		AppointmentService appointmentService = new AppointmentService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			List<AppointmentRespondDTO> arr = appointmentService.getAllAppointmentByToUserId(1);
		});
		String expectedMessage = "Designer Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	@Order(10)
	void testGetAppointmentById() {
		AppointmentService appointmentService = new AppointmentService();
		assertDoesNotThrow(() -> {
			AppointmentRespondDTO arr = appointmentService.findByAppointmentId(1);
			System.out.println(arr);
		});
	}
}