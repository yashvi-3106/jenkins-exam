package com.example.servlet_day_3;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/servlet_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Kyd@1114";

    public static Connection getConnection() throws Exception {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return connection;
    }
}
