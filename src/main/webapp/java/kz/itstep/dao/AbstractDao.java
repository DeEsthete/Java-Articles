package kz.itstep.dao;

import kz.itstep.entity.Entity;
import kz.itstep.entity.Role;

import java.util.List;

public abstract class AbstractDao<T extends Entity> {
    public abstract List<T> findAll();

    public abstract T findById(int id);

    public abstract boolean insert(T entity);

    public abstract boolean update(T entity);

    public abstract boolean delete(T entity);

    public abstract boolean delete(int id);
}
