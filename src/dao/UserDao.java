package dao;

import entities.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao extends GenericDao<User> {

    List<String> getUsernames() throws SQLException;
    List<String> getEmails() throws SQLException;
    List<User> getUsersByRole(String role) throws SQLException;
}
