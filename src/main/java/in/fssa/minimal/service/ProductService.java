package in.fssa.minimal.service;

import java.util.Set;

import in.fssa.minimal.dao.ProductDAO;
import in.fssa.minimal.dto.ProductRespondDTO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ServiceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Product;
import in.fssa.minimal.validator.DesignValidator;
import in.fssa.minimal.validator.ProductValidator;
import in.fssa.minimal.validator.UserValidator;

 
public class ProductService {
	
	public Set<ProductRespondDTO> getAllProduct() throws ServiceException, ValidationException {
	    try {
	        ProductDAO productDAO = new ProductDAO(); 
	        return productDAO.findAll(); 
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while retrieving products.", e);
	    }
	}  
	
	public static ProductRespondDTO findByProductId(int productId) throws ValidationException, ServiceException {
	    try {
	        ProductValidator.validateProductId(productId); 
	        ProductDAO productDAO = new ProductDAO(); 
	        return productDAO.findProductById(productId);
	    } catch (PersistenceException e) { 
	        throw new ServiceException("Error occurred while finding product by their id.", e);
	    }
	}

	public  Set<ProductRespondDTO>  findProductBySellerId(int sellerId) throws ValidationException, ServiceException {
	    try {
	        UserValidator.validateSellerId(sellerId);
	        ProductDAO productDAO = new ProductDAO(); 
	        return productDAO.findAllProductsBySellerId(sellerId);
	    } catch (PersistenceException e) { 
	        throw new ServiceException("Error occurred while finding product by their id.", e);
	    }
	} 

	public  Set<ProductRespondDTO>  findProductByCategoryId(int categoryId) throws ValidationException, ServiceException {
	    try {
	    	ProductValidator.validateCategoryId(categoryId);
	        ProductDAO productDAO = new ProductDAO(); 
	        return productDAO.findAllProductsByCategoryId(categoryId);
	    } catch (PersistenceException e) { 
	        throw new ServiceException("Error occurred while finding product by their id.", e);
	    }
	}
	
	public void createProduct(Product newProduct) throws ValidationException, ServiceException {
	    try {
	    	ProductValidator.validateProduct(newProduct);
	    	ProductDAO productDAO = new ProductDAO(); 
		    productDAO.create(newProduct);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while creating product.", e);
	    }
	}
	
	public void updateProduct(int productId, Product updatedProduct) throws ValidationException, ServiceException {
		 try {
		        ProductValidator.validateProductId(productId);
		        if(updatedProduct == null) {
		        	throw new ValidationException("Nothing has been updated in the product");
		        }
		        if (updatedProduct.getName() != null) {
		        	ProductValidator.validateProductName(updatedProduct.getName(), "Product Name");
		        }
		        if (updatedProduct.getImageUrl() != null) {
		        	DesignValidator.validateDescription(updatedProduct.getImageUrl());
		        }
		        if (updatedProduct.getDescription() != null) {
		        	DesignValidator.validateDescription(updatedProduct.getDescription());
		        }
		        if (updatedProduct.getSellerId() <= 0) {
		        	UserValidator.validateSellerId(updatedProduct.getSellerId());
		        }
		        if (updatedProduct.getCategoryId() <= 0) {
		        	ProductValidator.validateCategoryId(updatedProduct.getCategoryId());
		        }
		        if (updatedProduct.getPrice() <= 0) {
		        	ProductValidator.validatePrice(updatedProduct.getPrice());
		        }
		        if (updatedProduct.getDiscount() <= 0) {
		        	ProductValidator.validateDiscount(updatedProduct.getDiscount());
		        }
		        if (updatedProduct.getWarranty() != null) {
		        	ProductValidator.validateWarranty(updatedProduct.getWarranty());
		        }
		        if (updatedProduct.getQuantity() <= 0) {
		        	ProductValidator.validateQuantity(updatedProduct.getQuantity());
		        }
		        ProductDAO productDAO = new ProductDAO(); 
			    productDAO.update(productId, updatedProduct);
		    } catch (PersistenceException e) {
		        throw new ServiceException("Error occurred while updating product", e);
		    }
	}
	
	public void deleteProduct(int productId) throws ValidationException, ServiceException {
	    try {
	    	ProductValidator.validateProductId(productId);
	    	ProductDAO productDAO = new ProductDAO(); 
		    productDAO.delete(productId);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while creating product.", e);
	    }
	}
	public void reactivateProduct(int productId) throws ValidationException, ServiceException {
	    try {
	    	ProductDAO productDAO = new ProductDAO(); 
		    productDAO.reactivateProduct(productId);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while creating product.", e);
	    }
	}
}
