package dao.impl;

import dao.UserDao;
import entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDao implements UserDao {

    private static final String GET_ALL_USERS = "SELECT * FROM users";
    private static final String GET_USERNAMES_QUERY = "SELECT username FROM users";
    private static final String GET_EMAILS_QUERY = "SELECT user_email FROM users";
    private static final String GET_USERS_BY_ROLE_QUERY = "SELECT * FROM users WHERE role=?";
    private static final String ADD_USER_QUERY = "INSERT INTO users" +
            "(username, user_email, user_password) VALUES (?, ?, ?)";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET username,=?" +
            "user_email=?, user_password=?, role=? WHERE user_id=?";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE user_id=?";

    private PreparedStatement getUsernames;
    private PreparedStatement getEmails;
    private PreparedStatement getUsersByRole;
    private PreparedStatement getAllUsers;
    private PreparedStatement addUser;
    private PreparedStatement updateUser;
    private PreparedStatement deleteUser;


    private static volatile UserDao INSTANCE = null;

    public static UserDao getInstance() {

        UserDao userDao = INSTANCE;
        if (userDao == null) {
            synchronized (UserDaoImpl.class) {
                userDao = INSTANCE;
                if (userDao == null) {
                    INSTANCE = userDao = new UserDaoImpl();
                }
            }
        }
        return userDao;
    }

    private User populateEntity(ResultSet resultSet) throws SQLException {

        User entity = new User();

        entity.setUser_id(resultSet.getLong(1));
        entity.setUsername(resultSet.getString(2));
        entity.setUser_email(resultSet.getString(3));
        entity.setUser_password(resultSet.getString(4));
        entity.setRole(resultSet.getString(5));

        return entity;
    }

    @Override
    public User add(User user) throws SQLException{

    addUser = preparedStatement(ADD_USER_QUERY);
    addUser.setString(1, user.getUsername());
    addUser.setString(2, user.getUser_email());
    addUser.setString(3, user.getUser_password());
    addUser.executeUpdate();

        return user;
    }

    @Override
    public List<String> getUsernames() throws SQLException {

        getUsernames = preparedStatement(GET_USERNAMES_QUERY);

        List<String> usernamesList = new ArrayList<>();
        ResultSet resultSet = getUsernames.executeQuery();

        while (resultSet.next()) {
            usernamesList.add(resultSet.getString(1));
        }
        close(resultSet);
        return usernamesList;
    }

    @Override
    public List<String> getEmails() throws SQLException {

        getEmails = preparedStatement(GET_EMAILS_QUERY);

        List<String> emailsList = new ArrayList<>();
        ResultSet resultSet = getEmails.executeQuery();

        while (resultSet.next()) {
            emailsList.add(resultSet.getString(1));
        }
        close(resultSet);
        return emailsList;
    }

    @Override
    public List<User> getUsersByRole(String role) throws SQLException {

        getUsersByRole = preparedStatement(GET_USERS_BY_ROLE_QUERY);
        getUsersByRole.setString(1, role);

        List<User> userList = new ArrayList<>();
        ResultSet resultSet = getUsersByRole.executeQuery();

        while (resultSet.next()) {
            userList.add(populateEntity(resultSet));
        }
        close(resultSet);
        return userList;
    }

    @Override
    public List<User> getAll() throws SQLException {

        getAllUsers = preparedStatement(GET_ALL_USERS);

        List<User> userList = new ArrayList<>();
        ResultSet resultSet = getAllUsers.executeQuery();

        while (resultSet.next()) {
            userList.add(populateEntity(resultSet));
        }
        close(resultSet);
        return userList;
    }

    @Override
    public User update(User user) throws SQLException {

        updateUser = preparedStatement(UPDATE_USER_QUERY);
        updateUser.setString(1, user.getUsername());
        updateUser.setString(2, user.getUser_email());
        updateUser.setString(3, user.getUser_password());
        updateUser.setString(4, user.getRole());
        updateUser.setLong(5, user.getUser_id());
        updateUser.executeUpdate();

        return user;
    }

    @Override
    public void delete(long user_id) throws SQLException {

        deleteUser = preparedStatement(DELETE_USER_QUERY);
        deleteUser.setLong(1, user_id);
        deleteUser.executeUpdate();
    }
}

