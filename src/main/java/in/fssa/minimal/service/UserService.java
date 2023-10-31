package in.fssa.minimal.service;

import java.util.Set;
import in.fssa.minimal.dao.UserDAO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ServiceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.User;
import in.fssa.minimal.util.StringUtil;
import in.fssa.minimal.validator.UserValidator;

public class UserService {
	
	/**
	 * Retrieves a set of all users from the database.
	 *
	 * @return A set containing all User objects in the database.
	 * @throws ServiceException If a service-related error occurs during retrieval.
	 * @throws ValidationException If a validation error occurs during the operation.
	 */
	public Set<User> getAllUser() throws ServiceException, ValidationException {
	    try {
	        UserDAO userDAO = new UserDAO(); 
	        return userDAO.findAll(); 
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while retrieving users.", e);
	    }
	}    

	/**
	 * Retrieves a user by their ID.
	 *
	 * @param id The ID of the user to retrieve.
	 * @return The User object corresponding to the given ID.
	 * @throws ValidationException If the provided ID is invalid.
	 * @throws ServiceException   If a service-related error occurs during retrieval.
	 */
	public static User findByUserId(int userId) throws ValidationException, ServiceException {
	    try {
	        UserValidator.validateUserId(userId);
	        UserDAO userDAO = new UserDAO();
	        return userDAO.findById(userId);
	    } catch (PersistenceException e) { 
	        throw new ServiceException("Error occurred while finding user by their id.", e);
	    }
	}

	/**
	 * Retrieves a user by their email address.
	 *
	 * @param email The email address of the user to retrieve.
	 * @return The User object corresponding to the given email address.
	 * @throws ValidationException If the provided email address is invalid.
	 * @throws ServiceException   If a service-related error occurs during retrieval.
	 */
	public User findByEmail(String email) throws ValidationException, ServiceException {
	    try {
	        UserValidator.validateEmail(email);
	        UserDAO userDAO = new UserDAO(); 
	        return userDAO.findByEmail(email);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while finding user by their email.", e);
	    }
	}

	/**
	 * Creates a new user.
	 *
	 * @param newUser The User object representing the new user.
	 * @throws ValidationException If the provided user data is invalid.
	 * @throws ServiceException   If a service-related error occurs during creation.
	 */
	public void createUser(User newUser) throws ValidationException, ServiceException {
	    try {
	    	UserValidator.validateUser(newUser);
	    	String role = newUser.getRole();
	    	if(role.equals("seller")) {
	    		createSeller(newUser);
	    	}else if(role.equals("designer")) {
	    		createDesigner(newUser);
	    	}else {
	       
	        UserDAO userDAO = new UserDAO();
	        userDAO.create(newUser);
	    	}
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while creating user.", e);
	    }
	}
	
	/**
	 * Creates a designer user.
	 *
	 * @param newUser The User object representing the new designer user to create.
	 * @throws ValidationException If validation of the user object fails.
	 * @throws ServiceException If a service-related error occurs during user creation.
	 */
	public void createDesigner(User newUser) throws ValidationException, ServiceException {
	    try {
	        UserValidator.validateUser(newUser);
	       UserValidator.validateDesigner(newUser);
	        UserDAO userDAO = new UserDAO();
	        userDAO.create(newUser);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while creating user.", e);
	    }
	} 
	
	/**
	 * Creates a seller user.
	 *
	 * @param newUser The User object representing the new seller user to create.
	 * @throws ValidationException If validation of the user object fails.
	 * @throws ServiceException If a service-related error occurs during user creation.
	 */
	public void createSeller(User newUser) throws ValidationException, ServiceException {
	    try {
	        UserValidator.validateUser(newUser);
	       UserValidator.validateSeller(newUser);
	        UserDAO userDAO = new UserDAO();
	        userDAO.create(newUser);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while creating user.", e);
	    }
	}

	/**
	 * Updates a user's information.
	 *
	 * @param id          The ID of the user to update.
	 * @param updatedUser The User object containing updated information.
	 * @throws ValidationException If the provided data is invalid.
	 * @throws ServiceException   If a service-related error occurs during update.
	 */
	public void updateUser(int userId, User updatedUser) throws ValidationException, ServiceException {
	    try {
	        UserValidator.validateUserId(userId);

	        // Validate updated user fields
	        if (updatedUser != null) {
	            if (updatedUser.getName() != null && !updatedUser.getName().trim().isEmpty()) {
	                UserValidator.validateName(updatedUser.getName().trim());
	            }
	            if (updatedUser.getImage() != null && !updatedUser.getImage().trim().isEmpty()) {
	                UserValidator.validateImage(updatedUser.getImage().trim());
	            }
	            if (updatedUser.getPhoneNumber() != 0) {
	                UserValidator.validatePhoneNumber(updatedUser.getPhoneNumber());
	            }
	            if (updatedUser.getDateOfBirth() != null) {
	                UserValidator.validateDateOfBirth(updatedUser.getDateOfBirth());
	            }
	            if (updatedUser.getGender() != null) {
	                UserValidator.validateGender(updatedUser.getGender());
	            }
	            if (updatedUser.getRole() == null) {
	                updatedUser.setRole("user");
	            }
	            if (updatedUser.getRole().equals("seller")) {
	                if (updatedUser.getGst_number() != null && !updatedUser.getGst_number().trim().isEmpty()) {
	                    UserValidator.validateGstNumber(updatedUser.getGst_number().trim());
	                }
	                if (updatedUser.getShop_address() != null && !updatedUser.getShop_address().trim().isEmpty()) {
	                    StringUtil.rejectIfInvalidString(updatedUser.getShop_address().trim(), "Shop Address");
	                }
	            } else if (updatedUser.getRole().equals("designer")) {
	                if (updatedUser.getExperience() != 0) {
	                    UserValidator.validateExperience("Experience", updatedUser.getExperience());
	                }
	                if (updatedUser.getDesigner_description() != null && !updatedUser.getDesigner_description().trim().isEmpty()) {
	                    StringUtil.rejectIfInvalidString(updatedUser.getDesigner_description().trim(), "Designer Description");
	                }
	            }
	        }

	        UserDAO userDao = new UserDAO();
	        userDao.update(userId, updatedUser);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while updating user.", e);
	    }
	}


	/**
	 * Deletes a user.
	 *
	 * @param Id The ID of the user to delete.
	 * @throws ValidationException If the provided ID is invalid.
	 * @throws ServiceException   If a service-related error occurs during deletion.
	 */
	public void deleteUser(int userId) throws ValidationException, ServiceException {
	    try {
	        UserValidator.validateUserId(userId);
	        UserDAO userDAO = new UserDAO();
	        userDAO.delete(userId);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while deleting user.", e);
	    }
	}

	/**
	 * Retrieves a set of all designer users from the database.
	 *
	 * @return A set containing all designer User objects in the database.
	 * @throws ServiceException If a service-related error occurs during retrieval.
	 * @throws ValidationException If validation of the retrieved data fails.
	 */
	public Set<User> getAllDesigner() throws ServiceException, ValidationException {
	    try {
	        UserDAO userDAO = new UserDAO();
	        return userDAO.findAllDesigner();
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while retrieving designers.", e);
	    }
	}
	
	/**
	 * Retrieves a set of all seller users from the database.
	 *
	 * @return A set containing all seller User objects in the database.
	 * @throws ServiceException If a service-related error occurs during retrieval.
	 * @throws ValidationException If validation of the retrieved data fails.
	 */
	public Set<User> getAllSeller() throws ServiceException, ValidationException {
	    try {
	        UserDAO userDAO = new UserDAO();
	        return userDAO.findAllSeller();
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while retrieving designers.", e);
	    }
	}

	/**
	 * Retrieves a designer user by their ID.
	 *
	 * @param id The ID of the designer user to retrieve.
	 * @return The User object corresponding to the given designer ID.
	 * @throws ValidationException If the provided ID is invalid.
	 * @throws ServiceException   If a service-related error occurs during retrieval.
	 */
	public User findByDesignerId(int designerId) throws ValidationException, ServiceException {
	    try {
	        UserValidator.validateDesignerId(designerId);
	        UserDAO userDao = new UserDAO();
	        return userDao.findDesignerById(designerId);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while finding designer by their id.", e);
	    }
	}
	
	/**
	 * Retrieves a seller user from the database based on their seller ID.
	 *
	 * @param sellerId The ID of the seller user to retrieve.
	 * @return The User object corresponding to the provided seller user ID.
	 * @throws ValidationException If validation of the provided seller ID fails.
	 * @throws ServiceException If a service-related error occurs during retrieval.
	 */
	public static User findBySellerId(int sellerId) throws ValidationException, ServiceException {
	    try {
	        UserValidator.validateSellerId(sellerId);
	        UserDAO userDao = new UserDAO();
	        return userDao.findSellerById(sellerId);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while finding designer by their id.", e);
	    }
	}

	/** 
	 * Retrieves a user from the database based on their user ID for appointment purposes.
	 *
	 * @param userId The ID of the user to retrieve.
	 * @return The User object corresponding to the provided user ID.
	 * @throws ValidationException If validation of the provided user ID fails.
	 * @throws ServiceException If a service-related error occurs during retrieval.
	 */
	public static User findByUserIdForAppointment(int userId) throws ValidationException, ServiceException {
	    try {
	        UserValidator.validateId("Id", userId);
	        UserDAO userDAO = new UserDAO();
	        return userDAO.findByUserIdForAppointment(userId);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while finding user by their id.", e);
	    }
	}
}
