package com.example.bestdictionaryever.UserManager;

import com.example.bestdictionaryever.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserLeaderBoard extends DatabaseConnection {
    private static final int limit = 10;
    private static ArrayList<User> leaderBoard = new ArrayList<>();

    public static void setLeaderBoard() {
        leaderBoard.clear();
        final String sql_query = "SELECT * FROM user_account ORDER BY score DESC LIMIT ?;";

        try {
            PreparedStatement p = databaseLink.prepareStatement(sql_query);
            p.setInt(1, limit);

            try {
                ResultSet r = p.executeQuery();

                try {
                    while (r.next()) {
                        String fullName = r.getString("fullname");
                        String userName = r.getString("username");
                        String password = r.getString("password");
                        int score = r.getInt("score");
                        int id = r.getInt("accountID");
                        User user = new User(fullName, userName, password, score, id);
                        leaderBoard.add(user);
                    }
                } finally {
                    DatabaseConnection.close(r);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DatabaseConnection.close(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<User> getLeaderBoard() {
        setLeaderBoard();
        return leaderBoard;
    }
}
