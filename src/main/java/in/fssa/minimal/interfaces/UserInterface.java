package in.fssa.minimal.interfaces;

import in.fssa.minimal.model.User;


public interface UserInterface extends Base<User>{
	public abstract void update(int id, User object);
	public abstract void delete(int id);
	public abstract User findByEmail(String email);
	public abstract <User> User findAllDesigner();
	public abstract <User> User findDesignerById(int id);
}
