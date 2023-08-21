package in.fssa.minimal.service;

import java.util.Set;
import in.fssa.minimal.dao.UserDAO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.User;
import in.fssa.minimal.validator.UserValidator;

public class UserService {
	 /**
     * Retrieves a set of all users from the database.
     *
     * @return A set containing all User objects in the database.
     * @throws PersistenceException If a database-related error occurs during retrieval.
     */
	public Set<User> getAll() throws PersistenceException {
		UserDAO userDao = new UserDAO();
		Set<User> userList = userDao.findAll();
		for (User user : userList) {
			System.out.println(user);
		}
		return userList;
	}

	/**
     * Retrieves a user by their ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return The User object corresponding to the given ID.
     * @throws ValidationException If the provided ID is invalid.
     * @throws PersistenceException If a database-related error occurs during retrieval.
     */
	public static User findById(int userId) throws ValidationException, PersistenceException {
		UserValidator.validateId(userId);
		UserDAO.checkIdExists(userId);
		UserDAO userDao = new UserDAO();
		return userDao.findById(userId);
	}

	 /**
     * Retrieves a user by their email.
     *
     * @param email The email address of the user to retrieve.
     * @return The User object corresponding to the given email address.
     * @throws ValidationException If the provided email address is invalid.
     * @throws PersistenceException If a database-related error occurs during retrieval.
     */
	public User findByEmail(String email) throws ValidationException, PersistenceException {
		UserValidator.validateEmail(email);
		UserDAO.checkEmailExists(email);
		UserDAO userDao = new UserDAO();
		return userDao.findByEmail(email);
	}

	 /**
     * Creates a new user.
     *
     * @param newUser The User object representing the new user.
     * @throws ValidationException If the provided user data is invalid.
     * @throws PersistenceException If a database-related error occurs during creation.
     */
	public void create(User newUser) throws ValidationException, PersistenceException {
		UserValidator.validate(newUser);
		UserDAO.emailExists(newUser.getEmail());
		UserDAO userDao = new UserDAO();
		userDao.create(newUser);
	}

	  /**
     * Updates a user's information.
     *
     * @param id The ID of the user to update.
     * @param updatedUser The User object containing updated information.
     * @throws ValidationException If the provided data is invalid.
     * @throws PersistenceException If a database-related error occurs during update.
     */
	public void update(int id, User updatedUser) throws ValidationException, PersistenceException {
		UserValidator.validateId(id);
		UserDAO.checkIdExists(id);
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
	}


    /**
     * Deletes a user.
     *
     * @param userId The ID of the user to delete.
     * @throws ValidationException If the provided ID is invalid.
     * @throws PersistenceException If a database-related error occurs during deletion.
     */
	public void delete(int userId) throws ValidationException, PersistenceException {
		UserValidator.validateId(userId);
		UserDAO.checkIdExists(userId);
		UserDAO userDao = new UserDAO();
		userDao.delete(userId);
	}

	/**
     * Retrieves a set of all designer users from the database.
     *
     * @return A set containing all designer User objects in the database.
     * @throws PersistenceException If a database-related error occurs during retrieval.
     */
	public Set<User> getAllDesigner() throws PersistenceException {
		UserDAO userDao = new UserDAO();
		Set<User> userList = userDao.findAllDesigner();
		for (User user : userList) {
			System.out.println(user);
		}
		return userList;
	}

	 /**
     * Retrieves a designer user by their ID.
     *
     * @param userId The ID of the designer user to retrieve.
     * @return The User object corresponding to the given designer ID.
     * @throws ValidationException If the provided ID is invalid.
     * @throws PersistenceException If a database-related error occurs during retrieval.
     */
	public User findDesignerById(int userId) throws ValidationException, PersistenceException {
		UserValidator.validateId(userId);
		UserDAO.checkDesignerIdExists(userId);
		UserDAO userDao = new UserDAO();
		return userDao.findDesignerById(userId);
	}
}
