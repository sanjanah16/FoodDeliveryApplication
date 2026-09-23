package com.tap.util;

import java.sql.Connection;

public class App {

    public static void main(String[] args) {

        Connection connection = DBConnection.getConnection();

        if (connection != null) {
            System.out.println("CONNECTION TEST SUCCESSFUL");
        } else {
            System.out.println("CONNECTION TEST FAILED");
        }
    }
}