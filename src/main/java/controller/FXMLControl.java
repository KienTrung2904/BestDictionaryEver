package controller;

import com.example.bestdictionaryever.Application;
import com.example.bestdictionaryever.UserManager.User;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLControl implements Initializable {
    protected  static Stage primaryStage;

    protected static User user;
    protected  static ScreenControl screen;
    protected  static Scene scene;

    public static void setPrimaryStage(Stage _primaryStage) {
        primaryStage = _primaryStage;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setUser(User _user) {
        user = _user;
    }

    public static User getUser() {
        return user;
    }

    public static ScreenControl getScreenControl() {
        return screen;
    }

    public static void setScreenControl(ScreenControl _screen) {
        screen = _screen;
    }

    public static Scene getScene() {
        return scene;
    }

    public static void setScene(Scene _scene) {
        scene = _scene;
    }

    public void initialize(URL location, ResourceBundle resources) {}

    protected  void loadScreenFXML(String screenFXML) {
        String path = screenFXML + ".fxml";

        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource(path));
            Parent root = loader.load();
            scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            screen = loader.getController();
            screen.decor();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void decor() {}
}
