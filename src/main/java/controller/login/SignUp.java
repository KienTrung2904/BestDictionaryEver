package controller.login;

import com.example.bestdictionaryever.UserManager.User;
import controller.ScreenControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class SignUp extends SignIn {

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

    protected void load_signUp() {
        super.sign_up();
    }

    public void signInChangeScene(ActionEvent Event) {
        super.load_signIn();
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
            signUp();
        }
    }

    private void signUp() {
        String fullName = fullnameTextField.getText();
        String userName = usernameTextField.getText();
        String password = setPasswordField.getText();
        String checkPassword = confirmPasswordField.getText();

        User user = new User();
        String result = user.signUp(fullName, userName, password);

        if (result.equals("Signed up successffully!")) {
            super.started();
        }
    }

}
