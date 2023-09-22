package in.fssa.minimal.interfaces;

import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ServiceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.User;


public interface UserInterface extends Base<User> {
    public abstract void update(int id, User object) throws PersistenceException, ValidationException, ServiceException;
    public abstract void delete(int id) throws PersistenceException;
    public abstract User findByEmail(String email) throws PersistenceException, ValidationException;
  
}

