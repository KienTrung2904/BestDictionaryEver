package controller.translation;

import com.example.bestdictionaryever.Application;
import com.example.bestdictionaryever.DatabaseConnection;
import com.example.bestdictionaryever.GoogleTranslation;
import com.example.bestdictionaryever.TextToSpeech;
import controller.FXMLControl;
import controller.ScreenControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

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
        String inputTextText = inputText.getText();
        String outputTextText = outputText.getText();
        TextToSpeech textToSpeech = new TextToSpeech();
        if (enToVi) {
            textToSpeech.speak(inputTextText);
        } else {
            textToSpeech.speak(outputTextText);
        }
    }

}
