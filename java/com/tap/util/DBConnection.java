package com.tap.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://fooddelivery-db-sanjanah2004-caec.h.aivencloud.com:21159/food_devivery_application?sslMode=REQUIRED";

    private static final String USERNAME = "avnadmin";

    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    public static Connection getConnection() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection =
                    DriverManager.getConnection(URL, USERNAME, PASSWORD);

            System.out.println("DATABASE CONNECTED SUCCESSFULLY");

            return connection;

        } catch (ClassNotFoundException e) {

            System.out.println("MYSQL DRIVER ERROR");
            e.printStackTrace();

        } catch (SQLException e) {

            System.out.println("MYSQL CONNECTION ERROR");
            e.printStackTrace();
        }

        return null;
    }
}
