package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Set;
import org.junit.jupiter.api.Test;
import in.fssa.minimal.dto.AppointmentRespondDto;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.User;
import in.fssa.minimal.service.AppointmentService;
import in.fssa.minimal.service.UserService;

public class TestGetAllAppointment {
	@Test
	public void getAllAppointment() throws ValidationException {
		AppointmentService appService = new AppointmentService();
		Set<AppointmentRespondDto> arr = appService.getAll();
	}

	@Test
	public void getAllByStatus() throws ValidationException {
		AppointmentService appService = new AppointmentService();
		Set<AppointmentRespondDto> arr = appService.getAllByStatus("approved");
	}

	@Test
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
	public void getAppointmentById() throws ValidationException {
		AppointmentService appService = new AppointmentService();
		AppointmentRespondDto arr = appService.findById(2);
		System.out.println(arr);
	}

	@Test
	public void testUpdateUser() throws ValidationException {
		AppointmentService appService = new AppointmentService();
		appService.updateRequestStatus(1, "approved");
	}

	@Test
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
	public void testUpdateUserWithStatusPattern() throws ValidationException {
		AppointmentService appService = new AppointmentService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			appService.updateRequestStatus(1, "completed");
		});
		String expectedMessage = "Status doesn't match the expected values";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
}
