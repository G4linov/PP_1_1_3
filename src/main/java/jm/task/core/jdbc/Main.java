package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;
import jm.task.core.jdbc.model.User;
import java.util.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        try {
            Util.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();
        userDao.createUsersTable();

        userDao.saveUser("Name1", "LastName1", (byte) 20);
        userDao.saveUser("Name2", "LastName2", (byte) 25);
        userDao.saveUser("Name3", "LastName3", (byte) 31);
        userDao.saveUser("Name4", "LastName4", (byte) 38);

        userDao.removeUserById(1);
        userDao.removeUserById(1);
        List<User> users = userDao.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
        userDao.dropUsersTable();
    }
}
