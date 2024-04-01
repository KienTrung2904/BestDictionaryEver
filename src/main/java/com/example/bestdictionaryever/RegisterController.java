package com.example.bestdictionaryever;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.sql.Connection;
import java.sql.Statement;


public class RegisterController {

    @FXML
    private Label registerMessageLabel;
    @FXML
    private PasswordField setPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private TextField fullnameTextField;
    @FXML
    private TextField usernameTextField;

    @FXML
    private Hyperlink signIn;

    public void changeSceneToSignIn() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("sign-in.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage dashboard = new Stage();
            //registerStage.setTitle("Dictionary");
            dashboard.setScene(scene);
            dashboard.show();

        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void signInChangeScene(ActionEvent Event) {
        changeSceneToSignIn();
        Stage stage = (Stage) setPasswordField.getScene().getWindow();
        stage.close();
    }

    public void changeSceneToDashboard() {
        openDashboard();
        Stage stage = (Stage) setPasswordField.getScene().getWindow();
        stage.close();
    }


    public void registerButtonOnAction(ActionEvent event) {

        if (fullnameTextField.getText().isEmpty()
                || usernameTextField.getText().isEmpty()
                || setPasswordField.getText().isEmpty()
                || confirmPasswordField.getText().isEmpty()) {
            registerMessageLabel.setText("Please enter all field!");
        } else if (!setPasswordField.getText().equals(confirmPasswordField.getText())) {
            registerMessageLabel.setText("Password does not match!");
        } else {
            registerUser();
        }
    }


    public void registerUser() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String fullname = fullnameTextField.getText();
        String username = usernameTextField.getText();
        String password = setPasswordField.getText();

        String insertField = "insert into user_account(fullname, username, password) values('";
        String insertValues = fullname + "','" + username + "','" + password + "');";
        String insertRegister = insertField + insertValues;
        try {

            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertRegister);
            //confirmPasswordMessageLabel.setText("User registered successfully!");
            changeSceneToDashboard();
        } catch (Exception e) {
            System.out.println("Can not register user");
            e.printStackTrace();
            e.getCause();
        }
    }

    public void openDashboard() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("dashboard.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage dashboard = new Stage();
            //registerStage.setTitle("Dictionary");
            dashboard.setScene(scene);
            dashboard.show();

        } catch(Exception e) {
            System.out.println("Can not open dashboard");
            e.printStackTrace();
            e.getCause();
        }
    }
}
