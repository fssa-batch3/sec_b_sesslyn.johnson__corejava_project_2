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
import in.fssa.minimal.model.Asset;
import in.fssa.minimal.model.Design;
import in.fssa.minimal.model.DesignAsset;
import in.fssa.minimal.service.AssetService;
import in.fssa.minimal.service.DesignAssetService;
@TestMethodOrder(OrderAnnotation.class)
public class TestUpdateDesignAsset {
	@Test
    @Order(1)
    void testUpdateAsset() {
        assertDoesNotThrow(() -> {
            AssetService assetService = new AssetService();
            Asset newAsset = new Asset();
            String generate = generateRandomUrl();
            newAsset.setAssetsUrl(generate);
            assetService.updateAsset(1, newAsset);
        });
    }

	@Test
	@Order(2)
	void testUpdateAssetWithExistingUrl() throws ValidationException, PersistenceException {
		AssetService assetService = new AssetService();
		Asset newAsset = new Asset();
		newAsset.setAssetsUrl("https://youtu.be/DFgL3URDOr4");
		Exception exception = assertThrows(ValidationException.class, () -> {
			assetService.updateAsset(1, newAsset);
		});
		String expectedMessage = "Asset Url already exists";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(3)
	void testUpdateAssetWithNonExistingId() throws ValidationException, PersistenceException {
		AssetService assetService = new AssetService();
		Asset newAsset = new Asset();
		newAsset.setAssetsUrl("https://youtu.be/e7IF-MbBkl8");
		Exception exception = assertThrows(ValidationException.class, () -> {
			assetService.updateAsset(5000, newAsset);
		});
		String expectedMessage = "Asset Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	

	private String generateRandomUrl() {
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder randomUrl = new StringBuilder("https://youtu.be/");

		Random random = new Random();

		for (int i = 0; i < 11; i++) { // Adjust the length of the ID as needed
			int index = random.nextInt(characters.length());
			randomUrl.append(characters.charAt(index));
		}

		return randomUrl.toString();
	}
	
	@Test
	@Order(4)
	void testCreateDesignAssetWithValidInput() {
	    DesignAssetService designAssetService = new DesignAssetService();
	    DesignAsset newDesignAsset = new DesignAsset();
	    Design updateDesign = new Design();
	    updateDesign.setName("Modern 4 Bhk Home");
	    updateDesign.setDescription("Customer Name: Mr. Johnson & Mrs. Ruby \r\n" + "Apartment Size: 4 BHK, 3200 Sq Ft\r\n"
	            + "Project Value:  35-38 Lakhs\r\n" + "Project Manager: Muzammil\r\n"
	            + "Enveloped in the grace of contemporary bliss, this modern 4BHK home interiors of Johnson and Ruby offers some major design goals. The house is the true epitome of elegance and warmth, intertwining warmth and grace. The hardware used gives a royal metallic touch to all the rooms, making them look regal. The accessories scattered throughout the space club everything together, radiating charm and opulence.\r\n");
	    updateDesign.setLocation("Chennai");
	    updateDesign.setStyleId(2);
	    updateDesign.setCreatedBy(2);
	    
	    Asset updateAsset = new Asset();
	    String generatedUrl = generateRandomUrl();
	    updateAsset.setAssetsUrl(generatedUrl);
	   
	 
	    assertDoesNotThrow(() -> {
	        designAssetService.updateDesignAsset(2,updateDesign, updateAsset);
	    });
	}
	
	@Test
	@Order(5)
	void testUpdateDesignAsset() {
	    assertDoesNotThrow(() -> {
	        DesignAssetService designAssetService = new DesignAssetService();
	        
	        DesignAsset newDesignAsset = new DesignAsset();
	        Design updateDesign = new Design();
	        updateDesign.setName("Modern 2 Bhk Home");
	        updateDesign.setStyleId(1);
	        updateDesign.setCreatedBy(4);
	        
	        Asset updateAsset = new Asset();
	        String generatedUrl = generateRandomUrl();
	        updateAsset.setAssetsUrl(generatedUrl);
	        
	        assertDoesNotThrow(() -> {
	            designAssetService.updateDesignAsset(2, updateDesign, updateAsset);
	        });
	    });
	}

	@Test
	@Order(6)
	void testUpdateDesign() {
	    assertDoesNotThrow(() -> {
	        DesignAssetService designAssetService = new DesignAssetService();
	        
	        DesignAsset newDesignAsset = new DesignAsset();
	        Design updateDesign = new Design();
	        updateDesign.setName("Modern 2 Bhk Home");
	        updateDesign.setStyleId(1);
	        updateDesign.setCreatedBy(4);
	        
	     
	        assertDoesNotThrow(() -> {
	            designAssetService.updateDesignAsset(2, updateDesign, null);
	        });
	    });
	}

	@Test
	@Order(7)
	void testUpdateAssetObject() {
	    assertDoesNotThrow(() -> {
	        DesignAssetService designAssetService = new DesignAssetService();
	        
	        Asset updateAsset = new Asset();
	        String generatedUrl = generateRandomUrl();
	        updateAsset.setAssetsUrl(generatedUrl);
	        
	     
	        assertDoesNotThrow(() -> {
	            designAssetService.updateDesignAsset(2, null, updateAsset);
	        });
	    });
	}


	@Test
	@Order(6)
	void testUpdateSpecificFields() {
		 DesignAssetService designAssetService = new DesignAssetService();
		    Exception exception = assertThrows(ValidationException.class, () -> {
		    	 designAssetService.updateDesignAsset(3,null, null);
			});
			String expectedMessage = "Design object and Asset Object cannot be null";
			String actualMessage = exception.getMessage();

			assertEquals(expectedMessage, actualMessage);
	}

	
}
