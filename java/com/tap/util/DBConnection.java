
package com.tap.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/food_devivery_application";

    private static final String USERNAME = "root";

    private static final String PASSWORD = "Sanjana@1661";

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
