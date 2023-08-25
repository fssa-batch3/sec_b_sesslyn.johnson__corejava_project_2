package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Asset;
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
	void testCreateAssetUrlAlreadyExists() {
		AssetService assetService = new AssetService();
		Asset newAsset = new Asset();
		newAsset.setAssetsUrl("https://youtu.be/DFgL3URDOr4");
		Exception exception = assertThrows(ValidationException.class, () -> {
			assetService.createAsset(newAsset);
		});
		String expectedMessage = "Asset Url already exists";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(3)
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
	@Order(4)
	void testCreateUserWithValidInput() {
		DesignAssetService designAssetService = new DesignAssetService();
		DesignAsset newDesign = new DesignAsset();
		newDesign.setAssetsId(2);
		newDesign.setDesignId(1);
		assertDoesNotThrow(() -> {
			designAssetService.createDesignAsset(newDesign);
		});
	}

	@Test
	@Order(5)
	void testCreateDesignWithInvalidInput() {
		DesignAssetService designAssetService = new DesignAssetService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			designAssetService.createDesignAsset(null);
		});
		String expectedMessage = "Design Asset Object cannot be null";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(6)
	void testCreateDesignWithNonExistingAssetId() {
		DesignAssetService designAssetService = new DesignAssetService();
		DesignAsset newDesign = new DesignAsset();
		newDesign.setAssetsId(5000);
		newDesign.setDesignId(1);
		Exception exception = assertThrows(ValidationException.class, () -> {
			designAssetService.createDesignAsset(newDesign);
		});
		String expectedMessage = "Asset Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(7)
	void testCreateDesignWithNonExistingDesignId() {
		DesignAssetService designAssetService = new DesignAssetService();
		DesignAsset newDesign = new DesignAsset();
		newDesign.setAssetsId(1);
		newDesign.setDesignId(5000);
		Exception exception = assertThrows(ValidationException.class, () -> {
			designAssetService.createDesignAsset(newDesign);
		});
		String expectedMessage = "Design Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

}
