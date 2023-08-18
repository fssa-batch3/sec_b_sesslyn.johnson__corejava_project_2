package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Asset;
import in.fssa.minimal.service.AssetService;


public class TestGetAllAsset {
	@Test
	public void testCreateAssetWithValidInput() {
		AssetService assetService = new AssetService();
		Asset newAsset = new Asset();
		newAsset.setAssetsUrl("https://youtu.be/9xkxT2hY634");

		assertDoesNotThrow(() -> {
			assetService.create(newAsset);
		});
	}
	@Test
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
	public void testCreateAssetUrlWithNull() {
		AssetService assetService = new AssetService();
		Asset newAsset = new Asset();
		newAsset.setAssetsUrl(null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			assetService.create(newAsset);
		});
		String expectedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateAssetUrlWithEmpty() {
		AssetService assetService = new AssetService();
		Asset newAsset = new Asset();
		newAsset.setAssetsUrl("");
		Exception exception = assertThrows(ValidationException.class, () -> {
			assetService.create(newAsset);
		});
		String expectedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
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
	public void testUpdateAsset() throws ValidationException, PersistenceException {
		AssetService assetService = new AssetService();
		Asset newAsset = new Asset();
		newAsset.setAssetsUrl("https://youtu.be/cqAGTQgoDl4");
		assetService.update(1, newAsset);
	}
	
	@Test
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
	public void testUpdateAssetWithNonExistingId() throws ValidationException, PersistenceException {
		AssetService assetService = new AssetService();
		Asset newAsset = new Asset();
		newAsset.setAssetsUrl("https://youtu.be/6uV24JdbUUI");
		Exception exception = assertThrows(ValidationException.class, () -> {
			assetService.update(10, newAsset);
		});
		String expectedMessage = "Asset Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));

	}
	
	@Test
	public void getAssetById() throws ValidationException, PersistenceException {
		AssetService assetService = new AssetService();
		Asset arr = assetService.findByAssetId(1);
		System.out.println(arr);
	}
	
	@Test
	public void testWitNonExistingId() {
		AssetService assetService = new AssetService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			Asset arr = assetService.findByAssetId(10);
		});
		String expectedMessage = "Asset Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
}
