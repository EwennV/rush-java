package fr.cesi.java.rush.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String url = "jdbc:mysql://localhost:3306/rush";
    private static final String user = "root";
    private static final String password = "";

    public static Connection getInstance() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
