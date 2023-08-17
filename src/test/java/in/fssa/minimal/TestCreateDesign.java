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
		newDesign.setName("Modern 2Bhk Home");
		newDesign.setDescription("Customer Name: Sonam & Ashish Gupta \r\n"
				+ "Apartment Size: 1200 sqft.\r\n"
				+ "Project Value: 10.8 lac\r\n"
				+ "Project Manager: Irfan Khan\r\n"
				+ "Discover the aesthetics of this unique home interior which is filled with vibrancy and all splendors. Dipped in the essence of Art Déco style, the space speaks a lot about class and comfort. Art Deco style has a fluidity that translates opulence into reality. This abode is a perfect example of the style. The color palette in the entire space is a combination of subtlety and warmth. Grooved wall paneling, biophilic upholstery, abstract artwork, buddha statue- we just cannot miss these elements!");
		newDesign.setLocation("Chennai");
		newDesign.setStyleId(1);
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
		newDesign.setStyleId(6);
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
