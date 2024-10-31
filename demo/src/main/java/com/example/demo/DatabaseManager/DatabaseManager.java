package com.example.demo.DatabaseManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String url="jdbc:mysql://red-db:3306/red";
    private static final String user="red";
    private static final String password="secret";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, user, password);
    } 

}
