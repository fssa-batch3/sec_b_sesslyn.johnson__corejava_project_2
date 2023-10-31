package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import in.fssa.minimal.dao.CategoryDAO;
import in.fssa.minimal.dao.ProductDAO;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.service.CategoryService;
import in.fssa.minimal.service.ProductService;

public class TestDeleteProduct {
	@Test
	@Order(1)
	void testDeleteProduct() {
		assertDoesNotThrow(() -> {
			ProductService productService = new ProductService();
			productService.deleteProduct(1);
	        ProductDAO.reactivateProduct(1);
		});
	}
	
	@Test
	@Order(2)
	void testDeleteProductWithWrongId() {
		ProductService productService = new ProductService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.deleteProduct(5000);
		});
		String expectedMessage = "Product doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	@Order(3)
	void testDeleteCategory() {
		assertDoesNotThrow(() -> {
			CategoryService categoryService = new CategoryService();
			categoryService.deleteCategory(1);
	        CategoryDAO.reactivateCategory(1);
		});
	}
	
	@Test
	@Order(4)
	void testDeleteCategoryWithWrongId() {
		CategoryService categoryService = new CategoryService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			categoryService.deleteCategory(5000);
		});
		String expectedMessage = "Category Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
}
