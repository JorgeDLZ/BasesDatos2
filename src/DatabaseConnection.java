package com.biblioteca.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Configuración de la base de datos - ¡AJUSTA ESTO CON TUS PROPIOS DATOS!
    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca_db";
    private static final String USER = "JDL07";
    private static final String PASSWORD = "1234"; // tu contraseña de MySQL

    public static Connection getConnection() throws SQLException {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Error: MySQL JDBC Driver not found.");
            e.printStackTrace();

            throw new SQLException("MySQL JDBC Driver not found: " + e.getMessage());
        }
    }

    // Método para cerrar la conexión de manera segura
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión:");
                e.printStackTrace();
            }
        }
    }

    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar el Statement:");
                e.printStackTrace();
            }
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar el ResultSet:");
                e.printStackTrace();
            }
        }
    }
}