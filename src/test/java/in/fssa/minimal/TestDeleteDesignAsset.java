package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import in.fssa.minimal.dao.DesignAssetDAO;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.service.DesignAssetService;
@TestMethodOrder(OrderAnnotation.class)
class TestDeleteDesignAsset {
	@Test
	@Order(1)
	void deleteWithNonExistingId() throws ValidationException {
		DesignAssetService designAssetService = new DesignAssetService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			designAssetService.deleteDesignAsset(5000);
		});
		String expectedMessage = "Design Asset Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(2)
	void testDeleteDesignAsset() {
		assertDoesNotThrow(() -> {
			DesignAssetDAO app = new DesignAssetDAO();
			int design = app.getLastUpdatedDesignAssetId();
			DesignAssetService designAssetService = new DesignAssetService();
			designAssetService.deleteDesignAsset(design);
		});
	}

}
