package controller.library;

import com.example.bestdictionaryever.DatabaseConnection;
import com.example.bestdictionaryever.Word.Add;
import controller.ScreenControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.EventListener;

public class addControl extends ScreenControl implements EventListener {
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
        definitionUser.add(new Pair<>(partOfSpeech.getText(),"• " + definition.getText() + "\n" +  "    ◦ " + example.getText()));
        Add.add(targets, definitionUser);

        searchControl.setTarget(targets);
        DatabaseConnection.initialize();
        super.search();
    }

    public void back(ActionEvent actionEvent) {
        super.library();
    }
}
