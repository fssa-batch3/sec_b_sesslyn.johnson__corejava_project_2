package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import in.fssa.minimal.dao.DesignAssetDAO;
import in.fssa.minimal.dao.UserDAO;
import in.fssa.minimal.dto.DesignAssetRespondDto;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.DesignAsset;
import in.fssa.minimal.service.DesignAssetService;

@TestMethodOrder(OrderAnnotation.class)
class TestGetAllDesignAsset {
	@Test
	@Order(1)
	void createUserWithValidInput() {
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
	void createDesignWithInvalidInput() {
		DesignAssetService designAssetService = new DesignAssetService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			designAssetService.create(null);
		});
		String expectedMessage = "Design Asset Object cannot be null";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(3)
	void createDesignWithNonExistingAssetId() {
		DesignAssetService designAssetService = new DesignAssetService();
		DesignAsset newDesign = new DesignAsset();
		newDesign.setAssetsId(5000);
		newDesign.setDesignId(1);
		Exception exception = assertThrows(ValidationException.class, () -> {
			designAssetService.create(newDesign);
		});
		String expectedMessage = "Asset Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(4)
	void createDesignWithNonExistingDesignId() {
		DesignAssetService designAssetService = new DesignAssetService();
		DesignAsset newDesign = new DesignAsset();
		newDesign.setAssetsId(1);
		newDesign.setDesignId(5000);
		Exception exception = assertThrows(ValidationException.class, () -> {
			designAssetService.create(newDesign);
		});
		String expectedMessage = "Design Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(5)
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
	@Order(6)
	void updateSpecificFields() {
		assertDoesNotThrow(() -> {
			DesignAssetService designAssetService = new DesignAssetService();
			DesignAsset newDesign = new DesignAsset();
			newDesign.setDesignId(1);
			designAssetService.update(1, newDesign);
		});
	}

	@Test
	@Order(7)
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
	@Order(8)
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
	@Order(9)
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

	@Test
	@Order(10)
	void deleteWithNonExistingId() throws ValidationException {
		DesignAssetService designAssetService = new DesignAssetService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			designAssetService.delete(5000);
		});
		String expectedMessage = "Design Asset Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(11)
	void getAllDesignAndAsset() {
		assertDoesNotThrow(() -> {
			DesignAssetService designAssetService = new DesignAssetService();
			Set<DesignAssetRespondDto> arr = designAssetService.getAllByDesignAsset();
		});
	}

	@Test
	@Order(12)
	void getDesignById() {
		assertDoesNotThrow(() -> {
			DesignAssetService designAssetService = new DesignAssetService();
			DesignAssetRespondDto arr = designAssetService.findDesignAssetById(1);
			System.out.println(arr);
		});
	}

	@Test
	@Order(13)
	void testWithIdLessThanZero() {
		DesignAssetService designAssetService = new DesignAssetService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			DesignAssetRespondDto arr = designAssetService.findDesignAssetById(0);
		});
		String expectedMessage = "ID cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(14)
	void testWithNonExistingId() {
		DesignAssetService designAssetService = new DesignAssetService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			DesignAssetRespondDto arr = designAssetService.findDesignAssetById(20);
		});
		String expectedMessage = "Design Asset Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(15)
	void testDeleteDesignAsset() {
		assertDoesNotThrow(() -> {
			DesignAssetDAO app = new DesignAssetDAO();
			int design = app.getLastUpdatedDesignAssetId();
			DesignAssetService designAssetService = new DesignAssetService();
			designAssetService.delete(design);
		});
	}

}
