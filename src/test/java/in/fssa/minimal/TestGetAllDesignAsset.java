package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Set;
import org.junit.jupiter.api.Test;
import in.fssa.minimal.dto.DesignAssetRespondDto;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.DesignAsset;
import in.fssa.minimal.service.DesignAssetService;


public class TestGetAllDesignAsset {
	@Test
	public void testCreateUserWithValidInput() {
		DesignAssetService designAssetService = new DesignAssetService();
		DesignAsset newDesign = new DesignAsset();
		newDesign.setAssetsId(3);
		newDesign.setDesignId(1);
		assertDoesNotThrow(() -> {
			designAssetService.create(newDesign);
		});
	}

	@Test
	public void testCreateDesignWithInvalidInput() {
		DesignAssetService designAssetService = new DesignAssetService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			designAssetService.create(null);
		});
		String expectedMessage = "Design Asset Object can't be null";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateDesignWithNonExistingAssetId() {
		DesignAssetService designAssetService = new DesignAssetService();
		DesignAsset newDesign = new DesignAsset();
		newDesign.setAssetsId(20);
		newDesign.setDesignId(1);
		Exception exception = assertThrows(ValidationException.class, () -> {
			designAssetService.create(newDesign);
		});
		String expectedMessage = "Asset Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateDesignWithNonExistingDesignId() {
		DesignAssetService designAssetService = new DesignAssetService();
		DesignAsset newDesign = new DesignAsset();
		newDesign.setAssetsId(1);
		newDesign.setDesignId(20);
		Exception exception = assertThrows(ValidationException.class, () -> {
			designAssetService.create(newDesign);
		});
		String expectedMessage = "Design Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateDesignAsset() throws ValidationException, PersistenceException {
		DesignAssetService designAssetService = new DesignAssetService();
		DesignAsset newDesign = new DesignAsset();
		newDesign.setAssetsId(1);
		newDesign.setDesignId(2);
		designAssetService.update(1, newDesign);
	}

	@Test
	public void testUpdateSpecificFields() throws ValidationException, PersistenceException {
		DesignAssetService designAssetService = new DesignAssetService();
		DesignAsset newDesign = new DesignAsset();
		newDesign.setDesignId(2);
		designAssetService.update(2, newDesign);
	}

	@Test
	public void testUpdateNonExistingDesignId() throws ValidationException, PersistenceException {
		DesignAssetService designAssetService = new DesignAssetService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			DesignAsset newDesign = new DesignAsset();
			newDesign.setDesignId(20);
			designAssetService.update(1, newDesign);
		});
		String expectedMessage = "Design Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateNonExistingAssetId() throws ValidationException, PersistenceException {
		DesignAssetService designAssetService = new DesignAssetService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			DesignAsset newDesign = new DesignAsset();
			newDesign.setAssetsId(20);
			designAssetService.update(1, newDesign);
		});
		String expectedMessage = "Asset Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateNonExistingId() throws ValidationException, PersistenceException {
		DesignAssetService designAssetService = new DesignAssetService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			DesignAsset newDesign = new DesignAsset();
			newDesign.setAssetsId(1);
			designAssetService.update(20, newDesign);
		});
		String expectedMessage = "Design Asset Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testDeleteDesignAsset() throws ValidationException, PersistenceException {
		DesignAssetService designAssetService = new DesignAssetService();
		designAssetService.delete(1);
	}

	@Test
	public void testDeleteWithNonExistingId() throws ValidationException {
		DesignAssetService designAssetService = new DesignAssetService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			designAssetService.delete(20);
		});
		String expectedMessage = "Design Asset Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void getAllDesignAndAsset() throws PersistenceException, ValidationException {
		DesignAssetService designAssetService = new DesignAssetService();
		Set<DesignAssetRespondDto> arr = designAssetService.getAllByDesignAsset();
	}
	
	@Test
	public void getDesignById() throws ValidationException, PersistenceException {
		DesignAssetService designAssetService = new DesignAssetService();
		DesignAssetRespondDto arr = designAssetService.findAllDesignAssetById(1);
		System.out.println(arr);
	}

	
	@Test
	public void testWithIdLessThanZero() {
		DesignAssetService designAssetService = new DesignAssetService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			DesignAssetRespondDto arr = designAssetService.findAllDesignAssetById(0);
		});
		String expectedMessage = "Id can't be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testWitNonExistingId() {
		DesignAssetService designAssetService = new DesignAssetService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			DesignAssetRespondDto arr = designAssetService.findAllDesignAssetById(20);
		});
		String expectedMessage = "Design Asset Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
}
