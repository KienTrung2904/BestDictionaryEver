package controller.library;

import com.example.bestdictionaryever.Word.Add;
import controller.ScreenControl;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.util.Pair;

import java.util.ArrayList;

public class addControl extends ScreenControl {
    @FXML
    protected TextField target;
    @FXML
    protected TextField partOfSpeech;
    @FXML
    protected TextField definition;
    @FXML
    protected TextField example;
    protected String targets;
    protected ArrayList<Pair<String, String>> definitionUser = new ArrayList<>();
    public void save() {
        definitionUser.clear();
        targets = target.getText();
        definitionUser.add(new Pair<>(partOfSpeech.getText(), definition.getText()));
        Add.add(targets, definitionUser);
        super.dashboard();
    }
}
