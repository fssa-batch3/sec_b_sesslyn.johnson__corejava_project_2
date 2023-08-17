package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Set;
import org.junit.jupiter.api.Test;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Design;
import in.fssa.minimal.service.DesignService;

public class TestGetAllDesign {
	@Test
	public void testUpdateDesign() throws ValidationException, PersistenceException {
		DesignService designService = new DesignService();
		Design newDesign = new Design();
		newDesign.setName("Modern 3.5 Bhk Home");
		newDesign.setDescription("Customer Name: Mr. Karan Kunjur & Mrs. Ramya Kiran\r\n"
				+ "Apartment Size: 3.5 BHK, 2800 Sq Ft\r\n"
				+ "Project Value:  33-35 Lakhs\r\n"
				+ "Project Manager: Muzammil\r\n"
				+ "Enveloped in the grace of contemporary bliss, this modern 3BHK home interiors of Kiran and Ramya offers some major design goals. The house is the true epitome of elegance and warmth, intertwining warmth and grace. The hardware used gives a royal metallic touch to all the rooms, making them look regal. The accessories scattered throughout the space club everything together, radiating charm and opulence.");
		newDesign.setLocation("Chennai");
		newDesign.setStyleId(1);
		newDesign.setCreatedBy(2);
		designService.update(1, newDesign);
	}
	
	@Test
	public void testUpdateSpecificFields() throws ValidationException, PersistenceException {
		DesignService designService = new DesignService();
		Design newDesign = new Design();
		newDesign.setLocation("Bangalore");
		newDesign.setStyleId(1);
		newDesign.setCreatedBy(4);
		designService.update(1, newDesign);
	}
	
	@Test
	public void testUpdateNonExistingId() throws ValidationException, PersistenceException {	
		DesignService designService = new DesignService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			Design newDesign = new Design();
			newDesign.setLocation("Bangalore");
			newDesign.setStyleId(1);
			newDesign.setCreatedBy(4);
			designService.update(4, newDesign);
		});
		String expectedMessage = "Design Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));	
	}
	
	@Test
	public void getAllDesign() throws PersistenceException {
		DesignService designService = new DesignService();
		Set<Design> arr = designService.getAllDesign();
	}
	
	@Test
	public void getDesignById() throws ValidationException, PersistenceException {
		DesignService designService = new DesignService();
		Design arr = designService.findByDesignId(1);
		System.out.println(arr);
	}

	
	@Test
	public void testWithIdLessThanZero() {
		DesignService designService = new DesignService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			Design arr = designService.findByDesignId(-4);
		});
		String expectedMessage = "Id can't be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testWitNonExistingId() {
		DesignService designService = new DesignService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			Design arr = designService.findByDesignId(4);
		});
		String expectedMessage = "Design Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void getAllDesignByDesignerId() throws PersistenceException, ValidationException {
		DesignService designService = new DesignService();
		Set<Design>  designList = designService.findAllDesignsByDesignerId(4);
	}
	
	@Test
	public void testGetAllDesignWithNonExistingId() throws PersistenceException, ValidationException {
		DesignService designService = new DesignService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			Set<Design>  designList = designService.findAllDesignsByDesignerId(2);
		});
		String expectedMessage = "Designers doesn't have any design yet";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));	
	}
	
	@Test
	public void testGetAllDesignWithNonExistingDesigner() throws PersistenceException, ValidationException {
		DesignService designService = new DesignService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			Set<Design>  designList = designService.findAllDesignsByDesignerId(3);
		});
		String expectedMessage = "Designer Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));	
	}
	
}
