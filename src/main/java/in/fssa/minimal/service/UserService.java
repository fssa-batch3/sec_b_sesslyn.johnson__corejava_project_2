package in.fssa.minimal.service;

import java.util.Set;
import in.fssa.minimal.dao.UserDAO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ServiceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.User;
import in.fssa.minimal.validator.UserValidator;

public class UserService {
	/**
	 * Retrieves a set of all users from the database.
	 *
	 * @return A set containing all User objects in the database.
	 * @throws ServiceException If a service-related error occurs during retrieval.
	 */
	public Set<User> getAllUser() throws ServiceException {
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
	public static User findById(int id) throws ValidationException, ServiceException {
	    try {
	        UserValidator.validateId(id);
	        UserDAO userDAO = new UserDAO();
	        return userDAO.findById(id);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while finding user by ID.", e);
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
	        throw new ServiceException("Error occurred while finding user by email.", e);
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
	        UserValidator.validate(newUser);
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
	public void updateUser(int id, User updateUser) throws ValidationException, ServiceException {
		    try {
		    	 UserDAO userDAO = new UserDAO();
		    	 User user = userDAO.findById(id);
		        UserValidator.validateId(id);
		        UserValidator.validateUpdateUserFields(id, updateUser);
		       
		        if (updateUser.getName() != null) {
		            UserValidator.validateName(updateUser.getName());
		        }
		       
		        if (updateUser.getPhoneNumber() != 0) {
		            UserValidator.validatePhoneNumber(updateUser.getPhoneNumber());
		        }
		        if (updateUser.isDesigner() != user.isDesigner()) {
		            updateUser.setDesigner(user.isDesigner());
		            boolean result = UserValidator.validateDesignerStatus(updateUser.isDesigner(), user.isDesigner());
		        }
		        if (updateUser.getPassword() != null) {
		            UserValidator.validatePassword(updateUser.getPassword());
		        } else {
		            updateUser.setPassword(user.getPassword()); // Set the existing password
		        }

		       
		       
		        userDAO.update(id, updateUser);
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
	public void deleteUser(int id) throws ValidationException, ServiceException {
	    try {
	        UserValidator.validateId(id);
	        UserDAO userDAO = new UserDAO();
	        userDAO.delete(id);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while deleting user.", e);
	    }
	}

	/**
	 * Retrieves a set of all designer users from the database.
	 *
	 * @return A set containing all designer User objects in the database.
	 * @throws ServiceException If a service-related error occurs during retrieval.
	 */
	public Set<User> getAllDesigner() throws ServiceException {
	    try {
	        UserDAO userDAO = new UserDAO();
	        return userDAO.findAllDesigner();
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while retrieving designer users.", e);
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
	public User findByDesignerId(int id) throws ValidationException, ServiceException {
	    try {
	        UserValidator.validateDesignerId(id);
	        UserDAO userDao = new UserDAO();
	        return userDao.findDesignerById(id);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while finding designer user by ID.", e);
	    }
	}

}
