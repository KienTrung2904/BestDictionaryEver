package controller.dashboard;

import com.example.bestdictionaryever.UserManager.User;
import com.example.bestdictionaryever.dashboard.leaderBoard.leaderBoard;
import com.example.bestdictionaryever.dashboard.search.SearchBar;
import controller.ScreenControl;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DashboardControl extends ScreenControl {
    @FXML
    protected AnchorPane anchorPane;
    @FXML
    protected Text greeting;
    @FXML
    protected  Text userID;
    @FXML
    protected HBox hbox;
    @FXML
    protected TextField searchField;
    @FXML
    protected VBox suggestedList;
    protected SearchBar searchBar;
    @FXML
    protected VBox tableUserName;
    @FXML
    protected VBox tableScore;
    protected TableView<User> leaderBoardTable;

    public void decor() {
        String _greeting = "Good day, " + user.getUserName();
        greeting.setText(_greeting);
        String _userID = "User ID: " + user.getId();
        userID.setText(_userID);
        searchBar = new SearchBar(anchorPane, hbox, searchField, suggestedList);
        leaderBoard leader_board = new leaderBoard(tableUserName, tableScore);
        leader_board.show();
    }

}
