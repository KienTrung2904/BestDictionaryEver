package com.example.bestdictionaryever.UserManager;

import com.example.bestdictionaryever.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User extends DatabaseConnection {
    private String fullName;
    private String userName;
    private String password;
    private int score;
    private int id;

    public User() {}

    public User(String fullName, String userName, String password, int score, int id ) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.score = score;
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }


    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void logOut() {
        this.fullName = null;
        this.userName = null;
        this.password = null;
        this.score = 0;
        this.id = 0;
    }

    public String signIn(String userName, String password) {
        final String sql_query = "SELECT * FROM user_account WHERE username = ? AND password = ?;";

        try {
            PreparedStatement p = databaseLink.prepareStatement(sql_query);
            p.setString(1,userName);
            p.setString(2,password);

            try {
                ResultSet r = p.executeQuery();

                try {
                    if (r.next()) {
                        fullName = r.getString("fullname");
                        id = r.getInt("accountID");
                        score = r.getInt("score");
                        this.setFullName(fullName);
                        this.setUserName(userName);
                        this.setPassword(password);
                        this.setScore(score);
                        this.setId(id);
                        return "Signed in successfully!";
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

        return "Invalid username or password.";
    }

    public String signUp(String fullName, String userName, String password) {
        final String sql_query = "SELECT * FROM user_account WHERE username = ?;";

        try {
            PreparedStatement ps = databaseLink.prepareStatement(sql_query);
            ps.setString(1, userName);

            try {
                ResultSet rs = ps.executeQuery();

                try {
                    if (rs.next()) {
                        return "Username has already existed.";
                    }
                } finally {
                    DatabaseConnection.close(rs);
                }
            } finally {
                DatabaseConnection.close(ps);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        final String sqlQuery = "INSERT INTO user_account(fullname, username, password, score) VALUES(?, ?, ?, ?);";

        try {
            PreparedStatement p = databaseLink.prepareStatement(sqlQuery);
            p.setString(1, fullName);
            p.setString(2, userName);
            p.setString(3, password);
            p.setInt(4, score);

            try {
                p.executeUpdate();
                return "Signed up successffully!";
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DatabaseConnection.close(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Signed up failed.";
    }

    public void updateScore(int bonus) {
        System.out.println("Old TotalScore = " + this.score + ". Bonus = "
                + bonus + ". New TotalScore = " + (this.score + bonus));
        this.score += bonus;

        final String sql_query = "UPDATE user_account SET score = ? WHERE accountID = ?;";

        try {
            PreparedStatement p = databaseLink.prepareStatement(sql_query);
            p.setInt(1, this.score);
            p.setInt(2, id);

            try {
                p.executeUpdate();
            } finally {
                DatabaseConnection.close(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateScoreTopic(String column, int newScore) {
        int oldScore;
        // get old score from mysql database
        final String sql_queryGetOldScore = "SELECT " + column + " FROM user_score WHERE accountID = " + id + ";";
        // execute query
        try {
            PreparedStatement p = databaseLink.prepareStatement(sql_queryGetOldScore);
            try {
                ResultSet r = p.executeQuery();
                try {
                    if (r.next()) {
                        oldScore = r.getInt(column);
                        System.out.println("Old Score of " + column + " = " + oldScore);
                        // update new score
                        if (newScore > oldScore) {
                            System.out.println("New score is higher than old score! Can update!");
                            updateScore(newScore - oldScore);
                            final String sql_queryUpdateNewScore = "UPDATE user_score SET " + column + " = ? WHERE accountID = ?;";
                            PreparedStatement p2 = databaseLink.prepareStatement(sql_queryUpdateNewScore);
                            p2.setInt(1, newScore);
                            p2.setInt(2, id);
                            p2.executeUpdate();

                        }
                    }
                } finally {
                    DatabaseConnection.close(r);
                }
            } finally {
                DatabaseConnection.close(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public int getHighestScore(String column) {
        int highestScore = 0;
        if (score == 0) return 0;
        final String sql_query = "SELECT " + column + " FROM user_score WHERE accountID = " + id + ";";
        try {
            PreparedStatement p = databaseLink.prepareStatement(sql_query);
            try {
                ResultSet r = p.executeQuery();
                try {
                    if (r.next()) {
                        highestScore = r.getInt(column);
                    }
                } finally {
                    DatabaseConnection.close(r);
                }
            } finally {
                DatabaseConnection.close(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
    }
        return highestScore;
    }
}
