package in.fssa.minimal.service;

import java.util.Set;
import in.fssa.minimal.dao.UserDAO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.User;
import in.fssa.minimal.validator.UserExists;
import in.fssa.minimal.validator.UserValidator;

public class UserService {
	public Set<User> getAll() throws PersistenceException {
		UserDAO userDao = new UserDAO();
		Set<User> userList = userDao.findAll();
		for (User user : userList) {
			System.out.println(user);
		}
		return userList;
	}

	public static User findById(int userId) throws ValidationException, PersistenceException {
		UserValidator.validateId(userId);
		UserExists.checkIdExists(userId);
		UserDAO userDao = new UserDAO();
		return userDao.findById(userId);
	}

	public User findByEmail(String email) throws ValidationException, PersistenceException {
		UserValidator.validateEmail(email);
		UserExists.checkEmailExists(email);
		UserDAO userDao = new UserDAO();
		return userDao.findByEmail(email);
	}

	public void create(User newUser) throws ValidationException, PersistenceException {
		UserValidator.Validate(newUser);
		UserExists.emailExists(newUser.getEmail());
		UserDAO userDao = new UserDAO();
		userDao.create(newUser);
	}

	public void update(int id, User updatedUser) throws ValidationException, PersistenceException {
		UserValidator.validateId(id);
		UserExists.checkIdExists(id);
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

	public void delete(int userId) throws ValidationException, PersistenceException {
		UserValidator.validateId(userId);
		UserExists.checkIdExists(userId);
		UserDAO userDao = new UserDAO();
		userDao.delete(userId);
	}

	
	public Set<User> getAllDesigner() throws PersistenceException {
		UserDAO userDao = new UserDAO();
		Set<User> userList = userDao.findAllDesigner();
		for (User user : userList) {
			System.out.println(user);
		}
		return userList;
	}

	public User findDesignerById(int userId) throws ValidationException, PersistenceException {
		UserValidator.validateId(userId);
		UserExists.checkDesignerIdExists(userId);
		UserDAO userDao = new UserDAO();
		return userDao.findDesignerById(userId);
	}
}
