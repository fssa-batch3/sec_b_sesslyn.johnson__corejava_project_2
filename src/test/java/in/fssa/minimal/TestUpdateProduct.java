package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Category;
import in.fssa.minimal.model.Product;
import in.fssa.minimal.service.CategoryService;
import in.fssa.minimal.service.ProductService;

public class TestUpdateProduct {
	@Test 
	@Order(1) 
	 void testUpdateProductWithValidInput() {
	    ProductService productService = new ProductService(); 
	    Product newProduct = new Product();
	    newProduct.setName("Jordan Soft Fabric Sofa");
	    newProduct.setPrice(45000);
	    newProduct.setDiscount(6);
	    newProduct.setQuantity(14);
	    newProduct.setSellerId(15);
	    newProduct.setCategoryId(1);
	    newProduct.setWarranty("2 years");
	    assertDoesNotThrow(() -> { 
	        productService.updateProduct(1,newProduct); 
	    });
	} 
	
	@Test 
	@Order(2) 
	 void testUpdateProductWithInValidInput() {
	    ProductService productService = new ProductService(); 
	    Exception exception = assertThrows(ValidationException.class, () -> {
	    	 productService.updateProduct(1,null); 
		});
		String expectedMessage = "Nothing has been updated in the product";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	} 
	
	@Test 
	@Order(3) 
	 void testUpdateProductWithInValidId() {
	    ProductService productService = new ProductService(); 
	    Exception exception = assertThrows(ValidationException.class, () -> {
	    	Product newProduct = new Product();
	 	    newProduct.setName("Jordan Soft Fabric Sofa");
	 	    newProduct.setPrice(45000); 
	    	productService.updateProduct(1000,newProduct); 
		});
		String expectedMessage = "Product doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	} 
	
	
	@Test 
	@Order(4) 
	 void testUpdateCategoryWithValidInput() {
		CategoryService categoryService = new CategoryService();
        Category category = new Category();
	    category.setName("Lounges");
	    assertDoesNotThrow(() -> { 
	    	categoryService.updateCategory(6,category); 
	    });
	} 
	 
	@Test 
	@Order(5) 
	 void testUpdateCategoryWithInValidInput() {
		CategoryService categoryService = new CategoryService();
	    Exception exception = assertThrows(ValidationException.class, () -> {
	    	categoryService.updateCategory(6,null); 
		});
		String expectedMessage = "Nothing has been updated in the category";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	} 
	
	@Test 
	@Order(6) 
	 void testUpdateCategoryWithInValidId() {
		CategoryService categoryService = new CategoryService();
	    Exception exception = assertThrows(ValidationException.class, () -> {
	    	Category category = new Category();
	    	 category.setName("Lounges");
	    	categoryService.updateCategory(1000,category); 
		});
		String expectedMessage = "Category Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	} 
}
