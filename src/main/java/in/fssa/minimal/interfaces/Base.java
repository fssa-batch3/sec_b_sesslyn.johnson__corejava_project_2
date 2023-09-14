package in.fssa.minimal.interfaces;

import java.util.Set;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;

public interface Base<T> {
    public abstract Set<T> findAll() throws PersistenceException, ValidationException;
    public abstract void create(T object) throws PersistenceException,ValidationException;
    public abstract T findById(int id) throws PersistenceException, ValidationException;
}
