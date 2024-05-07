package controller.library;

import com.example.bestdictionaryever.DatabaseConnection;
import com.example.bestdictionaryever.TextToSpeech;
import com.example.bestdictionaryever.Word.Delete;
import com.example.bestdictionaryever.Word.Prepare;
import com.example.bestdictionaryever.dashboard.search.SearchBar;
import com.example.bestdictionaryever.dictionary.API_Dictionary.DictionaryAPI;
import controller.ScreenControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import com.example.bestdictionaryever.dictionary.word;

import java.util.ArrayList;
import java.util.Map;


public class searchControl extends ScreenControl {
    private static String target;
    @FXML
    protected TextField searchField;
    @FXML
    protected VBox suggestedList;
    @FXML
    protected HBox hbox;
    @FXML
    protected AnchorPane anchorPane;
    @FXML
    private Text word;
    @FXML
    private Text phonetic;
    @FXML
    private Text noun;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox explainBox;

    protected SearchBar searchBar;

    private word wordData = new word();

    public static String getTarget() {
        return target;
    }

    public static void setTarget(String _target) {
        target = _target;
    }


    public void decor() {
        searchBar = new SearchBar(anchorPane, hbox, searchField, suggestedList);
        searchBar.getClass();
        searchField.setText(target);
        this.getWordDefinition();
    }

    public void playSound(ActionEvent actionEvent) {
        TextToSpeech textToSpeech = new TextToSpeech();
        textToSpeech.speak(target);
    }

    public void back() {
        super.library();
    }

    public void deleteAlert() {
        DatabaseConnection.initialize();
        Delete.delete(target);
        super.dashboard();
    }



    public void addWord(ActionEvent actionEvent) {
        super.add();
    }

    public void editWord(ActionEvent actionEvent) {
        if (Prepare.getUserEdit(wordData)) {
            super.editSelect(wordData);
        }
    }

    private void getWordDefinition() {
        wordData.setWordTarget(target);

        word.setText(target);

        if (!Prepare.isUserWord(target)) {
            DictionaryAPI.fetchDefinition(wordData);
        }
        else {Prepare.lookUpUserWord(wordData);}
        phonetic.setText(wordData.getWordPhonetic());


        Map<String, ArrayList<String>> wordExplain = wordData.getWordExplain();

        for (String pos : wordData.getWordPartOfSpeech()) {
            Text partOfSpeech = new Text(pos);
            partOfSpeech.getStyleClass().add("noun");
            explainBox.getChildren().add(partOfSpeech);


            for (String e : wordExplain.get(pos)) {
                Text explain = new Text(e);
                explain.setWrappingWidth(explainBox.getPrefWidth());

                if (e.charAt(0) == '•') {
                    explain.getStyleClass().add("definition");
                }
                else {
                    explain.getStyleClass().add("example");
                }

                explainBox.getChildren().add(explain);
            }
        }
    }
}
