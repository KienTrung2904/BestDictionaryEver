package com.example.bestdictionaryever;
import com.example.bestdictionaryever.UserManager.User;
import com.example.bestdictionaryever.dashboard.search.Dictionary;
import java.sql.*;

public abstract class DatabaseConnection {
    protected static User user;

    public static void setUser(User _user) {
        user = _user;
    }

    public static User getUser() {
        return user;
    }

    public static Connection databaseLink = null;

    public static Connection getConnection() {
        return databaseLink;
    }

    private  static void loadDatabase() {
        final String databaseName = "trungdic";
        final String databaseUser = "root";
        final String databasePassword = "";
        String url = "jdbc:mysql://127.0.0.1/" + databaseName;

        System.out.println("Connecting to database...");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);

        } catch (Exception e) {
            System.out.println("Can not get connection to database");
            e.printStackTrace();
        }
        Dictionary.trieStruct();
    }

    public static void initialize() {
        loadDatabase();
    }

    protected static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static void close(PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static void close(Connection databaseLink) {
        try {
            if (databaseLink != null) {
                databaseLink.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
