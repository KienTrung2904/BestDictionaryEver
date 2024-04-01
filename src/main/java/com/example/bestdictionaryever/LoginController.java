package com.example.bestdictionaryever;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterPasswordField;


    public void changeSceneToDashboard() {
        openDashboard();
        Stage stage = (Stage) usernameTextField.getScene().getWindow();
        stage.close();
    }

    public void signUpChangeScene(ActionEvent event) {
        createAccountForm();
        Stage stage = (Stage) usernameTextField.getScene().getWindow();
        stage.close();
    }

    public void loginButtonOnAction(ActionEvent event) {
        if (!usernameTextField.getText().isBlank() && ! enterPasswordField.getText().isBlank()) {
            validateLogin();
        } else {
            loginMessageLabel.setText("Please enter username and password!");
        }
    }

    private void validateLogin() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "select count(1) from user_account where username = '" + usernameTextField.getText()
                + "' and password = '" + enterPasswordField.getText() + "';";
        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    changeSceneToDashboard();
                } else {
                    loginMessageLabel.setText("Invalid login. Please try again!");
                }
            }

        } catch (Exception e) {
            System.out.println("Can not connect database");
            e.printStackTrace();
            e.getCause();
        }
    }

    public void createAccountForm() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("sign-up.fxml"));
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

    public void openDashboard() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(com.example.bestdictionaryever.Application.class.getResource("dashboard.fxml"));
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
