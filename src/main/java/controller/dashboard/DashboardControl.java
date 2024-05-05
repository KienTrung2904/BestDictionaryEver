package controller.dashboard;

import com.example.bestdictionaryever.UserManager.User;
import com.example.bestdictionaryever.dashboard.leaderBoard.leaderBoard;
import com.example.bestdictionaryever.dashboard.search.SearchBar;
import com.example.bestdictionaryever.dashboard.topic.topicIntroduction;
import controller.ScreenControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class DashboardControl extends ScreenControl {
    public static int indexTopic;
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
    @FXML
    private ImageView topicAvatar;
    @FXML
    private Label topicName;
    @FXML
    private TextFlow topicDescription;

    @FXML
    private Button translationButton;
    @FXML
    private Button toLeft;
    @FXML
    private Button toRight;

    private static topicIntroduction topic;



    public void decor() {
        String _greeting = "Good day, " + user.getUserName();
        greeting.setText(_greeting);
        String _userID = "User ID: " + user.getId();
        userID.setText(_userID);
        searchBar = new SearchBar(anchorPane, hbox, searchField, suggestedList);
        leaderBoard leader_board = new leaderBoard(tableUserName, tableScore);
        leader_board.show();

        topic = new topicIntroduction(topicAvatar, topicName, topicDescription);
        topic.show();
    }

    public void toRight() {
        topic.toRight();
    }

    public void toLeft() {
        topic.toLeft();
    }

    public void practice() {
        super.topic();
    }
    public static int getIndexTopic() {
        indexTopic = topic.getIndex() + 1;
        System.out.println((indexTopic));
        return indexTopic;
    }

    public void libra() {
        super.library();
    }
    public void translationButtonOnAction(ActionEvent actionEvent) {
        super.translationPage();
    }

    public void game () {
        super.game();
    }

    public void logOutButtonOnAction(ActionEvent actionEvent) {
        super.started();
    }
}
