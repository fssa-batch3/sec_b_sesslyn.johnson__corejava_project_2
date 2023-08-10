package in.fssa.minimal.interfaces;

import in.fssa.minimal.exception.PersistenceException;

public interface Base<T> {
	public abstract <T> T findAll() throws PersistenceException;
	public abstract void create(T object) throws PersistenceException;
	public abstract <T> T findById(int id) throws PersistenceException ;
}