package dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> {

    T add(T t) throws SQLException;

    List<T> getAll() throws SQLException;

    T update(T t) throws SQLException;

    void delete (long id) throws SQLException;
}
