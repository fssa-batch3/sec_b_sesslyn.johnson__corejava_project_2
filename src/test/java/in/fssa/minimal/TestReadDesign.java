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
import in.fssa.minimal.model.Style;
import in.fssa.minimal.service.DesignService;
import in.fssa.minimal.service.StyleService;

@TestMethodOrder(OrderAnnotation.class)
class TestReadDesign {
	 @Test
	    @Order(1)
	    void testGetAllDesign() {
	        assertDoesNotThrow(() -> {
	            DesignService designService = new DesignService();
	            Set<Design> arr = designService.getAllDesign();
	        });
	    } 
	    
	    @Test 
	    @Order(2)
	    void testGetDesignById() {
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
		String expectedMessage = "Design Id cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	@Order(4)
	void testWithNonExistingId() {
		DesignService designService = new DesignService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			Design arr = designService.findByDesignId(6000);
		});
		String expectedMessage = "Design Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	@Order(5)
	void testWithValidStyleName() {
		 assertDoesNotThrow(() -> {
	            StyleService styleService = new StyleService();
	             int styleId = styleService.findStyleId("Minimalism");
	            System.out.println(styleId);
	        });
	    }
	
	@Test
	@Order(6)
	void testWithInValidStyleName() {
		StyleService styleService = new StyleService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			  int styleId = styleService.findStyleId("Rustic Architecture");
		});
		String expectedMessage = "Style Name doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	@Test
    @Order(7)
    void testGetAllStyle() {
        assertDoesNotThrow(() -> {
        	StyleService styleService = new StyleService();
            Set<Style> arr = styleService.getAllStyle();
            System.out.println(arr);
        });
    }
			
}
