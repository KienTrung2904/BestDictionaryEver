package com.example.bestdictionaryever.dashboard.search;

import com.example.bestdictionaryever.ComponentManager;
import com.example.bestdictionaryever.dashboard.search.Dictionary;
import controller.ScreenControl;
import controller.dashboard.DashboardControl;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class SearchBar implements ComponentManager {
    private HBox hbox;
    private TextField search;
    private VBox suggestedList;
    private AnchorPane anchorPane;
    private ScreenControl screen;
    private static ArrayList<String> history = new ArrayList<>();
    private static ArrayList<String> prefixList = new ArrayList<>();
    private final static int searchLimit = 10;

    public SearchBar(AnchorPane anchorPane, HBox hbox, TextField search, VBox suggestedList) {
        this.anchorPane = anchorPane;
        this.hbox = hbox;
        this.search = search;
        this.suggestedList = suggestedList;
        screen = ScreenControl.getScreen();
        this.initialize();
    }
    @Override
    public void initialize() {
        search.setEditable(false);
        search.setOnKeyReleased(this::handleKeyPressed);
        search.setOnMouseClicked(this::searchWord);
        anchorPane.setOnMousePressed(this::quitSearch);
    }

    private void handleKeyPressed(KeyEvent event) {
        if (search.isEditable()) {
            prefixList.clear();
            suggestedList.getChildren().clear();

            addHistoryToSearch();
            Dictionary.lookUpWord(search.getText(), searchLimit - history.size());
            prefixList.addAll(Dictionary.getPrefixList());
            showPrefixList();
        }
    }


    private void searchWord(MouseEvent event) {
        prefixList.clear();
        addHistoryToSearch();

        hbox.setStyle("");
        hbox.getStyleClass().add("grey-box");

        if (!search.isEditable()) {
            search.setEditable(true);
            showPrefixList();
        }
    }

    private void quitSearch(MouseEvent event) {}

    private void addHistoryToSearch() {}

    private void showPrefixList() {
        suggestedList.setSpacing(12);
        int historySz = history.size();
        prefixList.addAll(0, history);
        double h = 92;
        for (String w : prefixList) {
            HBox prefixBox = new HBox(6);
            prefixBox.setAlignment(Pos.CENTER);
            prefixBox.getStyleClass().add("transparent-box");

            Button prefix = new Button();
            prefix.getStyleClass().add("transparent-box");
            prefix.setPrefWidth(search.getPrefWidth());
            prefix.setText(w);

            prefixBox.setPadding(new Insets(4, 0, 4, 12));
            prefix.setOnMouseClicked(this::wordShow);

            ImageView icon;

//            if (historySz > 0) {
//                icon = new ImageView(new Image("resources/icons/clock.png"));
//                historySz--;
//            } else {
//                icon = new ImageView(new Image("resources/icons/search.png"));
//            }
//            icon.setFitHeight(21);
//            icon.setFitWidth(21);

//            prefixBox.getChildren().add(icon);
            prefixBox.getChildren().add(prefix);
            suggestedList.getChildren().add(prefixBox);
            h += 47.5;
        }
//        hbox.getHeight(h);
    }

    private void wordShow(MouseEvent event) {
        Button selectedButton = (Button) event.getSource();
        String target = selectedButton.getText();
        WordHistory.addToHistory(target);
//        if (screen instanceof DashboardControl) {
//            DictionaryControl.setTopicFrom(false);
//        }
//        screen.dictionaryStarted(target);
    }
}
