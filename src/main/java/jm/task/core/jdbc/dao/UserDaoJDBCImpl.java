package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Connection con = Util.getConnection()) {
            con.setAutoCommit(false);
            String sql = "CREATE TABLE IF NOT EXISTS users (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100) NOT NULL, " +
                    "lastName VARCHAR(100) NOT NULL, age INT NOT NULL)";
            con.createStatement().execute(sql);
            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Connection con = Util.getConnection()) {
            con.setAutoCommit(false);
            String sql = "DROP TABLE IF EXISTS users";
            con.createStatement().execute(sql);
            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection con = Util.getConnection()) {
            con.setAutoCommit(false);
            String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setInt(3, age);
            ps.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Connection con = Util.getConnection()) {
            con.setAutoCommit(false);
            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection con = Util.getConnection()) {
            con.setAutoCommit(false);
            String sql = "SELECT * FROM users";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String lastName = rs.getString("lastName");
                byte age = rs.getByte("age");
                User user = new User(name, lastName, age);
                users.add(user);
            }
            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection con = Util.getConnection()) {
            con.setAutoCommit(false);
            String sql = "DELETE FROM users";
            con.createStatement().execute(sql);
            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
