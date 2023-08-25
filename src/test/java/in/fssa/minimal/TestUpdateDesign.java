package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Design;
import in.fssa.minimal.model.Style;
import in.fssa.minimal.service.DesignService;
import in.fssa.minimal.service.StyleService;

@TestMethodOrder(OrderAnnotation.class)
class TestUpdateDesign {
	@Test
	@Order(1)
	void testUpdateStyle() {
		assertDoesNotThrow(() -> {
			StyleService styleService = new StyleService();
			Style newStyle = new Style();
			String generatedName = generateRandomString(8);
			newStyle.setName(generatedName);
			styleService.update(1, newStyle);
		});
	}

	@Test
	@Order(2)
	void testUpdateStyleWithNonExistingId() throws ValidationException, PersistenceException {
		StyleService styleService = new StyleService();
		Style newStyle = new Style();
		newStyle.setName("Bohemianism");
		Exception exception = assertThrows(ValidationException.class, () -> {
			styleService.update(5000, newStyle);
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

	@Test
	@Order(3)
	void updateDesign() {
		assertDoesNotThrow(() -> {
			DesignService designService = new DesignService();
			Design newDesign = new Design();
			newDesign.setName("Modern 3.5 Bhk Home");
			newDesign.setDescription("Customer Name: Madhu & Naveen\r\n" + "Apartment Size: 2 BHK\r\n"
					+ "Project Value: 9.02 lacs\r\n" + "Project Manager: Nanda Kumar\r\n"
					+ "Welcome to Bonito Designs! Today, we will take you on a tour of Madhu & Naveen’s Contemporary 2bhk interior design, which is a world of zen and whimsy, where tranquillity meets playfulness.  Are you also looking for a home that embodies the comfort and beauty that you need in your home? Look no further than Bonito Designs!");
			newDesign.setLocation("Chennai");
			newDesign.setStyleId(1);
			newDesign.setCreatedBy(2);
			designService.update(1, newDesign);
		});
	}

	@Test
	@Order(4)
	void updateSpecificFields() {
		assertDoesNotThrow(() -> {
			DesignService designService = new DesignService();
			Design newDesign = new Design();
			newDesign.setLocation("Bangalore");
			designService.update(1, newDesign);
		});
	}

	@Test
	@Order(5)
	void updateNonExistingId() throws ValidationException, PersistenceException {
		DesignService designService = new DesignService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			Design newDesign = new Design();
			newDesign.setLocation("Bangalore");
			designService.update(5000, newDesign);
		});
		String expectedMessage = "Design Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
}