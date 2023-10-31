package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Category;
import in.fssa.minimal.model.Product;
import in.fssa.minimal.service.CategoryService;
import in.fssa.minimal.service.ProductService;
import in.fssa.minimal.util.RandomValue;
@TestMethodOrder(OrderAnnotation.class)
class TestCreateProduct {

	@Test 
	@Order(1) 
	 void testCreateProductWithValidInput() {
	    ProductService productService = new ProductService(); 
	    Product newProduct = new Product();
	    String name = RandomValue.generateRandomStringWithAlphabets(8); 
	    newProduct.setName(name);
	    newProduct.setDescription("*3+ Lounger and  2 pillows. *Brand : Muebles Casa. *Dimensions (In Centimeters) :Lounger: H 79 x W 183 x D 83; 2 Seater Sofa: H 79 x W 130 x D 83; 1 Seater Sofa : H 79 x W 56 x D 83. * Seating Height : 17. *Weight :  65kg. * Frame Material : Meranti Wood & Plywood,  Fabric: PolyCotton * 10 days replacement.  * Perfect for Living room.");
	    newProduct.setImageUrl("https://ii2.pepperfry.com/media/catalog/product/j/o/494x544/jordan-lhs-sectional-sofa-in-blue-colour-by-muebles-casa-jordan-lhs-sectional-sofa-in-blue-colour-by-anyyeu.jpg");
	    newProduct.setPrice(46799);
	    newProduct.setDiscount(5);
	    newProduct.setWarranty("3 years"); 
	    newProduct.setQuantity(15);
	    newProduct.setCategoryId(1); 
	    newProduct.setSellerId(15);

	    assertDoesNotThrow(() -> { 
	        productService.createProduct(newProduct); 
	    });
	} 
	
	@Test 
	@Order(2) 
	 void testCreateProductWithInValidInput() {
	    ProductService productService = new ProductService(); 
		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(null);
		});
		String expectedMessage = "Product object cannot be null";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	} 
	
	@Test 
	@Order(3) 
	 void testCreateProductWithShortName() {
	    ProductService productService = new ProductService(); 
	    Product newProduct = new Product();
	    newProduct.setName("Jo");
	    newProduct.setDescription("*3+ Lounger and  2 pillows. *Brand : Muebles Casa. *Dimensions (In Centimeters) :Lounger: H 79 x W 183 x D 83; 2 Seater Sofa: H 79 x W 130 x D 83; 1 Seater Sofa : H 79 x W 56 x D 83. * Seating Height : 17. *Weight :  65kg. * Frame Material : Meranti Wood & Plywood,  Fabric: PolyCotton * 10 days replacement.  * Perfect for Living room.");
	    newProduct.setImageUrl("https://ii2.pepperfry.com/media/catalog/product/j/o/494x544/jordan-lhs-sectional-sofa-in-blue-colour-by-muebles-casa-jordan-lhs-sectional-sofa-in-blue-colour-by-anyyeu.jpg");
	    newProduct.setPrice(46799);
	    newProduct.setDiscount(5);
	    newProduct.setQuantity(15);
	    newProduct.setCategoryId(1);
	    newProduct.setWarranty("3 years");
	    newProduct.setSellerId(17);
	    
	    Exception exception = assertThrows(ValidationException.class, () -> {
	    	productService.createProduct(newProduct);
		});
		String expectedMessage = "Product Name should be at least 3 characters long";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	} 
	
	@Test 
	@Order(4) 
	 void testCreateProductWithWrongPattern() {
	    ProductService productService = new ProductService(); 
	    Product newProduct = new Product();
	    newProduct.setName("Jo67ardan Fa56bric");
	    newProduct.setDescription("*3+ Lounger and  2 pillows. *Brand : Muebles Casa. *Dimensions (In Centimeters) :Lounger: H 79 x W 183 x D 83; 2 Seater Sofa: H 79 x W 130 x D 83; 1 Seater Sofa : H 79 x W 56 x D 83. * Seating Height : 17. *Weight :  65kg. * Frame Material : Meranti Wood & Plywood,  Fabric: PolyCotton * 10 days replacement.  * Perfect for Living room.");
	    newProduct.setImageUrl("https://ii2.pepperfry.com/media/catalog/product/j/o/494x544/jordan-lhs-sectional-sofa-in-blue-colour-by-muebles-casa-jordan-lhs-sectional-sofa-in-blue-colour-by-anyyeu.jpg");
	    newProduct.setPrice(46799);
	    newProduct.setDiscount(5);
	    newProduct.setQuantity(15);
	    newProduct.setCategoryId(1);
	    newProduct.setWarranty("3 years");
	    newProduct.setSellerId(17);
	    
	    Exception exception = assertThrows(ValidationException.class, () -> {
	    	productService.createProduct(newProduct);
		});
		String expectedMessage = "Product Name should only contain alphabetic characters and allow spaces between words";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	} 

	
	@Test 
	@Order(5) 
	 void testCreateProductWithInvalidImageUrl() {
	    ProductService productService = new ProductService(); 
	    Product newProduct = new Product();
	    newProduct.setName("Johans Fabric");
	    newProduct.setDescription("*3+ Lounger and  2 pillows. *Brand : Muebles Casa. *Dimensions (In Centimeters) :Lounger: H 79 x W 183 x D 83; 2 Seater Sofa: H 79 x W 130 x D 83; 1 Seater Sofa : H 79 x W 56 x D 83. * Seating Height : 17. *Weight :  65kg. * Frame Material : Meranti Wood & Plywood,  Fabric: PolyCotton * 10 days replacement.  * Perfect for Living room.");
		newProduct.setImageUrl("by-anyyeu.g");
	    newProduct.setPrice(46799);
	    newProduct.setDiscount(5);
	    newProduct.setQuantity(15);
	    newProduct.setCategoryId(1);
	    newProduct.setWarranty("3 years");
	    newProduct.setSellerId(17);
	    
	    Exception exception = assertThrows(ValidationException.class, () -> {
	    	productService.createProduct(newProduct);
		});
		String expectedMessage = "Invalid image format. Please provide a valid image url.";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	} 
	
	@Test 
	@Order(6) 
	 void testCreateProductWithInvalidPrice() {
	    ProductService productService = new ProductService(); 
	    Product newProduct = new Product();
	    newProduct.setName("Johans Fabric");
	    newProduct.setDescription("*3+ Lounger and  2 pillows. *Brand : Muebles Casa. *Dimensions (In Centimeters) :Lounger: H 79 x W 183 x D 83; 2 Seater Sofa: H 79 x W 130 x D 83; 1 Seater Sofa : H 79 x W 56 x D 83. * Seating Height : 17. *Weight :  65kg. * Frame Material : Meranti Wood & Plywood,  Fabric: PolyCotton * 10 days replacement.  * Perfect for Living room.");
	    newProduct.setImageUrl("https://ii2.pepperfry.com/media/catalog/product/j/o/494x544/jordan-lhs-sectional-sofa-in-blue-colour-by-muebles-casa-jordan-lhs-sectional-sofa-in-blue-colour-by-anyyeu.jpg");
		newProduct.setPrice(100);
	    newProduct.setDiscount(5);
	    newProduct.setQuantity(15);
	    newProduct.setCategoryId(1);
	    newProduct.setWarranty("3 years");
	    newProduct.setSellerId(15);
	    
	    Exception exception = assertThrows(ValidationException.class, () -> {
	    	productService.createProduct(newProduct); 
		});
		String expectedMessage = "Price should be in the range between 300 and 100,000";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	} 
	
	@Test 
	@Order(7) 
	 void testCreateProductWithInvalidDiscount() {
	    ProductService productService = new ProductService(); 
	    Product newProduct = new Product();
	    newProduct.setName("Johans Fabric");
	    newProduct.setDescription("*3+ Lounger and  2 pillows. *Brand : Muebles Casa. *Dimensions (In Centimeters) :Lounger: H 79 x W 183 x D 83; 2 Seater Sofa: H 79 x W 130 x D 83; 1 Seater Sofa : H 79 x W 56 x D 83. * Seating Height : 17. *Weight :  65kg. * Frame Material : Meranti Wood & Plywood,  Fabric: PolyCotton * 10 days replacement.  * Perfect for Living room.");
	    newProduct.setImageUrl("https://ii2.pepperfry.com/media/catalog/product/j/o/494x544/jordan-lhs-sectional-sofa-in-blue-colour-by-muebles-casa-jordan-lhs-sectional-sofa-in-blue-colour-by-anyyeu.jpg");
		newProduct.setPrice(5000);
	    newProduct.setDiscount(-3);
	    newProduct.setQuantity(15);
	    newProduct.setCategoryId(1);
	    newProduct.setWarranty("3 years");
	    newProduct.setSellerId(15);
	    
	    Exception exception = assertThrows(ValidationException.class, () -> {
	    	productService.createProduct(newProduct);
		});
		String expectedMessage = "Discount should be in the range between 1 and 99";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	} 
	
	@Test 
	@Order(8) 
	 void testCreateProductWithInvalidQuantity() {
	    ProductService productService = new ProductService(); 
	    Product newProduct = new Product();
	    newProduct.setName("Johans Fabric");
	    newProduct.setDescription("*3+ Lounger and  2 pillows. *Brand : Muebles Casa. *Dimensions (In Centimeters) :Lounger: H 79 x W 183 x D 83; 2 Seater Sofa: H 79 x W 130 x D 83; 1 Seater Sofa : H 79 x W 56 x D 83. * Seating Height : 17. *Weight :  65kg. * Frame Material : Meranti Wood & Plywood,  Fabric: PolyCotton * 10 days replacement.  * Perfect for Living room.");
		newProduct.setImageUrl("https://ii2.pepperfry.com/media/catalog/product/j/o/494x544/jordan-lhs-sectional-sofa-in-blue-colour-by-muebles-casa-jordan-lhs-sectional-sofa-in-blue-colour-by-anyyeu.jpg");
		newProduct.setPrice(5000);
	    newProduct.setDiscount(3);
	    newProduct.setQuantity(-3);
	    newProduct.setCategoryId(1);
	    newProduct.setWarranty("3 years");
	    newProduct.setSellerId(15);
	    
	    Exception exception = assertThrows(ValidationException.class, () -> {
	    	productService.createProduct(newProduct);
		});
		String expectedMessage = "Quantity should be in the range between 1 and 99";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	} 
	
	@Test 
	@Order(9) 
	 void testCreateProductWithInvalidCategory() {
	    ProductService productService = new ProductService(); 
	    Product newProduct = new Product();
	    newProduct.setName("Johans Fabric");
	    newProduct.setDescription("*3+ Lounger and  2 pillows. *Brand : Muebles Casa. *Dimensions (In Centimeters) :Lounger: H 79 x W 183 x D 83; 2 Seater Sofa: H 79 x W 130 x D 83; 1 Seater Sofa : H 79 x W 56 x D 83. * Seating Height : 17. *Weight :  65kg. * Frame Material : Meranti Wood & Plywood,  Fabric: PolyCotton * 10 days replacement.  * Perfect for Living room.");
		newProduct.setImageUrl("https://ii2.pepperfry.com/media/catalog/product/j/o/494x544/jordan-lhs-sectional-sofa-in-blue-colour-by-muebles-casa-jordan-lhs-sectional-sofa-in-blue-colour-by-anyyeu.jpg");
		newProduct.setPrice(5000);
	    newProduct.setDiscount(3);
	    newProduct.setQuantity(-3);
	    newProduct.setWarranty("3 years");
	    newProduct.setCategoryId(1000);
	    newProduct.setSellerId(15);
	    
	    Exception exception = assertThrows(ValidationException.class, () -> {
	    	productService.createProduct(newProduct);
		});
		String expectedMessage = "Category Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	} 
	
	@Test 
	@Order(10) 
	 void testCreateProductWithInvalidSeller() {
	    ProductService productService = new ProductService(); 
	    Product newProduct = new Product();
	    newProduct.setName("Johans Fabric");
	    newProduct.setDescription("*3+ Lounger and  2 pillows. *Brand : Muebles Casa. *Dimensions (In Centimeters) :Lounger: H 79 x W 183 x D 83; 2 Seater Sofa: H 79 x W 130 x D 83; 1 Seater Sofa : H 79 x W 56 x D 83. * Seating Height : 17. *Weight :  65kg. * Frame Material : Meranti Wood & Plywood,  Fabric: PolyCotton * 10 days replacement.  * Perfect for Living room.");
		newProduct.setImageUrl("https://ii2.pepperfry.com/media/catalog/product/j/o/494x544/jordan-lhs-sectional-sofa-in-blue-colour-by-muebles-casa-jordan-lhs-sectional-sofa-in-blue-colour-by-anyyeu.jpg");
		newProduct.setPrice(5000);
	    newProduct.setDiscount(3);
	    newProduct.setQuantity(3);
	    newProduct.setCategoryId(1000);
	    newProduct.setWarranty("3 years");
	    newProduct.setSellerId(15);
	    
	    Exception exception = assertThrows(ValidationException.class, () -> {
	    	productService.createProduct(newProduct);
		});
		String expectedMessage = "Category Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	} 
	
	@Test 
	@Order(11) 
	 void testCreateProductWithInvalidWarranty() {
	    ProductService productService = new ProductService(); 
	    Product newProduct = new Product();
	    newProduct.setName("Johans Fabric");
	    newProduct.setDescription("*3+ Lounger and  2 pillows. *Brand : Muebles Casa. *Dimensions (In Centimeters) :Lounger: H 79 x W 183 x D 83; 2 Seater Sofa: H 79 x W 130 x D 83; 1 Seater Sofa : H 79 x W 56 x D 83. * Seating Height : 17. *Weight :  65kg. * Frame Material : Meranti Wood & Plywood,  Fabric: PolyCotton * 10 days replacement.  * Perfect for Living room.");
		newProduct.setImageUrl("https://ii2.pepperfry.com/media/catalog/product/j/o/494x544/jordan-lhs-sectional-sofa-in-blue-colour-by-muebles-casa-jordan-lhs-sectional-sofa-in-blue-colour-by-anyyeu.jpg");
		newProduct.setPrice(5000);
	    newProduct.setDiscount(3);
	    newProduct.setQuantity(3);
	    newProduct.setCategoryId(3);
	    newProduct.setWarranty("3");
	    newProduct.setSellerId(15);
	    
	    Exception exception = assertThrows(ValidationException.class, () -> {
	    	productService.createProduct(newProduct);
		});
		String expectedMessage = "Warranty should be in digit followed by years or months";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	} 
	
	@Test 
	@Order(12) 
	 void testCreateCategoryWithValidaData() {
	    CategoryService categoryService = new CategoryService(); 
	    Category newCategory = new Category();
	    String name = RandomValue.generateRandomStringWithAlphabets(8); 
	    newCategory.setName(name);
	    assertDoesNotThrow(() -> { 
	        categoryService.createCategory(newCategory); 
	    });
	} 
	
	@Test 
	@Order(13) 
	 void testCreateCategoryWithInValidInput() {
		CategoryService categoryService = new CategoryService(); 
		Exception exception = assertThrows(ValidationException.class, () -> {
			 categoryService.createCategory(null); 
		});
		String expectedMessage = "Category object cannot be null";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	} 
	
	@Test 
	@Order(14) 
	 void testCreateCategoryWithShortName() {
		CategoryService categoryService = new CategoryService(); 
		Category newCategory = new Category();
		newCategory.setName("Lo");
	 
	    Exception exception = assertThrows(ValidationException.class, () -> {
	    	categoryService.createCategory(newCategory);
		});
		String expectedMessage = "Category Name should be at least 3 characters long";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	} 
	
	@Test 
	@Order(15) 
	 void testCreateCategoryWithWrongPattern() {
		CategoryService categoryService = new CategoryService(); 
		Category newCategory = new Category();
		newCategory.setName("Lo45 S34d fghj");
	 
	    Exception exception = assertThrows(ValidationException.class, () -> {
	    	categoryService.createCategory(newCategory);
		});
	    String expectedMessage = "Category Name should only contain alphabetic characters and allow spaces between words";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	} 
}
