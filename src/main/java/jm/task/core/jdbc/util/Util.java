package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static Connection conn;

    private static final String url = "jdbc:mysql://localhost:3306/pp113";
    private static final String user = "root";
    private static final String password = "Slatt4561";

    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(url, user, password);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return conn;
    }
}
