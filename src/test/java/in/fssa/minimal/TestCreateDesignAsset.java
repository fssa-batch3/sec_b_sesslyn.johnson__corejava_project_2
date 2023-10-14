package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Asset;
import in.fssa.minimal.model.Design;
import in.fssa.minimal.model.DesignAsset;
import in.fssa.minimal.service.AssetService;
import in.fssa.minimal.service.DesignAssetService;

public class TestCreateDesignAsset {
	@Test
	@Order(1)
	void testCreateAssetWithValidInput() {
		AssetService assetService = new AssetService();
		Asset newAsset = new Asset();
		String generatedUrl = generateRandomUrl();
		newAsset.setAssetsUrl(generatedUrl);

		assertDoesNotThrow(() -> {
			assetService.createAsset(newAsset); 
		}); 
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
	@Order(2)
	void testCreateAssetWithInValidInput() {
		AssetService assetService = new AssetService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			assetService.createAsset(null);
		});
		String expectedMessage = "Asset object can not be null";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(3)
	void testCreateDesignAssetWithValidInput() {
	    DesignAssetService designAssetService = new DesignAssetService();
	    DesignAsset newDesignAsset = new DesignAsset();
	    Design newDesign = new Design();
	    newDesign.setName("Modern 4 Bhk Home");
	    newDesign.setDescription("Customer Name: Mr. Johnson & Mrs. Ruby \r\n" + "Apartment Size: 4 BHK, 3200 Sq Ft\r\n"
	            + "Project Value:  35-38 Lakhs\r\n" + "Project Manager: Muzammil\r\n"
	            + "Design Description: Enveloped in the grace of contemporary bliss, this modern 4BHK home interiors of Johnson and Ruby offers some major design goals. The house is the true epitome of elegance and warmth, intertwining warmth and grace. The hardware used gives a royal metallic touch to all the rooms, making them look regal. The accessories scattered throughout the space club everything together, radiating charm and opulence.\r\n");
	    newDesign.setLocation("Chennai");
	    newDesign.setStyleId(2);
	
	    Asset newAsset = new Asset();
	    String generatedUrl = generateRandomUrl();
	    newAsset.setAssetsUrl(generatedUrl);
	   int designerId = 2;
	 
	    assertDoesNotThrow(() -> {
	        designAssetService.createDesignAsset(newDesign, newAsset,designerId);
	    });
	}
	
	@Test
	@Order(4)
	void testCreateDesignAssetWithInValidInput() {
	    DesignAssetService designAssetService = new DesignAssetService();
	    Exception exception = assertThrows(ValidationException.class, () -> {
	    	 designAssetService.createDesignAsset(null, null,0);
		});
		String expectedMessage = "Design object and Asset Object cannot be null";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}


	@Test
	@Order(5)
	void testCreateDesignAssetWithDesignerIdInvalid() {
	    DesignAssetService designAssetService = new DesignAssetService();
	    DesignAsset newDesignAsset = new DesignAsset();
	    Design newDesign = new Design();
	    newDesign.setName("Modern 4 Bhk Home");
	    newDesign.setDescription("Customer Name: Mr. Johnson & Mrs. Ruby \r\n" + "Apartment Size: 4 BHK, 3200 Sq Ft\r\n"
	            + "Project Value:  35-38 Lakhs\r\n" + "Project Manager: Muzammil\r\n"
	            + "Design Description:Enveloped in the grace of contemporary bliss, this modern 4BHK home interiors of Johnson and Ruby offers some major design goals. The house is the true epitome of elegance and warmth, intertwining warmth and grace. The hardware used gives a royal metallic touch to all the rooms, making them look regal. The accessories scattered throughout the space club everything together, radiating charm and opulence.\r\n");
	    newDesign.setLocation("Chennai");
	    newDesign.setStyleId(2);
	
	    Asset newAsset = new Asset();
	    String generatedUrl = generateRandomUrl();
	    newAsset.setAssetsUrl(generatedUrl);
	   int designerId = 3;
	 
	   Exception exception = assertThrows(ValidationException.class, () -> {
	    	 designAssetService.createDesignAsset(newDesign,newAsset,designerId);
		});
		String expectedMessage = "Designer Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	@Order(6)
	void testCreateDesignAssetWithDesignerIdNegative() {
	    DesignAssetService designAssetService = new DesignAssetService();
	    DesignAsset newDesignAsset = new DesignAsset();
	    Design newDesign = new Design();
	    newDesign.setName("Modern 4 Bhk Home");
	    newDesign.setDescription("Customer Name: Mr. Johnson & Mrs. Ruby \r\n" + "Apartment Size: 4 BHK, 3200 Sq Ft\r\n"
	            + "Project Value:  35-38 Lakhs\r\n" + "Project Manager: Muzammil\r\n"
	            + "Design Description:Enveloped in the grace of contemporary bliss, this modern 4BHK home interiors of Johnson and Ruby offers some major design goals. The house is the true epitome of elegance and warmth, intertwining warmth and grace. The hardware used gives a royal metallic touch to all the rooms, making them look regal. The accessories scattered throughout the space club everything together, radiating charm and opulence.\r\n");
	    newDesign.setLocation("Chennai");
	    newDesign.setStyleId(2);
	
	    Asset newAsset = new Asset();
	    String generatedUrl = generateRandomUrl();
	    newAsset.setAssetsUrl(generatedUrl);
	   int designerId = -3;
	 
	   Exception exception = assertThrows(ValidationException.class, () -> {
	    	 designAssetService.createDesignAsset(newDesign,newAsset,designerId);
		});
		String expectedMessage = "Designer Id cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	

}
