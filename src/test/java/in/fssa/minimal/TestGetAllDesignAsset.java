package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import in.fssa.minimal.dto.DesignAssetRespondDto;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.DesignAsset;
import in.fssa.minimal.service.DesignAssetService;

@TestMethodOrder(OrderAnnotation.class)
public class TestGetAllDesignAsset {
	@Test
	@Order(1)
	public void testCreateUserWithValidInput() {
		DesignAssetService designAssetService = new DesignAssetService();
		DesignAsset newDesign = new DesignAsset();
		newDesign.setAssetsId(2);
		newDesign.setDesignId(1);
		assertDoesNotThrow(() -> {
			designAssetService.create(newDesign);
		});
	}

	@Test
	@Order(2)
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
	@Order(3)
	public void testCreateDesignWithNonExistingAssetId() {
		DesignAssetService designAssetService = new DesignAssetService();
		DesignAsset newDesign = new DesignAsset();
		newDesign.setAssetsId(5000);
		newDesign.setDesignId(1);
		Exception exception = assertThrows(ValidationException.class, () -> {
			designAssetService.create(newDesign);
		});
		String expectedMessage = "Asset Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(4)
	public void testCreateDesignWithNonExistingDesignId() {
		DesignAssetService designAssetService = new DesignAssetService();
		DesignAsset newDesign = new DesignAsset();
		newDesign.setAssetsId(1);
		newDesign.setDesignId(5000);
		Exception exception = assertThrows(ValidationException.class, () -> {
			designAssetService.create(newDesign);
		});
		String expectedMessage = "Design Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(5)
	public void testUpdateDesignAsset() throws ValidationException, PersistenceException {
		DesignAssetService designAssetService = new DesignAssetService();
		DesignAsset newDesign = new DesignAsset();
		newDesign.setAssetsId(1);
		newDesign.setDesignId(1);
		designAssetService.update(1, newDesign);
	}

	@Test
	@Order(6)
	public void testUpdateSpecificFields() throws ValidationException, PersistenceException {
		DesignAssetService designAssetService = new DesignAssetService();
		DesignAsset newDesign = new DesignAsset();
		newDesign.setDesignId(1);
		designAssetService.update(1, newDesign);
	}

	@Test
	@Order(7)
	public void testUpdateNonExistingDesignId() throws ValidationException, PersistenceException {
		DesignAssetService designAssetService = new DesignAssetService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			DesignAsset newDesign = new DesignAsset();
			newDesign.setDesignId(5000);
			designAssetService.update(1, newDesign);
		});
		String expectedMessage = "Design Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(8)
	public void testUpdateNonExistingAssetId() throws ValidationException, PersistenceException {
		DesignAssetService designAssetService = new DesignAssetService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			DesignAsset newDesign = new DesignAsset();
			newDesign.setAssetsId(5000);
			designAssetService.update(1, newDesign);
		});
		String expectedMessage = "Asset Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(9)
	public void testUpdateNonExistingId() throws ValidationException, PersistenceException {
		DesignAssetService designAssetService = new DesignAssetService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			DesignAsset newDesign = new DesignAsset();
			newDesign.setAssetsId(1);
			designAssetService.update(5000, newDesign);
		});
		String expectedMessage = "Design Asset Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	

	@Test
	@Order(10)
	public void testDeleteWithNonExistingId() throws ValidationException {
		DesignAssetService designAssetService = new DesignAssetService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			designAssetService.delete(5000);
		});
		String expectedMessage = "Design Asset Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(11)
	public void getAllDesignAndAsset() throws PersistenceException, ValidationException {
		DesignAssetService designAssetService = new DesignAssetService();
		Set<DesignAssetRespondDto> arr = designAssetService.getAllByDesignAsset();
	}
	
	@Test
	@Order(12)
	public void getDesignById() throws ValidationException, PersistenceException {
		DesignAssetService designAssetService = new DesignAssetService();
		DesignAssetRespondDto arr = designAssetService.findAllDesignAssetById(1);
		System.out.println(arr);
	}

	
	@Test
	@Order(13)
	public void testWithIdLessThanZero() {
		DesignAssetService designAssetService = new DesignAssetService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			DesignAssetRespondDto arr = designAssetService.findAllDesignAssetById(0);
		});
		String expectedMessage = "ID cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	@Order(14)
	public void testWitNonExistingId() {
		DesignAssetService designAssetService = new DesignAssetService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			DesignAssetRespondDto arr = designAssetService.findAllDesignAssetById(20);
		});
		String expectedMessage = "Design Asset Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	@Order(15)
	public void testDeleteDesignAsset() throws ValidationException, PersistenceException {
		DesignAssetService designAssetService = new DesignAssetService();
		designAssetService.delete(1);
	}
}
