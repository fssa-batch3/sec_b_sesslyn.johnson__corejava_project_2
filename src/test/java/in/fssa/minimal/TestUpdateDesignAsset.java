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
import in.fssa.minimal.model.DesignAsset;
import in.fssa.minimal.service.AssetService;
import in.fssa.minimal.service.DesignAssetService;
@TestMethodOrder(OrderAnnotation.class)
public class TestUpdateDesignAsset {
	@Test
    @Order(1)
    void updateAsset() {
        assertDoesNotThrow(() -> {
            AssetService assetService = new AssetService();
            Asset newAsset = new Asset();
            String generate = generateRandomUrl();
            newAsset.setAssetsUrl(generate);
            assetService.update(1, newAsset);
        });
    }

	@Test
	@Order(2)
	void updateAssetWithExistingUrl() throws ValidationException, PersistenceException {
		AssetService assetService = new AssetService();
		Asset newAsset = new Asset();
		newAsset.setAssetsUrl("https://youtu.be/DFgL3URDOr4");
		Exception exception = assertThrows(ValidationException.class, () -> {
			assetService.update(1, newAsset);
		});
		String expectedMessage = "Asset Url already exists";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(3)
	void updateAssetWithNonExistingId() throws ValidationException, PersistenceException {
		AssetService assetService = new AssetService();
		Asset newAsset = new Asset();
		newAsset.setAssetsUrl("https://youtu.be/e7IF-MbBkl8");
		Exception exception = assertThrows(ValidationException.class, () -> {
			assetService.update(5000, newAsset);
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
	void updateDesignAsset() {
		assertDoesNotThrow(() -> {
			DesignAssetService designAssetService = new DesignAssetService();
			DesignAsset newDesign = new DesignAsset();
			newDesign.setAssetsId(1);
			newDesign.setDesignId(1);
			designAssetService.update(1, newDesign);
		});
	}

	@Test
	@Order(5)
	void updateSpecificFields() {
		assertDoesNotThrow(() -> {
			DesignAssetService designAssetService = new DesignAssetService();
			DesignAsset newDesign = new DesignAsset();
			newDesign.setDesignId(1);
			designAssetService.update(1, newDesign);
		});
	}

	@Test
	@Order(6)
	void updateNonExistingDesignId() throws ValidationException, PersistenceException {
		DesignAssetService designAssetService = new DesignAssetService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			DesignAsset newDesign = new DesignAsset();
			newDesign.setDesignId(5000);
			designAssetService.update(1, newDesign);
		});
		String expectedMessage = "Design Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(7)
	void updateNonExistingAssetId() throws ValidationException, PersistenceException {
		DesignAssetService designAssetService = new DesignAssetService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			DesignAsset newDesign = new DesignAsset();
			newDesign.setAssetsId(5000);
			designAssetService.update(1, newDesign);
		});
		String expectedMessage = "Asset Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(8)
	void updateNonExistingId() throws ValidationException, PersistenceException {
		DesignAssetService designAssetService = new DesignAssetService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			DesignAsset newDesign = new DesignAsset();
			newDesign.setAssetsId(1);
			designAssetService.update(5000, newDesign);
		});
		String expectedMessage = "Design Asset Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
}
