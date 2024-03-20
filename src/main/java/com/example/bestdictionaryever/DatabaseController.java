package com.example.bestdictionaryever;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseController {

    @FXML
    private Label showUsernameLabel;
    public void connectButton(ActionEvent event) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String connectQuery = "select EnglishWord from dictionary";

        try{

            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);

            while (queryOutput.next()) {
                showUsernameLabel.setText(queryOutput.getString("EnglishWord"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
