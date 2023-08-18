package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Style;
import in.fssa.minimal.service.StyleService;

@TestMethodOrder(OrderAnnotation.class)
public class TestGetAllStyle {

	@Test
	@Order(1)
	public void testCreateStyleWithValidInput() {
		StyleService styleService = new StyleService();
		Style newStyle = new Style();
		String generate = generateRandomString(8);
		newStyle.setName(generate);

		assertDoesNotThrow(() -> {
			styleService.create(newStyle);
		});
	}
	

	@Test
	@Order(2)
	public void testCreateStyleWithInValidInput() {
		StyleService styleService = new StyleService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			styleService.create(null);
		});
		String expectedMessage = "Style object can not be null";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(3)
	public void testCreateStyleWithNameNull() {
		StyleService styleService = new StyleService();
		Style newStyle = new Style();
		newStyle.setName(null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			styleService.create(newStyle);
		});
		String expectedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(4)
	public void testCreateStyleWithNameEmpty() {
		StyleService styleService = new StyleService();
		Style newStyle = new Style();
		newStyle.setName("");
		Exception exception = assertThrows(ValidationException.class, () -> {
			styleService.create(newStyle);
		});
		String expectedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(5)
	public void testCreateStyleWithNameExists() {
		StyleService styleService = new StyleService();
		Style newStyle = new Style();
		newStyle.setName("Shabby Chic");
		Exception exception = assertThrows(ValidationException.class, () -> {
			styleService.create(newStyle);
		});
		String expectedMessage = "Style Name already exists";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(6)
	public void testUpdateStyle() throws ValidationException, PersistenceException {
		StyleService styleService = new StyleService();
		Style newStyle = new Style();
		String generate = generateRandomString(8);
		newStyle.setName(generate);
		styleService.update(1, newStyle);
	}

	@Test
	@Order(7)
	public void testUpdateStyleWithNonExistingId() throws ValidationException, PersistenceException {
		StyleService styleService = new StyleService();
		Style newStyle = new Style();
		newStyle.setName("Bohemianism");
		Exception exception = assertThrows(ValidationException.class, () -> {
			styleService.update(5000, newStyle);
		});
		String expectedMessage = "Style Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);

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
