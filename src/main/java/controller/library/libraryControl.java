package controller.library;

import com.example.bestdictionaryever.dashboard.search.SearchBar;
import controller.ScreenControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class libraryControl extends ScreenControl {
    @FXML
    protected TextField searchField;
    @FXML
    protected VBox suggestedList;
    @FXML
    protected HBox hbox;
    @FXML
    protected AnchorPane anchorPane;
    protected SearchBar searchBar;
    public void decor() {
        searchBar = new SearchBar(anchorPane, hbox, searchField, suggestedList);
        searchBar.getClass();
    }
    public void addWord(ActionEvent actionEvent) {
        super.add();
    }

    public void editWord(ActionEvent actionEvent) {
    }

    public void deleteAlert(ActionEvent actionEvent) {
    }

    public void back(ActionEvent actionEvent) {
        super.dashboard();
    }
}
