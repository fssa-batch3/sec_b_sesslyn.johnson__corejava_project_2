package in.fssa.minimal.interfaces;

import java.util.Set;

import in.fssa.minimal.exception.ValidationException;

public interface Base<T> {
	public abstract <T> T findAll();
	public abstract void create(T object);
	public abstract <T> T findById(int id) ;
}