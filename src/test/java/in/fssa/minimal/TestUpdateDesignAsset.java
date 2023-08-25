package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import in.fssa.minimal.dao.DesignAssetDAO;
import in.fssa.minimal.dao.DesignDAO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Asset;
import in.fssa.minimal.model.DesignAsset;
import in.fssa.minimal.service.AssetService;
import in.fssa.minimal.service.DesignAssetService;

@TestMethodOrder(OrderAnnotation.class)
class TestUpdateDesignAsset {
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
	void tesUpdateAssetWithExistingUrl() throws ValidationException, PersistenceException {
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
	void testUpdateDesignAsset() {
		assertDoesNotThrow(() -> {
			DesignAssetService designAssetService = new DesignAssetService();
			DesignAsset newDesign = new DesignAsset();
			int designId = DesignAssetDAO.getLastUpdatedDesignId();
			int assetId = DesignAssetDAO.getLastUpdatedAssetId();
			newDesign.setAssetsId(assetId);
			newDesign.setDesignId(designId);
			designAssetService.updateDesignAsset(1, newDesign);
		});
	}

	@Test
	@Order(5)
	void testUpdateDesignIdField() {
		assertDoesNotThrow(() -> {
			DesignAssetService designAssetService = new DesignAssetService();
			DesignAsset newDesign = new DesignAsset();
			int designId = DesignDAO.getLastUpdatedDesignId();
			newDesign.setDesignId(designId);
			designAssetService.updateDesignAsset(4, newDesign);
		});
	}
	
	@Test
	@Order(6)
	void testUpdateAssetIdField() {
		assertDoesNotThrow(() -> {
			DesignAssetService designAssetService = new DesignAssetService();
			DesignAsset newDesign = new DesignAsset();
			int assetId = DesignAssetDAO.getLastUpdatedAssetId();
			System.out.println(assetId);
			newDesign.setAssetsId(assetId);
			designAssetService.updateDesignAsset(3, newDesign);
		});
	}

	@Test
	@Order(7)
	void testUpdateOldValueInSpecificFields() {
		DesignAssetService designAssetService = new DesignAssetService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			DesignAsset newDesign = new DesignAsset();
			newDesign.setDesignId(1);
			designAssetService.updateDesignAsset(2, newDesign);
		});
		String expectedMessage = "No fields have been updated";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(8)
	void testUpdateNonExistingDesignId() throws ValidationException, PersistenceException {
		DesignAssetService designAssetService = new DesignAssetService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			DesignAsset newDesign = new DesignAsset();
			newDesign.setDesignId(5000);
			designAssetService.updateDesignAsset(1, newDesign);
		});
		String expectedMessage = "Design Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(9)
	void testUpdateNonExistingAssetId() throws ValidationException, PersistenceException {
		DesignAssetService designAssetService = new DesignAssetService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			DesignAsset newDesign = new DesignAsset();
			newDesign.setAssetsId(5000);
			designAssetService.updateDesignAsset(1, newDesign);
		});
		String expectedMessage = "Asset Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(10)
	void testUpdateNonExistingId() throws ValidationException, PersistenceException {
		DesignAssetService designAssetService = new DesignAssetService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			DesignAsset newDesign = new DesignAsset();
			newDesign.setAssetsId(1);
			designAssetService.updateDesignAsset(5000, newDesign);
		});
		String expectedMessage = "Design Asset Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
}
