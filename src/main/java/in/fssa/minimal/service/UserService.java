package in.fssa.minimal.service;

import java.util.Set;

import in.fssa.minimal.dao.UserDAO;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.User;
import in.fssa.minimal.validator.UserValidator;

public class UserService {
	public Set<User> getAll() {
		UserDAO userDao = new UserDAO();
		Set<User> userList = userDao.findAll();
		for (User user : userList) {
			System.out.println(user);
		}
		return userList;
	}

	public User findById(int userId) throws ValidationException {
		UserValidator.validateId(userId);
		UserDAO userDao = new UserDAO();
		return userDao.findById(userId);
	}

	public User findByEmail(String email) throws ValidationException {
		UserValidator.validateEmail(email);
		UserDAO userDao = new UserDAO();
		return userDao.findByEmail(email);
	}
	
	public void create(User newUser) throws ValidationException {
		UserValidator.Validate(newUser);
		UserDAO userDao = new UserDAO();
		userDao.create(newUser);
	}

	public void update(int id, User updatedUser) throws ValidationException {
		UserValidator.validateId(id);
		UserValidator.Validate(updatedUser);
		UserDAO userDao = new UserDAO();
		userDao.update(id, updatedUser);
	}

	public void delete(int userId) throws ValidationException {
		UserValidator.validateId(userId);
		UserDAO userDao = new UserDAO();
		userDao.delete(userId);
	}

	public Set<User> getAllDesigner() {
		UserDAO userDao = new UserDAO();
		Set<User> userList = userDao.findAllDesigner();
		for (User user : userList) {
			System.out.println(user);
		}
		return userList;
	}

	public User findDesignerById(int userId) throws ValidationException {
		UserValidator.validateId(userId);
		UserDAO userDao = new UserDAO();
		return userDao.findDesignerById(userId);
	}
}
