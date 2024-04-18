package controller.login;
import com.example.bestdictionaryever.Application;
import com.example.bestdictionaryever.DatabaseConnection;
import com.example.bestdictionaryever.UserManager.User;
import controller.FXMLControl;
import controller.ScreenControl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SignIn extends ScreenControl {
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterPasswordField;

    protected  void load_signIn() {
        super.started();
    }

    public void signUpChangeScene(ActionEvent event) {
        super.sign_up();
    }

    public void loginButtonOnAction(ActionEvent event) {
        if (!usernameTextField.getText().isBlank() && ! enterPasswordField.getText().isBlank()) {
            signIn();
        } else {
            loginMessageLabel.setText("Please enter username and password!");
        }
    }

    private void signIn() {
        String userName = usernameTextField.getText();
        String password = enterPasswordField.getText();

        User user = new User();
        String result = user.signIn(userName, password);

        if (result.equals("Signed in successfully!")) {
            FXMLControl.setUser(user);
            DatabaseConnection.setUser(user);
            super.dashboard();
        }
    }
}
