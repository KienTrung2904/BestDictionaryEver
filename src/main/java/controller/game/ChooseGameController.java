package controller.game;

import controller.ScreenControl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ChooseGameController extends ScreenControl implements Initializable {
    @FXML
    private ImageView back;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        back.setOnMouseClicked(event -> {
            super.dashboard();
        });
    }
}
