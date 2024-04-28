package controller.translation;

import com.example.bestdictionaryever.Audio;
import com.example.bestdictionaryever.GoogleTranslation;
import controller.ScreenControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class TranslationPage extends ScreenControl {
    @FXML
    private Label inputLang;
    @FXML
    private Label outputLang;
    @FXML
    private TextArea inputText;
    @FXML
    private TextArea outputText;

    @FXML
    private Button switchButton;

    @FXML
    private Button backButton;

    private boolean enToVi = true;

    @FXML
    private Button listenButton;

    public TranslationPage() {
        super.translationPage();
        translate();
    }

    private void translate() {
        String source = inputText.getText();
        if (enToVi) {
            outputText.setText(GoogleTranslation.getTranslation(source, "en", "vi"));
        } else {
            outputText.setText(GoogleTranslation.getTranslation(source, "vi", "en"));
        }
    }

    public void switchButtonOnAction(ActionEvent event) {
        if (enToVi) {
            inputLang.setText("Vietnamese");
            outputLang.setText("English");
            enToVi = false;
        } else {
            inputLang.setText("English");
            outputLang.setText("Vietnamese");
            enToVi = true;
        }
    }

    public void backButtonOnAction(ActionEvent actionEvent) {
        super.dashboard();
    }

    public void listenButtonOnAction(ActionEvent actionEvent) {
        String text = outputText.getText();
        if (enToVi) {
            Audio.playAudio(text, "en");
        } else {
            Audio.playAudio(text, "vi");
        }
    }
}
