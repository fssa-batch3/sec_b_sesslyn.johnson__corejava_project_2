package in.fssa.minimal.interfaces;

import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.model.User;


public interface UserInterface extends Base<User>{
	public abstract void update(int id, User object) throws PersistenceException;
	public abstract void delete(int id) throws PersistenceException;
	public abstract User findByEmail(String email) throws PersistenceException;
	public abstract <User> User findAllDesigner() throws PersistenceException;
	public abstract <User> User findDesignerById(int id) throws PersistenceException;
}
