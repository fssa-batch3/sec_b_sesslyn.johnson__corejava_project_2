package in.fssa.minimal.service;

import java.util.Set;
import in.fssa.minimal.dao.CategoryDAO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ServiceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Category;
import in.fssa.minimal.validator.ProductValidator;
import in.fssa.minimal.validator.UserValidator;

public class CategoryService {
	
	public void createCategory(Category newCategory) throws ValidationException, ServiceException {
	    try {
	    	if(newCategory == null) {
	    		throw new ValidationException("Category object cannot be null");
	    	}
	    	ProductValidator.validateProductName(newCategory.getName(), "Category Name");
	        CategoryDAO categoryDAO = new CategoryDAO();
	        categoryDAO.create(newCategory);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while creating category.", e);
	    }
	}
	
	public void updateCategory(int categoryId, Category updateCategory)
	        throws ValidationException, PersistenceException, ServiceException {
	    try {
	    	if(updateCategory == null) {
	    		throw new ValidationException("Nothing has been updated in the category");
	    	}
	        UserValidator.validateId("Category Id", categoryId);
	        CategoryDAO.checkCategoryExistWithId(categoryId);
            ProductValidator.validateProductName(updateCategory.getName(), "Category Name");
            CategoryDAO categoryDAO = new CategoryDAO();
	        categoryDAO.update(categoryId, updateCategory);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while updating category", e);
	    }
	}
	
	public void deleteCategory(int categoryId) throws ValidationException, ServiceException {
	    try {
	        UserValidator.validateId("Category Id",categoryId);
	        CategoryDAO.checkCategoryExistWithId(categoryId);
	        CategoryDAO categoryDAO = new CategoryDAO();
	        categoryDAO.delete(categoryId);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while deleting category.", e);
	    }
	}

	public Set<Category> getAllCategory() throws ServiceException {
	    try {
	    	CategoryDAO categoryDAO = new CategoryDAO();
	        return categoryDAO.findAll(); 
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while retrieving categories.", e);
	    }
	}   
	
	public Category getAllCategoryById(int categoryId) throws ServiceException, ValidationException {
	    try {
	    	UserValidator.validateId("Category Id", categoryId);
		    CategoryDAO.checkCategoryExistWithId(categoryId);
	    	CategoryDAO categoryDAO = new CategoryDAO();
	        return categoryDAO.findCategoryById(categoryId); 
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while retrieving categories.", e);
	    }
	}   

}
