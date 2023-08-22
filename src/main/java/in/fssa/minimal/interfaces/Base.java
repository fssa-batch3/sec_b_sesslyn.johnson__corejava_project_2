package in.fssa.minimal.interfaces;

import java.util.Set;
import in.fssa.minimal.exception.PersistenceException;

public interface Base<T> {
    public abstract Set<T> findAll() throws PersistenceException;
    public abstract void create(T object) throws PersistenceException;
    public abstract T findById(int id) throws PersistenceException;
}
