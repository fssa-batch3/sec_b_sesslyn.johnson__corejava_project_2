package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Design;
import in.fssa.minimal.service.DesignService;

@TestMethodOrder(OrderAnnotation.class)
class TestReadDesign {
	 @Test
	    @Order(1)
	    void getAllDesign() {
	        assertDoesNotThrow(() -> {
	            DesignService designService = new DesignService();
	            Set<Design> arr = designService.getAllDesign();
	        });
	    }
	    
	    @Test
	    @Order(2)
	    void getDesignById() {
	        assertDoesNotThrow(() -> {
	            DesignService designService = new DesignService();
	            Design arr = designService.findByDesignId(1);
	            System.out.println(arr);
	        });
	    }

	@Test
	@Order(3)
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
	@Order(4)
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
 @Order(5)
 void getAllDesignByDesignerId() {
     assertDoesNotThrow(() -> {
         DesignService designService = new DesignService();
         Set<Design> designList = designService.findAllDesignsByDesignerId(2);
     });
 }
 
 @Test
 @Order(6)
 void testGetAllDesignWithNonExistingId() {
     DesignService designService = new DesignService();
     Exception exception = assertThrows(ValidationException.class, () -> {
         Set<Design> designList = designService.findAllDesignsByDesignerId(4);
     });
     String expectedMessage = "Designers doesn't have any design yet";
     String actualMessage = exception.getMessage();

     assertEquals(expectedMessage, actualMessage);    
 }
 
	@Test
	@Order(7)
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
