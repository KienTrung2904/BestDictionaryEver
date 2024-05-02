package com.example.bestdictionaryever.dashboard.leaderBoard;


import com.example.bestdictionaryever.ComponentManager;
import com.example.bestdictionaryever.UserManager.User;
import com.example.bestdictionaryever.UserManager.UserLeaderBoard;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class leaderBoard implements ComponentManager {
    private VBox tableUserName;
    private VBox tableScore;
    private ArrayList<User> leaderBoard = new ArrayList<>();

    public leaderBoard(VBox tableUserName, VBox tableScore) {
        this.tableUserName = tableUserName;
        this.tableScore = tableScore;
        this.initialize();
    }

    @Override
    public void initialize() {
        leaderBoard = UserLeaderBoard.getLeaderBoard();
    }

    public void show() {
        tableUserName.setSpacing(26.5);
        tableScore.setSpacing(26.5);
        for (int i = 0; i < leaderBoard.size(); i++) {
            HBox userInTable = new HBox(6);
            userInTable.setAlignment(Pos.CENTER);
            userInTable.getStyleClass().add("leaderboard-box");


            Text user = new Text();
            user.getStyleClass().add("leaderboard-box");

            user.setText(leaderBoard.get(i).getUserName());
            user.setFill(Color.rgb(28, 27, 27, 0.6));

            userInTable.getChildren().add(user);
            tableUserName.getChildren().add(userInTable);
        }

        for (int i = 0; i < leaderBoard.size(); i++) {
            HBox userInTable = new HBox(6);
            userInTable.setAlignment(Pos.CENTER);
            userInTable.getStyleClass().add("leaderboard-box");


            Text score = new Text();
            score.getStyleClass().add("leaderboard-box");


            score.setText(String.valueOf(leaderBoard.get(i).getScore()));
            score.setFill(Color.rgb(28, 27, 27, 0.6));

            userInTable.getChildren().add(score);
            tableScore.getChildren().add(userInTable);
        }
    }

}
