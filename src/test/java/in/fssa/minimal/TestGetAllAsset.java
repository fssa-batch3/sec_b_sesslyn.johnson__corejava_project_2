package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Asset;
import in.fssa.minimal.service.AssetService;

@TestMethodOrder(OrderAnnotation.class)
public class TestGetAllAsset {

	@Test
	@Order(1)
	public void testCreateAssetWithValidInput() {
		AssetService assetService = new AssetService();
		Asset newAsset = new Asset();
		String generatedUrl = generateRandomUrl();
		newAsset.setAssetsUrl(generatedUrl);

		assertDoesNotThrow(() -> {
			assetService.create(newAsset);
		});
	}

	@Test
	@Order(2)
	public void testCreateAssetUrlAlreadyExists() {
		AssetService assetService = new AssetService();
		Asset newAsset = new Asset();
		newAsset.setAssetsUrl("https://youtu.be/DFgL3URDOr4");
		Exception exception = assertThrows(ValidationException.class, () -> {
			assetService.create(newAsset);
		});
		String expectedMessage = "Asset Url already exists";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(3)
	public void testCreateAssetWithInValidInput() {
		AssetService assetService = new AssetService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			assetService.create(null);
		});
		String expectedMessage = "Asset object can not be null";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(4)
	public void testCreateAssetUrlWithNull() {
		AssetService assetService = new AssetService();
		Asset newAsset = new Asset();
		newAsset.setAssetsUrl(null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			assetService.create(newAsset);
		});
		String expectedMessage = "Asset Url Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(5)
	public void testCreateAssetUrlWithEmpty() {
		AssetService assetService = new AssetService();
		Asset newAsset = new Asset();
		newAsset.setAssetsUrl("");
		Exception exception = assertThrows(ValidationException.class, () -> {
			assetService.create(newAsset);
		});
		String expectedMessage = "Asset Url Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(6)
	public void testUpdateAsset() throws ValidationException, PersistenceException {
		AssetService assetService = new AssetService();
		Asset newAsset = new Asset();
		String generate = generateRandomUrl();
		newAsset.setAssetsUrl(generate);
		assetService.update(1, newAsset);
	}

	@Test
	@Order(7)
	public void testUpdateAssetWithExistingUrl() throws ValidationException, PersistenceException {
		AssetService assetService = new AssetService();
		Asset newAsset = new Asset();
		newAsset.setAssetsUrl("https://youtu.be/DFgL3URDOr4");
		Exception exception = assertThrows(ValidationException.class, () -> {
			assetService.update(1, newAsset);
		});
		String expectedMessage = "Asset Url already exists";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(8)
	public void testUpdateAssetWithNonExistingId() throws ValidationException, PersistenceException {
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

	@Test
	@Order(9)
	public void getAssetById() throws ValidationException, PersistenceException {
		AssetService assetService = new AssetService();
		Asset arr = assetService.findByAssetId(1);
		System.out.println(arr);
	}

	@Test
	@Order(10)
	public void testWitNonExistingId() {
		AssetService assetService = new AssetService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			Asset arr = assetService.findByAssetId(5000);
		});
		String expectedMessage = "Asset Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
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

}
