package com.example.bestdictionaryever;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection() {
        String databaseName = "trungdic";
        String databaseUser = "root";
        String databasePassword = "";
        String url = "jdbc:mysql://127.0.0.1/" + databaseName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);

        } catch (Exception e) {
            System.out.println("Can not get connection to database");
            e.printStackTrace();
        }
        return databaseLink;
    }

}
