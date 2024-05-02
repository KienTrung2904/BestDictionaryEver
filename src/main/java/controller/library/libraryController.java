package controller.library;

import com.example.bestdictionaryever.dashboard.search.SearchBar;
import controller.ScreenControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class libraryController extends ScreenControl {
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
    private TextFlow definition;
    @FXML
    private TextFlow example;
    @FXML
    private Button speaker;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    protected SearchBar searchBar;

    private static boolean topicFrom = false;

    public static String getTarget() {
        return target;
    }

    public static void setTarget(String _target) {
        target = _target;
    }

    public static void setTopicFrom(boolean _topicFrom) {
        topicFrom = _topicFrom;
    }

    public void decor() {
        searchBar = new SearchBar(anchorPane, hbox, searchField, suggestedList);
        searchBar.getClass();
        word.setText(getTarget());
    }

    public void playSound(ActionEvent actionEvent) {
    }

    public void back(ActionEvent actionEvent) {
        super.dashboard();
    }

    public void deleteAlert(ActionEvent actionEvent) {
    }



    public void addWord(ActionEvent actionEvent) {

    }

    public void editWord(ActionEvent actionEvent) {
    }
}
