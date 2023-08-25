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
	public Set<User> getAll() throws ServiceException {
	    try {
	        UserDAO userDao = new UserDAO();
	        return userDao.findAll();
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while retrieving users.", e);
	    }
	}

	/**
	 * Retrieves a user by their ID.
	 *
	 * @param userId The ID of the user to retrieve.
	 * @return The User object corresponding to the given ID.
	 * @throws ValidationException If the provided ID is invalid.
	 * @throws ServiceException   If a service-related error occurs during retrieval.
	 */
	public static User findById(int userId) throws ValidationException, ServiceException {
	    try {
	        UserValidator.validateId(userId);
	        UserDAO userDao = new UserDAO();
	        return userDao.findById(userId);
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

	        UserDAO userDao = new UserDAO();
	        return userDao.findByEmail(email);
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
	public void create(User newUser) throws ValidationException, ServiceException {
	    try {
	        UserValidator.validate(newUser);
	        UserDAO userDao = new UserDAO();
	        userDao.create(newUser);
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
	public void update(int id, User updatedUser) throws ValidationException, ServiceException {
	    try {
	        UserValidator.validateId(id);
	        if (updatedUser.getName() != null) {
	            UserValidator.validateName(updatedUser.getName());
	        }
	        if (updatedUser.getPassword() != null) {
	            UserValidator.validatePassword(updatedUser.getPassword());
	        }
	        if (updatedUser.getPhoneNumber() != 0) {
	            UserValidator.validatePhoneNumber(updatedUser.getPhoneNumber());
	        }
	        UserDAO userDao = new UserDAO();
	        userDao.update(id, updatedUser);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while updating user.", e);
	    }
	}

	/**
	 * Deletes a user.
	 *
	 * @param userId The ID of the user to delete.
	 * @throws ValidationException If the provided ID is invalid.
	 * @throws ServiceException   If a service-related error occurs during deletion.
	 */
	public void delete(int userId) throws ValidationException, ServiceException {
	    try {
	        UserValidator.validateId(userId);
	        UserDAO userDao = new UserDAO();
	        userDao.delete(userId);
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
	        UserDAO userDao = new UserDAO();
	        return userDao.findAllDesigner();
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while retrieving designer users.", e);
	    }
	}

	/**
	 * Retrieves a designer user by their ID.
	 *
	 * @param userId The ID of the designer user to retrieve.
	 * @return The User object corresponding to the given designer ID.
	 * @throws ValidationException If the provided ID is invalid.
	 * @throws ServiceException   If a service-related error occurs during retrieval.
	 */
	public User findDesignerById(int userId) throws ValidationException, ServiceException {
	    try {
	        UserValidator.validateDesignerId(userId);
	        UserDAO userDao = new UserDAO();
	        return userDao.findDesignerById(userId);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while finding designer user by ID.", e);
	    }
	}

}
