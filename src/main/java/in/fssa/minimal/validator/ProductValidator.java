package in.fssa.minimal.validator;

import java.util.regex.Pattern;
import in.fssa.minimal.dao.CategoryDAO;
import in.fssa.minimal.dao.ProductDAO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ServiceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Product;
import in.fssa.minimal.util.StringUtil;

public class ProductValidator {
	
	private static final String NAME_PATTERN = "^[A-Za-z]+(\\s[A-Za-z]+)*$";
	private static final String WARRANTY_PATTERN = "^(\\d{1,2})\\s(years?|months?)$";

	public static void validateProduct(Product product) throws ValidationException, ServiceException, PersistenceException {
		if (product == null) {
			throw new ValidationException("Product object cannot be null");
		}
		validateProductName(product.getName(), "Product Name");
		UserValidator.validateImage(product.getImageUrl()); 
		DesignValidator.validateDescription(product.getDescription());
		UserValidator.validateSellerId(product.getSellerId()); 
		validateCategoryId(product.getCategoryId());
		validatePrice(product.getPrice());
		validateDiscount(product.getDiscount()); 
		validateWarranty(product.getWarranty());
		validateQuantity(product.getQuantity());
	} 
	 
	public static void validateProductId(int productId) throws ValidationException, ServiceException {
		try {
			UserValidator.validateId("Product Id", productId);
			 ProductDAO.checkProductExist(productId);
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred during validation", e);
		}
	}
	
    public static void validateProductName(String productName, String value) throws ValidationException, ServiceException {
		StringUtil.rejectIfInvalidString(productName, value);
		productName = productName.trim();
		try {
			ProductDAO.checkProductNameExist(productName);
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred during validation", e);
		}
		if (productName.length() < 3) {
			throw new ValidationException(value + " should be at least 3 characters long");
		}
		if (!Pattern.matches(NAME_PATTERN, productName)) {
			throw new ValidationException(
					value + " should only contain alphabetic characters and allow spaces between words");
		}
	}
    
    public static void validateCategoryId(int categoryId) throws ValidationException, PersistenceException {
    	UserValidator.validateId("Category Id", categoryId);
	    CategoryDAO.checkCategoryExistWithId(categoryId);
    }
    
    public static void validatePrice(int price) throws ValidationException {
        if (price < 300 || price > 100000) {
            throw new ValidationException("Price should be in the range between 300 and 100,000");
        }
    }
    
    public static void validateDiscount(int discount) throws ValidationException {
        if (discount < 1 || discount > 99) {
            throw new ValidationException("Discount should be in the range between 1 and 99");
        }
    }
    
    public static void validateQuantity(int quantity) throws ValidationException {
        if (quantity < 1 || quantity > 99) {
            throw new ValidationException("Quantity should be in the range between 1 and 99");
        }
    } 
    
    public static void validateWarranty(String warranty) throws ValidationException {
        StringUtil.rejectIfInvalidString(warranty, "Warranty");
        warranty = warranty.trim();
        if (!warranty.matches(WARRANTY_PATTERN)) {
            throw new ValidationException("Warranty should be in digit followed by years or months");
        }
    }


}
