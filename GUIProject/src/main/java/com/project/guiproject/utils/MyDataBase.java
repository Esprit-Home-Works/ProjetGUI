package com.project.guiproject.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyDataBase {
    private static MyDataBase instance;
    private static final String URL = "jdbc:mysql://localhost:3306/projetgui2";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection connection;

    public MyDataBase() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection established");
        } catch (Exception e) {
            System.out.println("Connection refused");

            System.err.println(e.getMessage());
        }
    }

    public static MyDataBase getInstace() {
        if (instance == null) {
            instance = new MyDataBase();
        }
        return instance;
    }

    public static Connection getConnection() {
        return connection;
    }

}
