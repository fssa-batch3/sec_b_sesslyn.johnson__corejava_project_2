package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Set;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import in.fssa.minimal.dto.AppointmentRespondDto;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.User;
import in.fssa.minimal.service.AppointmentService;
import in.fssa.minimal.service.UserService;
@TestMethodOrder(OrderAnnotation.class)
public class TestGetAllAppointment {
	@Test
	@Order(1)
	public void getAllAppointment() throws ValidationException, PersistenceException {
		AppointmentService appService = new AppointmentService();
		Set<AppointmentRespondDto> arr = appService.getAll();
	}

	@Test
	@Order(2)
	public void getAllByStatus() throws ValidationException, PersistenceException {
		AppointmentService appService = new AppointmentService();
		Set<AppointmentRespondDto> arr = appService.getAllByStatus("approved");
	}

	@Test
	@Order(3)
	public void testWithStatusNull() {
		AppointmentService appService = new AppointmentService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			Set<AppointmentRespondDto> arr = appService.getAllByStatus(null);
		});
		String expectedMessage = "Status cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(4)
	public void testWithStatusEmpty() {
		AppointmentService appService = new AppointmentService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			Set<AppointmentRespondDto> arr = appService.getAllByStatus("");
		});
		String expectedMessage = "Status cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(5)
	public void testWithStatusPattern() {
		AppointmentService appService = new AppointmentService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			Set<AppointmentRespondDto> arr = appService.getAllByStatus("completed");
		});
		String expectedMessage = "Status doesn't match the expected values";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(6)
	public void getAppointmentById() throws ValidationException, PersistenceException {
		AppointmentService appService = new AppointmentService();
		AppointmentRespondDto arr = appService.findById(1);
		System.out.println(arr);
	}

	@Test
	@Order(7)
	public void testUpdateUser() throws ValidationException, PersistenceException {
		AppointmentService appService = new AppointmentService();
		appService.updateRequestStatus(1, "approved");
	}

	@Test
	@Order(8)
	public void testUpdateUserWithStatusNull() throws ValidationException {
		AppointmentService appService = new AppointmentService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			appService.updateRequestStatus(1, null);
		});
		String expectedMessage = "Status cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	@Test
	@Order(9)
	public void testUpdateUserWithStatusEmpty() throws ValidationException {
		AppointmentService appService = new AppointmentService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			appService.updateRequestStatus(1, " ");
		});
		String expectedMessage = "Status cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	@Test
	@Order(10)
	public void testUpdateUserWithStatusPattern() throws ValidationException {
		AppointmentService appService = new AppointmentService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			appService.updateRequestStatus(1, "rejected");
		});
		String expectedMessage = "Status doesn't match the expected values";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
}
