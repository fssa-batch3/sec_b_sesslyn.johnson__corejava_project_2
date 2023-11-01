package in.fssa.minimal.interfaces;

import java.util.List;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.User;

public interface Base<T> {
    public abstract List<User> findAll() throws PersistenceException, ValidationException;
    public abstract void create(T object) throws PersistenceException,ValidationException;
    public abstract T findById(int id) throws PersistenceException, ValidationException;
}
