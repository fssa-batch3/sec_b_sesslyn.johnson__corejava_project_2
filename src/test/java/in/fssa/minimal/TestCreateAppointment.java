package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import in.fssa.minimal.dao.UserDAO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Appointment;
import in.fssa.minimal.service.AppointmentService;


@TestMethodOrder(OrderAnnotation.class)
class TestCreateAppointment {

	@Test
	@Order(1)
	void testCreateAppointmentWithValidInput() throws PersistenceException {
	    AppointmentService appointmentService = new AppointmentService();
	    Appointment newAppointment = new Appointment();
	    UserDAO app = new UserDAO();
	    int user = app.getLastUpdatedUserId();
	    newAppointment.setFromUser(user);
	    int designer = app.getLastUpdatedDesignerId();
	    newAppointment.setToUser(designer);
	    newAppointment.setEmail("sesslyn@gmail.com");
	    newAppointment.setPhoneNumber(6381040916L);
	    newAppointment.setStatus("waiting_list");
	    LocalDate currentDate = LocalDate.now();
	    newAppointment.setDate(currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	    LocalTime currentTime = LocalTime.now();
	    
	    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	    LocalTime dueTime = currentTime;

	    LocalTime minTime = LocalTime.parse("08:00:00");
	    LocalTime maxTime = LocalTime.parse("20:00:00");

	    if (dueTime.isBefore(minTime) || dueTime.isAfter(maxTime)) {
	        dueTime = LocalTime.parse("11:35:29", inputFormatter);
	    }

	    newAppointment.setTime(dueTime.format(inputFormatter));
	    newAppointment.setAddress(null);
	    
	    assertDoesNotThrow(() -> {
	        appointmentService.createAppointment(newAppointment);
	    });
	}

	@Test
	@Order(2)
	 void testCreateAppointmentWithInvalidInput() {
		AppointmentService appointmentService = new AppointmentService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			appointmentService.createAppointment(null);
		});
		String expectedMessage = "Appointment object cannot be null";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(3)
	 void testCreateAppointmentWithInvalidUserId(){
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
			appointmentService.createAppointment(newAppointment);
		});
		String expectedMessage = "ID cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(4)
	 void testCreateAppointmentWithInvalidDesignerId() {
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
			appointmentService.createAppointment(newAppointment);
		});
		String expectedMessage = "ID cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(5)
	void testInvalidUserIdInCreateAppointment() {
	    AppointmentService appointmentService = new AppointmentService();
	    Appointment newAppointment = new Appointment();
	    newAppointment.setFromUser(5000);
	    newAppointment.setToUser(2);
	    newAppointment.setEmail("sesslyn@gmail.com");
	    newAppointment.setPhoneNumber(6381040916L);
	    newAppointment.setStatus("waiting_list");
	    newAppointment.setDate("2023-09-30");
	    newAppointment.setTime("15:30:00");
	    newAppointment.setAddress(null);
	    Exception exception = assertThrows(ValidationException.class, () -> {
	        appointmentService.createAppointment(newAppointment);
	    });
	    String expectedMessage = "Id doesn't exist";
	    String actualMessage = exception.getMessage();

	    assertEquals(expectedMessage, actualMessage);
	}
	@Test
	@Order(6)
	 void testCreateAppointmentWithExistingAppointment() {
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
			appointmentService.createAppointment(newAppointment);
		});
		String expectedMessage = "The appointment you have is yet to be completed. Please be patient";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(7)
	 void testCreateAppointmentWithDesignerHasAppointment() {
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
			appointmentService.createAppointment(newAppointment);
		});
		String expectedMessage = "The designer has an appointment at that specific time. Please reschedule the appointment for a different time slot";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(8)
	 void testInvalidToUserIdInCreateAppointment()  {
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
			appointmentService.createAppointment(newAppointment);
		});
		String expectedMessage = "Designer Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(9)
	 void testCreateAppointmentWithEmailNull(){
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
			appointmentService.createAppointment(newAppointment);
		});
		String expectedMessage = "Email cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(10)
	 void testCreateAppointmentWithEmailEmpty() {
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
			appointmentService.createAppointment(newAppointment);
		});
		String expectedMessage = "Email cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	@Test
	@Order(11)
	 void testCreateAppointmentWithInvalidEmailPattern() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment newAppointment = new Appointment();
		newAppointment.setFromUser(1);
		newAppointment.setToUser(2);
		newAppointment.setEmail("sess@com");
		newAppointment.setPhoneNumber(6381040916l);
		newAppointment.setStatus("waiting_list");
		newAppointment.setDate("2023-09-30");
		newAppointment.setTime("15:30:00");
		newAppointment.setAddress(null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			appointmentService.createAppointment(newAppointment);
		});
		String expectedMessage = "Invalid email format. Please provide a valid email address";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	@Order(12)
	 void testCreateAppointmentWithNegativePhoneNumber() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment newAppointment = new Appointment();
		newAppointment.setFromUser(1);
		newAppointment.setToUser(2);
		newAppointment.setEmail("sam@gmail.com");
		newAppointment.setPhoneNumber(-10);
		newAppointment.setStatus("waiting_list");
		newAppointment.setDate("2023-09-30");
		newAppointment.setTime("15:30:00");
		newAppointment.setAddress(null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			appointmentService.createAppointment(newAppointment);
		});
		String expectedMessage = "Phone number cannot be zero or negative";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(13)
	 void testCreateAppointmentWithAllZeroPhoneNumber() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment newAppointment = new Appointment();
		newAppointment.setFromUser(1);
		newAppointment.setToUser(2);
		newAppointment.setEmail("sam@gmail.com");
		newAppointment.setPhoneNumber(987645678l);
		newAppointment.setStatus("waiting_list");
		newAppointment.setDate("2023-09-30");
		newAppointment.setTime("15:30:00");
		newAppointment.setAddress(null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			appointmentService.createAppointment(newAppointment);
		});
		String expectedMessage = "Phone number should be exactly 10 digits long";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(14)
	 void testCreateAppointmentWithInvalidPhoneNumber() {
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
			appointmentService.createAppointment(newAppointment);
		});
		String expectedMessage = "Invalid phone number format. Make sure not to include +91";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(15)
	void testCreateAppointmentWithStatusNull(){
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
			appointmentService.createAppointment(newAppointment);
		});
		String expectedMessage = "Status cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(16)
	 void testCreateAppointmentWithStatusEmpty() {
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
			appointmentService.createAppointment(newAppointment);
		});
		String expectedMessage = "Status cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(17)
	 void testCreateAppointmentWithInvalidStatusPattern() {
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
			appointmentService.createAppointment(newAppointment);
		});
		String expectedMessage = "Invalid status value. The status can only be one of: waiting_list, approved, rejected";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(18)
	void testCreateAppointmentWithDateNull() {
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
			appointmentService.createAppointment(newAppointment);
		});
		String expectedMessage = "Date cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(19)
	void testCreateAppointmentWithDateEmpty() {
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
			appointmentService.createAppointment(newAppointment);
		});
		String expectedMessage = "Date cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(20)
	void testCreateAppointmentWithInvalidDateFormat() {
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
			appointmentService.createAppointment(newAppointment);
		});
		String expectedMessage = "Invalid date or Invalid date format (yyyy-MM-dd)";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(21)
	void testCreateAppointmentWithPastDate() {
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
			appointmentService.createAppointment(newAppointment);
		});
		String expectedMessage = "Invalid date. The date should be within the next 90 days";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(22)
	void testCreateAppointmentWithFutureDate() {
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
			appointmentService.createAppointment(newAppointment);
		});
		String expectedMessage = "Invalid date. The date should be within the next 90 days";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(23)
	void testCreateAppointmentWithTimeNull() {
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
			appointmentService.createAppointment(newAppointment);
		});
		String expectedMessage = "Time cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(24)
	void testCreateAppointmentWithTimeEmpty() {
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
			appointmentService.createAppointment(newAppointment);
		});
		String expectedMessage = "Time cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(25)
	void testCreateAppointmentWithInvalidTimeFormat() {
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
			appointmentService.createAppointment(newAppointment);
		});
		String expectedMessage = "Invalid time or Invalid time format (HH:mm:ss)";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(26)
	void testCreateAppointmentWithInvalidTimeRange() {
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
			appointmentService.createAppointment(newAppointment);
		});
		String expectedMessage = "Invalid time. The time should be between 08:00:00 and 20:00:00";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

}