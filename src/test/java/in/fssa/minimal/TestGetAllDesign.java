package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Design;
import in.fssa.minimal.service.DesignService;

@TestMethodOrder(OrderAnnotation.class)
class TestGetAllDesign {
	@Test
	@Order(1)
	void updateDesign() throws ValidationException, PersistenceException {
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
	@Order(2)
	void updateSpecificFields() throws ValidationException, PersistenceException {
		DesignService designService = new DesignService();
		Design newDesign = new Design();
		newDesign.setLocation("Bangalore");
		designService.update(1, newDesign);
	}
	
	@Test
	@Order(3)
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
	
	@Test
	@Order(4)
	void getAllDesign() throws PersistenceException {
		DesignService designService = new DesignService();
		Set<Design> arr = designService.getAllDesign();
	}
	
	@Test
	@Order(5)
	void getDesignById() throws ValidationException, PersistenceException {
		DesignService designService = new DesignService();
		Design arr = designService.findByDesignId(1);
		System.out.println(arr);
	}

	@Test
	@Order(6)
	void testWithIdLessThanZero() {
		DesignService designService = new DesignService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			Design arr = designService.findByDesignId(-4);
		});
		String expectedMessage = "ID cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	@Order(7)
	void testWitNonExistingId() {
		DesignService designService = new DesignService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			Design arr = designService.findByDesignId(5000);
		});
		String expectedMessage = "Design Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	@Order(8)
	void getAllDesignByDesignerId() throws PersistenceException, ValidationException {
		DesignService designService = new DesignService();
		Set<Design>  designList = designService.findAllDesignsByDesignerId(2);
	}
	
	@Test
	@Order(9)
	void testGetAllDesignWithNonExistingId() throws PersistenceException, ValidationException {
		DesignService designService = new DesignService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			Set<Design>  designList = designService.findAllDesignsByDesignerId(4);
		});
		String expectedMessage = "Designers doesn't have any design yet";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);	
	}
	
	@Test
	@Order(10)
	void testGetAllDesignWithNonExistingDesigner() throws PersistenceException, ValidationException {
		DesignService designService = new DesignService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			Set<Design>  designList = designService.findAllDesignsByDesignerId(5000);
		});
		String expectedMessage = "Designer Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);	
	}
}
