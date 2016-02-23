package app.services;

import app.entity.User;

import java.util.List;

public interface AbstractServiceInterface<T> {

    void save(T obj);
    void update(T obj);
    void delete(long id);
    List<T> list();
    T findById(long id);


}
