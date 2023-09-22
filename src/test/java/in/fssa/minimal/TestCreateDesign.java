package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Design;
import in.fssa.minimal.model.Style;
import in.fssa.minimal.service.DesignService;
import in.fssa.minimal.service.StyleService;

@TestMethodOrder(OrderAnnotation.class)
class TestCreateDesign {
	// Style
	@Test
	@Order(1)
	void testCreateStyleWithValidInput() {
		StyleService styleService = new StyleService();
		Style newStyle = new Style();
		String generatedName = generateRandomString(8);
		newStyle.setName(generatedName);

		assertDoesNotThrow(() -> {
			styleService.createStyle(newStyle);
		});
	}

	@Test
	@Order(2)
	void testCreateStyleWithInvalidInput() {
		StyleService styleService = new StyleService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			styleService.createStyle(null);
		});
		String expectedMessage = "Style object cannot be null";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(3)
	void testCreateStyleWithNameExists() {
		StyleService styleService = new StyleService();
		Style newStyle = new Style();
		newStyle.setName("Minimalism");
		Exception exception = assertThrows(ValidationException.class, () -> {
			styleService.createStyle(newStyle);
		});
		String expectedMessage = "Style Name already exists";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	// Design
	@Order(4)
	@Test
	void testCreateDesignWithValidInput() {
		DesignService designService = new DesignService();
		Design newDesign = new Design();
		newDesign.setName("Modern 4 Bhk Home");
		newDesign.setDescription("Customer Name: Mr. Johnson & Mrs. Ruby \r\n" + "Apartment Size: 4 BHK, 3200 Sq Ft\r\n"
				+ "Project Value:  35-38 Lakhs\r\n" + "Project Manager: Muzammil\r\n"
				+ "Design Description: Enveloped in the grace of contemporary bliss, this modern 4BHK home interiors of "
				+ "Johnson and Ruby offers some major design goals. The house is the true epitome of elegance and"
				+ " warmth, intertwining warmth and grace. The hardware used gives a royal metallic touch to all the rooms,"
				+ " making them look regal. The accessories scattered throughout the space club everything together, "
				+ "radiating charm and opulence.\r\n");
		newDesign.setLocation("Chennai");
		newDesign.setStyleId(2);

		assertDoesNotThrow(() -> {
			designService.createDesign(newDesign);
		});
	}

	@Order(5)
	@Test
	void testCreateDesignWithInvalidInput() {
		DesignService designService = new DesignService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			designService.createDesign(null);
		});
		String expectedMessage = "Design object cannot be null";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Order(6)
	@Test
	void testCreateDesignWithNameNull() {
		DesignService designService = new DesignService();
		Design newDesign = new Design();
		newDesign.setName(null);
		newDesign.setDescription("Customer Name: Mr. Kiran Kunjur & Mrs. Ramya Kiran\r\n");
		newDesign.setLocation("Chennai");
		newDesign.setStyleId(2);

		Exception exception = assertThrows(Exception.class, () -> {
			designService.createDesign(newDesign);
		});
		String expectedMessage = "Design Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Order(7)
	@Test
	void testCreateDesignWithNameEmpty() {
		DesignService designService = new DesignService();
		Design newDesign = new Design();
		newDesign.setName("");
		newDesign.setDescription("Customer Name: Mr. Kiran Kunjur & Mrs. Ramya Kiran\r\n");
		newDesign.setLocation("Chennai");
		newDesign.setStyleId(2);

		Exception exception = assertThrows(Exception.class, () -> {
			designService.createDesign(newDesign);
		});
		String expectedMessage = "Design Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Order(8)
	@Test
	void testCreateDesignWithDescriptionNull() {
		DesignService designService = new DesignService();
		Design newDesign = new Design();
		newDesign.setName("Modern 3BHK Home");
		newDesign.setDescription(null);
		newDesign.setLocation("Chennai");
		newDesign.setStyleId(2);

		Exception exception = assertThrows(Exception.class, () -> {
			designService.createDesign(newDesign);
		});
		String expectedMessage = "Description cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Order(9)
	@Test
	void testCreateDesignWithDescriptionEmpty() {
		DesignService designService = new DesignService();
		Design newDesign = new Design();
		newDesign.setName("Modern 3BHK Home");
		newDesign.setDescription("");
		newDesign.setLocation("Chennai");
		newDesign.setStyleId(2);

		Exception exception = assertThrows(Exception.class, () -> {
			designService.createDesign(newDesign);
		});
		String expectedMessage = "Description cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Order(10)
	@Test
	void testCreateDesignWithDescriptionLessCharacters() {
		DesignService designService = new DesignService();
		Design newDesign = new Design();
		newDesign.setName("Modern 3BHK Home");
		newDesign.setDescription("Modern 3BHK Home");
		newDesign.setLocation("Chennai");
		newDesign.setStyleId(2);

		Exception exception = assertThrows(Exception.class, () -> {
			designService.createDesign(newDesign);
		});
		String expectedMessage = "Description should be at least 30 characters long";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Order(8)
	@Test
	void testCreateDesignWithLocationNull() {
		DesignService designService = new DesignService();
		Design newDesign = new Design();
		newDesign.setName("Modern 3BHK Home");
		newDesign.setDescription("Customer Name: Mr. Kiran Kunjur & Mrs. Ramya Kiran\r\n");
		newDesign.setLocation(null);
		newDesign.setStyleId(2);

		Exception exception = assertThrows(Exception.class, () -> {
			designService.createDesign(newDesign);
		});
		String expectedMessage = "City name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Order(9)
	@Test
	void testCreateDesignWithLocationEmpty() {
		DesignService designService = new DesignService();
		Design newDesign = new Design();
		newDesign.setName("Modern 3BHK Home");
		newDesign.setDescription("Customer Name: Mr. Kiran Kunjur & Mrs. Ramya Kiran\r\n");
		newDesign.setLocation("");
		newDesign.setStyleId(2);

		Exception exception = assertThrows(Exception.class, () -> {
			designService.createDesign(newDesign);
		});
		String expectedMessage = "City name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Order(10)
	@Test
	void testCreateDesignWithLocationPattern() {
		DesignService designService = new DesignService();
		Design newDesign = new Design();
		newDesign.setName("Modern 3BHK Home");
		newDesign.setDescription("Customer Name: Mr. Kiran Kunjur & Mrs. Ramya Kiran\r\n");
		newDesign.setLocation("Chennai1234");
		newDesign.setStyleId(-2);

		Exception exception = assertThrows(Exception.class, () -> {
			designService.createDesign(newDesign);
		});
		String expectedMessage = "City Name should only contain alphabetic characters";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Order(11)
	@Test
	void testCreateDesignWithStyleIdLessThanZero() {
		DesignService designService = new DesignService();
		Design newDesign = new Design();
		newDesign.setName("Modern 3Bhk Home");
		newDesign.setDescription("Customer Name: Mr. Kiran Kunjur & Mrs. Ramya Kiran");
		newDesign.setLocation("Chennai");
		newDesign.setStyleId(0);

		Exception exception = assertThrows(Exception.class, () -> {
			designService.createDesign(newDesign);
		});
		String expectedMessage = "Style Id cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Order(12)
	@Test
	void testCreateDesignWithNonExistingStyleId() {
		DesignService designService = new DesignService();
		Design newDesign = new Design();
		newDesign.setName("Modern 3Bhk Home");
		newDesign.setDescription("Customer Name: Mr. Kiran Kunjur & Mrs. Ramya Kiran");
		newDesign.setLocation("Chennai");
		newDesign.setStyleId(5000);

		Exception exception = assertThrows(Exception.class, () -> {
			designService.createDesign(newDesign);
		});
		String expectedMessage = "Style Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}


	private String generateRandomString(int length) {
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder randomString = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < length; i++) {
			int index = random.nextInt(characters.length());
			randomString.append(characters.charAt(index));
		}

		return randomString.toString();
	}

}
