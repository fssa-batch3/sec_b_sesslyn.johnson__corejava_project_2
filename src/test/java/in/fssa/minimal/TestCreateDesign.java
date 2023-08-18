package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Design;
import in.fssa.minimal.service.DesignService;

public class TestCreateDesign {
	@Test
	public void testCreateUserWithValidInput() {
		DesignService designService = new DesignService();
		Design newDesign = new Design();
		newDesign.setName("Modern 4 Bhk Home");
		newDesign.setDescription("Customer Name: Mr. Johnson & Mrs. Ruby \r\n"
				+ "Apartment Size: 4 BHK, 3200 Sq Ft\r\n"
				+ "Project Value:  35-38 Lakhs\r\n"
				+ "Project Manager: Muzammil\r\n"
				+ "Enveloped in the grace of contemporary bliss, this modern 4BHK home interiors of Johnson and Ruby offers some major design goals. The house is the true epitome of elegance and warmth, intertwining warmth and grace. The hardware used gives a royal metallic touch to all the rooms, making them look regal. The accessories scattered throughout the space club everything together, radiating charm and opulence.\r\n");
		newDesign.setLocation("Chennai");
		newDesign.setStyleId(4);
		newDesign.setCreatedBy(4);

		assertDoesNotThrow(() -> {
			designService.create(newDesign);
		});
	}

	@Test
	public void testCreateDesignWithInvalidInput() {
		DesignService designService = new DesignService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			designService.create(null);
		});
		String expectedMessage = "Design object can not be null";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateDesignWithNameNull() {
		DesignService designService = new DesignService();
		Design newDesign = new Design();
		newDesign.setName(null);
		newDesign.setDescription("Customer Name: Mr. Kiran Kunjur & Mrs. Ramya Kiran\r\n");
		newDesign.setLocation("Chennai");
		newDesign.setStyleId(2);
		newDesign.setCreatedBy(2);

		Exception exception = assertThrows(Exception.class, () -> {
			designService.create(newDesign);
		});
		String expectedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateDesignWithNameEmpty() {
		DesignService designService = new DesignService();
		Design newDesign = new Design();
		newDesign.setName("");
		newDesign.setDescription("Customer Name: Mr. Kiran Kunjur & Mrs. Ramya Kiran\r\n");
        newDesign.setLocation("Chennai");
		newDesign.setStyleId(2);
		newDesign.setCreatedBy(2);

		Exception exception = assertThrows(Exception.class, () -> {
			designService.create(newDesign);
		});
		String expectedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateDesignWithDescriptionNull() {
		DesignService designService = new DesignService();
		Design newDesign = new Design();
		newDesign.setName("Modern 3BHK Home");
		newDesign.setDescription(null);
		newDesign.setLocation("Chennai");
		newDesign.setStyleId(2);
		newDesign.setCreatedBy(2);

		Exception exception = assertThrows(Exception.class, () -> {
			designService.create(newDesign);
		});
		String expectedMessage = "Description cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateDesignWithDescriptionEmpty() {
		DesignService designService = new DesignService();
		Design newDesign = new Design();
		newDesign.setName("Modern 3BHK Home");
		newDesign.setDescription("");
		newDesign.setLocation("Chennai");
		newDesign.setStyleId(2);
		newDesign.setCreatedBy(2);

		Exception exception = assertThrows(Exception.class, () -> {
			designService.create(newDesign);
		});
		String expectedMessage = "Description cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateDesignWithDescriptionLessCharacters() {
		DesignService designService = new DesignService();
		Design newDesign = new Design();
		newDesign.setName("Modern 3BHK Home");
		newDesign.setDescription("Modern 3BHK Home");
		newDesign.setLocation("Chennai");
		newDesign.setStyleId(2);
		newDesign.setCreatedBy(2);

		Exception exception = assertThrows(Exception.class, () -> {
			designService.create(newDesign);
		});
		String expectedMessage = "Description doesn't match the length";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateDesignWithLocationNull() {
		DesignService designService = new DesignService();
		Design newDesign = new Design();
		newDesign.setName("Modern 3BHK Home");
		newDesign.setDescription("Customer Name: Mr. Kiran Kunjur & Mrs. Ramya Kiran\r\n");
		newDesign.setLocation(null);
		newDesign.setStyleId(2);
		newDesign.setCreatedBy(2);

		Exception exception = assertThrows(Exception.class, () -> {
			designService.create(newDesign);
		});
		String expectedMessage = "City name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateDesignWithLocationEmpty() {
		DesignService designService = new DesignService();
		Design newDesign = new Design();
		newDesign.setName("Modern 3BHK Home");
		newDesign.setDescription("Customer Name: Mr. Kiran Kunjur & Mrs. Ramya Kiran\r\n");
		newDesign.setLocation("");
		newDesign.setStyleId(2);
		newDesign.setCreatedBy(2);

		Exception exception = assertThrows(Exception.class, () -> {
			designService.create(newDesign);
		});
		String expectedMessage = "City name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateDesignWithLocationPattern() {
		DesignService designService = new DesignService();
		Design newDesign = new Design();
		newDesign.setName("Modern 3BHK Home");
		newDesign.setDescription("Customer Name: Mr. Kiran Kunjur & Mrs. Ramya Kiran\r\n");
		newDesign.setLocation("Chennai1234");
		newDesign.setStyleId(-2);
		newDesign.setCreatedBy(2);

		Exception exception = assertThrows(Exception.class, () -> {
			designService.create(newDesign);
		});
		String expectedMessage = "City name doesn't match the pattern";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateUserWithIdLessThanZero() {
		DesignService designService = new DesignService();
		Design newDesign = new Design();
		newDesign.setName("Modern 3Bhk Home");
		newDesign.setDescription("Customer Name: Mr. Kiran Kunjur & Mrs. Ramya Kiran");
		newDesign.setLocation("Chennai");
		newDesign.setStyleId(0);
		newDesign.setCreatedBy(3);

		Exception exception = assertThrows(Exception.class, () -> {
			designService.create(newDesign);
		});
		String expectedMessage = "Id can't be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateUserWithNonExistingStyleId() {
		DesignService designService = new DesignService();
		Design newDesign = new Design();
		newDesign.setName("Modern 3Bhk Home");
		newDesign.setDescription("Customer Name: Mr. Kiran Kunjur & Mrs. Ramya Kiran");
		newDesign.setLocation("Chennai");
		newDesign.setStyleId(20);
		newDesign.setCreatedBy(2);

		Exception exception = assertThrows(Exception.class, () -> {
			designService.create(newDesign);
		});
		String expectedMessage = "Style Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateUserWithNonExistingDesigner() {
		DesignService designService = new DesignService();
		Design newDesign = new Design();
		newDesign.setName("Modern 3Bhk Home");
		newDesign.setDescription("Customer Name: Mr. Kiran Kunjur & Mrs. Ramya Kiran");
		newDesign.setLocation("Chennai");
		newDesign.setStyleId(1);
		newDesign.setCreatedBy(3);

		Exception exception = assertThrows(Exception.class, () -> {
			designService.create(newDesign);
		});
		String expectedMessage = "Designer Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

}
