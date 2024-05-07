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
    private ImageView back, vocabularyIcon, listeningIcon;
    @FXML
    private Button vocabularyButton;
    @FXML
    private Button listeningButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        back.setOnMouseClicked(event -> {
            super.dashboard();
        });
        vocabularyIcon.setOnMouseClicked(event -> {
            super.chooseTopicAndPractice();
        });
        vocabularyButton.setOnMouseClicked(event -> {
            super.chooseTopicAndPractice();
        });
        listeningButton.setOnMouseClicked(event -> {
            super.listening();
        });
        listeningIcon.setOnMouseClicked(event -> {
            super.listening();
        });
        // set if mouse does not enter the button will return to the original color
        vocabularyIcon.setOnMouseExited(event -> {
            vocabularyIcon.setStyle("-fx-cursor: hand;");
            vocabularyButton.setStyle("-fx-border-width: 0;\n" +
                    "    -fx-border-radius: 21.33;\n" +
                    "    -fx-border-color: transparent;");
        });
        vocabularyIcon.setOnMouseEntered(event -> {
            // set cursor is hand
            vocabularyIcon.setStyle("-fx-cursor: hand;");
            vocabularyButton.setStyle("-fx-border-width: 3;\n" +
                    "    -fx-border-radius: 21.33;\n" +
                    "    -fx-border-color: rgba(255, 104, 104, 1);");

        });
        listeningIcon.setOnMouseExited(event -> {
            listeningIcon.setStyle("-fx-cursor: hand;");
            listeningButton.setStyle("-fx-border-width: 0;\n" +
                    "    -fx-border-radius: 21.33;\n" +
                    "    -fx-border-color: transparent;");
        });
        listeningIcon.setOnMouseEntered(event -> {
            listeningIcon.setStyle("-fx-cursor: hand;");
            listeningButton.setStyle("-fx-border-width: 3;\n" +
                    "    -fx-border-radius: 21.33;\n" +
                    "    -fx-border-color: rgba(255, 104, 104, 1);");
        });
    }
}
