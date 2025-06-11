package org.example.apiculture1.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    // Configuration de la base de données
    private static final String URL = "jdbc:mysql://localhost:3306/apiculture";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Méthode pour obtenir une connexion
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Chargement du driver
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL non trouvé", e);
        }
    }
}