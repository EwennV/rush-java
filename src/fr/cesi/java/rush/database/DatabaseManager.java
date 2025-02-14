package fr.cesi.java.rush.database;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager {
    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
