package services;

import entities.User;
import services.impl.UserServiceImpl;

import java.sql.SQLException;

public class TestUserService {

    UserService userService = UserServiceImpl.getInstance();
    User user = new User();


    public static void main (String[] args) {

        TestUserService run = new TestUserService();
        run.addUser();
    }

    public void addUser() {

        user.setUsername("mynameeqqqqq");
        user.setUser_email("myemail@commmmmmmmm");
        user.setUser_password("1234");
        userService.add(user);


    }
}
