package services.impl;

import com.google.protobuf.ServiceException;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import entities.User;
import org.apache.log4j.Logger;
import services.UserService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl extends AbstractService implements UserService {

    private static final Logger LOGGER = Logger.getLogger(TrackServiceImpl.class);
    private static volatile UserService INSTANCE = null;
    private UserDao userDao = UserDaoImpl.getInstance();
    public static boolean userErrorStatusLog=false;

    public static UserService getInstance() {
        UserService userService = INSTANCE;
        if (userService == null) {
            synchronized (UserServiceImpl.class) {
                userService = INSTANCE;
                if (userService == null) {
                    INSTANCE = userService = new UserServiceImpl();
                }
            }
        }
        return userService;
    }

    @Override
    public List<String> getUsernames() {

        List<String> usernamesList = new ArrayList<>();
        try {
            usernamesList = userDao.getUsernames();
            userErrorStatusLog=false;
        } catch (SQLException e) {
            LOGGER.error("Error get usernames");
            userErrorStatusLog=true;
        }
        return usernamesList;
    }

    @Override
    public List<String> getEmails() {

            List<String> emailsList = new ArrayList<>();
            try {
                emailsList = userDao.getEmails();
                userErrorStatusLog=false;
            } catch (SQLException e) {
                LOGGER.error("Error get emails");
                userErrorStatusLog=true;
            }
            return emailsList;
        }
    @Override
    public List<User> getUsersByRole(String role) {

        List<User> usersByRole = new ArrayList<>();

        try {
            usersByRole = userDao.getUsersByRole(role);
            userErrorStatusLog=false;
        } catch (SQLException e) {
            LOGGER.error("Error get users by role");
            userErrorStatusLog=true;
        }
        return usersByRole;
    }

    @Override
    public User add(User user) {
        try {
            user = userDao.add(user);
            userErrorStatusLog=false;
        } catch (SQLException e) {
            LOGGER.error("Error create user");
            userErrorStatusLog=true;
        }
        return user;
    }

    @Override
    public List<User> getAll(){

        List<User> allUsers = new ArrayList<>();
        try {
            allUsers = userDao.getAll();
            userErrorStatusLog=false;
        } catch (SQLException e) {
            LOGGER.error("Error get all users");
            userErrorStatusLog=true;
        }
        return allUsers;

    }

    @Override
    public User update(User user) {

        try {
            user= userDao.update(user);
            userErrorStatusLog=false;
        } catch (SQLException e) {
            LOGGER.error("Error update user");
            userErrorStatusLog=true;
        }
        return user;
    }

    @Override
    public void delete(long user_id)  {

        try {
            userDao.delete(user_id);
            userErrorStatusLog=false;
        } catch (SQLException e) {
            LOGGER.error("Error delete user #" + user_id);
            userErrorStatusLog=true;
        }
    }
}
