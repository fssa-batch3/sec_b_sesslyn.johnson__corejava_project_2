package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Appointment;
import in.fssa.minimal.service.AppointmentService;
import in.fssa.minimal.validator.UserId;

@TestMethodOrder(OrderAnnotation.class)
public class TestCreateAppointment {

	@Test
	@Order(1)
	public void testCreateAppointmentWithValidInput() throws PersistenceException {
		AppointmentService appointmentService = new AppointmentService();
		Appointment newAppointment = new Appointment();
		UserId app = new UserId();
		int user = app.getLastUpdatedUserId();
		newAppointment.setFromUser(user);
		int designer = app.getLastUpdatedDesignerId();
		newAppointment.setToUser(designer);
		newAppointment.setEmail("sesslyn@gmail.com");
		newAppointment.setPhoneNumber(6381040916l);
		newAppointment.setStatus("waiting_list");
		LocalDate currentDate = LocalDate.now();
		newAppointment.setDate(currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		LocalTime currentTime = LocalTime.now();
		newAppointment.setTime(currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));

		newAppointment.setAddress(null);
		assertDoesNotThrow(() -> {
			appointmentService.create(newAppointment);
		});
	}

	@Test
	@Order(2)
	public void testCreateAppointmentWithInvalidInput() {
		AppointmentService appointmentService = new AppointmentService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			appointmentService.create(null);
		});
		String expectedMessage = "Appointment object can not be null";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(3)
	public void testCreateAppointmentWithInvalidUserId() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment newAppointment = new Appointment();
		newAppointment.setFromUser(-2);
		newAppointment.setToUser(2);
		newAppointment.setEmail("sess@gmail.com");
		newAppointment.setPhoneNumber(6381040916l);
		newAppointment.setStatus("waiting_list");
		newAppointment.setDate("2023-09-30");
		newAppointment.setTime("15:30:00");
		newAppointment.setAddress(null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			appointmentService.create(newAppointment);
		});
		String expectedMessage = "Id can't be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(4)
	public void testCreateAppointmentWithInvalidDesignerId() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment newAppointment = new Appointment();
		newAppointment.setFromUser(2);
		newAppointment.setToUser(-2);
		newAppointment.setEmail("sess@gmail.com");
		newAppointment.setPhoneNumber(6381040916l);
		newAppointment.setStatus("waiting_list");
		newAppointment.setDate("2023-09-30");
		newAppointment.setTime("15:30:00");
		newAppointment.setAddress(null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			appointmentService.create(newAppointment);
		});
		String expectedMessage = "Id can't be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(5)
	public void testCreateAppointmentWithInvalidFromUserId() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment newAppointment = new Appointment();
		newAppointment.setFromUser(20);
		newAppointment.setToUser(2);
		newAppointment.setEmail("sesslyn@gmail.com");
		newAppointment.setPhoneNumber(6381040916l);
		newAppointment.setStatus("waiting_list");
		newAppointment.setDate("2023-09-30");
		newAppointment.setTime("15:30:00");
		newAppointment.setAddress(null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			appointmentService.create(newAppointment);
		});
		String expectedMessage = "Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(6)
	public void testCreateAppointmentHasAppointment() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment newAppointment = new Appointment();
		newAppointment.setFromUser(3);
		newAppointment.setToUser(2);
		newAppointment.setEmail("sess@gmail.com");
		newAppointment.setPhoneNumber(6381040916l);
		newAppointment.setStatus("waiting_list");
		newAppointment.setDate("2023-09-30");
		newAppointment.setTime("15:30:00");
		newAppointment.setAddress(null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			appointmentService.create(newAppointment);
		});
		String expectedMessage = "The appointment you have is yet to be completed. Please be patient";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(7)
	public void testCreateAppointmentDesignerHasAppointment() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment newAppointment = new Appointment();
		newAppointment.setFromUser(6);
		newAppointment.setToUser(2);
		newAppointment.setEmail("sess@gmail.com");
		newAppointment.setPhoneNumber(6381040916l);
		newAppointment.setStatus("waiting_list");
		newAppointment.setDate("2023-08-26");
		newAppointment.setTime("10:00:00");
		newAppointment.setAddress(null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			appointmentService.create(newAppointment);
		});
		String expectedMessage = "The designer has an appointment at that specific time. Please reschedule the appointment for a different time slot";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(8)
	public void testCreateAppointmentWithInvalidToUserId() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment newAppointment = new Appointment();
		newAppointment.setFromUser(3);
		newAppointment.setToUser(5);
		newAppointment.setEmail("sess@gmail.com");
		newAppointment.setPhoneNumber(6381040916l);
		newAppointment.setStatus("waiting_list");
		newAppointment.setDate("2023-09-30");
		newAppointment.setTime("15:30:00");
		newAppointment.setAddress(null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			appointmentService.create(newAppointment);
		});
		String expectedMessage = "Designer Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(9)
	public void testCreateAppointmentWithEmailNull() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment newAppointment = new Appointment();
		newAppointment.setFromUser(1);
		newAppointment.setToUser(2);
		newAppointment.setEmail(null);
		newAppointment.setPhoneNumber(6381040916l);
		newAppointment.setStatus("waiting_list");
		newAppointment.setDate("2023-09-30");
		newAppointment.setTime("15:30:00");
		newAppointment.setAddress(null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			appointmentService.create(newAppointment);
		});
		String expectedMessage = "Email cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(10)
	public void testCreateAppointmentWithEmailEmpty() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment newAppointment = new Appointment();
		newAppointment.setFromUser(1);
		newAppointment.setToUser(2);
		newAppointment.setEmail("");
		newAppointment.setPhoneNumber(6381040916l);
		newAppointment.setStatus("waiting_list");
		newAppointment.setDate("2023-09-30");
		newAppointment.setTime("15:30:00");
		newAppointment.setAddress(null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			appointmentService.create(newAppointment);
		});
		String expectedMessage = "Email cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(11)
	public void testCreateAppointmentWithAllZeroPhoneNumber() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment newAppointment = new Appointment();
		newAppointment.setFromUser(1);
		newAppointment.setToUser(2);
		newAppointment.setEmail("sam@gmail.com");
		newAppointment.setPhoneNumber(0l);
		newAppointment.setStatus("waiting_list");
		newAppointment.setDate("2023-09-30");
		newAppointment.setTime("15:30:00");
		newAppointment.setAddress(null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			appointmentService.create(newAppointment);
		});
		String expectedMessage = "PhoneNumber doesn't match the length";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(12)
	public void testCreateAppointmentWithInvalidPhoneNumber() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment newAppointment = new Appointment();
		newAppointment.setFromUser(1);
		newAppointment.setToUser(2);
		newAppointment.setEmail("sam@gmail.com");
		newAppointment.setPhoneNumber(3456789876L);
		newAppointment.setStatus("waiting_list");
		newAppointment.setDate("2023-09-30");
		newAppointment.setTime("15:30:00");
		newAppointment.setAddress(null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			appointmentService.create(newAppointment);
		});
		String expectedMessage = "PhoneNumber doesn't match the pattern";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(13)
	public void testCreateAppointmentWithStatusNull() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment newAppointment = new Appointment();
		newAppointment.setFromUser(1);
		newAppointment.setToUser(2);
		newAppointment.setEmail("sam@gmail.com");
		newAppointment.setPhoneNumber(6381040916l);
		newAppointment.setStatus(null);
		newAppointment.setDate("2023-09-10");
		newAppointment.setTime("15:30:00");
		newAppointment.setAddress(null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			appointmentService.create(newAppointment);
		});
		String expectedMessage = "Status cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(14)
	public void testCreateAppointmentWithStatusEmpty() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment newAppointment = new Appointment();
		newAppointment.setFromUser(1);
		newAppointment.setToUser(2);
		newAppointment.setEmail("sam@gmail.com");
		newAppointment.setPhoneNumber(6381040916l);
		newAppointment.setStatus("");
		newAppointment.setDate("2023-09-30");
		newAppointment.setTime("15:30:00");
		newAppointment.setAddress(null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			appointmentService.create(newAppointment);
		});
		String expectedMessage = "Status cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(15)
	public void testCreateAppointmentWithStatusPattern() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment newAppointment = new Appointment();
		newAppointment.setFromUser(1);
		newAppointment.setToUser(2);
		newAppointment.setEmail("sam@gmail.com");
		newAppointment.setPhoneNumber(6381040916l);
		newAppointment.setStatus("completed");
		newAppointment.setDate("2023-09-10");
		newAppointment.setTime("15:30:00");
		newAppointment.setAddress(null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			appointmentService.create(newAppointment);
		});
		String expectedMessage = "Status doesn't match the expected values";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(16)
	public void testCreateAppointmentWithDateNUll() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment newAppointment = new Appointment();
		newAppointment.setFromUser(1);
		newAppointment.setToUser(2);
		newAppointment.setEmail("sam@gmail.com");
		newAppointment.setPhoneNumber(6381040916l);
		newAppointment.setStatus("approved");
		newAppointment.setDate(null);
		newAppointment.setTime("15:30:00");
		newAppointment.setAddress(null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			appointmentService.create(newAppointment);
		});
		String expectedMessage = "Date cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(17)
	public void testCreateAppointmentWithDateEmpty() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment newAppointment = new Appointment();
		newAppointment.setFromUser(1);
		newAppointment.setToUser(2);
		newAppointment.setEmail("sam@gmail.com");
		newAppointment.setPhoneNumber(6381040916l);
		newAppointment.setStatus("approved");
		newAppointment.setDate("");
		newAppointment.setTime("15:30:00");
		newAppointment.setAddress(null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			appointmentService.create(newAppointment);
		});
		String expectedMessage = "Date cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(18)
	public void testCreateAppointmentWithInvalidDateFormat() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment newAppointment = new Appointment();
		newAppointment.setFromUser(1);
		newAppointment.setToUser(2);
		newAppointment.setEmail("sam@gmail.com");
		newAppointment.setPhoneNumber(6381040916L);
		newAppointment.setStatus("approved");
		newAppointment.setDate("10-09-2023");
		newAppointment.setTime("15:30:00");
		newAppointment.setAddress(null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			appointmentService.create(newAppointment);
		});
		String expectedMessage = "Invalid date or Invalid date format ( yyyy-MM-dd)";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(19)
	public void testCreateAppointmentWithPassedDate() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment newAppointment = new Appointment();
		newAppointment.setFromUser(1);
		newAppointment.setToUser(2);
		newAppointment.setEmail("sam@gmail.com");
		newAppointment.setPhoneNumber(6381040916l);
		newAppointment.setStatus("approved");
		newAppointment.setDate("2023-07-10");
		newAppointment.setTime("15:30:00");
		newAppointment.setAddress(null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			appointmentService.create(newAppointment);
		});
		String expectedMessage = "Invalid date or Invalid date format ( yyyy-MM-dd)";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(20)
	public void testCreateAppointmentWithFutureDate() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment newAppointment = new Appointment();
		newAppointment.setFromUser(1);
		newAppointment.setToUser(2);
		newAppointment.setEmail("sam@gmail.com");
		newAppointment.setPhoneNumber(6381040916l);
		newAppointment.setStatus("approved");
		newAppointment.setDate("2023-12-15");
		newAppointment.setTime("15:30:00");
		newAppointment.setAddress(null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			appointmentService.create(newAppointment);
		});
		String expectedMessage = "Invalid date or Invalid date format ( yyyy-MM-dd)";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(21)
	public void testCreateAppointmentWithTimeNUll() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment newAppointment = new Appointment();
		newAppointment.setFromUser(1);
		newAppointment.setToUser(2);
		newAppointment.setEmail("sam@gmail.com");
		newAppointment.setPhoneNumber(6381040916l);
		newAppointment.setStatus("approved");
		newAppointment.setDate("2023-10-08");
		newAppointment.setTime(null);
		newAppointment.setAddress(null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			appointmentService.create(newAppointment);
		});
		String expectedMessage = "Time cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(22)
	public void testCreateAppointmentWithTimeEmpty() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment newAppointment = new Appointment();
		newAppointment.setFromUser(1);
		newAppointment.setToUser(2);
		newAppointment.setEmail("sam@gmail.com");
		newAppointment.setPhoneNumber(6381040916l);
		newAppointment.setStatus("approved");
		newAppointment.setDate("2023-10-08");
		newAppointment.setTime("");
		newAppointment.setAddress(null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			appointmentService.create(newAppointment);
		});
		String expectedMessage = "Time cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(23)
	public void testCreateAppointmentWithParsedTime() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment newAppointment = new Appointment();
		newAppointment.setFromUser(1);
		newAppointment.setToUser(2);
		newAppointment.setEmail("sam@gmail.com");
		newAppointment.setPhoneNumber(6381040916l);
		newAppointment.setStatus("approved");
		newAppointment.setDate("2023-09-10");
		newAppointment.setTime("15:30");
		newAppointment.setAddress(null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			appointmentService.create(newAppointment);
		});
		String expectedMessage = "Invalid time or Invalid time format ( HH:mm:ss)";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(24)
	public void testCreateAppointmentWithPassedTime() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment newAppointment = new Appointment();
		newAppointment.setFromUser(8);
		newAppointment.setToUser(6);
		newAppointment.setEmail("joe@gmail.com");
		newAppointment.setPhoneNumber(6381040916l);
		newAppointment.setStatus("approved");
		newAppointment.setDate("2023-09-12");
		newAppointment.setTime("22:30:00");
		newAppointment.setAddress(null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			appointmentService.create(newAppointment);
		});
		String expectedMessage = "Invalid time or Invalid time format ( HH:mm:ss)";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

}
