package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import in.fssa.minimal.dto.DesignAssetRespondDTO;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Asset;
import in.fssa.minimal.model.DesignAsset;
import in.fssa.minimal.service.AssetService;
import in.fssa.minimal.service.DesignAssetService;

@TestMethodOrder(OrderAnnotation.class)
class TestReadDesignAsset {
	@Test
    @Order(1)
    void testGetAssetById() {
        assertDoesNotThrow(() -> {
            AssetService assetService = new AssetService();
            Asset arr = assetService.findByAssetId(1);
            System.out.println(arr);
        });
    }
	
	@Test
	@Order(2)
	void testWithNonExistingAssetId() {
		AssetService assetService = new AssetService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			Asset arr = assetService.findByAssetId(5000);
		});
		String expectedMessage = "Asset Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	@Order(3)
	void testGetAllDesignAndAsset() {
		assertDoesNotThrow(() -> {
			DesignAssetService designAssetService = new DesignAssetService();
			Set<DesignAssetRespondDTO> arr = designAssetService.getAllDesignAsset();
		});
	}

	@Test
	@Order(4)
	void testGetDesignById() {
		assertDoesNotThrow(() -> {
			DesignAssetService designAssetService = new DesignAssetService();
			DesignAssetRespondDTO arr = designAssetService.findDesignAssetById(1);
			System.out.println(arr);
		});
	}

	@Test
	@Order(5)
	void testWithIdLessThanZero() {
		DesignAssetService designAssetService = new DesignAssetService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			DesignAssetRespondDTO arr = designAssetService.findDesignAssetById(0);
		});
		String expectedMessage = "ID cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(6)
	void testWithNonExistingDesignAssetId() {
		DesignAssetService designAssetService = new DesignAssetService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			DesignAssetRespondDTO arr = designAssetService.findDesignAssetById(20);
		});
		String expectedMessage = "Design Asset Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	@Order(4)
	void getDesignByDesignAssetId() {
		assertDoesNotThrow(() -> {
			DesignAssetService designAssetService = new DesignAssetService();
			DesignAsset arr = designAssetService.findDesignIdByDesignAssetId(1);
			System.out.println(arr);
		});
	}
}
