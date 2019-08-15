package services;

import java.util.List;

public interface GenericService<T> {

    T add(T t);

    T update(T t);

    void delete(long id);

    List<T> getAll();
}
