package in.fssa.minimal.interfaces;

import java.util.Set;

public interface Base<T> {
	public abstract <T> T findAll();
	public abstract void create(T object);
	public abstract <T> T findById(int id);
}