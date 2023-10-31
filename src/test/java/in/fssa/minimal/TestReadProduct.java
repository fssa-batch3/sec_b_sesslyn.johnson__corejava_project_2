package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Set;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import in.fssa.minimal.dto.ProductRespondDTO;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Category;
import in.fssa.minimal.service.CategoryService;
import in.fssa.minimal.service.ProductService;

@TestMethodOrder(OrderAnnotation.class)
class TestReadProduct {
	@Test
    @Order(1)
    void testGetAllProduct() {
        assertDoesNotThrow(() -> {
            ProductService productService = new ProductService();
            Set<ProductRespondDTO> arr = productService.getAllProduct();
            System.out.println(arr);
        });
    }
   
	@Test
    @Order(2)
    void testGetAllProductBySellerId() {
        assertDoesNotThrow(() -> {
            ProductService productService = new ProductService();
            Set<ProductRespondDTO> arr = productService.findProductBySellerId(15);
            System.out.println(arr);
        });
    }
	
	@Test
	@Order(3)
	void testGetProductByIdLessThanZeroSellerId() {
		ProductService productService = new ProductService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			Set<ProductRespondDTO> arr = productService.findProductBySellerId(-5);
		});
		String expectedMessage = "Seller Id cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	@Order(4)
	void testGetProductByNotExistingSellerId() {
		ProductService productService = new ProductService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			Set<ProductRespondDTO> arr = productService.findProductBySellerId(1);
		});
		String expectedMessage = "Seller Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
    @Order(5)
    void testGetAllProductByCategoryId() {
        assertDoesNotThrow(() -> {
            ProductService productService = new ProductService();
            Set<ProductRespondDTO> arr = productService.findProductByCategoryId(1);
            System.out.println(arr);
        });
    }
	
	@Test
	@Order(6)
	void testGetProductByNotExistingCategoryId(){
		ProductService productService = new ProductService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			Set<ProductRespondDTO> arr = productService.findProductByCategoryId(1000);
		});
		String expectedMessage = "Category Id doesn't exist";
		String actualMessage = exception.getMessage();
 
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test 
    @Order(7)
    void testGetProductById() {
        assertDoesNotThrow(() -> {
            ProductService productService = new ProductService();
            ProductRespondDTO arr = productService.findByProductId(1);
            System.out.println(arr);
        });
    }
	
	@Test
	@Order(8)
	void testGetProductByWrongId(){
		ProductService productService = new ProductService();
		Exception exception = assertThrows(ValidationException.class, () -> {
		   ProductRespondDTO arr = productService.findByProductId(1000);
		});
		String expectedMessage = "Product doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
    @Order(9)
    void testGetAllCategory() {
        assertDoesNotThrow(() -> {
            CategoryService categoryService = new CategoryService();
            Set<Category> arr = categoryService.getAllCategory();
            System.out.println(arr);
        });
    }
	
	@Test
    @Order(10)
    void testGetCategoryById() {
        assertDoesNotThrow(() -> {
        	CategoryService categoryService = new CategoryService();
            Category arr = categoryService.getAllCategoryById(1);
            System.out.println(arr);
        });
    }
	
	@Test
	@Order(11)
	void testGetCategoryByWrongId(){
		CategoryService categoryService = new CategoryService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			 Category arr = categoryService.getAllCategoryById(1000);
		});
		String expectedMessage = "Category Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
}
