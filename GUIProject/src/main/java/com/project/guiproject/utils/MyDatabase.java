package com.project.guiproject.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyDatabase {
    private static MyDatabase instance;
    private static final String URL = "jdbc:mysql://localhost:3306/projetgui";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection connection;

    public MyDatabase() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection established");
        } catch (Exception e) {
            System.out.println("Connection refused");

            System.err.println(e.getMessage());
        }
    }

    public static MyDatabase getInstace() {
        if (instance == null) {
            instance = new MyDatabase();
        }
        return instance;
    }

    public static Connection getConnection() {
        return connection;
    }

}
