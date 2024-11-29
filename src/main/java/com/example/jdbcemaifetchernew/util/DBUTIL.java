package com.example.jdbcemaifetchernew.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUTIL {

    private static final String URL="jdbc:mysql://localhost:3306/emaildb";
    private static final String USER="root";
    private static final String PASSWORD="1S4z]6>uM6e5";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Explicitly load the driver
            return DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found.", e);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database.", e);
        }
    }



}
