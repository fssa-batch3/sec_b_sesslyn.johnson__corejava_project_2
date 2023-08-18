package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Style;
import in.fssa.minimal.service.StyleService;

public class TestGetAllStyle {
	@Test
	public void testCreateStyleWithValidInput() {
		StyleService styleService = new StyleService();
		Style newStyle = new Style();
		newStyle.setName("Modern Architecture");

		assertDoesNotThrow(() -> {
			styleService.create(newStyle);
		});
	}

	@Test
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
	public void testUpdateStyle() throws ValidationException, PersistenceException {
		StyleService styleService = new StyleService();
		Style newStyle = new Style();
		newStyle.setName("Minimal Architecture");
		styleService.update(1, newStyle);
	}

	@Test
	public void testUpdateStyleWithNonExistingId() throws ValidationException, PersistenceException {
		StyleService styleService = new StyleService();
		Style newStyle = new Style();
		newStyle.setName("Mid-Century Style");
		Exception exception = assertThrows(ValidationException.class, () -> {
			styleService.update(10, newStyle);
		});
		String expectedMessage = "Style Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));

	}
	
}
