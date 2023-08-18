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
		newDesign.setDescription("Customer Name: Madhu & Naveen\r\n"
				+ "Apartment Size: 2 BHK\r\n"
				+ "Project Value: 9.02 lacs\r\n"
				+ "Project Manager: Nanda Kumar\r\n"
				+ "Welcome to Bonito Designs! Today, we will take you on a tour of Madhu & Naveen’s Contemporary 2bhk interior design, which is a world of zen and whimsy, where tranquillity meets playfulness.  Are you also looking for a home that embodies the comfort and beauty that you need in your home? Look no further than Bonito Designs!");
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
		designService.update(1, newDesign);
	}
	
	@Test
	public void testUpdateNonExistingId() throws ValidationException, PersistenceException {	
		DesignService designService = new DesignService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			Design newDesign = new Design();
			newDesign.setLocation("Bangalore");
			designService.update(20, newDesign);
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
			Design arr = designService.findByDesignId(20);
		});
		String expectedMessage = "Design Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void getAllDesignByDesignerId() throws PersistenceException, ValidationException {
		DesignService designService = new DesignService();
		Set<Design>  designList = designService.findAllDesignsByDesignerId(2);
	}
	
	@Test
	public void testGetAllDesignWithNonExistingId() throws PersistenceException, ValidationException {
		DesignService designService = new DesignService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			Set<Design>  designList = designService.findAllDesignsByDesignerId(4);
		});
		String expectedMessage = "Designers doesn't have any design yet";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));	
	}
	
	@Test
	public void testGetAllDesignWithNonExistingDesigner() throws PersistenceException, ValidationException {
		DesignService designService = new DesignService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			Set<Design>  designList = designService.findAllDesignsByDesignerId(7);
		});
		String expectedMessage = "Designer Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));	
	}
	
}
