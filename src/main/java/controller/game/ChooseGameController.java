package controller.game;

import controller.ScreenControl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ChooseGameController extends ScreenControl implements Initializable {
    @FXML
    private ImageView back;
    @FXML
    private Button vocabularyButton;
    @FXML
    private Button listeningButton;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        back.setOnMouseClicked(event -> {
            super.dashboard();
        });
        vocabularyButton.setOnMouseClicked(event -> {
            super.vocabulary();
        });
        listeningButton.setOnMouseClicked(event -> {
            super.listening();
        });

    }
}
