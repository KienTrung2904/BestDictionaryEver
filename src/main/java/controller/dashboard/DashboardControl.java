package controller.dashboard;

import com.example.bestdictionaryever.dashboard.search.SearchBar;
import controller.ScreenControl;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DashboardControl extends ScreenControl {
    protected Text greeting;
    protected  Text userID;
    protected HBox hbox;
    protected TextField search;
    protected VBox suggestedList;
    @FXML
    protected AnchorPane anchorPane;

    protected SearchBar searchBar;

    public void decor() {
        searchBar = new SearchBar(anchorPane, hbox, search, suggestedList);
    }

}
