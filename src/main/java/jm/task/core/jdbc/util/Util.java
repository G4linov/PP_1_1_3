package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getConnection() throws SQLException {
        String host = "localhost";
        int port = 3306;
        String user = "root";
        String password = "Slatt4561";
        String database = "pp113";
        String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
        return DriverManager.getConnection(url, user, password);
    }
}
