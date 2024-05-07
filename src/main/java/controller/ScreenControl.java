package controller;

import com.example.bestdictionaryever.dictionary.word;
import controller.library.editControl;
import controller.library.searchControl;

// add link page
public abstract class ScreenControl extends FXMLControl{
    public static ScreenControl getScreen() {
        return screen;
    }

    public void started() {
        super.loadScreenFXML("sign-in");
    }

    public void sign_up() {
        super.loadScreenFXML("sign-up");
    }

    public void dashboard() {
        super.loadScreenFXML("dashboard");
    }

    public void topic() {
        super.loadScreenFXML("topic");
    }

    public void library() {
        super.loadScreenFXML("library");
    }

    public void search() {
        super.loadScreenFXML("search");
    }

    public void add() {
        super.loadScreenFXML("addWord");
    }

    public void edit() {
        super.loadScreenFXML("editWord");
    }

    public void librarySelect(String target) {
        searchControl.setTarget(target);
        super.loadScreenFXML("search");
    }

    public void editSelect(word word) {
        editControl.setWord(word);
        edit();
    }

    public void translationPage() {
        super.loadScreenFXML("translation");
    }

    public void chooseGame() {
        super.loadScreenFXML("Game/FXML/ChooseGame");
    }


    protected void listening() {
        super.loadScreenFXML("Game/FXML/Listening");
    }

    protected void chooseTopicAndPractice() {
        super.loadScreenFXML("Game/FXML/TopicWord");
    }

}
