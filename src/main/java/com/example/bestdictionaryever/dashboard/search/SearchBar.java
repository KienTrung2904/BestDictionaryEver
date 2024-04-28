package com.example.bestdictionaryever.dashboard.search;

import com.example.bestdictionaryever.ComponentManager;
import controller.ScreenControl;
import controller.dashboard.DashboardControl;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class SearchBar implements ComponentManager {
    private HBox hbox;
    private TextField searchBar;
    private VBox suggestedList;
    private AnchorPane anchorPane;
    private ScreenControl screen;
    private static ArrayList<String> history = new ArrayList<>();
    private static ArrayList<String> prefixList = new ArrayList<>();
    private final static int searchLimit = 10;

    public SearchBar(AnchorPane anchorPane, HBox hbox, TextField searchBar, VBox suggestedList) {
        this.anchorPane = anchorPane;
        this.hbox = hbox;
        this.searchBar = searchBar;
        this.suggestedList = suggestedList;
        screen = ScreenControl.getScreen();
        this.initialize();
    }
    @Override
    public void initialize() {
        searchBar.setEditable(false);
        searchBar.setOnKeyReleased(this::handleKeyPressed);
        searchBar.setOnMouseClicked(this::searchWord);
        anchorPane.setOnMousePressed(this::quitSearch);
    }

    private void addHistoryToSearch() {
        history.clear();
        String target = searchBar.getText();
        for (String word: WordHistory.getHistoryList()) {
            if (word.startsWith(target) || target.isEmpty()) {
                history.add(word);
            }
        }
    }

    private void showPrefixList() {
        suggestedList.setSpacing(11);
        prefixList.addAll(0, history);
        for (String w : prefixList) {
            HBox prefixBox = new HBox(6);
            prefixBox.setAlignment(Pos.CENTER);
            prefixBox.getStyleClass().add("transparent-box");

            Button prefix = new Button();
            prefix.getStyleClass().add("transparent-box");
            prefix.setPrefWidth(searchBar.getPrefWidth());
            prefix.setText(w);

            prefixBox.setPadding(new Insets(3, 0, 3, 12));
            prefix.setOnMouseClicked(this::wordShow);
            prefixBox.getChildren().add(prefix);
            suggestedList.getChildren().add(prefixBox);
            suggestedList.getStyleClass().add("suggestedList");
        }
    }

    private void wordShow(MouseEvent event) {
        Button selectedButton = (Button) event.getSource();
        String target = selectedButton.getText();
        WordHistory.addToHistory(target);
//        if (screen instanceof DashboardControl) {
//            libraryController.setTopicFrom(false);
//        }
        screen.library();
    }

    private void handleKeyPressed(KeyEvent event) {
        if (searchBar.isEditable()) {
            prefixList.clear();
            suggestedList.getChildren().clear();

            addHistoryToSearch();
            Dictionary.lookUpWord(searchBar.getText(), searchLimit - history.size());
            prefixList.addAll(Dictionary.getPrefixList());
            showPrefixList();
        }
    }

    private void searchWord(MouseEvent event) {
        prefixList.clear();
        addHistoryToSearch();
        if (!searchBar.isEditable()) {
            searchBar.setEditable(true);
            showPrefixList();
        }
    }

    private void quitSearch(MouseEvent event) {
        suggestedList.getChildren().clear();
        searchBar.setEditable(false);
        searchBar.setFocusTraversable(false);
    }
}
